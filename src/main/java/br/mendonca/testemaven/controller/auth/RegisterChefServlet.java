package br.mendonca.testemaven.controller.auth;


import br.mendonca.testemaven.dao.ChefDAO;
import br.mendonca.testemaven.model.entities.Chef;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@WebServlet("/register/chef")
public class RegisterChefServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Caso o usu rio tente acessar este end point pelo m todo GET, recebe a p gina de formul rio JSP.
        request.getRequestDispatcher("/form-register-chef.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        try {

            String nome = request.getParameter("nome");
            Integer idade = Integer.valueOf(request.getParameter("idade"));
            Boolean ativo = Boolean.valueOf(request.getParameter("ativo"));

            Chef chef = new Chef();
            chef.setNome(nome);
            chef.setIdade(idade);
            chef.setAtivo(ativo);

            ChefDAO chefDAO = new ChefDAO();
            chefDAO.register(chef);

            response.sendRedirect("/dashboard/chefs");

        } catch (Exception e) {
            // Escreve as mensagens de Exception em uma p gina de resposta.
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
