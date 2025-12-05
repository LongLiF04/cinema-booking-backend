package com.example.CineBook.repository.irepository;

import com.example.CineBook.model.Room;
import com.example.CineBook.repository.custom.RoomRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID>, RoomRepositoryCustom {
    List<Room> findByBranchId(UUID branchId);
    long countByBranchId(UUID branchId);
    boolean existsByName(String name);
    
    @Modifying
    @Query("UPDATE Room r SET r.isDelete = true, r.deleteTime = CURRENT_TIMESTAMP WHERE r.id = :id")
    void softDeleteById(@Param("id") UUID id);
}
