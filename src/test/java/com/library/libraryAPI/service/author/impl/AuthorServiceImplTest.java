package com.library.libraryAPI.service.author.impl;

import com.library.libraryAPI.exceptions.impl.ResourceBadRequestException;
import com.library.libraryAPI.exceptions.impl.ResourceNotFoundException;
import com.library.libraryAPI.mapper.author.AuthorMapper;
import com.library.libraryAPI.model.author.dto.AuthorDTO;
import com.library.libraryAPI.model.author.entity.AuthorEntity;
import com.library.libraryAPI.repository.author.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    void testCreateAuthor() {

        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setNumberDocument(123456789L);
        authorEntity.setName("John");
        authorEntity.setLastName("Doe");


        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setNumberDocument(123456789L);

        when(authorRepository.findByNumberDocument(authorDTO.getNumberDocument())).thenReturn(Optional.empty());
        when(authorMapper.getAuthorDtoToEntity(authorDTO)).thenReturn(authorEntity);
        authorService.createAuthor(authorDTO);

        verify(authorRepository, times(1)).saveAndFlush(authorEntity);
    }

    @Test
    void testCreateAuthorAlreadyExists() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setNumberDocument(123456789L);

        when(authorRepository.findByNumberDocument(authorDTO.getNumberDocument())).thenReturn(Optional.of(new AuthorEntity()));

        assertThrows(ResourceBadRequestException.class, () -> authorService.createAuthor(authorDTO));
    }

    @Test
    void testGetAuthor() {
        Long numberDocument = 123456789L;

        authorService.getAuthor(numberDocument);

        verify(authorRepository, times(1)).findByNumberDocument(numberDocument);
    }

    @Test
    void testFindAuthor() {
        Long numberDocument = 123456789L;

        when(authorRepository.findByNumberDocument(numberDocument)).thenReturn(Optional.of(new AuthorEntity()));

        authorService.findAuthor(numberDocument);

        verify(authorRepository, times(1)).findByNumberDocument(numberDocument);
    }

    @Test
    void testFindAuthorNotFound() {
        Long numberDocument = 123456789L;

        when(authorRepository.findByNumberDocument(numberDocument)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> authorService.findAuthor(numberDocument));
    }

    @Test
    void testListAuthors() {
        Pageable pageable = mock(Pageable.class);
        Page<AuthorEntity> page = new PageImpl<>(Collections.singletonList(new AuthorEntity()));

        when(authorRepository.findAll(pageable)).thenReturn(page);

        authorService.listAuthors(pageable);

        verify(authorRepository, times(1)).findAll(pageable);
        verify(authorMapper, times(1)).getAuthorEntityToAuthorDto(any(AuthorEntity.class));
    }
}