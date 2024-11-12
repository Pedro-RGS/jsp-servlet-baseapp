package br.mendonca.testemaven.controller.install;

import br.mendonca.testemaven.services.InstallService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@WebServlet("/install")
public class InstallDatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter page = response.getWriter();
		StringBuilder msg = new StringBuilder("<h1>INSTALL DATABASE</h1>");

		try {
			InstallService service = new InstallService();

			service.testConnection();
			msg.append("<h2>Connection to DB successful!</h2>");

			service.deleteUserTable();
			service.createUserTable();
			msg.append("<h2>Delete and Create table 'user' successful!</h2>");

			service.deleteLivroTable();
			service.createLivroTable();
			msg.append("<h2>Delete and Create table 'livro' successful!</h2>");
			msg.append("<h2>Create table livro sucessful!</h2>");

			service.povoarLivro();
			msg.append("<h2>Povoar table livro de receitas sucessful!</h2>");


			service.deleteChefTable();
			service.createChefTable();
			msg.append("<h2>Delete and Create table 'chef' successful!</h2>");

			service.createIngredienteTable();
			msg.append("<h2>Create table 'ingredientes' successful!</h2>");

			page.println("<html lang='pt-br'><head><title>Teste</title></head><body>");
			page.println(msg);
			page.println("</body></html>");
			page.close();

		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);

			page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
			page.println("<h1>Error</h1>");
			page.println("<code>");
			page.println(sw);
			page.println("</code>");
			page.println("</body></html>");
			page.close();
		}
	}
}
