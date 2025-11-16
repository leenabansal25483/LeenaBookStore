package com.bookhub.dao;

import java.sql.*;
import java.util.*;
import com.bookhub.model.Book; // ✅ Make sure you have this model class

public class BookDAO {

    // ✅ Database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bookhub_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "root";

    // ✅ SQL queries
    private static final String INSERT_BOOK_SQL = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_BOOKS = "SELECT * FROM books";

    // ✅ Load MySQL JDBC Driver once
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL JDBC Driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Failed to load MySQL JDBC Driver");
            e.printStackTrace();
        }
    }

    // ✅ Method to get a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
    }

    // ✅ Method to insert a new book
    public void insertBook(Book book) {
        try (Connection connection = getConnection()) {
            if (connection == null) {
                throw new SQLException("Connection is null — failed to connect to database.");
            }
            try (PreparedStatement ps = connection.prepareStatement(INSERT_BOOK_SQL)) {
                ps.setString(1, book.getTitle());
                ps.setString(2, book.getAuthor());
                ps.setDouble(3, book.getPrice());
                ps.executeUpdate();
                System.out.println("✅ Book inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to insert book: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ✅ Method to fetch all books
    public List<Book> selectAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = getConnection()) {
            if (connection == null) {
                throw new SQLException("Connection is null — failed to connect to database.");
            }
            try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_BOOKS);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Book b = new Book();
                    b.setBookId(rs.getInt("id"));
                    b.setTitle(rs.getString("title"));
                    b.setAuthor(rs.getString("author"));
                    b.setPrice(rs.getDouble("price"));
                    books.add(b);
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to fetch books: " + e.getMessage());
            e.printStackTrace();
        }
        return books;
    }
}
