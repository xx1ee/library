package com.xx1ee.repos;

import com.xx1ee.entity.Book;
import com.xx1ee.entity.PersonBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonBooksRepository extends JpaRepository<PersonBooks, Integer> {
    PersonBooks findByBook(Book book);
}
