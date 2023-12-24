package com.xx1ee.model;

import lombok.*;
import org.springframework.context.annotation.Bean;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Book {
    Integer id;
    String name;
    String author;
    Integer year;
}
