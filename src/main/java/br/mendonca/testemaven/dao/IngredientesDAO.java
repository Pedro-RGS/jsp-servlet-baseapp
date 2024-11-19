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

            String sql = "INSERT INTO ingredientes (nome, descricao, categoria, quantidade, gramas, preco, disponivel,oculto) VALUES (?,?,?,?,?,?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, ingredientes.getNome());
                ps.setString(2, ingredientes.getDescricao());
                ps.setString(3, ingredientes.getCategoria());
                ps.setInt(4, ingredientes.getQuantidade());
                ps.setDouble(5, ingredientes.getGramas());
                ps.setDouble(6, ingredientes.getPreco());
                ps.setBoolean(7, ingredientes.isDisponivel());
                ps.setBoolean(8, ingredientes.isOculto());
                ps.execute();
            }
        }
    }

    public List<Ingredientes> listarTodosIngredientes() throws ClassNotFoundException, SQLException {
        List<Ingredientes> lista = new ArrayList<>();

        String sql = "SELECT * FROM ingredientes WHERE oculto = false"; // Filtra ingredientes que não estão ocultos
        try (Connection conn = ConnectionPostgres.getConexao();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Ingredientes ingredientes = new Ingredientes();
                ingredientes.setNome(rs.getString("nome"));
                ingredientes.setDescricao(rs.getString("descricao"));
                ingredientes.setCategoria(rs.getString("categoria"));
                ingredientes.setQuantidade(rs.getInt("quantidade"));
                ingredientes.setGramas(rs.getInt("gramas"));
                ingredientes.setPreco(rs.getInt("preco"));
                ingredientes.setDisponivel(rs.getBoolean("disponivel"));
                ingredientes.setOculto(rs.getBoolean("oculto"));

                lista.add(ingredientes);
            }
        }
        return lista;
    }

    public List<IngredientesDTO> listarPaginado(int offset, int limit) throws SQLException, ClassNotFoundException {
        List<IngredientesDTO> ingredientes = new ArrayList<>();
        String sql = "SELECT * FROM ingredientes WHERE oculto = false LIMIT ? OFFSET ?";

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
                            rs.getBoolean("disponivel"),
                            rs.getBoolean("oculto")
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

    public List<Ingredientes> listarIngredientesOcultos() throws ClassNotFoundException, SQLException {
        ArrayList<Ingredientes> lista = new ArrayList<>();
        String sql = "SELECT * FROM ingredientes WHERE oculto = true";

        try (Connection conn = ConnectionPostgres.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Ingredientes ingredientes = new Ingredientes();
                ingredientes.setId(rs.getString("uuid"));
                ingredientes.setNome(rs.getString("nome"));
                ingredientes.setDescricao(rs.getString("descricao"));
                ingredientes.setCategoria(rs.getString("categoria"));
                ingredientes.setQuantidade(rs.getInt("quantidade"));
                ingredientes.setGramas(rs.getInt("gramas"));
                ingredientes.setPreco(rs.getInt("preco"));
                ingredientes.setDisponivel(rs.getBoolean("disponivel"));
                ingredientes.setOculto(rs.getBoolean("oculto"));
                lista.add(ingredientes);
            }
        }
        return lista;
    }

    public void excluir(String nome) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("UPDATE ingredientes SET oculto = true WHERE nome = ?");
        ps.setString(1, nome);

        int rs = ps.executeUpdate();
    }


}
