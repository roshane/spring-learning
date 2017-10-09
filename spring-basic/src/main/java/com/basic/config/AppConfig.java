package com.basic.config;

import com.basic.Application;
import com.github.javafaker.Faker;
import org.h2.jdbcx.JdbcDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Created by roshane on 3/28/2017.
 */
@Configuration
@PropertySource({"classpath:application.properties"})
@ComponentScan(basePackageClasses = Application.class)
public class AppConfig {

    @Value("${spring.application.name}")
    private String applicationName;



    @PostConstruct
    public void postConstruct() {
        System.out.println(">>>>> application name " + applicationName);
    }

    @Bean
    public DataSource dataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUser("sa");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:h2:G:\\IdeaProjects\\spring-learning\\DB\\test");
        return dataSource;
    }

    @Bean
    public Faker faker(){
        return new Faker(new Random(System.currentTimeMillis()));
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Logger logger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
