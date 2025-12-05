package com.example.CineBook.repository.custom;

import com.example.CineBook.dto.genre.GenreSearchDTO;
import com.example.CineBook.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenreRepositoryCustom {
    Page<Genre> searchWithFilters(GenreSearchDTO searchDTO, Pageable pageable);
}
