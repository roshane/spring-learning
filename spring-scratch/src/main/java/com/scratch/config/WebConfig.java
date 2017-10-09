package com.scratch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by roshane on 10/9/2017.
 */
@Configuration
@PropertySource("classpath:messages-${spring.profiles.active}.properties")
public class WebConfig {

    @Value("${application.name}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
