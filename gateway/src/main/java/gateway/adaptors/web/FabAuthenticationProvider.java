package gateway.adaptors.web;

import gateway.adaptors.models.UserVO;
import gateway.domain.User;
import gateway.implementations.UsersImpl;
import gateway.init.DemoUsersFactory;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.token.jwt.generator.AccessRefreshTokenGenerator;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Singleton
public class FabAuthenticationProvider implements AuthenticationProvider {
    @Inject
    private UsersImpl userImpl;
    protected final AccessRefreshTokenGenerator accessRefreshTokenGenerator;
    @Inject
    public FabAuthenticationProvider(AccessRefreshTokenGenerator accessRefreshTokenGenerator) {
        this.accessRefreshTokenGenerator = accessRefreshTokenGenerator;
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
        User user = userImpl.findByUsernameAndPassword(authenticationRequest.getIdentity().toString(),authenticationRequest.getSecret().toString()).get();
        if (user!=null) {
            Collection<String> roles = userImpl.getStringRoles(user);
            UserDetails userDetails = new UserDetails(user.getUsername(),roles);
            /*
            Optional<AccessRefreshToken> accessRefreshToken = accessRefreshTokenGenerator.generate(userDetails);
            System.out.println("1 -- "+accessRefreshToken.isPresent()+"---- "+user.getUsername()+" R"+roles+" _ "+userDetails.getMessage()+" "+userDetails.toString());

            if (accessRefreshToken.isPresent()) {
                System.out.println("2 -- "+accessRefreshToken.get().getAccessToken()+"---- ");
                System.out.println("3 -- "+accessRefreshToken.get().getRefreshToken()+"---- ");
                UserVO userVO = new UserVO(user.getUsername(),    roles, accessRefreshToken.get().getAccessToken(), accessRefreshToken.get().getRefreshToken());
                System.out.println(new UserDetails(authenticationRequest.getIdentity().toString(), new ArrayList<>())+" --------------");
                //if (Arrays.asList("sherlock", "watson").contains(authenticationRequest.getIdentity().toString()) && authenticationRequest.getSecret().equals("elementary"))     {
                // return Flowable.just(new UserDetails(authenticationRequest.getIdentity().toString(), new ArrayList<>()));
                return Flowable.just(userVO);
            }

             */
            return Flowable.just(userDetails);
        }
        return Flowable.just(new AuthenticationFailed());
    }
}
