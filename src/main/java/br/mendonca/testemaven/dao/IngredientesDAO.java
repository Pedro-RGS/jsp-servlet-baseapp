package br.mendonca.testemaven.dao;

import br.mendonca.testemaven.model.entities.Ingredientes;
import br.mendonca.testemaven.services.dto.IngredientesDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientesDAO {

    public void registrar(Ingredientes ingredientes) throws ClassNotFoundException, SQLException {
        try (Connection conn = ConnectionPostgres.getConexao()) {
            conn.setAutoCommit(true);

            String sql = "INSERT INTO ingredientes (nome, descricao, categoria, quantidade, gramas, preco, disponivel) VALUES (?,?,?,?,?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, ingredientes.getNome());
                ps.setString(2, ingredientes.getDescricao());
                ps.setString(3, ingredientes.getCategoria());
                ps.setInt(4, ingredientes.getQuantidade());
                ps.setDouble(5, ingredientes.getGramas());
                ps.setDouble(6, ingredientes.getPreco());
                ps.setBoolean(7, ingredientes.isDisponivel());
                ps.execute();
            }
        }
    }

    public List<Ingredientes> listarTodosIngredientes() throws ClassNotFoundException, SQLException {
        List<Ingredientes> lista = new ArrayList<>();

        try (Connection conn = ConnectionPostgres.getConexao();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM ingredientes")) {

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

            ps.setString(1, id + "%");
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

    public List<IngredientesDTO> listarPaginado(int offset, int limit) throws SQLException, ClassNotFoundException {
        List<IngredientesDTO> ingredientes = new ArrayList<>();
        String sql = "SELECT * FROM ingredientes LIMIT ? OFFSET ?";

        try (Connection conn = ConnectionPostgres.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, limit);
            stmt.setInt(2, offset);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    IngredientesDTO ingrediente = new IngredientesDTO(
                            rs.getString("nome"),
                            rs.getString("descricao"),
                            rs.getString("categoria"),
                            rs.getInt("quantidade"),
                            rs.getInt("gramas"),
                            rs.getInt("preco"),
                            rs.getBoolean("disponivel")
                    );
                    ingredientes.add(ingrediente);
                }
            }
        }
        return ingredientes;
    }

    public int contarTotal() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM ingredientes";

        try (Connection conn = ConnectionPostgres.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
}
