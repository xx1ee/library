package com.xx1ee.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;
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
    Integer id;
    @NotEmpty(message = "Имя не должно быть пустым")
    String fio;
    @NotEmpty(message = "Дата не должна быть пустой")
    Date birth;
}
