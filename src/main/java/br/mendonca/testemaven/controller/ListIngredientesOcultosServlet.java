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
        PrintWriter page = response.getWriter();

        try {
            IngredientesService ingredientesService = new IngredientesService();
            List<IngredientesDTO> lista = ingredientesService.listarIngredientesOcultos();

            request.setAttribute("lista", lista);
            request.getRequestDispatcher("/webapp/ingredientes/list-ingredientesOcultos.jsp").forward(request, response);
        }
        catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
            page.println("<h1>Error</h1>");
            page.println("<code>" + sw.toString() + "</code>");
            page.println("</body></html>");
            page.close();
        } finally {

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        String parametro = request.getParameter("uidIngrediente");

        try {
            IngredientesService service = new IngredientesService();
            service.excluir(parametro);
            response.sendRedirect(request.getContextPath() + "/ingredientes/register");

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
            page.println("<h1>Error</h1>");
            page.println("<code>");
            page.println(sw.toString());
            page.println("</code>");
            page.println("</body></html>");
            page.close();
        } finally {

        }
    }
}
