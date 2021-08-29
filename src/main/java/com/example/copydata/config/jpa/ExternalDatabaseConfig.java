package com.example.copydata.config.jpa;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = DatabaseConfigUtils.REPOSITORY_PACKAGE)
@Configuration
public class ExternalDatabaseConfig {

    @Bean(name = "externalEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("externalDataSource") DataSource dataSource) {
        return DatabaseConfigUtils.entityManagerFactoryBean("externalMember", dataSource);
    }

    @Bean(name = "externalTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("externalEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return DatabaseConfigUtils.jpaTransactionManager(entityManagerFactory.getObject());
    }
}