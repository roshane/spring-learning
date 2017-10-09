package com.aeon.ssjwt.sec.ex;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by roshane on 2/26/2017.
 */
public class JwtMissingException extends AuthenticationException{

    public JwtMissingException(String msg) {
        super(msg);
    }
}
