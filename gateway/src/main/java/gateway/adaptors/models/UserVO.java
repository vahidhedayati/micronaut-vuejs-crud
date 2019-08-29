package gateway.adaptors.models;

import io.micronaut.security.authentication.AuthenticationResponse;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UserVO implements AuthenticationResponse {

   private  String username;
    private Collection<String> roles;
    private String accessToken;
    private String refreshToken;

    public  UserVO(String username, Collection<String> roles, String accessToken, String refreshToken) {
        this.username = username;
        this.roles = roles;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public Optional<String> getMessage() {
        return Optional.empty();
    }
}
