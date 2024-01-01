package com.xx1ee.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "\"Person\"")
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer person_id;
    @NotEmpty(message = "Имя не должно быть пустым")
    String fio;
    @NotEmpty(message = "Дата не должна быть пустой")
    Date birth;
    @OneToMany(mappedBy = "person")
    private List<PersonBooks> bookList;
}
