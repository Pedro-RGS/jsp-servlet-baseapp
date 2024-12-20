package br.mendonca.testemaven.controller;

import br.mendonca.testemaven.services.LivroService;
import br.mendonca.testemaven.services.dto.LivroDeReceitasDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@WebServlet("/dashboard/ocultos")
public class ListOcultsLivrosServlet extends HttpServlet {
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

            List<LivroDeReceitasDTO> lista = service.listLivrosOcultos(pagina);

            request.setAttribute("lista", lista);
            request.setAttribute("currentPage", pagina);
            request.setAttribute("lista", lista);
            request.getRequestDispatcher("list-livrosOcultos.jsp").forward(request, response);
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

        try {
            String parametro = request.getParameter("nomeparametro");

            page.println("Parametro: " + parametro);
            page.close();


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