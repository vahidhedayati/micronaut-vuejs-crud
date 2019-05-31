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
import io.micronaut.retry.annotation.Fallback;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Client(id = "userbase", path = "/")
@Fallback
public class UserClientFallBack implements UserClient {

    @Get("/status")
    public HttpResponse status() {
        System.out.println("Backend app is down using fallback status");
        return HttpResponse.serverError();
    }

    @Get("/{id}")
    public Optional<User> findById(@NotNull Long id) {
        System.out.println("Backend app is down using fallback findById");
        return Optional.ofNullable(new User());
    }

    @Get("/delete/{id}")
    public void deleteById(@NotNull Long id) {
        System.out.println("Backend app is down using fallback deleteById");
            //
    }
    @Post(uri = "/", consumes = MediaType.APPLICATION_JSON)
    public Hotel save(@Body UserSaveCommand args) {
        System.out.println("Backend app is down using fallback save");
        return null;
    }

    @Put(uri = "/update/{id}", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse update(Long id, @Body UserUpdateCommand args) {
        System.out.println("Backend app is down using fallback update");
        return HttpResponse.serverError();
    }


    @Get(uri="/list{?args*}" , consumes = MediaType.APPLICATION_JSON)
    public Optional<UserModel> findAll(SortingAndOrderArguments args) {
        System.out.println("Backend app is down using fallback  findAll");
        return null;
    }

    @Get("/find/{username}")
    public Optional<User> findByUsername(String username) {
        System.out.println("Backend app is down using fallback  findByCode");
        return Optional.ofNullable(new User());
    }


    @Delete("/{id}")
    public HttpResponse delete(Long id) {
        return HttpResponse.serverError();
    }
}