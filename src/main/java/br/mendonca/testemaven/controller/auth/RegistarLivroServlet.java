package br.mendonca.testemaven.controller.auth;

import br.mendonca.testemaven.dao.LivroDeReceitasDAO;
import br.mendonca.testemaven.dao.UserDAO;
import br.mendonca.testemaven.model.entities.LivroDeReceitas;
import br.mendonca.testemaven.model.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@WebServlet("/register/livro")
public class RegistarLivroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Caso o usu�rio tente acessar este end point pelo m�todo GET, recebe a p�gina de formul�rio JSP.
        response.sendRedirect("form-register-livro.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        try {
            String nome = request.getParameter("title");
            String numero = request.getParameter("numberOfPages");
            String ehBom = request.getParameter("isGood");

            LivroDeReceitas livro = new LivroDeReceitas();
            livro.setTitulo(nome);
            livro.setNumeroDePaginas(Integer.parseInt(numero));
            livro.setEhBom(Boolean.parseBoolean(ehBom));

            LivroDeReceitasDAO dao = new LivroDeReceitasDAO();
            dao.register(livro);

            response.sendRedirect("index.jsp");

        } catch (Exception e) {
            // Escreve as mensagens de Exception em uma p�gina de resposta.
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

}
