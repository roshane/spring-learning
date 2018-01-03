package com.aeon.aop.aspect;

import java.lang.annotation.*;

/**
 * Created by roshane on 1/3/2018.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableAudit {
    String value() default ("N/A");
}
