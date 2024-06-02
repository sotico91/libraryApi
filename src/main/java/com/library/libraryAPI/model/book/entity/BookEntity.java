package com.library.libraryAPI.model.book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books_detail_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String isbn;
    private String publisher;
    private String language;
    private String genre;
    private String format;
    private String status;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private com.library.libraryAPI.model.author.entity.AuthorEntity authorEntity;
}