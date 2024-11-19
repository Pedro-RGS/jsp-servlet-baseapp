package br.mendonca.testemaven.controller.auth;

import br.mendonca.testemaven.dao.LivroDeReceitasDAO;
import br.mendonca.testemaven.model.entities.LivroDeReceitas;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@WebServlet("/register/livro")
public class RegisterLivroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Caso o usuário tente acessar este endpoint pelo método GET, recebe a página de formulário JSP.
        request.getRequestDispatcher("/form-register-livro.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        try {
            // Obtém os parâmetros do formulário
            String titulo = request.getParameter("title");
            Integer paginas = Integer.valueOf(request.getParameter("paginas"));
            Boolean ehBom = Boolean.valueOf(request.getParameter("isGood"));

            // Cria a entidade e define seus atributos
            LivroDeReceitas livro = new LivroDeReceitas();
            livro.setTitulo(titulo);
            livro.setNumeroDePaginas(paginas);
            livro.setEhBom(ehBom);

            // Registra o livro no banco de dados
            LivroDeReceitasDAO livroDAO = new LivroDeReceitasDAO();
            livroDAO.register(livro);

            // Redireciona para a página de dashboard
            response.sendRedirect("/dashboard/livros");

        } catch (Exception e) {
            // Escreve as mensagens de Exception em uma página de resposta
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
            page.println("<h1>Error</h1>");
            page.println("<code>" + sw.toString() + "</code>");
            page.println("</body></html>");
            page.close();
        } finally {
            // Opcional, caso precise liberar recursos
        }
    }
}
