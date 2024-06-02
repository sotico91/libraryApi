package com.library.libraryAPI.model.book.entity;

import com.library.libraryAPI.model.author.entity.AuthorEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookEntityTest {

    @Test
    void testBookEntity() {
        AuthorEntity author = AuthorEntity.builder()
                .Id(1L)
                .name("Martin")
                .lastName("Garrison")
                .numberDocument(1256663399L)
                .dateOfBirth("12/05/1985")
                .nationality("British")
                .build();

        BookEntity book = BookEntity.builder()
                .id(1L)
                .title("The Great Gatsby")
                .isbn("9780743273565")
                .publisher("Scribner")
                .language("English")
                .genre("Novel")
                .format("Hardcover")
                .status("Available")
                .authorEntity(author)
                .build();

        assertEquals(1L, book.getId());
        assertEquals("The Great Gatsby", book.getTitle());
        assertEquals("9780743273565", book.getIsbn());
        assertEquals("Scribner", book.getPublisher());
        assertEquals("English", book.getLanguage());
        assertEquals("Novel", book.getGenre());
        assertEquals("Hardcover", book.getFormat());
        assertEquals("Available", book.getStatus());
        assertEquals(author, book.getAuthorEntity());
    }

}