package com.example.CineBook.mapper;

import com.example.CineBook.dto.movie.CreateMovieRequest;
import com.example.CineBook.dto.movie.MovieResponse;
import com.example.CineBook.dto.movie.UpdateMovieRequest;
import com.example.CineBook.model.Movie;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieMapper {
    
    @Mapping(target = "status", ignore = true)
    Movie toEntity(CreateMovieRequest request);
    
    void updateEntityFromDto(UpdateMovieRequest request, @MappingTarget Movie movie);
    
    @Mapping(target = "genres", ignore = true)
    MovieResponse toResponse(Movie movie);
}
