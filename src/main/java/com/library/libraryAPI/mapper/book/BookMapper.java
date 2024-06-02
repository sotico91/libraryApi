package com.library.libraryAPI.mapper.book;

import com.library.libraryAPI.model.author.entity.AuthorEntity;
import com.library.libraryAPI.model.book.dto.BookDTO;
import com.library.libraryAPI.model.book.entity.BookEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    private final static Logger logger =
            LogManager.getLogger(BookMapper.class.getName());

    public BookEntity getBookDtoToEntity(BookDTO bookDTO) {

        logger.info("Start mapper.getBookDtoToEntity method {}", bookDTO);

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


        logger.info("Start mapper.getBookEntityToBookDTO method {}", bookEntity);

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

        logger.info("Start mapper.getBookDtoToBookEntityWithAuthor method {}", bookDTO);

        bookEntity.setIsbn(bookDTO.getIsbn());
        bookEntity.setAuthorEntity(authorEntity);
        bookEntity.setGenre(bookDTO.getGenre());
        bookEntity.setFormat(bookDTO.getFormat());
        bookEntity.setLanguage(bookDTO.getLanguage());
        bookEntity.setPublisher(bookDTO.getPublisher());
        bookEntity.setStatus(bookDTO.getStatus());
        bookEntity.setTitle(bookDTO.getTitle());

        logger.info("End mapper.getBookDtoToBookEntityWithAuthor method {}", bookEntity);

        return bookEntity;
    }

    public BookDTO getBookEntityToBookDTOByAuthor(BookEntity bookEntity) {

        logger.info("Start mapper.getBookEntityToBookDTOByAuthor method {}", bookEntity);

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