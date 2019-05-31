package gateway.adaptors.models.implementation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserUpdateCommand {

    @NotNull
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;


    @NotBlank
    private String firstname;


    @NotBlank
    private String surname;


    public UserUpdateCommand() {}

    public UserUpdateCommand(@NotNull Long id, @NotBlank String username, @NotBlank String password, @NotBlank String firstname, @NotBlank String surname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
