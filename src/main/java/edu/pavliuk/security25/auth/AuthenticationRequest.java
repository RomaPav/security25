package edu.pavliuk.security25.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/*
    @author romat
    @project security25
    @class AuthenticationRequest
    @version 1.0.0
    @since 19.04.2025 - 19.24
*/
@Data
public class AuthenticationRequest {

//    @Email(message = "Email is not well formatted")
//    @NotEmpty(message = "Email is mandatory")
//    @NotNull(message = "Email is mandatory")
    private String login;

//    @NotEmpty(message = "Password is mandatory")
//    @NotNull(message = "Password is mandatory")
//    @Size(min=9, message = " Password should be 9 characters long minimum")
    private String password;
}
