package com.aeon.sdt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

/**
 * Created by roshane on 3/12/2017.
 */
@SpringBootApplication
public class Application {

    @Autowired
    private InjectionMetadata metadata;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void afterPropertySet() {
        System.out.println("\n\n");
        Assert.notNull(metadata,"InjectionMetadata cannot be null");
        System.out.println("\n\n");
    }
}
