package com.example.demo.mongo;

import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Configuration class for MongoDB.
 * Sets up the MongoTemplate bean and enables MongoDB repositories.
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.example.demo")
public class MongoConfig {

    /**
     * MongoDB connection URI, injected from application properties.
     */
    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    /**
     * Name of the MongoDB database, injected from application properties.
     */
    @Value("${spring.data.mongodb.database}")
    private String database;

    /**
     * Creates a {@link MongoTemplate} bean for interacting with MongoDB.
     *
     * @return A configured {@link MongoTemplate} instance.
     */
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(MongoClients.create(mongoUri), database);
    }
}
