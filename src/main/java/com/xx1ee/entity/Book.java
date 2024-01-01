package com.xx1ee.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "\"Book\"")
@Entity
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

    @OneToMany(mappedBy = "book")
    private List<PersonBooks> personList;
}
