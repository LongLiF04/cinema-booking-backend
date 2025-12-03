package com.example.CineBook.repository.irepository;

import com.example.CineBook.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface PositionRepository extends JpaRepository<Position, UUID>, JpaSpecificationExecutor<Position> {
    Optional<Position> findByCode(String code);
    boolean existsByCode(String code);
}
