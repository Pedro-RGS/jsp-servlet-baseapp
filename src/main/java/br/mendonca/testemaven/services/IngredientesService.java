package br.mendonca.testemaven.services;

import br.mendonca.testemaven.dao.IngredientesDAO;
import br.mendonca.testemaven.model.entities.Ingredientes;
import br.mendonca.testemaven.services.dto.IngredientesDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientesService {

    private final IngredientesDAO ingredientesDAO = new IngredientesDAO();

    public void registrar(String nome, String descricao, String categoria, int quantidade, int gramas, int preco, boolean disponivel) throws SQLException, ClassNotFoundException {
        Ingredientes ingrediente = new Ingredientes();
        ingrediente.setNome(nome);
        ingrediente.setDescricao(descricao);
        ingrediente.setCategoria(categoria);
        ingrediente.setQuantidade(quantidade);
        ingrediente.setGramas(gramas);
        ingrediente.setPreco(preco);
        ingrediente.setDisponivel(disponivel);

        ingredientesDAO.registrar(ingrediente);
    }

    public List<IngredientesDTO> listarTodosIngredientes() throws SQLException, ClassNotFoundException {
        List<Ingredientes> listaIngredientes = ingredientesDAO.listarTodosIngredientes();
        List<IngredientesDTO> ingredientesDTOList = new ArrayList<>();

        for (Ingredientes ingrediente : listaIngredientes) {
            ingredientesDTOList.add(IngredientesDTO.ingredientesMapper(ingrediente));
        }

        return ingredientesDTOList;
    }

    public List<IngredientesDTO> listarIngredientesPaginado(int page, int itemsPerPage) throws SQLException, ClassNotFoundException {
        int offset = (page - 1) * itemsPerPage;
        return ingredientesDAO.listarPaginado(offset, itemsPerPage);
    }

    public int contarIngredientes() throws SQLException, ClassNotFoundException {
        return ingredientesDAO.contarTotal();
    }

    public List<IngredientesDTO> listarIngredientesOcultos() throws ClassNotFoundException, SQLException {

        ArrayList<IngredientesDTO> ingredientesDTO = new ArrayList<>();

        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        List<Ingredientes> lista = ingredientesDAO.listarIngredientesOcultos();

        for (Ingredientes ingredientes : lista) {
            ingredientesDTO.add(IngredientesDTO.ingredientesMapper(ingredientes));
        }

        return ingredientesDTO;
    }

    public void excluir(String nome) throws ClassNotFoundException, SQLException{
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        ingredientesDAO.excluir(nome);
    }
}
