package gateway.adaptors.clients;

import gateway.adaptors.models.Hotel;
import gateway.adaptors.models.User;
import gateway.adaptors.models.UserModel;
import gateway.adaptors.models.implementation.SortingAndOrderArguments;
import gateway.adaptors.models.implementation.UserSaveCommand;
import gateway.adaptors.models.implementation.UserUpdateCommand;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Client(id = "userbase", path = "/")
public interface UserClient {

    @Get("/status")
    HttpResponse status();

    @Get("/{id}")
    Optional<User> findById(@NotNull Long id);

    @Get("/delete/{id}")
    void deleteById(@NotNull Long id);

    @Post(uri = "/", consumes = MediaType.APPLICATION_JSON)
    Hotel save(@Body UserSaveCommand args);


    @Put(uri = "/update/{id}", consumes = MediaType.APPLICATION_JSON)
    HttpResponse update(Long id, @Body UserUpdateCommand args);

    //@Get("/list/{?max,offset,order}")
    //List<Hotel> findAll(@Nullable Integer max, @Nullable Integer offset, @Nullable String order);

    @Get(uri="/list{?args*}" , consumes = MediaType.APPLICATION_JSON)
    Optional<UserModel> findAll(SortingAndOrderArguments args);

    @Get("/find/{username}")
    Optional<User> findByUsername(String username);

    @Delete("/{id}")
    HttpResponse delete(Long id);

}
