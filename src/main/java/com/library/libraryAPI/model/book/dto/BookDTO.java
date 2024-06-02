package com.library.libraryAPI.model.book.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Book details")
@Builder
public class BookDTO {
    @Schema(description = "Book title")
    private String title;

    @Schema(description = "author of the book")
    private String author;

    @Schema(description = "isbn of the book")
    private String isbn;

    @Schema(description = "publisher of the book")
    private String publisher;

    @Schema(description = "language of the book")
    private String language;

    @Schema(description = "genre of the book")
    private String genre;

    @Schema(description = "format of the book")
    private String format;

    @Schema(description = "status of the book")
    private String status;
}