package br.mendonca.testemaven.controller;

import br.mendonca.testemaven.services.IngredientesService;
import br.mendonca.testemaven.services.dto.IngredientesDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@WebServlet("/ingredientes/ocultar")
public class ListIngredientesOcultosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        try {
            // Instancia o serviço de ingredientes
            IngredientesService ingredientesService = new IngredientesService();

            // Obtém a lista de ingredientes ocultos
            List<IngredientesDTO> ingredientesOcultos = ingredientesService.listarIngredientesOcultos();

            // Define a lista como atributo da requisição
            request.setAttribute("listaOcultos", ingredientesOcultos);

            // Encaminha a requisição para o JSP
            request.getRequestDispatcher("/dashboard/list-ingredientesOcultos.jsp").forward(request, response);

        } catch (Exception e) {
            // Em caso de erro, exibe a stack trace na página
            response.setContentType("text/html");
            PrintWriter page = response.getWriter();
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        try {
            // Obtém o parâmetro do ingrediente a ser ocultado
            String parametro = request.getParameter("uidIngrediente");

            // Instancia o serviço de ingredientes
            IngredientesService service = new IngredientesService();

            // Oculta o ingrediente
            service.excluir(parametro);

            // Redireciona para a página de registro
            response.sendRedirect(request.getContextPath() + "/ingredientes/register");

        } catch (Exception e) {
            // Em caso de erro, exibe a stack trace na página
            response.setContentType("text/html");
            PrintWriter page = response.getWriter();
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
