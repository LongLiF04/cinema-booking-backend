package com.example.CineBook.repository.irepository;

import com.example.CineBook.model.Genre;
import com.example.CineBook.repository.custom.GenreRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GenreRepository extends JpaRepository<Genre, UUID>, GenreRepositoryCustom {
    boolean existsByName(String name);
    Optional<Genre> findByName(String name);
    
    @Modifying
    @Query("UPDATE Genre g SET g.isDelete = true, g.deleteTime = CURRENT_TIMESTAMP WHERE g.id = :id")
    void softDeleteById(@Param("id") UUID id);
}
