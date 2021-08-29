package com.example.copydata.config.jpa;

import com.example.copydata.config.jpa.properties.CustomDataSourceProperties;
import com.example.copydata.config.jpa.properties.ExternalDataSourceProperties;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Slf4j
@EnableJpaRepositories
@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

    private final ExternalDataSourceProperties externalDataSourceProperties;
    private final CustomDataSourceProperties customDataSourceProperties;

    @Bean(name = "externalDataSource")
    public DataSource externalDataSource() {
        return getDataSource(externalDataSourceProperties.getDriverClassName(), externalDataSourceProperties.getUrl(), externalDataSourceProperties.getUsername(), externalDataSourceProperties.getPassword());
    }

    @Primary
    @Bean(name = "customDataSource")
    public DataSource customDataSource() {
        return getDataSource(customDataSourceProperties.getDriverClassName(), customDataSourceProperties.getUrl(), customDataSourceProperties.getUsername(), customDataSourceProperties.getPassword());
    }

    private DataSource getDataSource(String driverClassName,
                                     String url,
                                     String username,
                                     String password) {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }
}
