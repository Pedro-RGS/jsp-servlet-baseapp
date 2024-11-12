package br.mendonca.testemaven.dao;

import br.mendonca.testemaven.model.entities.LivroDeReceitas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDeReceitasDAO {
    public void register(LivroDeReceitas livroDeReceitas) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("INSERT INTO livroDeReceitas (title, numberOfPages, isGood) values (?,?,?)");
        ps.setString(1, livroDeReceitas.getTitulo());
        ps.setInt(2, livroDeReceitas.getNumeroDePaginas());
        ps.setBoolean(3, livroDeReceitas.isEhBom());
        ps.execute();
        ps.close();
    }

    public List<LivroDeReceitas> listAllLivros() throws ClassNotFoundException, SQLException {
        ArrayList<LivroDeReceitas> lista = new ArrayList<>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM livroDeReceitas WHERE ativo = true");

        while (rs.next()) {
            LivroDeReceitas livro = new LivroDeReceitas();
            livro.setUUID(rs.getString("uuid"));
            livro.setTitulo(rs.getString("title"));
            livro.setNumeroDePaginas(rs.getInt("numberOfPages"));
            livro.setEhBom(rs.getBoolean("isGood"));

            lista.add(livro);
        }

        rs.close();

        return lista;
    }

    public LivroDeReceitas search(String titulo, int numeroDePaginas) throws ClassNotFoundException, SQLException {
        LivroDeReceitas livro = null;

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        // Apesar de qualquer SQL funcionar com Statement, a abordagem de usar Prepared Statement evita SQL Injection.
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM livroDeReceitas WHERE title = ? AND numberOfPages = ?");
        ps.setString(1, titulo);
        ps.setInt(2, numeroDePaginas);
        System.out.println(ps); // Exibe no console do Docker a query j� montada.

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            livro = new LivroDeReceitas();
            livro.setUUID(rs.getString("uuid"));
            livro.setTitulo(rs.getString("title"));
            livro.setNumeroDePaginas(rs.getInt("numberOfPages"));
            livro.setEhBom(rs.getBoolean("isGood"));
        }

        rs.close();

        return livro;
    }

    public List<LivroDeReceitas> search(String titulo) throws ClassNotFoundException, SQLException {
        ArrayList<LivroDeReceitas> lista = new ArrayList<>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        // Apesar de qualquer SQL funcionar com Statement, a abordagem de usar Prepared Statement evita SQL Injection.
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM livroDeReceitas WHERE ? LIKE LOWER(?) || LOWER(name) || '%'");
        ps.setString(1, titulo);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            LivroDeReceitas livro = new LivroDeReceitas();
            livro.setUUID(rs.getString("uuid"));
            livro.setTitulo(rs.getString("title"));
            livro.setNumeroDePaginas(rs.getInt("numberOfPages"));
            livro.setEhBom(rs.getBoolean("isGood"));

            lista.add(livro);
        }

        rs.close();

        return lista;
    }
}