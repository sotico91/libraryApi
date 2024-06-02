package com.library.libraryAPI.repository.author;

import com.library.libraryAPI.model.author.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long>{

    Optional< AuthorEntity> findByNumberDocument(Long numberDocument);
}
