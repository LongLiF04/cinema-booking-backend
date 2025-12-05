package com.example.CineBook.repository.irepository;

import com.example.CineBook.model.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenre, MovieGenre.MovieGenreId> {
    
    @Modifying
    @Query("DELETE FROM MovieGenre mg WHERE mg.movieId = :movieId")
    void deleteByMovieId(@Param("movieId") UUID movieId);
    
    @Query("SELECT mg.genreId FROM MovieGenre mg WHERE mg.movieId = :movieId")
    List<UUID> findGenreIdsByMovieId(@Param("movieId") UUID movieId);
    
    @Query("SELECT COUNT(mg) FROM MovieGenre mg WHERE mg.genreId = :genreId")
    long countByGenreId(@Param("genreId") UUID genreId);
}
