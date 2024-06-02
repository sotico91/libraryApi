package com.library.libraryAPI.model.book.dto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomPageBookDTOTest {

    @Test
    void testCustomPageBookDTO() {
        BookDTO book1 = BookDTO.builder()
                .title("The Great Gatsby")
                .author("F. Scott Fitzgerald")
                .isbn("9780743273565")
                .publisher("Scribner")
                .language("English")
                .genre("Novel")
                .format("Hardcover")
                .status("Available")
                .build();

        BookDTO book2 = BookDTO.builder()
                .title("To Kill a Mockingbird")
                .author("Harper Lee")
                .isbn("9780446310789")
                .publisher("Grand Central Publishing")
                .language("English")
                .genre("Southern Gothic, Bildungsroman")
                .format("Paperback")
                .status("Available")
                .build();

        CustomPageBookDTO page = CustomPageBookDTO.builder()
                .totalElements(2)
                .totalPages(1)
                .size(2)
                .content(Arrays.asList(book1, book2))
                .build();

        assertEquals(2, page.getTotalElements());
        assertEquals(1, page.getTotalPages());
        assertEquals(2, page.getSize());
        assertEquals(Arrays.asList(book1, book2), page.getContent());
    }

}