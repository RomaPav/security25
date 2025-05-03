package edu.pavliuk.security25.security;

import edu.pavliuk.security25.user.Role;
import edu.pavliuk.security25.user.User;
import edu.pavliuk.security25.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    @author romat
    @project security25
    @class UserDetailServiceImpl
    @version 1.0.0
    @since 03.05.2025 - 12.50
*/
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository repository;
//        private final PasswordEncoder passwordEncoder;
//
//     @PostConstruct
//  void init() {
//      User user = User.builder()
//              .firstName("Roma")
//              .lastName("Pavliuk")
//              .email("roma.pavliuk@mail.com")
//              .password(passwordEncoder.encode("password"))
//              .enabled(true)
//              .accountLocked(false)
//              .roles(List.of(Role.USER))
//              .build();
//     repository.save(user);
//  }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
