package com.library.libraryAPI.mapper.author;

import com.library.libraryAPI.model.author.dto.AuthorDTO;
import com.library.libraryAPI.model.author.entity.AuthorEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    private final static Logger logger = LogManager.getLogger(AuthorMapper.class.getName());

    public AuthorEntity getAuthorDtoToEntity(AuthorDTO  authorDTO) {

        logger.info("Start mapper.getAuthorDtoToEntity method {}", authorDTO);

        return AuthorEntity.builder()
                .name(authorDTO.getName())
                .lastName(authorDTO.getLastName())
                .numberDocument(authorDTO.getNumberDocument())
                .dateOfBirth(authorDTO.getDateOfBirth())
                .nationality(authorDTO.getNationality())
                .build();
    }

    public AuthorDTO getAuthorEntityToAuthorDto(AuthorEntity authorEntity) {

        logger.info("Start mapper.getAuthorEntityToAuthorDto method {}", authorEntity);
        return AuthorDTO.builder()
                .name(authorEntity.getName())
                .lastName(authorEntity.getLastName())
                .numberDocument(authorEntity.getNumberDocument())
                .dateOfBirth(authorEntity.getDateOfBirth())
                .nationality(authorEntity.getNationality())
                .build();
    }
}