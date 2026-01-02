package com.zee.themis.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.zee.themis.constant.ApplicationConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.zee.themis.repository"})
public class MongoClientConfig {

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.clusterEndpoint}")
    private String clusterEndpoint;

    @Value("${spring.data.mongodb.readPreference}")
    private String readPreference;

    @Bean
    public MongoClient getMongoClient(){
        String connectionString = String.format(ApplicationConstants.mongoConnectionTemplate, username, password, clusterEndpoint,database, readPreference);

        return MongoClients.create(connectionString);
    }
}
