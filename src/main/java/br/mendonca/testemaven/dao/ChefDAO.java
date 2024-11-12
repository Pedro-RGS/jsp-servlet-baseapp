package br.mendonca.testemaven.dao;


import br.mendonca.testemaven.model.entities.Chef;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChefDAO {

    public void register(Chef chef) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("INSERT INTO chef (nome, idade, ativo) values (?,?,?)");
        ps.setString(1, chef.getNome());
        ps.setInt(2, chef.getIdade());
        ps.setBoolean(3, chef.getAtivo());
        ps.execute();
        ps.close();
    }

    public List<Chef> listAllChefs() throws ClassNotFoundException, SQLException {
        ArrayList<Chef> lista = new ArrayList<Chef>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM chef");

        while (rs.next()) {
            Chef chef = new Chef();
            chef.setUuid(rs.getString("uuid"));
            chef.setNome(rs.getString("nome"));
            chef.setIdade(rs.getInt("idade"));
            chef.setAtivo(rs.getBoolean("ativo"));

            lista.add(chef);
        }

        rs.close();

        return lista;
    }

    public Chef search(String nome, Integer idade) throws ClassNotFoundException, SQLException {
        Chef chef = null;

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        // Apesar de qualquer SQL funcionar com Statement, a abordagem de usar Prepared Statement evita SQL Injection.
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM chef WHERE nome = ? AND idade = ?");
        ps.setString(1, nome);
        ps.setInt(2, idade);
        System.out.println(ps); // Exibe no console do Docker a query j� montada.

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {

            chef = new Chef();
            chef.setUuid(rs.getString("uuid"));
            chef.setNome(rs.getString("nome"));
            chef.setIdade(rs.getInt("idade"));
            chef.setAtivo(rs.getBoolean("ativo"));
        }

        rs.close();

        return chef;
    }

    // TODO: Não testado
    public List<Chef> search(String name) throws ClassNotFoundException, SQLException {
        ArrayList<Chef> lista = new ArrayList<Chef>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        // Apesar de qualquer SQL funcionar com Statement, a abordagem de usar Prepared Statement evita SQL Injection.
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM chef WHERE ? LIKE LOWER(?) || LOWER(name) || '%'");
        ps.setString(1, name);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            Chef chef = new Chef();
            chef.setUuid(rs.getString("uuid"));
            chef.setNome(rs.getString("nome"));
            chef.setIdade(rs.getInt("idade"));
            chef.setAtivo(rs.getBoolean("ativo"));

            lista.add(chef);
        }

        rs.close();

        return lista;
    }
}
