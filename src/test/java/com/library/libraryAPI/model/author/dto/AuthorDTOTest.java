package com.library.libraryAPI.model.author.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorDTOTest {

    @Test
    void testAuthorDTO() {
        AuthorDTO author = AuthorDTO.builder()
                .name("Martin")
                .lastName("Garrison")
                .numberDocument(1256663399L)
                .dateOfBirth("12/05/1985")
                .nationality("British")
                .build();

        assertEquals("Martin", author.getName());
        assertEquals("Garrison", author.getLastName());
        assertEquals(1256663399L, author.getNumberDocument());
        assertEquals("12/05/1985", author.getDateOfBirth());
        assertEquals("British", author.getNationality());
    }
}