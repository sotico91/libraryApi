package com.library.libraryAPI.mapper.book;

import com.library.libraryAPI.model.author.entity.AuthorEntity;
import com.library.libraryAPI.model.book.dto.BookDTO;
import com.library.libraryAPI.model.book.entity.BookEntity;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookEntity getBookDtoToEntity(BookDTO bookDTO) {

        return BookEntity.builder()
                .title(bookDTO.getTitle())
                .isbn(bookDTO.getIsbn())
                .publisher(bookDTO.getPublisher())
                .language(bookDTO.getLanguage())
                .genre(bookDTO.getGenre())
                .format(bookDTO.getFormat())
                .status(bookDTO.getStatus())
                .build();

    }

    public BookDTO getBookEntityToBookDTO(BookEntity bookEntity) {

        return BookDTO.builder()
                .title(bookEntity.getTitle())
                .isbn(bookEntity.getIsbn())
                .publisher(bookEntity.getPublisher())
                .language(bookEntity.getLanguage())
                .genre(bookEntity.getGenre())
                .format(bookEntity.getFormat())
                .status(bookEntity.getStatus())
                .build();
    }

    public BookEntity getBookDtoToBookEntityWithAuthor(BookEntity bookEntity, BookDTO bookDTO, AuthorEntity authorEntity) {

        bookEntity.setIsbn(bookDTO.getIsbn());
        bookEntity.setAuthorEntity(authorEntity);
        bookEntity.setGenre(bookDTO.getGenre());
        bookEntity.setFormat(bookDTO.getFormat());
        bookEntity.setLanguage(bookDTO.getLanguage());
        bookEntity.setPublisher(bookDTO.getPublisher());
        bookEntity.setStatus(bookDTO.getStatus());
        bookEntity.setTitle(bookDTO.getTitle());

        return bookEntity;
    }

    public BookDTO getBookEntityToBookDTOByAuthor(BookEntity bookEntity) {

        return BookDTO.builder()
                .title(bookEntity.getTitle())
                .isbn(bookEntity.getIsbn())
                .publisher(bookEntity.getPublisher())
                .language(bookEntity.getLanguage())
                .genre(bookEntity.getGenre())
                .format(bookEntity.getFormat())
                .status(bookEntity.getStatus())
                .author( bookEntity.getAuthorEntity() !=null ? (bookEntity.getAuthorEntity().getName() + " " + bookEntity.getAuthorEntity().getLastName()) : "")
                .build();
    }

}