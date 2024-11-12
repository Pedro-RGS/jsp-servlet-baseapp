package br.mendonca.testemaven.services;

import br.mendonca.testemaven.dao.LivroDeReceitasDAO;
import br.mendonca.testemaven.model.entities.LivroDeReceitas;
import br.mendonca.testemaven.services.dto.LivroDeReceitasDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroService {

    public void register(String titulo, int numeroPaginas, boolean ehBom) throws ClassNotFoundException, SQLException {
        LivroDeReceitasDAO dao = new LivroDeReceitasDAO();

        LivroDeReceitas livro = new LivroDeReceitas();
        livro.setTitulo(titulo);
        livro.setNumeroDePaginas(numeroPaginas);
        livro.setEhBom(ehBom);

        dao.register(livro);
    }

    public List<LivroDeReceitasDTO> listAllLivros() throws ClassNotFoundException, SQLException {

        ArrayList<LivroDeReceitasDTO> resp = new ArrayList<>();

        LivroDeReceitasDAO dao = new LivroDeReceitasDAO();
        List<LivroDeReceitas> lista = dao.listAllLivros();

        for (LivroDeReceitas livro : lista) {
            resp.add(LivroDeReceitasDTO.livroMapper(livro));
        }

        return resp;
    }

    public List<LivroDeReceitasDTO> listLivrosOcultos() throws ClassNotFoundException, SQLException {

        ArrayList<LivroDeReceitasDTO> resp = new ArrayList<>();

        LivroDeReceitasDAO dao = new LivroDeReceitasDAO();
        List<LivroDeReceitas> lista = dao.listLivrosOcultos();

        for (LivroDeReceitas livro : lista) {
            resp.add(LivroDeReceitasDTO.livroMapper(livro));
        }

        return resp;
    }

    public void delete(String titulo, int numero) throws ClassNotFoundException, SQLException{
        LivroDeReceitasDAO dao = new LivroDeReceitasDAO();
        dao.delete(titulo, numero);
    }
}
