package com.library.libraryAPI.model.book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

@Entity
@Table(schema = "books detail entity")
public record BookEntity (@jakarta.persistence.Id @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id, String title, String author, String isbn, String publisher, String language, String genre, String format, String status) {
}
