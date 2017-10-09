package com.aeon.ssjwt.conf;

import com.aeon.ssjwt.sec.token.JwtAuthenticationToken;
import com.aeon.ssjwt.sec.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by roshane on 3/2/2017.
 */
@Component("authenticationProvider")
class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println(">>>>> custom authentication provider attempt authentication with [JwtAuthenticationToken]");

        JwtAuthenticationToken token = ((JwtAuthenticationToken) authentication);
        UserDetails user = userDetailsService.loadUserByUsername(token.getName());
        System.out.println(">>> custom user " + user);

        if (!user.getUsername().equals(token.getPrincipal())) {
            throw new UsernameNotFoundException("user does not exist");
        }
        if (!user.getPassword().equals(token.getCredentials())) {
            throw new BadCredentialsException("invalid credentails");
        }
        return new JwtAuthenticationToken(user.getAuthorities(), jwtUtil.generateToken(user),
                user.getPassword(), user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        System.out.println(">>> CustomAuthenticationProvider check supports ");
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
