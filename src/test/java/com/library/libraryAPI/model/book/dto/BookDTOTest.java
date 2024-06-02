package com.library.libraryAPI.model.book.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookDTOTest {

    @Test
    void testBookDTO() {
        BookDTO book = BookDTO.builder()
                .title("The Great Gatsby")
                .author("F. Scott Fitzgerald")
                .isbn("9780743273565")
                .publisher("Scribner")
                .language("English")
                .genre("Novel")
                .format("Hardcover")
                .status("Available")
                .build();

        assertEquals("The Great Gatsby", book.getTitle());
        assertEquals("F. Scott Fitzgerald", book.getAuthor());
        assertEquals("9780743273565", book.getIsbn());
        assertEquals("Scribner", book.getPublisher());
        assertEquals("English", book.getLanguage());
        assertEquals("Novel", book.getGenre());
        assertEquals("Hardcover", book.getFormat());
        assertEquals("Available", book.getStatus());
    }

}