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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter page = response.getWriter();

		try {
			InstallService service = new InstallService();
			String msg = "<h1>INSTALL DATABASE</h1>";

			service.testConnection();
			msg += "<h2>Connection DB sucessful!</h2>\n";

			service.deleteUserTable();
			msg += "<h2>Delete table user sucessful!</h2>\n";

			service.createUserTable();
			msg += "<h2>Create table user sucessful!</h2>\n";

			service.deleteLivroTable();
			msg += "<h2>Delete table livro sucessful!</h2>\n";

			service.createLivroTable();
			msg += "<h2>Create table livro sucessful!</h2>\n";

			service.deleteChefTable();
			msg += "<h2>Delete table chef sucessful!</h2>\n";

			service.createChefTable();
			msg += "<h2>Create table chef sucessful!</h2>\n";


			page.println("<html lang='pt-br'><head><title>Teste</title></head><body>");
			page.println(msg);

			/*/
			page.println("<code>");
			for (Map.Entry<String,String> pair : env.entrySet()) {
			    page.println(pair.getKey());
			    page.println(pair.getValue());
			}
			//*/
			page.println("</code>");
			page.println("</body></html>");
			page.close();

		} catch (Exception e) {
			// Escreve as mensagens de Exception em uma página de resposta.
			// Não apagar este bloco.
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