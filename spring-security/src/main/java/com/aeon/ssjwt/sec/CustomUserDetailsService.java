package com.aeon.ssjwt.sec;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by roshane on 2/26/2017.
 */
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private static final Map<String, UserDetails> users;

    static {
        users = new HashMap<>();

        users.put("user", new CustomUser("user", "password", Collections.singletonList(new SimpleGrantedAuthority("USER"))));

        users.put("admin", new CustomUser("admin", "password",
                Arrays.asList(new SimpleGrantedAuthority("USER"), new SimpleGrantedAuthority("ADMIN"))));
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if (users.containsKey(userName)) {
            UserDetails original = users.get(userName);
            return new CustomUser(original.getUsername(), original.getPassword(), original.getAuthorities());
        }
        throw new UsernameNotFoundException(String.format("%s not found", userName));
    }
}
