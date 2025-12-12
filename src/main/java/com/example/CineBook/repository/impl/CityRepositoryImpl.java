package com.example.CineBook.repository.impl;

import com.example.CineBook.dto.city.CitySearchDTO;
import com.example.CineBook.model.City;
import com.example.CineBook.model.City_;
import com.example.CineBook.repository.base.BaseRepositoryImpl;
import com.example.CineBook.repository.custom.CityRepositoryCustom;
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
public class CityRepositoryImpl extends BaseRepositoryImpl<City, CitySearchDTO> implements CityRepositoryCustom {

    public CityRepositoryImpl() { super(City.class);}

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<City> searchWithFilters(CitySearchDTO searchDTO, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<City> query = cb.createQuery(City.class);
        Root<City> city = query.from(City.class);

        List<Predicate> predicates = buildPredicates(cb, city, searchDTO);

        query.where(predicates.toArray(new Predicate[0]));
        query.orderBy(cb.asc(city.get(City_.name)));

        List<City> cities = entityManager.createQuery(query)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        Long total = countTotal(cb, searchDTO);

        return new PageImpl<>(cities, pageable, total);
    }

    private List<Predicate> buildPredicates(CriteriaBuilder cb, Root<City> city, CitySearchDTO searchDTO) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(city.get(City_.isDelete), false));

        if (StringUtils.hasText(searchDTO.getKeyword())) {
            predicates.add(cb.like(cb.lower(city.get(City_.name)),
                    "%" + searchDTO.getKeyword().toLowerCase() + "%"));
        }

        return predicates;
    }

    private Long countTotal(CriteriaBuilder cb, CitySearchDTO searchDTO) {
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<City> countRoot = countQuery.from(City.class);

        List<Predicate> countPredicates = buildPredicates(cb, countRoot, searchDTO);

        countQuery.select(cb.count(countRoot));
        countQuery.where(countPredicates.toArray(new Predicate[0]));

        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
