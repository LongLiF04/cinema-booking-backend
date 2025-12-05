package com.example.CineBook.repository.irepository;

import com.example.CineBook.dto.movie.MovieSearchDTO;
import com.example.CineBook.model.Movie;
import com.example.CineBook.repository.base.BaseRepositoryCustom;
import com.example.CineBook.repository.custom.MovieRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID>, 
        BaseRepositoryCustom<Movie, MovieSearchDTO>, 
        MovieRepositoryCustom {
}
