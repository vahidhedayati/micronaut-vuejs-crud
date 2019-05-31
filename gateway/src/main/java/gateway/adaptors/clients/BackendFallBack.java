package gateway.adaptors.clients;

import gateway.adaptors.models.Hotel;
import gateway.adaptors.models.HotelModel;
import gateway.adaptors.models.implementation.HotelSaveCommand;
import gateway.adaptors.models.implementation.HotelUpdateCommand;
import gateway.adaptors.models.implementation.SortingAndOrderArguments;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Fallback;
import io.reactivex.Single;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Client(id = "backend", path = "/")
@Fallback
public class BackendFallBack  implements BackendClient {

    @Get("/status")
    public HttpResponse status() {
        System.out.println("Backend app is down using fallback status");
        return HttpResponse.serverError();
    }

    @Get("/{id}")
    public Optional<Hotel> findById(@NotNull Long id) {
        System.out.println("Backend app is down using fallback findById");
        return Optional.ofNullable(new Hotel());
    }

    @Get("/delete/{id}")
    public void deleteById(@NotNull Long id) {
        System.out.println("Backend app is down using fallback deleteById");
            //
    }
    @Post(uri = "/", consumes = MediaType.APPLICATION_JSON)
    public Hotel save(@Body HotelSaveCommand args) {
        System.out.println("Backend app is down using fallback save");
        return null;
    }

    @Put(uri = "/update/{id}", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse update(Long id, @Body HotelUpdateCommand args) {
        System.out.println("Backend app is down using fallback update");
        return HttpResponse.serverError();
    }


    @Get(uri="/list{?args*}" , consumes = MediaType.APPLICATION_JSON)
    public Optional<HotelModel> findAll(SortingAndOrderArguments args) {
        System.out.println("Backend app is down using fallback  findAll");
        return null;
    }

    @Get("/find/{code}")
    public Optional<Hotel> findByCode(String code) {
        System.out.println("Backend app is down using fallback  findByCode");
        return Optional.ofNullable(new Hotel());
    }


    @Delete("/{id}")
    public HttpResponse delete(Long id) {
        return HttpResponse.serverError();
    }
}