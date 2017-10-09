package com.aeon.sdt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by roshane on 3/12/2017.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.aeon.sdt.repository")
public class DbConfig {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseFactory databaseFactory = new EmbeddedDatabaseFactory();
        databaseFactory.setDatabaseType(EmbeddedDatabaseType.H2);
        databaseFactory.setDatabaseName("sample-db");
        return databaseFactory.getDatabase();
    }

    @Bean
    public DatabasePopulator databasePopulator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("import.sql"));
        try {
            populator.populate(dataSource().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return populator;
    }
}
