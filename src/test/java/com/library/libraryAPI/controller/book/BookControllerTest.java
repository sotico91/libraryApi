package com.library.libraryAPI.controller.book;

import com.library.libraryAPI.model.book.dto.BookDTO;
import com.library.libraryAPI.model.book.dto.CustomPageBookDTO;
import com.library.libraryAPI.service.book.IBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    private IBookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    void testGetBook() {
        String isbn = "1234567890";
        BookDTO bookDTO = new BookDTO();

        when(bookService.getBook(isbn)).thenReturn(bookDTO);

        ResponseEntity<BookDTO> result = bookController.getBook(isbn);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(bookDTO, result.getBody());
    }

    @Test
    void testListBooks() {
        Pageable pageable = mock(Pageable.class);
        Page<BookDTO> page = new PageImpl<>(Collections.singletonList(new BookDTO()));

        when(bookService.listBooks(pageable)).thenReturn(page);

        ResponseEntity<CustomPageBookDTO> result = bookController.listBooks(pageable);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(page.getContent().size(), Objects.requireNonNull(result.getBody()).getContent().size());
    }

    @Test
    void testGetBookByAuthor() {
        Long documentNumber = 123456789L;
        Pageable pageable = mock(Pageable.class);
        Page<BookDTO> page = new PageImpl<>(Collections.singletonList(new BookDTO()));

        when(bookService.getBooksByAuthor(documentNumber, pageable)).thenReturn(page);

        ResponseEntity<CustomPageBookDTO> result = bookController.getBookByAuthor(documentNumber, pageable);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(page.getContent().size(), Objects.requireNonNull(result.getBody()).getContent().size());
    }

    @Test
    void testCreateBook() {
        BookDTO bookDTO = new BookDTO();

        when(bookService.createBook(bookDTO)).thenReturn(bookDTO);

        ResponseEntity<BookDTO> result = bookController.createBook(bookDTO);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(bookDTO, result.getBody());
    }

    @Test
    void testUpdateBook() {
        Long documentNumber = 123456789L;
        BookDTO bookDTO = new BookDTO();

        when(bookService.updateBook(bookDTO, documentNumber)).thenReturn(bookDTO);

        ResponseEntity<BookDTO> result = bookController.updateBook(bookDTO, documentNumber);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(bookDTO, result.getBody());
    }


}