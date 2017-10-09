package com.aeon.ssjwt.sec.filter;

import com.aeon.ssjwt.sec.ex.JwtMissingException;
import com.aeon.ssjwt.sec.token.JwtAuthenticationToken;
import com.aeon.ssjwt.sec.util.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by roshane on 2/26/2017.
 */
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final String AUTH_HEADER = "Authentication";
    private final String BARER = "Bearer ";

    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println(">>> JwtAuthenticationFilter attemptAuthentication");

        String jwt = obtainJwt(request);

        if (jwt == null) {
            throw new JwtMissingException("jwt not present in request header");
        }

        if (jwtUtil.isValid(jwt)) {
            UserDetails userDetails = jwtUtil.parseToken(jwt);
            System.out.println(">>> valid jwt proceed authentication");
            JwtAuthenticationToken token = new JwtAuthenticationToken(
                    null, jwt, userDetails.getPassword(), userDetails.getUsername());
            token.setDetails(new WebAuthenticationDetails(request));
            return getAuthenticationManager().authenticate(token);
        }
        throw new BadCredentialsException("jwt validation failed");
    }

    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }

    private String obtainJwt(HttpServletRequest request) {

        String header = request.getHeader(AUTH_HEADER);
        return StringUtils.hasText(header) ?
                header.substring(BARER.length(), header.length()) : null;
    }
}
