package br.mendonca.testemaven.controller.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import br.mendonca.testemaven.services.AuthenticationService;
import br.mendonca.testemaven.services.dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/auth/logon")
public class LogonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter page = response.getWriter();
		
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			AuthenticationService service = new AuthenticationService();
			UserDTO user = service.login(email, password);
			
			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				// Configura a session para expirar em 30 minutos.
				session.setMaxInactiveInterval(30 * 60);
				
				response.sendRedirect("/dashboard/dashboard.jsp");
			}
			
			request.setAttribute("msg", "E-mail ou senha inv�lida.");
			request.getRequestDispatcher("//index.jsp").forward(request, response);
			
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
