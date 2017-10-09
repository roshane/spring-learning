package com.aeon.ssjwt.controller;

import com.aeon.ssjwt.sec.token.JwtAuthenticationToken;
import com.aeon.ssjwt.sec.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * Created by roshane on 3/4/2017.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtUtil jwtUtil;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println(">>> login attempt with " + username + " password " + password);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        JwtAuthenticationToken auth = new JwtAuthenticationToken(null, null, password, username);
        SecurityContextHolder.getContext().setAuthentication(auth);
        authenticationProvider.authenticate(auth);
        return ResponseEntity.ok(Collections.singletonMap("jwt", jwtUtil.generateToken(userDetails)));
    }
}
