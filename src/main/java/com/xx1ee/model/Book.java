package com.xx1ee.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "\"Book\"")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer book_id;
    @NotEmpty(message = "Название не должно быть пустым")
    String name;
    @NotEmpty(message = "Автор не должен быть пустым")
    String author;
    @NotEmpty(message = "Год не должен быть пустым")
    Integer year;
}
