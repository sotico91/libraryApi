package com.library.libraryAPI.service.author;

import com.library.libraryAPI.model.author.dto.AuthorDTO;
import com.library.libraryAPI.model.author.entity.AuthorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAuthorService {

    void createAuthor(AuthorDTO authorDTO);
    AuthorEntity getAuthor(Long numberDocument);
    AuthorEntity findAuthor(Long numberDocument);
    Page<AuthorDTO> listAuthors(Pageable pageable);
}