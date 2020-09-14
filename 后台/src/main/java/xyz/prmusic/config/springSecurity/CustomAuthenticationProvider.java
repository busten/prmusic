package xyz.prmusic.config.springSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private DbUserDetailsService dbUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String usernmae = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails userDetails = dbUserDetailsService.loadUserByUsername(usernmae);
        if (userDetails.getPassword().equals(password)){
            return new UsernamePasswordAuthenticationToken(usernmae, null, userDetails.getAuthorities());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
