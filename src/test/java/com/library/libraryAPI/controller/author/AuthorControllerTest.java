package com.library.libraryAPI.controller.author;

import com.library.libraryAPI.model.author.dto.AuthorDTO;
import com.library.libraryAPI.model.author.dto.CustomPageAuthorDTO;
import com.library.libraryAPI.service.author.IAuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {

    @Mock
    private IAuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    @Test
    void testCreateAuthor() {
        AuthorDTO authorDTO = new AuthorDTO();

        doNothing().when(authorService).createAuthor(authorDTO);

        ResponseEntity<HttpStatus> result = authorController.createAuthor(authorDTO);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        verify(authorService, times(1)).createAuthor(authorDTO);
    }

    @Test
    void testListAuthors() {
        Pageable pageable = mock(Pageable.class);
        Page<AuthorDTO> page = new PageImpl<>(Collections.singletonList(new AuthorDTO()));

        when(authorService.listAuthors(pageable)).thenReturn(page);

        ResponseEntity<CustomPageAuthorDTO> result = authorController.listAuthors(pageable);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(page.getContent().size(), Objects.requireNonNull(result.getBody()).getContent().size());
        verify(authorService, times(1)).listAuthors(pageable);
    }

}