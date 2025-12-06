package com.example.CineBook.repository.custom;

import com.example.CineBook.dto.room.RoomSearchDTO;
import com.example.CineBook.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface RoomRepositoryCustom {
    Page<Room> searchWithFilters(RoomSearchDTO searchDTO, Pageable pageable);
//    void softDeleteByIds(List<UUID> ids);
}
