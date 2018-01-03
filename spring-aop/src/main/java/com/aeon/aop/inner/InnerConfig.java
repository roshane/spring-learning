package com.aeon.aop.inner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by roshane on 1/2/2018.
 */
@Component
public class InnerConfig {

    private String message;

    public InnerConfig(@Value("${app.message:N/A}") String message) {
        this.message = message;
    }

    @PostConstruct
    private void postConstruct(){
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "InnerConfig{" +
                "message='" + message + '\'' +
                '}';
    }
}
