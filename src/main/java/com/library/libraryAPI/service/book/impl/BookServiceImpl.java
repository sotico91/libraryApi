package com.library.libraryAPI.service.book.impl;

import com.library.libraryAPI.mapper.book.BookMapper;
import com.library.libraryAPI.model.author.entity.AuthorEntity;
import com.library.libraryAPI.model.book.dto.BookDTO;
import com.library.libraryAPI.model.book.entity.BookEntity;
import com.library.libraryAPI.repository.book.BookRepository;
import com.library.libraryAPI.service.author.IAuthorService;
import com.library.libraryAPI.service.book.IBookService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements IBookService {

    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;

    private final IAuthorService authorService;

    private final BookMapper bookMapper;

    private final static String BOOK_NOT_FOUND = "Book not found";

    @Override
    public BookDTO getBook(String isbn) {

        logger.info("Start getBook method {}",isbn);

        BookEntity bookEntity = bookRepository.findBookEntityByIsbn(isbn);

            if(bookEntity != null){

                logger.info("End getBook method");
                return bookMapper.getBookEntityToBookDTOByAuthor(bookEntity);
            }
            throw new RuntimeException(BOOK_NOT_FOUND);
    }

    @Override
    public BookEntity findBook(String isbn) {

        logger.info("Start findBook method {}", isbn);

        BookEntity bookEntity = bookRepository.findBookEntityByIsbn(isbn);
        if(bookEntity != null){

            logger.info("End findBook method");
            return bookEntity;
        }
        throw new RuntimeException(BOOK_NOT_FOUND);
    }


    @Override
    public BookDTO createBook(BookDTO bookDTO) {

        logger.info("Start createBook method {}",bookDTO.getIsbn());

        return bookMapper.getBookEntityToBookDTO(bookRepository.save(bookMapper.getBookDtoToEntity(bookDTO)));
    }

    @Override
    public Page<BookDTO> getBooksByAuthor(Long documentNumber, Pageable pageable) {

        logger.info("Start getBooksByAuthor method {}",documentNumber);

        return bookRepository.findBookEntityByAuthorEntityNumberDocument(documentNumber, pageable)
                .map(bookMapper::getBookEntityToBookDTOByAuthor);
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO, Long documentNumber) {

        logger.info("Start updateBook method {}",bookDTO.getIsbn());

        AuthorEntity authorEntity = authorService.findAuthor(documentNumber);
        BookEntity bookEntityBefore = findBook(bookDTO.getIsbn());
        BookEntity bookEntityAfter = bookMapper.getBookDtoToBookEntityWithAuthor(bookEntityBefore, bookDTO, authorEntity);

        logger.info("End updateBook method");
        return bookMapper.getBookEntityToBookDTOByAuthor(bookRepository.saveAndFlush(bookEntityAfter));
    }

    @Override
    public Page<BookDTO> listBooks(Pageable pageable) {

            logger.info("Start listBooks method");
            return bookRepository.findAll(pageable).map(bookMapper::getBookEntityToBookDTOByAuthor);
    }
}