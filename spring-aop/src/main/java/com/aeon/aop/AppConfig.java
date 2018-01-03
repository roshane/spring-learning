package com.aeon.aop;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by roshane on 1/2/2018.
 */
@Configuration
//@EnableAspectJAutoProxy
public class AppConfig {

//    @Bean
//    static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
//        configurer.setOrder(1);
//        configurer.setLocation(new ClassPathResource("app.properties"));
//        return configurer;
//    }

    @Bean
    static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("app.properties"));
        return configurer;
    }

//    @Bean
//    static Environment environment() throws IOException {
//        StandardEnvironment environment = new StandardEnvironment();
//        environment.getPropertySources()
//                .addFirst(new ResourcePropertySource("classpath:app.properties"));
//        return environment;
//    }
}
