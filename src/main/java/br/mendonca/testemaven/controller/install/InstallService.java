package br.mendonca.testemaven.controller.install;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InstallService {
    String url = "jdbc:postgresql://localhost:5432/Beulch";
    String username = "postgres";
    String password = "postgres";

    public void testConnection() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            if (connection != null) {
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            }
        }
    }

    public void createUserTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS user (id SERIAL PRIMARY KEY, name VARCHAR(100), email VARCHAR(100));";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    public void deleteUserTable() throws SQLException {
        String sql = "DROP TABLE IF EXISTS user;";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }
}
