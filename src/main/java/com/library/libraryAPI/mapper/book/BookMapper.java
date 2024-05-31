package com.library.libraryAPI.mapper.book;

import com.library.libraryAPI.model.book.dto.BookDTO;
import com.library.libraryAPI.model.book.entity.BookEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookEntity getBookDtoToEntity(BookDTO bookDTO) {

        ModelMapper modelMapper = new ModelMapper();
        BookEntity bookEntity = modelMapper.map(bookDTO, BookEntity.class);
        return bookEntity;
    }

    public BookDTO getBookEntityToBookDTO(BookEntity bookEntity) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(bookEntity, BookDTO.class);
    }
}
