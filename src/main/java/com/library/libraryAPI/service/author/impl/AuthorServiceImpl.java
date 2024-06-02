package com.library.libraryAPI.service.author.impl;

import com.library.libraryAPI.exceptions.impl.ResourceBadRequestException;
import com.library.libraryAPI.exceptions.impl.ResourceNotFoundException;
import com.library.libraryAPI.mapper.author.AuthorMapper;
import com.library.libraryAPI.model.author.dto.AuthorDTO;
import com.library.libraryAPI.model.author.entity.AuthorEntity;
import com.library.libraryAPI.repository.author.AuthorRepository;
import com.library.libraryAPI.service.author.IAuthorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements IAuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    private final static String AUTHOR_ALREADY_EXISTS = "Author already exists";

    private final static String AUTHOR_NOT_FOUND = "Author not found";

    @Override
    public void createAuthor(AuthorDTO  authorDTO) {
        AuthorEntity authorEntity = getAuthor(authorDTO.getNumberDocument());
        if ( authorEntity != null) {
            throw new ResourceBadRequestException(AUTHOR_ALREADY_EXISTS);
        }
        authorRepository.saveAndFlush(authorMapper.getAuthorDtoToEntity(authorDTO));
    }

    @Override
    public AuthorEntity getAuthor(Long numberDocument) {
        return authorRepository.findByNumberDocument(numberDocument).orElse(null);
    }

    @Override
    public AuthorEntity findAuthor(Long numberDocument) {
        return authorRepository.findByNumberDocument(numberDocument).orElseThrow(() -> new ResourceNotFoundException(AUTHOR_NOT_FOUND));
    }

    @Override
    public Page<AuthorDTO> listAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable).map(authorMapper::getAuthorEntityToAuthorDto);
    }
}