package edu.pavliuk.security25.config;

/*
    @author romat
    @project security25
    @class AuditionConfig
    @version 1.0.0
    @since 19.04.2025 - 11.35
*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@Configuration
public class AuditionConfig {


    @Bean
    public AuditorAware<String> auditorAware() {
        return  new AuditorAwareImplementation();
    }

}
