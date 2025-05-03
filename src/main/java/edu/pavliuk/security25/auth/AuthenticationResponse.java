package edu.pavliuk.security25.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/*
    @author romat
    @project security25
    @class AuthenticationResponse
    @version 1.0.0
    @since 19.04.2025 - 19.28
*/
@Builder
@Getter
@Setter
public class AuthenticationResponse {
    private String token;
}
