package com.example.CineBook.repository.impl;

import com.example.CineBook.dto.customer.CustomerSearchDTO;
import com.example.CineBook.model.Customer;
import com.example.CineBook.model.Customer_;
import com.example.CineBook.model.SysUser;
import com.example.CineBook.model.SysUser_;
import com.example.CineBook.repository.custom.CustomerRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Customer> findAllWithFilters(CustomerSearchDTO searchDTO) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> customer = query.from(Customer.class);
        Join<Customer, SysUser> user = customer.join("userId");

        List<Predicate> predicates = new ArrayList<>();

        // Filter by soft delete
        predicates.add(cb.equal(customer.get(Customer_.isDelete), false));

        // Search by keyword (name, email, phone)
        if (StringUtils.hasText(searchDTO.getKeyword())) {
            String keyword = "%" + searchDTO.getKeyword().toLowerCase() + "%";
            Predicate namePredicate = cb.like(cb.lower(user.get(SysUser_.name)), keyword);
            Predicate emailPredicate = cb.like(cb.lower(user.get(SysUser_.email)), keyword);
            Predicate phonePredicate = cb.like(user.get(SysUser_.phone), keyword);
            predicates.add(cb.or(namePredicate, emailPredicate, phonePredicate));
        }

        // Filter by membership level
        if (StringUtils.hasText(searchDTO.getMembershipLevel())) {
            predicates.add(cb.equal(customer.get(Customer_.membershipLevel), searchDTO.getMembershipLevel()));
        }

        // Filter by city
        if (StringUtils.hasText(searchDTO.getCity())) {
            predicates.add(cb.like(cb.lower(customer.get(Customer_.city)), 
                "%" + searchDTO.getCity().toLowerCase() + "%"));
        }

        query.where(predicates.toArray(new Predicate[0]));
        query.orderBy(cb.desc(customer.get(Customer_.createdAt)));

        // Pagination
        Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize());
        List<Customer> customers = entityManager.createQuery(query)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        // Count total
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Customer> countRoot = countQuery.from(Customer.class);
        countRoot.join("userId");
        countQuery.select(cb.count(countRoot));
        countQuery.where(predicates.toArray(new Predicate[0]));
        Long total = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(customers, pageable, total);
    }
}
