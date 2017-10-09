package com.aeon.ssjwt.sec.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by roshane on 2/26/2017.
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserDetailsService userDetailsService;

    public String generateToken(UserDetails userDetails) {

        String userName = userDetails.getUsername();
        String password = userDetails.getPassword();
        String authorities[] = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
                .toArray(new String[userDetails.getAuthorities().size()]);

        try {
            return JWT.create()
                    .withClaim("user", userName)
                    .withClaim("password", password)
                    .withArrayClaim("authorities", authorities)
                    .sign(Algorithm.HMAC256(secret));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserDetails parseToken(String jwt) {

        JWT decode = JWT.decode(jwt);
        String username = decode.getClaim("user").asString();
        String password = decode.getClaim("password").asString();
        List<GrantedAuthority> authorities = decode.getClaim("authorities")
                .asList(String.class).stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new User(username, password, authorities);
    }

    public boolean isValid(String token) {
        System.out.println(">>> validating jwt token");
        UserDetails userDetails = this.parseToken(token);
        if (userDetailsService.loadUserByUsername(userDetails.getUsername()) != null) {
            System.out.println(">>> valid jwt token ");
            return true;
        }
        System.out.println(">>> invalid jwt token");
        return false;
    }
}
