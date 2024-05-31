package com.library.libraryAPI.service;

import com.library.libraryAPI.model.book.dto.BookDTO;
import com.library.libraryAPI.model.book.entity.BookEntity;

public interface IBookService {

    BookDTO getBook(String isbn);
    BookDTO createBook(BookDTO bookDTO);
}
