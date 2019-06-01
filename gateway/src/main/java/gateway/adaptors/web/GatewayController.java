package gateway.adaptors.web;

import gateway.adaptors.clients.BackendClient;
import gateway.adaptors.clients.UserClient;
import gateway.adaptors.models.Hotel;
import gateway.adaptors.models.HotelModel;
import gateway.adaptors.models.implementation.HotelSaveCommand;
import gateway.adaptors.models.implementation.HotelUpdateCommand;
import gateway.adaptors.models.implementation.SortingAndOrderArguments;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Error;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.validation.*;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller("/")
public class GatewayController {
   // @Inject
   // Validator validator;
    private final BackendClient backendClient;
    public GatewayController(BackendClient backendClient) {
        this.backendClient = backendClient;
    }

    @Get(uri="/list{?args*}" , consumes = MediaType.APPLICATION_JSON)
    public Optional<HotelModel> findAll(SortingAndOrderArguments args) {
        //System.out.println("Trying to find"+args.getValues());
        return backendClient.findAll(args);
    }

    @Get("/{id}")
    public Optional<Hotel> findById(@NotNull Long id) {
        return backendClient.findById(id);
    }

    @Delete("/{id}")
    public HttpResponse delete(Long id) {
        return backendClient.delete(id);
    }

    @Put(uri = "/update/{id}", consumes = MediaType.APPLICATION_JSON)
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
                violationMessages.add(constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage());
            }
            //System.out.println(" 01 ---->"+violationMessages);
//            throw new ValidationException("Hotel is not valid:\n" + violationMessages);
            return HttpResponse.serverError(violationMessages);
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
