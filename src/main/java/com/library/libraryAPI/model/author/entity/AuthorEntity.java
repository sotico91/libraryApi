package com.library.libraryAPI.model.author.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "author")
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String lastName;
    private Long numberDocument;
    private String dateOfBirth;
    private String nationality;

}
