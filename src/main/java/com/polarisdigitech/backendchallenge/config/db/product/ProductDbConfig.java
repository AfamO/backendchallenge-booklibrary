
package com.polarisdigitech.backendchallenge.config.db.product;

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
//import org.springframework.orm.jpa.EntityManagerFactoryBuilder;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "productDbEntityManager",
        basePackages = {"com.polarisdigitech.backendchallenge.repository.product"}
)
@Slf4j
public class ProductDbConfig {

    @Autowired
    private Environment env;
    @Bean
    @ConfigurationProperties(prefix = "spring.second-datasource")
    public DataSourceProperties productDataSourceproperties(){
        return new DataSourceProperties();
    }

    @Bean(name ="productDataSource")
    public DataSource productDataSource(){
        log.info("The RETRIEVED bookDb DataSource Url is ::{}", productDataSourceproperties().getUrl());
        return productDataSourceproperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }
///**
    @Bean(name ="productDbEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(productDataSource());
        localContainerEntityManagerFactoryBean.setPackagesToScan(new String[]
                {"com.polarisdigitech.backendchallenge.model.product"});
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
                env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect",
                env.getProperty("hibernate.dialect"));
        properties.put("hibernate.use-new-id-generator-mappings",env.getProperty("hibernate.use-new-id-generator-mappings"));
        localContainerEntityManagerFactoryBean.setJpaPropertyMap(properties);
       return localContainerEntityManagerFactoryBean;

    }
                                    //*/
}
