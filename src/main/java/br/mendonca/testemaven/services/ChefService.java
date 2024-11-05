package br.mendonca.testemaven.services;

import br.mendonca.testemaven.dao.ChefDAO;
import br.mendonca.testemaven.model.entities.Chef;
import br.mendonca.testemaven.services.dto.ChefDTO;
import br.mendonca.testemaven.services.dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChefService {

    public void register(String nome, Integer idade, Boolean ativo) throws ClassNotFoundException, SQLException {
        ChefDAO dao = new ChefDAO();

        Chef chef = new Chef();
        chef.setNome(nome);
        chef.setIdade(idade);
        chef.setAtivo(ativo);

        dao.register(chef);
    }

    public List<ChefDTO> listAllUsers() throws ClassNotFoundException, SQLException {
        ArrayList<ChefDTO> resp = new ArrayList<>();

        ChefDAO dao = new ChefDAO();
        List<Chef> lista = dao.listAllUser();

        for (Chef chef : lista) {
            resp.add(ChefDTO.chefMapper(chef));
        }

        return resp;
    }
}