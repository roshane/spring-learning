package com.aeon.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by roshane on 1/3/2018.
 */
@Component
@Aspect
public class AutidAspect {

    @Before("@annotation(com.aeon.aop.aspect.EnableAudit)")
    public void logAudit(JoinPoint joinPoint) {
        MethodSignature signature = ((MethodSignature) joinPoint.getSignature());
        String methodName = signature.getMethod().getName();
        EnableAudit audit = signature.getMethod().getDeclaredAnnotation(EnableAudit.class);
        String message = String.format("AuditLog|%s|Args-%s|Desc-%s",
                methodName,
                Arrays.toString(joinPoint.getArgs()),
                audit.value());
        System.out.println(message);
    }
}
