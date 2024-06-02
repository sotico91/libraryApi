package com.library.libraryAPI.controller.author;

import com.library.libraryAPI.model.author.dto.AuthorDTO;
import com.library.libraryAPI.model.author.dto.CustomPageAuthorDTO;
import com.library.libraryAPI.model.generalMessage.MessageBadRequestDTO;
import com.library.libraryAPI.model.generalMessage.MessageInternalErrorDTO;
import com.library.libraryAPI.service.author.IAuthorService;
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
@RequestMapping("/api/v1")
public class AuthorController {

    private final static Logger logger = Logger.getLogger(AuthorController.class.getName());

    private final IAuthorService  authorService;

    @Operation(summary = "Create a new Author")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "created author successful",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AuthorDTO.class))}),
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MessageInternalErrorDTO.class))}),
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MessageBadRequestDTO.class))})
    })

    @PostMapping("/author")
    public ResponseEntity<HttpStatus> createAuthor(@RequestBody AuthorDTO authorDTO){
        logger.info(" Start method creating a new author");

        authorService.createAuthor(authorDTO);

        logger.info(" End method creating a new author");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @Operation(summary = "List all authors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list authors successful",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthorDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageInternalErrorDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageBadRequestDTO.class))})
    })
    @GetMapping("/authors")
    public ResponseEntity<CustomPageAuthorDTO> listAuthors(Pageable pageable){

        logger.info(" Start method list all authors");

        Page<AuthorDTO> page = authorService.listAuthors(pageable);

        CustomPageAuthorDTO customPageAuthorDTO = CustomPageAuthorDTO.builder()
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .size(page.getSize())
                .content(page.getContent())
                .build();

        logger.info(" End method list all authors");
        return ResponseEntity.status(HttpStatus.OK).body(customPageAuthorDTO);
    }
}
