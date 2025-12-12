package com.example.CineBook.repository.irepository;

import com.example.CineBook.dto.city.CitySearchDTO;
import com.example.CineBook.model.City;
import com.example.CineBook.repository.base.BaseRepositoryCustom;
import com.example.CineBook.repository.custom.CityRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, UUID>, BaseRepositoryCustom<City, CitySearchDTO>, CityRepositoryCustom {
    boolean existsByName(String name);
    Optional<City> findByName(String name);
    
    @Query("SELECT DISTINCT c FROM City c " +
           "JOIN Branch b ON b.cityId = c.id " +
           "JOIN Showtime s ON s.branchId = b.id " +
           "WHERE s.movieId = :movieId AND CAST(s.startTime AS LocalDate) = :date " +
           "AND s.isDelete = false AND c.isDelete = false")
    List<City> findAvailableCitiesByMovieAndDate(
        @Param("movieId") UUID movieId,
        @Param("date") LocalDate date
    );
}
