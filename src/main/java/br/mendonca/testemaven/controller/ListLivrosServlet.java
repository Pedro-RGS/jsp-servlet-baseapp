package br.mendonca.testemaven.controller;

import br.mendonca.testemaven.dao.LivroDeReceitasDAO;
import br.mendonca.testemaven.model.entities.LivroDeReceitas;
import br.mendonca.testemaven.services.LivroService;
import br.mendonca.testemaven.services.UserService;
import br.mendonca.testemaven.services.dto.LivroDeReceitasDTO;
import br.mendonca.testemaven.services.dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@WebServlet("/dashboard/livros")
public class ListLivrosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        try {
            LivroService service = new LivroService();

            int pagina = 1;
            if (request.getParameter("page") != null) {
                pagina = Integer.parseInt(request.getParameter("page"));
            }

            List<LivroDeReceitasDTO> lista = service.listAllLivros(pagina);

            request.setAttribute("lista", lista);
            request.setAttribute("currentPage", pagina);
            request.getRequestDispatcher("list-livrosReceita.jsp").forward(request, response);
        } catch (Exception e) {
            // Escreve as mensagens de Exception em uma p�gina de resposta.
            // N�o apagar este bloco.
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
        String method = request.getParameter("_method");

        if ("DELETE".equalsIgnoreCase(method)) {
            delete(request, response);
        }

        try {
            // A programa��o do servlet deve ser colocada neste bloco try.
            // Apague o conte�do deste bloco try e escreva seu c�digo.
            String parametro = request.getParameter("nomeparametro");

            page.println("Parametro: " + parametro);
            page.close();


        } catch (Exception e) {
            // Escreve as mensagens de Exception em uma p�gina de resposta.
            // N�o apagar este bloco.
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

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        String titulo = request.getParameter("titulo");
        int numero = Integer.parseInt(request.getParameter("paginas"));

        try {
            LivroService service = new LivroService();
            service.delete(titulo, numero);

            response.sendRedirect(request.getContextPath() + "/dashboard/livros");
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
