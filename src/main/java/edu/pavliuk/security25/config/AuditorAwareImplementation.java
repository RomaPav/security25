package edu.pavliuk.security25.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/*
    @author romat
    @project security25
    @class AuditorAwareImplementation
    @version 1.0.0
    @since 19.04.2025 - 11.30
*/public class AuditorAwareImplementation implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(System.getProperty("user.name"));
    }
}
