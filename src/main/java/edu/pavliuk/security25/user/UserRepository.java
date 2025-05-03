package edu.pavliuk.security25.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
    @author romat
    @project security25
    @class UserRepository
    @version 1.0.0
    @since 03.05.2025 - 12.52
*/
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
