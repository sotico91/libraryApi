package com.library.libraryAPI.model.author.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema (description = "Author details")
@Builder
public class AuthorDTO {

    @Schema(description = "author name", example = "Martin")
    private String name;

    @Schema(description = "author last name", example = "Garrison")
    private String lastName;

    @Schema(description = "number document", example = "1256663399")
    private Long numberDocument;

    @Schema(description = "date of birth", example = "12/05/1985")
    private String dateOfBirth;

    @Schema(description = "country nationality", example = "British")
    private String nationality;
}
