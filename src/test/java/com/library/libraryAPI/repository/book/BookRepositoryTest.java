package com.library.libraryAPI.repository.book;

import com.library.libraryAPI.model.book.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testFindBookEntityByIsbn() {
        BookEntity book = new BookEntity();
        book.setIsbn("1234567890");
        book.setTitle("Test Book");
        entityManager.persistAndFlush(book);

        BookEntity foundBook = bookRepository.findBookEntityByIsbn("1234567890");

        assertNotNull(foundBook);
        assertEquals(book.getIsbn(), foundBook.getIsbn());
    }

    @Test
    void testFindBookEntityByAuthorEntityNumberDocument() {
        Page<BookEntity> books = bookRepository.findBookByAuthorName("Gabriel garcia Marquez", PageRequest.of(0, 10));
        assertNotNull(books);
    }

}