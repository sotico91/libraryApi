package com.library.libraryAPI.repository.author;

import com.library.libraryAPI.model.author.entity.AuthorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
class AuthorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void testFindByNumberDocument() {
        AuthorEntity author = AuthorEntity.builder()
                .name("Martin")
                .lastName("Garrison")
                .numberDocument(1256663399L)
                .dateOfBirth("12/05/1985")
                .nationality("British")
                .build();

        entityManager.persistAndFlush(author);

        Optional<AuthorEntity> foundAuthor = authorRepository.findByNumberDocument(1256663399L);

        assertTrue(foundAuthor.isPresent());
        assertEquals(author.getNumberDocument(), foundAuthor.get().getNumberDocument());
    }

}