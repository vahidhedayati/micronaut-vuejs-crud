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
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Slf4j
@Controller("/")
public class GatewayController {

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

    @Post(uri = "/", consumes = MediaType.APPLICATION_JSON)
    public Hotel save(@Body HotelSaveCommand args) {
        return backendClient.save(args);
    }



}
