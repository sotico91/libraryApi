package com.library.libraryAPI.service.impl;

import com.library.libraryAPI.mapper.book.BookMapper;
import com.library.libraryAPI.model.book.dto.BookDTO;
import com.library.libraryAPI.model.book.entity.BookEntity;
import com.library.libraryAPI.repository.BookRepository;
import com.library.libraryAPI.service.IBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Override
    public BookDTO getBook(String isbn) {
        BookEntity bookEntity = bookRepository.findBookEntityByIsbn(isbn);
            if(bookEntity != null){
                return bookMapper.getBookEntityToBookDTO(bookEntity);
            }
            throw new RuntimeException("Book not found");
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        BookEntity bookEntity = bookRepository.save(bookMapper.getBookDtoToEntity(bookDTO));
        return bookMapper.getBookEntityToBookDTO(bookEntity);
    }


    
}