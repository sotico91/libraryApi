package com.library.libraryAPI.mapper.book;

import com.library.libraryAPI.model.author.entity.AuthorEntity;
import com.library.libraryAPI.model.book.dto.BookDTO;
import com.library.libraryAPI.model.book.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class BookMapperTest {

    @InjectMocks
    private BookMapper bookMapper;

    @Test
    void testGetBookDtoToEntity() {
        BookDTO bookDTO = BookDTO.builder()
                .title("The Great Gatsby")
                .isbn("9780743273565")
                .publisher("Scribner")
                .language("English")
                .genre("Novel")
                .format("Hardcover")
                .status("Available")
                .build();

        BookEntity bookEntity = bookMapper.getBookDtoToEntity(bookDTO);

        assertEquals(bookDTO.getTitle(), bookEntity.getTitle());
        assertEquals(bookDTO.getIsbn(), bookEntity.getIsbn());
        assertEquals(bookDTO.getPublisher(), bookEntity.getPublisher());
        assertEquals(bookDTO.getLanguage(), bookEntity.getLanguage());
        assertEquals(bookDTO.getGenre(), bookEntity.getGenre());
        assertEquals(bookDTO.getFormat(), bookEntity.getFormat());
        assertEquals(bookDTO.getStatus(), bookEntity.getStatus());
    }

    @Test
    void testGetBookEntityToBookDTO() {
        BookEntity bookEntity = BookEntity.builder()
                .id(1L)
                .title("The Great Gatsby")
                .isbn("9780743273565")
                .publisher("Scribner")
                .language("English")
                .genre("Novel")
                .format("Hardcover")
                .status("Available")
                .build();

        BookDTO bookDTO = bookMapper.getBookEntityToBookDTO(bookEntity);

        assertEquals(bookEntity.getTitle(), bookDTO.getTitle());
        assertEquals(bookEntity.getIsbn(), bookDTO.getIsbn());
        assertEquals(bookEntity.getPublisher(), bookDTO.getPublisher());
        assertEquals(bookEntity.getLanguage(), bookDTO.getLanguage());
        assertEquals(bookEntity.getGenre(), bookDTO.getGenre());
        assertEquals(bookEntity.getFormat(), bookDTO.getFormat());
        assertEquals(bookEntity.getStatus(), bookDTO.getStatus());
    }

    @Test
    void testGetBookDtoToBookEntityWithAuthor() {
        AuthorEntity authorEntity = AuthorEntity.builder()
                .Id(1L)
                .name("Martin")
                .lastName("Garrison")
                .numberDocument(1256663399L)
                .dateOfBirth("12/05/1985")
                .nationality("British")
                .build();

        BookDTO bookDTO = BookDTO.builder()
                .title("The Great Gatsby")
                .isbn("9780743273565")
                .publisher("Scribner")
                .language("English")
                .genre("Novel")
                .format("Hardcover")
                .status("Available")
                .build();

        BookEntity bookEntity = new BookEntity();

        BookEntity updatedBookEntity = bookMapper.getBookDtoToBookEntityWithAuthor(bookEntity, bookDTO, authorEntity);

        assertEquals(bookDTO.getTitle(), updatedBookEntity.getTitle());
        assertEquals(bookDTO.getIsbn(), updatedBookEntity.getIsbn());
        assertEquals(bookDTO.getPublisher(), updatedBookEntity.getPublisher());
        assertEquals(bookDTO.getLanguage(), updatedBookEntity.getLanguage());
        assertEquals(bookDTO.getGenre(), updatedBookEntity.getGenre());
        assertEquals(bookDTO.getFormat(), updatedBookEntity.getFormat());
        assertEquals(bookDTO.getStatus(), updatedBookEntity.getStatus());
        assertEquals(authorEntity, updatedBookEntity.getAuthorEntity());
    }

    @Test
    void testGetBookEntityToBookDTOByAuthor() {
        AuthorEntity authorEntity = AuthorEntity.builder()
                .Id(1L)
                .name("Martin")
                .lastName("Garrison")
                .numberDocument(1256663399L)
                .dateOfBirth("12/05/1985")
                .nationality("British")
                .build();

        BookEntity bookEntity = BookEntity.builder()
                .id(1L)
                .title("The Great Gatsby")
                .isbn("9780743273565")
                .publisher("Scribner")
                .language("English")
                .genre("Novel")
                .format("Hardcover")
                .status("Available")
                .authorEntity(authorEntity)
                .build();

        BookDTO bookDTO = bookMapper.getBookEntityToBookDTOByAuthor(bookEntity);

        assertEquals(bookEntity.getTitle(), bookDTO.getTitle());
        assertEquals(bookEntity.getIsbn(), bookDTO.getIsbn());
        assertEquals(bookEntity.getPublisher(), bookDTO.getPublisher());
        assertEquals(bookEntity.getLanguage(), bookDTO.getLanguage());
        assertEquals(bookEntity.getGenre(), bookDTO.getGenre());
        assertEquals(bookEntity.getFormat(), bookDTO.getFormat());
        assertEquals(bookEntity.getStatus(), bookDTO.getStatus());
        assertEquals(authorEntity.getName() + " " + authorEntity.getLastName(), bookDTO.getAuthor());
    }

}