package com.library.libraryAPI.controller.book;

import com.library.libraryAPI.model.book.dto.BookDTO;
import com.library.libraryAPI.model.book.dto.CustomPageBookDTO;
import com.library.libraryAPI.model.generalMessage.MessageBadRequestDTO;
import com.library.libraryAPI.model.generalMessage.MessageInternalErrorDTO;
import com.library.libraryAPI.service.book.IBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/library")
public class BookController {

    private final static Logger logger = Logger.getLogger(BookController.class.getName());

    private final IBookService bookService;

    @Operation(summary = "Book found by ISBN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageInternalErrorDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageBadRequestDTO.class))})
    })

    @GetMapping("/book")
    public ResponseEntity<BookDTO> getBook(@RequestParam String isbn){

        logger.info(" Start method get book by ISBN");

        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBook(isbn));
    }


    @Operation(summary = "list all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list books successful",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageInternalErrorDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageBadRequestDTO.class))})
    })

    @GetMapping("/book/listBooks")
    public ResponseEntity<CustomPageBookDTO> listBooks(Pageable pageable){

        logger.info(" Start method list all books");

        Page<BookDTO> page = bookService.listBooks(pageable);

        CustomPageBookDTO response = CustomPageBookDTO.builder()
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .size(page.getSize())
                .content(page.getContent())
                .build();

        logger.info(" End method list all books");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @Operation(summary = "Book founds by author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book found by author",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDTO[].class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageInternalErrorDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageBadRequestDTO.class))})
    })

    @GetMapping("/book/author")
    public ResponseEntity<CustomPageBookDTO> getBookByAuthor(@RequestParam Long documentNumber, Pageable pageable){

        logger.info(" Start method get book by author");

        Page<BookDTO> page =bookService.getBooksByAuthor(documentNumber,pageable);
        CustomPageBookDTO response = CustomPageBookDTO.builder()
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .size(page.getSize())
                .content(page.getContent())
                .build();

        logger.info(" End method get book by author");

       return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "create a new book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created book successful",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageInternalErrorDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageBadRequestDTO.class))})
    })

    @PostMapping("/book")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO){

        logger.info(" Start method creating a new book");

        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(bookDTO));
    }

    @Operation(summary = "update a book by author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update book successful",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageInternalErrorDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageBadRequestDTO.class))})
    })

    @PutMapping("/book/author")
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO, @RequestParam Long documentNumber){

        logger.info(" Start method updating a book");

        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(bookDTO, documentNumber));
    }
}