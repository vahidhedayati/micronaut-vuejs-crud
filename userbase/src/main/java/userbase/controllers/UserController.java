package userbase.controllers;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import io.reactivex.annotations.Nullable;
import userbase.adaptors.models.UserModel;
import userbase.domain.User;
import userbase.adaptors.models.SortingAndOrderArguments;
import userbase.adaptors.models.UserSaveCommand;
import userbase.adaptors.models.UserUpdateCommand;
import userbase.interfaces.Users;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@Validated
@Controller("/")
public class UserController {

    protected final Users users;

    public UserController(Users users) {
        this.users = users;
    }


    @Get("/status")
    public HttpResponse status() {
        return HttpResponse.ok();
    }


    @Get("/{id}")
    public User show(Long id) {
        return users
                .findById(id)
                .orElse(null);
    }

    @Put("/update/{id}")
    public HttpResponse update(Long id,@Body @Valid UserUpdateCommand command) {
        System.out.println(" In controller updateUser");
        int numberOfEntitiesUpdated = users.update(id, command.getUsername(),command.getPassword(),command.getFirstname(),command.getSurname());

        return HttpResponse
                .noContent()
                .header(HttpHeaders.LOCATION, location(command.getId()).getPath());
    }


    @Get("/list{?args*}")
    public Optional<UserModel> findAll(@Nullable SortingAndOrderArguments args) {
        return users.findAll(args);
    }

    @Post("/")
    public HttpResponse<User> save(@Body @Valid UserSaveCommand cmd) {
        User user = users.save(cmd.getUsername(),cmd.getPassword(),cmd.getFirstname(),cmd.getSurname());

        return HttpResponse
                .created(user)
                .headers(headers -> headers.location(location(user.getId())));
    }

    @Delete("/{id}")
    public HttpResponse delete(Long id) {
        users.deleteById(id);
        return HttpResponse.ok();
    }

    protected URI location(Long id) {
        return URI.create("/" + id);
    }

    protected URI location(User user) {
        return location(user.getId());
    }

}
