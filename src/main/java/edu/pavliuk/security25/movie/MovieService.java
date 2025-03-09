package edu.pavliuk.security25.movie;/*
    @author romat
    @project security25
    @class MovieService
    @version 1.0.0
    @since 09.03.2025 - 17.01
*/

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository  movieRepository;

    private final List<Movie> movies = new ArrayList<>();

    @PostConstruct
    void init() {
        movies.add(new Movie("1", "Title1", "Description1"));
        movies.add(new Movie("2", "Title2", "Description2"));
        movies.add(new Movie("3", "Title3", "Description3"));

        movieRepository.saveAll(movies);
    }


    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(String id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovie(String id) {
        movieRepository.deleteById(id);
    }
}
