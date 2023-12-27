package com.xx1ee.model;

import com.xx1ee.classes.PersonBooksPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "\"PersonBooks\"")
@Entity
public class PersonBooks {
    @EmbeddedId
    PersonBooksPK personBooksPK;
}
