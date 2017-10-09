package com.aeon.ssjwt.controller;

import com.aeon.ssjwt.sec.CurrentUser;
import com.aeon.ssjwt.sec.CustomUser;
import com.aeon.ssjwt.sec.token.JwtAuthenticationToken;
import com.aeon.ssjwt.sec.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by roshane on 2/14/2017.
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping(value = "/greet", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> greet(@RequestParam(value = "name", required = false) String name) {
        name = (!StringUtils.isEmpty(name)) ? name : "user";
        String message = MessageFormat.format("Hello {0}", name);

        Map<String, Object> respParams = new HashMap<>();
        respParams.put("message", message);
        respParams.put("date", dateFormatter.format(new Date(System.currentTimeMillis())));

        return ResponseEntity.ok(respParams);
    }

    @RequestMapping(value = "/current-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetails> getCurrentUser(@CurrentUser CustomUser currentUser) {
        return ResponseEntity.ok(currentUser);
    }

    @RequestMapping("/ping")
    public ResponseEntity<Map<String,String>> ping(){
        return ResponseEntity.ok(Collections.singletonMap("message","pong"));
    }
}
