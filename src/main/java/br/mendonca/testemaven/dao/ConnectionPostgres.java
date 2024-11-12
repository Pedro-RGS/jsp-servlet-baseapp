package br.mendonca.testemaven.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class ConnectionPostgres {

	private static Connection connection = null;

	public static Connection getConexao() throws SQLException, ClassNotFoundException {
		if (connection == null || connection.isClosed()) {
			Map<String, String> env = System.getenv();
			String DB_USER = env.get("DB_USER");
			String DB_PASS = env.get("DB_PASS");
			String DB_HOST = env.get("DB_HOST");
			String DB_NAME = env.get("DB_NAME");

			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://" + DB_HOST + ":5432/" + DB_NAME, DB_USER, DB_PASS);
		}
		return connection;
	}

}
