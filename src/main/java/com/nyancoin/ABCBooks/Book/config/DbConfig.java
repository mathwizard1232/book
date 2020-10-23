package com.nyancoin.ABCBooks.Book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration // Tells Spring to process this before startup/DI
public class DbConfig {

    @Bean
    // Makes the result of this available for DI, if you have multiple of the same type they must be differentiated
    // You can determine the name of the bean explicitly with @Qualifier, but it automatically is configured
    // based on the method name, so I would stick with that for ease-of-use + obviousness
    public DataSource dataSource() {
        // So generally at my work we have datasources configured at the Tomcat server level retrieved by JNDI lookup
        // Configure the datasource here as you like, including to an in-memory database if you so choose
        // BasicDataSourceBuilder is an easy interface for just providing credentials and a connection URL
    }

    @Bean
    // Template for SQL queries, automatically borrows and returns SQL connections when queries are done
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    // Provides support for transactional rollback of SQL work, such as if we fail to insert Book before BookContents
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
