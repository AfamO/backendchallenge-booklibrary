
package com.polarisdigitech.backendchallenge.config.db.book;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "bookDbEntityManager",
        basePackages = {"com.polarisdigitech.backendchallenge.repository.book"}
)
@Slf4j
public class BookDbConfig {

    @Autowired
    private Environment env;
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties bookDataSourceproperties(){
        return new DataSourceProperties();
    }

    @Bean(name ="bookDataSource")
    @Primary
    public DataSource dataSource(){
        log.info("The RETRIEVED bookDb DataSource Url is ::{}", bookDataSourceproperties().getUrl());
        return bookDataSourceproperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }
///**
    @Primary
    @Bean(name = "bookDbEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        localContainerEntityManagerFactoryBean.setPackagesToScan(new String[]
                {"com.polarisdigitech.backendchallenge.model.book"});
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
                env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect",
                env.getProperty("hibernate.dialect"));
        localContainerEntityManagerFactoryBean.setJpaPropertyMap(properties);
       return localContainerEntityManagerFactoryBean;

    }
                                    //*/
}
