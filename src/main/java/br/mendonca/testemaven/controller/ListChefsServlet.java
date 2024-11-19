package br.mendonca.testemaven.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import br.mendonca.testemaven.services.ChefService;
import br.mendonca.testemaven.services.dto.ChefDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard/chefs")
public class ListChefsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        try {
            ChefService service = new ChefService();
            List<ChefDTO> lista = service.listAllChefs();

            // Anexa � requisi��o um objeto ArrayList e despacha a requisi��o para uma JSP.
            request.setAttribute("lista", lista);
            request.getRequestDispatcher("list-chefs.jsp").forward(request, response);
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


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        try {
            // Recupera os parâmetros enviados pelo formulário
            String nome = request.getParameter("nome");
            String idadeParam = request.getParameter("idade");

            if (nome == null || idadeParam == null) {
                page.println("Parâmetro nome ou idade não foi enviado!");
                return;
            }

            int idade = Integer.parseInt(idadeParam);

            // Chama o serviço para deletar
            ChefService service = new ChefService();
            service.delete(nome, idade);

            // Redireciona de volta à página de chefs
            response.sendRedirect(request.getContextPath() + "/dashboard/chefs");
        } catch (NumberFormatException e) {
            page.println("<html lang='pt-br'><head><title>Erro</title></head><body>");
            page.println("<h1>Erro: Idade inválida</h1>");
            page.println("</body></html>");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
            page.println("<h1>Error</h1>");
            page.println("<code>" + sw.toString() + "</code>");
            page.println("</body></html>");
        } finally {

        }
    }


    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        String nome = request.getParameter("nome");
        int idade = Integer.parseInt(request.getParameter("idade"));

        try {
            ChefService service = new ChefService();
            service.delete(nome, idade);

            response.sendRedirect(request.getContextPath() + "/dashboard/chefs");
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
            page.close();
        }
    }
}