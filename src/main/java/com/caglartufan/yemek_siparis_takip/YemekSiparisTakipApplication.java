package com.caglartufan.yemek_siparis_takip;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class YemekSiparisTakipApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(YemekSiparisTakipApplication.class, args);

        Environment env = context.getEnvironment();
        String dataSourceUrl = env.getProperty("spring.datasource.url");
        String dataSourceUsername = env.getProperty("spring.datasource.username");
        String dataSourcePassword = env.getProperty("spring.datasource.password");

        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway.configure().dataSource(dataSourceUrl, dataSourceUsername, dataSourcePassword).load();

        // Add baseline for flyway_schema_history table
        // flyyway.baseline();

        // Start the migration
        flyway.migrate();
    }

}
