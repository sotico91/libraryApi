package com.library.libraryAPI.model.author.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorEntityTest {

    @Test
    void testAuthorEntity() {
        AuthorEntity author = AuthorEntity.builder()
                .Id(1L)
                .name("Martin")
                .lastName("Garrison")
                .numberDocument(1256663399L)
                .dateOfBirth("12/05/1985")
                .nationality("British")
                .build();

        assertEquals(1L, author.getId());
        assertEquals("Martin", author.getName());
        assertEquals("Garrison", author.getLastName());
        assertEquals(1256663399L, author.getNumberDocument());
        assertEquals("12/05/1985", author.getDateOfBirth());
        assertEquals("British", author.getNationality());
    }

}