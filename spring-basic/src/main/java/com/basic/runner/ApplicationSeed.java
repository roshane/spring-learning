package com.basic.runner;

import com.basic.entity.Customer;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * Created by roshane on 4/1/2017.
 */
//@Component
public class ApplicationSeed {

    private final JdbcTemplate jdbcTemplate;
    private final Faker faker;

    @Autowired
    private Logger logger;

    private final Consumer<String> logLineFunc = (String line) -> logger.info("{}", line);

    ApplicationSeed(JdbcTemplate jdbcTemplate, Faker faker) {
        this.jdbcTemplate = jdbcTemplate;
        this.faker = faker;
    }

    @PostConstruct
    public void readSchemaFromFile() {
        Assert.notNull(jdbcTemplate, "jdbcTemplate can't be null");
        ClassPathResource resource = new ClassPathResource("schema.sql");
        final String sql = "INSERT INTO customer (id,name,email) VALUES (?,?,?)";
        try {
            logger.info("reading schema file from [{}]", resource.getFile().getPath());
            Files.readAllLines(Paths.get(resource.getFile().getPath()))
                    .forEach(line -> {
                        logLineFunc.accept(line);
                        jdbcTemplate.execute(line);
                    });

            IntStream.range(1, 20)
                    .forEach(i -> {
                        jdbcTemplate.update(sql, faker.number().randomNumber(),
                                faker.name().firstName(),
                                faker.internet().emailAddress());
                    });

            List<Customer> customers = jdbcTemplate.query("SELECT * from customer",(resultSet,i) -> {
                return new Customer(resultSet.getLong("ID"),
                        resultSet.getString("NAME"),
                        resultSet.getString("EMAIL"));
            });
            customers.forEach(c->logLineFunc.accept(c.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
