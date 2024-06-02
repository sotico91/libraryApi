package com.library.libraryAPI.model.book.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookEntityTest {

    @Test
    void testBookEntity() {

        BookEntity book = BookEntity.builder()
                .id(1L)
                .title("The Great Gatsby")
                .isbn("9780743273565")
                .publisher("Scribner")
                .language("English")
                .genre("Novel")
                .format("Hardcover")
                .status("Available")
                .authorName("F. Scott Fitzgerald")
                .build();

        assertEquals(1L, book.getId());
        assertEquals("The Great Gatsby", book.getTitle());
        assertEquals("9780743273565", book.getIsbn());
        assertEquals("Scribner", book.getPublisher());
        assertEquals("English", book.getLanguage());
        assertEquals("Novel", book.getGenre());
        assertEquals("Hardcover", book.getFormat());
        assertEquals("Available", book.getStatus());
        assertEquals("F. Scott Fitzgerald", book.getAuthorName());
    }

}