package com.library.libraryAPI.model.author.dto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomPageAuthorDTOTest {


    @Test
    void testCustomPageAuthorDTO() {
        AuthorDTO author1 = AuthorDTO.builder()
                .name("Martin")
                .lastName("Garrison")
                .numberDocument(1256663399L)
                .dateOfBirth("12/05/1985")
                .nationality("British")
                .build();

        AuthorDTO author2 = AuthorDTO.builder()
                .name("John")
                .lastName("Doe")
                .numberDocument(1234567890L)
                .dateOfBirth("01/01/1990")
                .nationality("American")
                .build();

        CustomPageAuthorDTO page = CustomPageAuthorDTO.builder()
                .totalElements(2)
                .totalPages(1)
                .size(2)
                .content(Arrays.asList(author1, author2))
                .build();

        assertEquals(2, page.getTotalElements());
        assertEquals(1, page.getTotalPages());
        assertEquals(2, page.getSize());
        assertEquals(Arrays.asList(author1, author2), page.getContent());
    }

}