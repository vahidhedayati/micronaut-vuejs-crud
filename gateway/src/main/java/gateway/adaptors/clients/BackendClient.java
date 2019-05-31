package gateway.adaptors.clients;

import gateway.adaptors.models.Hotel;
import gateway.adaptors.models.implementation.HotelSaveCommand;
import gateway.adaptors.models.implementation.HotelUpdateCommand;
import gateway.adaptors.models.implementation.SortingAndOrderArguments;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Client(id = "backend", path = "/")
public interface BackendClient {

    @Get("/status")
    HttpResponse status();

    @Get("/{id}")
    Optional<Hotel> findById(@NotNull Long id);

    @Get("/delete/{id}")
    void deleteById(@NotNull Long id);

    @Post(uri = "/", consumes = MediaType.APPLICATION_JSON)
    Hotel save(@Body HotelSaveCommand args);


    @Put(uri = "/update/{id}", consumes = MediaType.APPLICATION_JSON)
    HttpResponse update(Long id,@Body HotelUpdateCommand args);

    //@Get("/list/{?max,offset,order}")
    //List<Hotel> findAll(@Nullable Integer max, @Nullable Integer offset, @Nullable String order);

    @Get(uri="/list{?args*}" , consumes = MediaType.APPLICATION_JSON)
    List<Hotel> findAll( SortingAndOrderArguments args);

    @Get("/find/{code}")
    Optional<Hotel> findByCode(String code);

    @Delete("/{id}")
    HttpResponse delete(Long id);

}
