package com.aeon.ssjwt.sec.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by roshane on 2/26/2017.
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private String token;
    private Object credentials;
    private Object principal;

    public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String token, Object credentials, Object principal) {
        super(authorities);
        this.token = token;
        this.credentials = credentials;
        this.principal = principal;
    }

    public String getToken() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
