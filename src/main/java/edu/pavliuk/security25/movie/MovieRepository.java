package edu.pavliuk.security25.movie;/*
    @author romat
    @project security25
    @class MovieRepository
    @version 1.0.0
    @since 09.03.2025 - 17.00
*/

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

}
