package com.xx1ee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "\"PersonBooks\"")
@Entity
public class PersonBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    private LocalDate date_of_taking;
    @Transient
    private Boolean overdue;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="person_id")
    private Person person;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="book_id")
    private Book book;

    public PersonBooks(Book book, Person person, LocalDate date_of_taking) {
        this.book = book;
        this.person = person;
        this.date_of_taking = date_of_taking;
    }
}
