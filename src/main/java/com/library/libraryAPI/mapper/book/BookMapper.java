package com.library.libraryAPI.mapper.book;

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
                .authorName(bookDTO.getAuthor())
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
                .author(bookEntity.getAuthorName())
                .build();
    }

    public BookEntity getBookDtoToBookEntityWithAuthor(BookEntity bookEntity, BookDTO bookDTO) {

        logger.info("Start mapper.getBookDtoToBookEntityWithAuthor method {}", bookDTO);

        bookEntity.setIsbn(bookDTO.getIsbn());
        bookEntity.setGenre(bookDTO.getGenre());
        bookEntity.setFormat(bookDTO.getFormat());
        bookEntity.setLanguage(bookDTO.getLanguage());
        bookEntity.setPublisher(bookDTO.getPublisher());
        bookEntity.setStatus(bookDTO.getStatus());
        bookEntity.setTitle(bookDTO.getTitle());
        bookEntity.setAuthorName(bookDTO.getAuthor());

        logger.info("End mapper.getBookDtoToBookEntityWithAuthor method {}", bookEntity);

        return bookEntity;
    }

}