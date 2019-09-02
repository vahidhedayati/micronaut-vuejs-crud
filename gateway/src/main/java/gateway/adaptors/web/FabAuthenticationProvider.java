package gateway.adaptors.web;

import gateway.DemoPasswordEncoder;
import gateway.domain.User;
import gateway.implementations.UsersImpl;
import io.micronaut.security.authentication.*;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.Optional;

@Singleton
public class FabAuthenticationProvider implements AuthenticationProvider {
    @Inject
    private UsersImpl userImpl;
    private final DemoPasswordEncoder passwordEncoder;

    public FabAuthenticationProvider(DemoPasswordEncoder passwordEncoder) {
        this.passwordEncoder=passwordEncoder;
    }



    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        System.out.println("authentication "+authenticationRequest.getIdentity().toString()+" --- "+authenticationRequest.getSecret());
        if (authenticationRequest.getIdentity() == null) {
            return Flowable.just(new AuthenticationFailed());
        }
        if (authenticationRequest.getSecret() == null) {
            return Flowable.just(new AuthenticationFailed());
        }
        Optional<User> user = userImpl.findByUsername(authenticationRequest.getIdentity().toString());
        if (user.isPresent()) {

            User u  = user.get();
            boolean matches = passwordEncoder.matches(authenticationRequest.getSecret().toString(),u.getPassword());
            System.out.println("User is found matches = "+matches);
            if (matches) {
                System.out.println("Password matched");
                Collection<String> roles = userImpl.getStringRoles(u);
                UserDetails userDetails = new UserDetails(u.getUsername(),roles);
                return Flowable.just(userDetails);
            } else {
                System.out.println("Password did not match "+u.getPassword()+" "+passwordEncoder.encode(authenticationRequest.getSecret().toString()));
                return Flowable.just(new AuthenticationFailed());
            }
        }
        return Flowable.just(new AuthenticationFailed());
    }
}
