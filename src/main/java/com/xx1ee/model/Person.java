package com.xx1ee.model;

import lombok.*;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Person {
    Integer id;
    String fio;
    Date birth;
}
