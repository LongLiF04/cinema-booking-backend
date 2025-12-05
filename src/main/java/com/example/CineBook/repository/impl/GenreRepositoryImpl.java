package com.example.CineBook.repository.impl;

import com.example.CineBook.dto.genre.GenreSearchDTO;
import com.example.CineBook.model.Genre;
import com.example.CineBook.model.Genre_;
import com.example.CineBook.repository.custom.GenreRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GenreRepositoryImpl implements GenreRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Genre> searchWithFilters(GenreSearchDTO searchDTO, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Genre> query = cb.createQuery(Genre.class);
        Root<Genre> genre = query.from(Genre.class);

        List<Predicate> predicates = buildPredicates(cb, genre, searchDTO);

        query.where(predicates.toArray(new Predicate[0]));
        query.orderBy(cb.asc(genre.get(Genre_.name)));

        List<Genre> genres = entityManager.createQuery(query)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        Long total = countTotal(cb, searchDTO);

        return new PageImpl<>(genres, pageable, total);
    }

    private List<Predicate> buildPredicates(CriteriaBuilder cb, Root<Genre> genre, GenreSearchDTO searchDTO) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(genre.get(Genre_.isDelete), false));

        if (StringUtils.hasText(searchDTO.getKeyword())) {
            predicates.add(cb.like(cb.lower(genre.get(Genre_.name)),
                    "%" + searchDTO.getKeyword().toLowerCase() + "%"));
        }

        return predicates;
    }

    private Long countTotal(CriteriaBuilder cb, GenreSearchDTO searchDTO) {
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Genre> countRoot = countQuery.from(Genre.class);

        List<Predicate> countPredicates = buildPredicates(cb, countRoot, searchDTO);

        countQuery.select(cb.count(countRoot));
        countQuery.where(countPredicates.toArray(new Predicate[0]));

        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
