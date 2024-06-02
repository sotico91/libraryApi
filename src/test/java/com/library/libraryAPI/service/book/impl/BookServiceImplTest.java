package com.library.libraryAPI.service.book.impl;

import com.library.libraryAPI.mapper.book.BookMapper;
import com.library.libraryAPI.model.book.dto.BookDTO;
import com.library.libraryAPI.model.book.entity.BookEntity;
import com.library.libraryAPI.repository.book.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {


    @Mock
    private BookRepository bookRepository;


    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void testGetBook() {
        String isbn = "1234567890";
        BookEntity bookEntity = new BookEntity();
        BookDTO bookDTO = new BookDTO();

        when(bookRepository.findBookEntityByIsbn(isbn)).thenReturn(bookEntity);
        when(bookMapper.getBookEntityToBookDTO(bookEntity)).thenReturn(bookDTO);

        BookDTO result = bookService.getBook(isbn);

        assertEquals(bookDTO, result);
    }

    @Test
    void testFindBook() {
        String isbn = "1234567890";
        BookEntity bookEntity = new BookEntity();

        when(bookRepository.findBookEntityByIsbn(isbn)).thenReturn(bookEntity);

        BookEntity result = bookService.findBook(isbn);

        assertEquals(bookEntity, result);
    }

    @Test
    void testCreateBook() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setIsbn("1234567890");
        bookDTO.setTitle("Test Book");
        bookDTO.setIsbn("1234567890");
        bookDTO.setGenre("genre");
        bookDTO.setFormat("format");
        bookDTO.setLanguage("language");
        bookDTO.setPublisher("publisher");
        bookDTO.setStatus("status");


        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn("1234567890");
        bookEntity.setTitle("Test Book");
        bookEntity.setIsbn("1234567890");
        bookEntity.setGenre("genre");
        bookEntity.setFormat("format");
        bookEntity.setLanguage("language");
        bookEntity.setPublisher("publisher");
        bookEntity.setStatus("status");


        when(bookMapper.getBookDtoToEntity(any())).thenReturn(bookEntity);
        when(bookRepository.save(any())).thenReturn(bookEntity);
        when(bookMapper.getBookEntityToBookDTO(any())).thenReturn(bookDTO);

        BookDTO result = bookService.createBook(bookDTO);

        assertEquals(bookDTO, result);
        verify(bookMapper, times(1)).getBookDtoToEntity(bookDTO);
        verify(bookRepository, times(1)).save(bookEntity);
        verify(bookMapper, times(1)).getBookEntityToBookDTO(bookEntity);
    }

    @Test
    void testGetBooksByAuthor() {
        String authorName = "pedro Baez";
        Pageable pageable = mock(Pageable.class);
        Page<BookEntity> page = new PageImpl<>(Collections.singletonList(new BookEntity()));
        Page<BookDTO> dtoPage = new PageImpl<>(Collections.singletonList(new BookDTO()));

        when(bookRepository.findBookByAuthorName(authorName, pageable)).thenReturn(page);
        when(bookMapper.getBookEntityToBookDTO(any(BookEntity.class))).thenReturn(new BookDTO());

        Page<BookDTO> result = bookService.getBooksByAuthor(authorName, pageable);

        assertEquals(dtoPage.getContent().size(), result.getContent().size());
    }

    @Test
    void testUpdateBook() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setIsbn("1234567890");
        BookEntity bookEntityBefore = new BookEntity();
        BookEntity bookEntityAfter = new BookEntity();

        when(bookRepository.findBookEntityByIsbn(bookDTO.getIsbn())).thenReturn(bookEntityBefore);
        when(bookService.findBook(bookDTO.getIsbn())).thenReturn(bookEntityBefore);
        when(bookMapper.getBookDtoToBookEntityWithAuthor(bookEntityBefore, bookDTO)).thenReturn(bookEntityAfter);
        when(bookRepository.saveAndFlush(bookEntityAfter)).thenReturn(bookEntityAfter);
        when(bookMapper.getBookEntityToBookDTO(bookEntityAfter)).thenReturn(bookDTO);

        BookDTO result = bookService.updateBook(bookDTO);

        assertEquals(bookDTO, result);
    }

    @Test
    void testListBooks() {
        Pageable pageable = mock(Pageable.class);
        Page<BookEntity> page = new PageImpl<>(Collections.singletonList(new BookEntity()));
        Page<BookDTO> dtoPage = new PageImpl<>(Collections.singletonList(new BookDTO()));

        when(bookRepository.findAll(pageable)).thenReturn(page);
        when(bookMapper.getBookEntityToBookDTO(any(BookEntity.class))).thenReturn(new BookDTO());

        Page<BookDTO> result = bookService.listBooks(pageable);

        assertEquals(dtoPage.getContent().size(), result.getContent().size());
    }

}