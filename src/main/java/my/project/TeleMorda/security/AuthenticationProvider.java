package my.project.TeleMorda.security;

import my.project.TeleMorda.repositories.UserRepository;
import my.project.TeleMorda.service.DefaultCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationProvider  extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private UserRepository ur;

    @Autowired
    private DefaultCustomerService dcs;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
            throws AuthenticationException {
        //
    }

    @Override
    protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
            throws AuthenticationException {

        Object token = usernamePasswordAuthenticationToken.getCredentials();
        Optional<UserDetails> result = Optional.ofNullable(token).map(String::valueOf).flatMap(dcs::findByToken);
        return result.orElseThrow(() -> new UsernameNotFoundException("Cannot find user with authentication token=" + token));
    }

}
