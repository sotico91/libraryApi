package com.library.libraryAPI.model.author.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Author page details")
@Builder
public class CustomPageAuthorDTO {

    @Schema(description = "total elements")
    private long totalElements;
    @Schema(description = "total pages")
    private int totalPages;
    @Schema(description = "current page")
    private int size;
    @Schema(description = "list of authors")
    private List<AuthorDTO> content;
}
