package com.xx1ee.repos;

import com.xx1ee.config.SpringConfig;
import com.xx1ee.model.Book;
import com.xx1ee.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private static Connection connection = null;
    @Autowired
    private PersonRepository personRepository;
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Book> findAll() {

        List<Book> bookList = new ArrayList<>();
        try {
            // Подключение к базе данных PostgreSQL
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lib", "postgres", "English56");

            // Запрос на получение даты из таблицы
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, name, author, year FROM \"Book\"");


            // Обработка результата
            while (resultSet.next()) {
                bookList.add(Book.builder()
                                .id(resultSet.getInt("id"))
                                .year(resultSet.getInt("year"))
                                .author(resultSet.getString("author"))
                                .name(resultSet.getString("name"))
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
        return bookList;
    }
    public Book findById(Integer id) {
        Book book = new Book();
        try {
            // Подключение к базе данных PostgreSQL
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lib", "postgres", "English56");

            // Запрос на получение даты из таблицы
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, name, author, year FROM \"Book\" where id = " + id);


            // Обработка результата
            while (resultSet.next()) {
                book = Book.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .author(resultSet.getString("author"))
                        .year(resultSet.getInt("year"))
                        .build();
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
        return book;
    }
    public Person findPersonOfBook(Integer id) {
        Person person = new Person();
        try {
            // Подключение к базе данных PostgreSQL
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lib", "postgres", "English56");

            // Запрос на получение даты из таблицы
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT person_id, book_id FROM \"PersonBooks\" where book_id = " + id);
            while (resultSet.next()) {
                person = (personRepository.findById(resultSet.getInt("person_id")));
            }
            // Обработка результата

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
    public void createBook(Book book) {
        try {
            // Подключение к базе данных PostgreSQL
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lib", "postgres", "English56");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO \"Book\"(name, author, year) VALUES (?, ?, ?)");
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getYear());
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
    public void releaseBook(Book book) {
        try {
            // Подключение к базе данных PostgreSQL
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lib", "postgres", "English56");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM \"PersonBooks\" WHERE book_id = ?");
            preparedStatement.setInt(1, book.getId());
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
    public void createPersonBook(Integer bookId, Integer personId) {
        try {
            // Подключение к базе данных PostgreSQL
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lib", "postgres", "English56");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO \"PersonBooks\" (person_id, book_id) VALUES (?, ?)");
            preparedStatement.setInt(1, personId);
            preparedStatement.setInt(2, bookId);
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
