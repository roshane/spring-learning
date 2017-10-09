package com.aeon.ssjwt.conf;

import com.aeon.ssjwt.sec.filter.JwtAuthenticationFilter;
import com.aeon.ssjwt.sec.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.StringWriter;
import java.util.Collections;
import java.util.Map;

/**
 * Created by roshane on 2/14/2017.
 */
@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable();

        http
                .exceptionHandling().authenticationEntryPoint((request, response, exception) -> {

            System.out.println(">>> authentication entry point executing : " + exception.getMessage());
            StringWriter stringWriter = new StringWriter();
            Map<String, String> params = Collections.singletonMap("message", exception.getMessage());
            try {
                objectMapper.writeValue(stringWriter, params);
                response.getWriter().write(stringWriter.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        });

        http
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }

    public AbstractAuthenticationProcessingFilter jwtAuthenticationFilter() throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter("/api/**");
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        jwtAuthenticationFilter.setJwtUtil(jwtUtil);
        return jwtAuthenticationFilter;
    }

}
