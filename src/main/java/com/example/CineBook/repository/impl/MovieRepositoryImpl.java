package com.example.CineBook.repository.impl;

import com.example.CineBook.dto.movie.MovieSearchDTO;
import com.example.CineBook.model.Movie;
import com.example.CineBook.model.Movie_;
import com.example.CineBook.repository.base.BaseRepositoryImpl;
import com.example.CineBook.repository.custom.MovieRepositoryCustom;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepositoryImpl extends BaseRepositoryImpl<Movie, MovieSearchDTO> implements MovieRepositoryCustom {

    public MovieRepositoryImpl() {
        super(Movie.class);
    }

    @Override
    protected List<Predicate> buildPredicates(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder cb, MovieSearchDTO searchDTO) {
        List<Predicate> predicates = new ArrayList<>();

        // Filter by soft delete
        predicates.add(cb.equal(root.get(Movie_.isDelete), false));

        // Search by keyword (title)
        if (StringUtils.hasText(searchDTO.getKeyword())) {
            String keyword = "%" + searchDTO.getKeyword().toLowerCase() + "%";
            predicates.add(cb.like(cb.lower(root.get(Movie_.title)), keyword));
        }

        // Filter by status
        if (StringUtils.hasText(searchDTO.getStatus())) {
            predicates.add(cb.equal(root.get(Movie_.status), searchDTO.getStatus()));
        }

        // Filter by release date range
        if (searchDTO.getReleaseDateFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(Movie_.releaseDate), searchDTO.getReleaseDateFrom()));
        }
        if (searchDTO.getReleaseDateTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get(Movie_.releaseDate), searchDTO.getReleaseDateTo()));
        }

        return predicates;
    }
}
