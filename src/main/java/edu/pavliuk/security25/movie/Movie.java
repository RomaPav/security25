package edu.pavliuk.security25.movie;/*
    @author romat
    @project security25
    @class Movie
    @version 1.0.0
    @since 09.03.2025 - 16.54
*/

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Movie {

    @Id
    private String id;
    private String title;
    private String description;

    public Movie(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie movie)) return false;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
