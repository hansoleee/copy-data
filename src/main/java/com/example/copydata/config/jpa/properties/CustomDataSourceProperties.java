package com.example.copydata.config.jpa.properties;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * second-datasource:
 * driver-class-name: org.h2.Driver
 * url: jdbc:h2:tcp://localhost/~/h2-db/copy_data_second
 * username: sa
 * password:
 */
@Component
@PropertySource("classpath:application-local.yml")
@Getter
@ToString
public class CustomDataSourceProperties {

    @Value("${custom-datasource.driver-class-name}")
    private String driverClassName;

    @Value("${custom-datasource.url}")
    private String url;

    @Value("${custom-datasource.username}")
    private String username;

    @Value("${custom-datasource.password}")
    private String password;
}
