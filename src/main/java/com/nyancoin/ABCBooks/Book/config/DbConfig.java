package com.nyancoin.ABCBooks.Book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import com.holonplatform.jdbc.BasicDataSource;

@Configuration // Tells Spring to process this before startup/DI
public class DbConfig {

    @Bean
    // Makes the result of this available for DI, if you have multiple of the same type they must be differentiated
    // You can determine the name of the bean explicitly with @Qualifier, but it automatically is configured
    // based on the method name, so I would stick with that for ease-of-use + obviousness
    public DataSource dataSource() {
        // So generally at my work we have datasources configured at the Tomcat server level retrieved by JNDI lookup
        // TODO Configure the datasource here as you like, including to an in-memory database like H2 if you so choose
        // BasicDataSourceBuilder is an easy interface for just providing credentials and a connection URL

        // This needs to be actually configured to work but adding first
        // in this way to confirm it's pulling in the dependency right
        return BasicDataSource.builder().build();
    }

    @Bean
    // Template for SQL queries, automatically borrows and returns SQL connections when queries are done
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    // Configuring the PlatformTransactionManager bean enables the use of @Transactional on a service method
    // containing two or more dao calls (i.e. separate transaction contexts)

    // If call A changes data and call B throws an exception, we will roll back call A for consistency
    // This applies even if you consolidate the two SQL methods into a single DAO. Spanning calls to the template means
    // you will span transaction contexts *unless* using this annotation to unify them.

    // If you catch the exception call B would have thrown, Transactional can't do its work

    // Transactional will only fire when annotating public methods, and will *not* fire when the method is called
    // internally (i.e. by another method from this class), because the behavior is implemented by a proxy class
    // wrapping the actual service instance, not by the instance itself.
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
