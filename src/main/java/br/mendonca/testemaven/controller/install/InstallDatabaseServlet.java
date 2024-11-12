package br.mendonca.testemaven.controller.install;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import br.mendonca.testemaven.services.InstallService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/install")
public class InstallDatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter page = response.getWriter();
		StringBuilder msg = new StringBuilder("<h1>INSTALL DATABASE</h1>");

		try {
			// Instancia o serviço de instalação
			InstallService service = new InstallService();

			// Testa conexão com o banco de dados
			service.testConnection();
			msg.append("<h2>Connection to DB successful!</h2>");

			// Deleta a tabela 'user' (caso exista) e a recria
			service.deleteUserTable();
			msg.append("<h2>Delete table 'user' successful!</h2>");

			service.createUserTable();
			msg.append("<h2>Create table 'user' successful!</h2>");

			service.createIngredienteTable(); // Adicionando criação da tabela 'ingredientes'
			msg.append("<h2>Create table 'ingredientes' successful!</h2>");

		} catch (Exception e) {
			// Em caso de erro, exibe a stack trace
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			msg.append("<h1>Error Occurred</h1><code>").append(sw.toString()).append("</code>");
		} finally {
			// Exibe a resposta no navegador
			page.println("<html lang='pt-br'><head><title>Database Installation</title></head><body>");
			page.println(msg);
			page.println("</body></html>");
			page.close();
		}
	}
}
