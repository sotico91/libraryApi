package com.library.libraryAPI.service.book;

import com.library.libraryAPI.model.book.dto.BookDTO;
import com.library.libraryAPI.model.book.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService {
    BookDTO getBook(String isbn);
    BookEntity findBook(String isbn);
    BookDTO createBook(BookDTO bookDTO);
    Page<BookDTO> getBooksByAuthor(Long documentNumber, Pageable pageable);
    BookDTO updateBook(BookDTO bookDTO, Long documentNumber);
    Page<BookDTO> listBooks(Pageable pageable);
}
