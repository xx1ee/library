package com.xx1ee.classes;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class PersonBooksPK implements Serializable {
    private int person_id;
    private int book_id;
}
