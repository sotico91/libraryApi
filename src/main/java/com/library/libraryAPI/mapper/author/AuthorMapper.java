package com.library.libraryAPI.mapper.author;

import com.library.libraryAPI.model.author.dto.AuthorDTO;
import com.library.libraryAPI.model.author.entity.AuthorEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorEntity getAuthorDtoToEntity(AuthorDTO  authorDTO) {

        return AuthorEntity.builder()
                .name(authorDTO.getName())
                .lastName(authorDTO.getLastName())
                .numberDocument(authorDTO.getNumberDocument())
                .dateOfBirth(authorDTO.getDateOfBirth())
                .nationality(authorDTO.getNationality())
                .build();
    }

    public AuthorDTO getAuthorEntityToAuthorDto(AuthorEntity authorEntity) {

        return AuthorDTO.builder()
                .name(authorEntity.getName())
                .lastName(authorEntity.getLastName())
                .numberDocument(authorEntity.getNumberDocument())
                .dateOfBirth(authorEntity.getDateOfBirth())
                .nationality(authorEntity.getNationality())
                .build();
    }
}