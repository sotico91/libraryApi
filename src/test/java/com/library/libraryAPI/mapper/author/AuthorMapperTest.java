package com.library.libraryAPI.mapper.author;

import com.library.libraryAPI.model.author.dto.AuthorDTO;
import com.library.libraryAPI.model.author.entity.AuthorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AuthorMapperTest {


    @InjectMocks
    private AuthorMapper authorMapper;

    @Test
    void testGetAuthorDtoToEntity() {
        AuthorDTO authorDTO = AuthorDTO.builder()
                .name("Martin")
                .lastName("Garrison")
                .numberDocument(1256663399L)
                .dateOfBirth("12/05/1985")
                .nationality("British")
                .build();

        AuthorEntity authorEntity = authorMapper.getAuthorDtoToEntity(authorDTO);

        assertEquals(authorDTO.getName(), authorEntity.getName());
        assertEquals(authorDTO.getLastName(), authorEntity.getLastName());
        assertEquals(authorDTO.getNumberDocument(), authorEntity.getNumberDocument());
        assertEquals(authorDTO.getDateOfBirth(), authorEntity.getDateOfBirth());
        assertEquals(authorDTO.getNationality(), authorEntity.getNationality());
    }

    @Test
    void testGetAuthorEntityToAuthorDto() {
        AuthorEntity authorEntity = AuthorEntity.builder()
                .Id(1L)
                .name("Martin")
                .lastName("Garrison")
                .numberDocument(1256663399L)
                .dateOfBirth("12/05/1985")
                .nationality("British")
                .build();

        AuthorDTO authorDTO = authorMapper.getAuthorEntityToAuthorDto(authorEntity);

        assertEquals(authorEntity.getName(), authorDTO.getName());
        assertEquals(authorEntity.getLastName(), authorDTO.getLastName());
        assertEquals(authorEntity.getNumberDocument(), authorDTO.getNumberDocument());
        assertEquals(authorEntity.getDateOfBirth(), authorDTO.getDateOfBirth());
        assertEquals(authorEntity.getNationality(), authorDTO.getNationality());
    }

}