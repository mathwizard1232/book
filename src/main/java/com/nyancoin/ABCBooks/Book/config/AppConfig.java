package com.nyancoin.ABCBooks.Book.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class})
public class AppConfig {
    // No other content needed
}
