package com.library.libraryAPI.repository;

import com.library.libraryAPI.model.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    BookEntity findBookEntityByIsbn(String isbn);
}
