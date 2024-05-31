package com.library.libraryAPI.controller.book;

import com.library.libraryAPI.model.book.dto.BookDTO;
import com.library.libraryAPI.service.IBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BookController {

    private final IBookService bookService;

    @Operation(summary = "Get book by ISBN")
    @ApiResponse(responseCode = "200", description = "Book found")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Bad request")

    @GetMapping("/book/{isbn}")
    public ResponseEntity<BookDTO> getBook(String isbn){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBook(isbn));
    }

    @Operation(summary = "Create a new book")
    @ApiResponse(responseCode = "201", description = "Book created")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Bad request")

    @PostMapping("/book")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(bookDTO));
    }
}
