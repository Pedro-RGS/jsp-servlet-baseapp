package br.mendonca.testemaven.services;

import br.mendonca.testemaven.dao.IngredientesDAO;
import br.mendonca.testemaven.model.entities.Ingredientes;
import br.mendonca.testemaven.services.dto.IngredientesDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientesService {

    public void registar(String nome, String descricao, String categoria, int quantidade, int gramas, int preco, boolean disponivel) throws ClassNotFoundException, SQLException {
        IngredientesDAO ingredientesDAO = new IngredientesDAO();

        Ingredientes ingredientesBanco = new Ingredientes();
        ingredientesBanco.setNome(nome);
        ingredientesBanco.setDescricao(descricao);
        ingredientesBanco.setCategoria(categoria);
        ingredientesBanco.setQuantidade(quantidade);
        ingredientesBanco.setGramas(gramas);
        ingredientesBanco.setPreco(preco);
        ingredientesBanco.setDisponivel(disponivel);

        ingredientesDAO.registrar(ingredientesBanco);
    }

    public List<IngredientesDTO> listarTodosIngredientes() throws ClassNotFoundException, SQLException {
        ArrayList<IngredientesDTO> ingredientesDTOArrayList = new ArrayList<IngredientesDTO>();

        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        List<Ingredientes> listaIngredientes = ingredientesDAO.listarTodosIngredientes();

        for (Ingredientes ingredientes : listaIngredientes) {
            ingredientesDTOArrayList.add(IngredientesDTO.userMapper(ingredientes));
        }

        return ingredientesDTOArrayList;
    }
}
