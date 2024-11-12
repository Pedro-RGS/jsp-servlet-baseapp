package br.mendonca.testemaven.controller.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.mendonca.testemaven.dao.IngredientesDAO;
import br.mendonca.testemaven.model.entities.Ingredientes;
import br.mendonca.testemaven.services.IngredientesService;
import br.mendonca.testemaven.services.dto.IngredientesDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ingredientes/register")
public class RegisterIngredientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final IngredientesService ingredientesService = new IngredientesService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redireciona para o formulário de registro de ingredientes
        try {
            List<IngredientesDTO> lista = ingredientesService.listarTodosIngredientes();
            request.setAttribute("listaIngredientes", lista);
            request.getRequestDispatcher("/dashboard/list-ingredientes.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        try {
            // Obtendo parâmetros do formulário
            String nome = request.getParameter("nome");
            String descricao = request.getParameter("descricao");
            String categoria = request.getParameter("categoria");
            int quantidade = Integer.parseInt(request.getParameter("quantidade"));
            double gramas = Integer.parseInt(request.getParameter("gramas"));
            double preco = Integer.parseInt(request.getParameter("preco"));
            boolean disponivel = request.getParameter("disponivel") != null;

            // Criando o objeto Ingrediente
            Ingredientes ingrediente = new Ingredientes();
            ingrediente.setNome(nome);
            ingrediente.setDescricao(descricao);
            ingrediente.setCategoria(categoria);
            ingrediente.setQuantidade(quantidade);
            ingrediente.setGramas((int) gramas);
            ingrediente.setPreco((int) preco);
            ingrediente.setDisponivel(disponivel);

            // Salvando o ingrediente no banco de dados
            IngredientesDAO dao = new IngredientesDAO();
            dao.registrar(ingrediente);

            // Redireciona para a página de listagem de ingredientes
            response.sendRedirect("/ingredientes/register");

        } catch (Exception e) {
            // Tratamento de exceções
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
            page.println("<h1>Error</h1>");
            page.println("<code>" + sw.toString() + "</code>");
            page.println("</body></html>");
            page.close();
        }
    }
}
