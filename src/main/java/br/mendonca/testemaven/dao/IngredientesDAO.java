package br.mendonca.testemaven.dao;

import br.mendonca.testemaven.model.entities.Ingredientes;
import br.mendonca.testemaven.model.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientesDAO {
    public void registrar(Ingredientes ingredientes) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("INSERT INTO ingredientes (nome, descricao, categoria, quantidade, gramas, preco, disponivel ) values (?,?,?,?,?,?,?)");
        ps.setString(1, ingredientes.getNome());
        ps.setString(2, ingredientes.getDescricao());
        ps.setString(3, ingredientes.getCategoria());
        ps.setInt(4, ingredientes.getQuantidade());
        ps.setDouble(5, ingredientes.getGramas());
        ps.setDouble(6, ingredientes.getPreco());
        ps.setBoolean(7, ingredientes.isDisponivel());
        ps.execute();
        ps.close();
    }

    public List<Ingredientes> listarTodosIngredientes() throws ClassNotFoundException, SQLException {
        ArrayList<Ingredientes> lista = new ArrayList<Ingredientes>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM ingredientes");

        while (rs.next()) {
            Ingredientes ingredientes = new Ingredientes();
            ingredientes.setNome(rs.getString("nome"));
            ingredientes.setDescricao(rs.getString("descricao"));
            ingredientes.setCategoria(rs.getString("categoria"));
            ingredientes.setQuantidade(rs.getInt("quantidade"));
            ingredientes.setGramas(rs.getInt("gramas"));
            ingredientes.setPreco(rs.getInt("preco"));
            ingredientes.setDisponivel(rs.getBoolean("disponivel"));

            lista.add(ingredientes);
        }

        rs.close();

        return lista;
    }

    public Ingredientes procurarPorId(String id) throws ClassNotFoundException, SQLException {
        Ingredientes ingredientes = null;
        String sql = "SELECT * FROM ingredientes WHERE id = ?";

        try (Connection conn = ConnectionPostgres.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ingredientes = new Ingredientes();
                    ingredientes.setNome(rs.getString("nome"));
                    ingredientes.setDescricao(rs.getString("descricao"));
                    ingredientes.setCategoria(rs.getString("categoria"));
                    ingredientes.setQuantidade(rs.getInt("quantidade"));
                    ingredientes.setGramas(rs.getInt("gramas"));
                    ingredientes.setPreco(rs.getInt("preco"));
                    ingredientes.setDisponivel(rs.getBoolean("disponivel"));
                }
            }
        }
        return ingredientes;
    }

    public List<Ingredientes> procurarIngredientesPorId(String id) throws ClassNotFoundException, SQLException {
        List<Ingredientes> lista = new ArrayList<>();
        String sql = "SELECT * FROM ingredientes WHERE id LIKE ?";

        try (Connection conn = ConnectionPostgres.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1,id+ "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Ingredientes ingredientes = new Ingredientes();
                    ingredientes.setNome(rs.getString("nome"));
                    ingredientes.setDescricao(rs.getString("descricao"));
                    ingredientes.setCategoria(rs.getString("categoria"));
                    ingredientes.setQuantidade(rs.getInt("quantidade"));
                    ingredientes.setGramas(rs.getInt("gramas"));
                    ingredientes.setPreco(rs.getInt("preco"));
                    ingredientes.setDisponivel(rs.getBoolean("disponivel"));
                    lista.add(ingredientes);
                }
            }
        }
        return lista;
    }
}