package com.xx1ee.repos;

import com.xx1ee.model.Book;
import com.xx1ee.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class PersonRepository {
    private static Connection connection = null;
    @Autowired
    private BookRepository bookRepository;
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Person> findAll() {

        List<Person> personList = new ArrayList<>();
        try {
            // Подключение к базе данных PostgreSQL
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lib", "postgres", "English56");

            // Запрос на получение даты из таблицы
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, fio, birth FROM \"Person\"");


            // Обработка результата
            while (resultSet.next()) {
                personList.add(Person.builder()
                        .id(resultSet.getInt("id"))
                        .fio(resultSet.getString("fio"))
                        .birth(resultSet.getDate("birth"))
                        .build());
            }

            // Закрытие ресурсов
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Закрытие соединения с базой данных PostgreSQL
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personList;
    }
    public Person findById(Integer id) {
        Person person = new Person();
        try {
            // Подключение к базе данных PostgreSQL
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lib", "postgres", "English56");

            // Запрос на получение даты из таблицы
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, fio, birth FROM \"Person\" where id = " + id);


            // Обработка результата
            while (resultSet.next()) {
                person = (Person.builder()
                        .id(resultSet.getInt("id"))
                        .fio(resultSet.getString("fio"))
                        .birth(resultSet.getDate("birth"))
                        .build());
            }

            // Закрытие ресурсов
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Закрытие соединения с базой данных PostgreSQL
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return person;
    }
    public List<Book> findBookOfPerson(Integer id) {
        List<Book> bookList = new ArrayList<>();
        try {
            // Подключение к базе данных PostgreSQL
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lib", "postgres", "English56");

            // Запрос на получение даты из таблицы
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT person_id, book_id FROM \"PersonBooks\" where person_id = " + id);


            // Обработка результата
            while (resultSet.next()) {
                bookList.add(bookRepository.findById(resultSet.getInt("book_id")));
            }

            // Закрытие ресурсов
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Закрытие соединения с базой данных PostgreSQL
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bookList;
    }
    public void createPerson(Person person) {
        try {
            // Подключение к базе данных PostgreSQL
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lib", "postgres", "English56");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO \"Person\"(fio, birth) VALUES (?, ?)");
            preparedStatement.setString(1, person.getFio());
            preparedStatement.setDate(2, person.getBirth());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Закрытие соединения с базой данных PostgreSQL
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
