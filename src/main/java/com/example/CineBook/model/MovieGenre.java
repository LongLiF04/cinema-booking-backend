package com.example.CineBook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "movie_genres")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(MovieGenre.MovieGenreId.class)
public class MovieGenre {
    
    @Id
    @Column(name = "movie_id")
    private UUID movieId;
    
    @Id
    @Column(name = "genre_id")
    private UUID genreId;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MovieGenreId implements Serializable {
        private UUID movieId;
        private UUID genreId;
    }
}
