package com.beatdev.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main configuration class.
 */
@EnableJpaRepositories("com.beatdev.api.repository")
@ComponentScan("com.beatdev.api")
@EnableTransactionManagement
@Configuration
public class ApplicationConfig {
}
