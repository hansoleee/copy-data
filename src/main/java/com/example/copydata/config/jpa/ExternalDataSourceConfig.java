package com.example.copydata.config.jpa;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * external-datasource:
 *   driver-class-name: org.h2.Driver
 *   url: jdbc:h2:tcp://localhost/~/h2-db/copy_data_first
 *   username: sa
 *   password:
 */
@Component
@PropertySource("classpath:application.yml")
@Getter
@ToString
public class ExternalDataSourceConfig {

    @Value("${external-datasource.driver-class-name}")
    private String driverClassName;

    @Value("${external-datasource.url}")
    private String url;

    @Value("${external-datasource.username}")
    private String username;

    @Value("${external-datasource.password}")
    private String password;
}
