package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        String host = System.getenv().getOrDefault("DB_HOST", "sem-mysql");
        String database = System.getenv().getOrDefault("DB_NAME", "semdb");
        String username = System.getenv().getOrDefault("DB_USER", "semuser");
        String password = System.getenv("DB_PASSWORD");

        if (password == null || password.isBlank()) {
            System.err.println("DB_PASSWORD environment variable is required.");
            System.exit(1);
        }

        String url = "jdbc:mysql://" + host + ":3306/" + database
                + "?useSSL=false&allowPublicKeyRetrieval=true";

        try (
                Connection connection =
                        DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT DATABASE()")
        ) {
            result.next();
            System.out.println(
                    "Connected to MySQL database: " + result.getString(1)
            );
        } catch (SQLException exception) {
            System.err.println(
                    "Database connection failed: " + exception.getMessage()
            );
            System.exit(1);
        }
    }
}