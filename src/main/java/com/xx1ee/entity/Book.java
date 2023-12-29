package com.xx1ee.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

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
    @ManyToMany
    @JoinTable(name = "\"PersonBooks\"", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> personList;
}
