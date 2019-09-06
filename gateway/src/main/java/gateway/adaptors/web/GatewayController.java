package gateway.adaptors.web;

import gateway.adaptors.clients.BackendClient;
import gateway.adaptors.clients.UserClient;
import gateway.adaptors.models.Hotel;
import gateway.adaptors.models.HotelModel;
import gateway.adaptors.models.implementation.HotelSaveCommand;
import gateway.adaptors.models.implementation.HotelUpdateCommand;
import gateway.adaptors.models.implementation.SortingAndOrderArguments;
import gateway.domain.Role;
import gateway.domain.User;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Error;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import javax.inject.Inject;
import javax.validation.*;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.net.URI;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller("/")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class GatewayController {
   // @Inject
   // Validator validator;
    private final BackendClient backendClient;
    private final UserClient userClient;
    public GatewayController(BackendClient backendClient,UserClient userClient) {
        this.userClient=userClient;
        this.backendClient = backendClient;
    }

    /*
    @Get(uri="/extract" , consumes = MediaType.APPLICATION_JSON)
    @Secured({"ROLE_ADMIN", "ROLE_X"})
    public  void extraDb() {
        String file = "tmp.sql";
        new File(file).delete();

        MetadataSources metadata = new MetadataSources(new StandardServiceRegistryBuilder()
                .applySetting("hibernate.dialect", "org.hibernate.dialect.H2Dialect").build());
        metadata.addAnnotatedClass(User.class);
        metadata.addAnnotatedClass(Role.class);
        SchemaExport export = new SchemaExport();
        export.setOutputFile(file);
        export.setDelimiter(";");
        export.setFormat(true);
        export.execute(EnumSet.of(TargetType.SCRIPT), SchemaExport.Action.CREATE, metadata.buildMetadata());
    }
*/

    @Get(uri="/list{?args*}" , consumes = MediaType.APPLICATION_JSON)
    @Secured({"ROLE_ADMIN", "ROLE_X"})
    public Optional<HotelModel> findAll(SortingAndOrderArguments args) {
        //System.out.println("Trying to find"+args.getValues());
        Optional<HotelModel> hotelModel =  backendClient.findAll(args);
        /**
         * We bind in userClient and have a slightly different modelled hotel on gateway application which has a User updateUser
         * defined - this binds in via flatMap to bind in actual user for given user -
         */
        hotelModel.flatMap(hotelModel1 -> {
            hotelModel1.getInstanceList().flatMap(hotel-> {
                hotel.forEach(hotel1 -> {
                    hotel1.setUpdateUser(userClient.findById(hotel1.getUpdateUserId()).get());
                });
                /*
                for (Hotel hotel1 : hotel) {
                    hotel1.setUpdateUser(userClient.findById(hotel1.getUpdateUserId()).get());
                };
                 */
                return Optional.of(hotel);
            });
            return Optional.of(hotelModel1);
        });
        return hotelModel;
    }

    @Get("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_X"})
    public Optional<Hotel> findById(@NotNull Long id) {
        return backendClient.findById(id);
    }

    @Delete("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_X"})
    public HttpResponse delete(Long id) {
        return backendClient.delete(id);
    }

    @Put(uri = "/update/{id}", consumes = MediaType.APPLICATION_JSON)
    @Secured({"ROLE_ADMIN", "ROLE_X"})
    public HttpResponse update(Long id, @Body HotelUpdateCommand args) {
        return backendClient.update(id,args);
    }


    /**
     * Validated backend class -    @Valid  does this
     * @Valid
     * throws ValidationException
     * //@Error(exception = ConstraintViolationException.class)
     */
    @Post(uri = "/", consumes = MediaType.APPLICATION_JSON)
    @Secured({"ROLE_ADMIN", "ROLE_X"})
    public HttpResponse save(@Body @Valid HotelSaveCommand args)  {

        /**
         * Below captures and returns a http response with all errors  -
         * this is backend validation against HotelSaveCommand in the Gateway application - gateway will decide on if it
         * needs to pass traffic on if item validated here or not - if ok - will save - if not will fail back
         * vuejs will return error on screen upon save
         */
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Set<ConstraintViolation<HotelSaveCommand>> constraintViolations = validator.validate(args);
        if (constraintViolations.size() > 0) {
            Set<String> violationMessages = new HashSet<String>();

            for (ConstraintViolation<?> constraintViolation : constraintViolations) {
                violationMessages.add(constraintViolation.getMessage());
                //violationMessages.add(constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage());
            }
            //System.out.println(" 01 ---->"+violationMessages);
//            throw new ValidationException("Hotel is not valid:\n" + violationMessages);
            return HttpResponse.badRequest(violationMessages);
        }

        //Hotel hotel = backendClient.save(args);
        Hotel hotel = backendClient.save(args);
        return HttpResponse
                .created(hotel)
                .headers(headers -> headers.location(location(hotel.getId())));



    }

    protected URI location(Long id) {
        return URI.create("/" + id);
    }


}
