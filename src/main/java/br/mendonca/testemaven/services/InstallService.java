package br.mendonca.testemaven.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.mendonca.testemaven.dao.ConnectionPostgres;

public class InstallService {

	private void statement(String sql) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);

		Statement st = conn.createStatement();
		st.executeUpdate(sql);
		st.close();
	}

	public void testConnection() throws ClassNotFoundException, SQLException {
		ConnectionPostgres.getConexao();
	}

	public void deleteUserTable() throws ClassNotFoundException, SQLException {
		statement("DROP TABLE users CASCADE");
	}

	public void deleteChefTable() throws ClassNotFoundException, SQLException {
		statement("DROP TABLE IF EXISTS chef");
	}

	public void deleteLivroTable() throws SQLException, ClassNotFoundException {
		statement("DROP TABLE IF EXISTS livroDeReceitas");
	}

	public void deleteIngredienteTable() throws SQLException, ClassNotFoundException {
		statement("DROP TABLE IF EXISTS ingredientes");
	}

	public void deleteSeguidoresTable() throws SQLException, ClassNotFoundException {
		statement("DROP TABLE IF EXISTS seguidores");
	}

	public void createUserTable() throws ClassNotFoundException, SQLException {
		statement("CREATE TABLE users ("
				+ "    uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
				+ "    name VARCHAR(255) NOT NULL,"
				+ "    email VARCHAR(255) NOT NULL,"
				+ "    password VARCHAR(255) NOT NULL)");
	}

	public void createChefTable() throws ClassNotFoundException, SQLException {
		statement("CREATE TABLE chef ("
				+ "    uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
				+ "    nome VARCHAR(255) NOT NULL,"
				+ "    idade INT NOT NULL,"
				+ "    ativo BOOLEAN NOT NULL,"
				+ "    visivel BOOLEAN NOT NULL);");
	}

	public void createLivroTable() throws ClassNotFoundException, SQLException {
		statement("CREATE TABLE livroDeReceitas ("
				+ "    uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
				+ "    title VARCHAR(255) NOT NULL,"
				+ "    numberOfPages INT NOT NULL,"
				+ "    isGood BOOLEAN NOT NULL,"
				+ "    ativo BOOLEAN DEFAULT TRUE)");
	}

	public void povoarChef() throws ClassNotFoundException, SQLException{
		statement("INSERT INTO chef (nome, idade, ativo, visivel) VALUES"
				+ "    ('Victoria', 32, FALSE, TRUE),"
				+ "    ('John', 35, FALSE, FALSE),"
				+ "    ('Kimiko', 28, TRUE, TRUE),"
				+ "    ('Annie', 22, TRUE, FALSE),"
				+ "    ('Stan', 67, FALSE, FALSE),"
				+ "    ('Frenchie', 33, TRUE, TRUE),"
				+ "    ('MM', 42, TRUE, TRUE);");
	}

	public void povoarLivro() throws ClassNotFoundException, SQLException{
		statement("INSERT INTO livroDeReceitas (title, numberOfPages, isGood, ativo) VALUES"
				+ "    ('Dom Quixote', 863, TRUE, TRUE),"
				+ "    ('O Senhor dos Anéis', 1216, TRUE, TRUE),"
				+ "    ('1984', 328, TRUE, TRUE),"
				+ "    ('O Pequeno Príncipe', 96, TRUE, TRUE),"
				+ "    ('Moby Dick', 635, FALSE, TRUE),"
				+ "    ('Drácula', 418, TRUE, FALSE),"
				+ "    ('O Código Da Vinci', 689, FALSE, TRUE);");
	}

	public void createIngredienteTable() throws ClassNotFoundException, SQLException {
		statement("CREATE TABLE ingredientes ("
				+ "    uuid UUID DEFAULT gen_random_uuid() NOT NULL PRIMARY KEY,"
				+ "    nome VARCHAR(255) NOT NULL,"
				+ "    descricao VARCHAR(255) NOT NULL,"
				+ "    categoria VARCHAR(255) NOT NULL,"
				+ "    quantidade INT NOT NULL,"
				+ "    gramas INT NOT NULL,"
				+ "    preco INT NOT NULL,"
				+ "    disponivel BOOLEAN NOT NULL,"
				+ "    oculto BOOLEAN DEFAULT FALSE" // Nova coluna com valor padrão como FALSE
				+ ");");

		// Inserindo dados com a nova estrutura
		statement("INSERT INTO ingredientes (nome, descricao, categoria, quantidade, gramas, preco, disponivel, oculto) VALUES "
				+ "('Ingrediente1', 'Descrição1', 'Categoria1', 1, 1, 1, true, false),"
				+ "('Ingrediente2', 'Descrição2', 'Categoria2', 1, 1, 1, true, false),"
				+ "('Ingrediente3', 'Descrição3', 'Categoria3', 1, 1, 1, true, false),"
				+ "('Ingrediente4', 'Descrição4', 'Categoria4', 1, 1, 1, true, false),"
				+ "('Ingrediente5', 'Descrição5', 'Categoria5', 1, 1, 1, true, false),"
				+ "('Ingrediente6', 'Descrição6', 'Categoria6', 1, 1, 1, true, false),"
				+ "('Ingrediente7', 'Descrição7', 'Categoria7', 1, 1, 1, true, false);"
		);
	}

	public void createSeguidoresTable() throws ClassNotFoundException, SQLException{
		statement("CREATE TABLE seguidores (" +
				"    user_id UUID REFERENCES users(uuid)," +
				"    followed_id UUID REFERENCES users(uuid)," +
				"    PRIMARY KEY (user_id, followed_id)" +
				");"
		);
	}
}
