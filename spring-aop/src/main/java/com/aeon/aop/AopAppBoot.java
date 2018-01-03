package com.aeon.aop;

import com.aeon.aop.comp.TestComponent;
import com.aeon.aop.inner.InnerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collections;

/**
 * Created by roshane on 1/2/2018.
 */
public class AopAppBoot {

    public static void main(String[] args) {
//        runSpringApplication(args);
        runOnlyApplicationContext();
    }

    private static void runSpringApplication(String[] args) {
        SpringApplication application = new SpringApplication();
        application.setMainApplicationClass(AopAppBoot.class);
        application.setSources(Collections.singleton("com.aeon.aop"));
        application.run(args);
    }

    private static void runOnlyApplicationContext() {
        AnnotationConfigApplicationContext context=
                new AnnotationConfigApplicationContext("com.aeon.aop");
        context.start();
//        InnerConfig bean = context.getBean(InnerConfig.class);
        TestComponent testComponent = context.getBean(TestComponent.class);
        testComponent.doSomething();
        context.stop();
    }
}
