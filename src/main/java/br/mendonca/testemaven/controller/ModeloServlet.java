package br.mendonca.testemaven.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/end/point")
public class ModeloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter page = response.getWriter();
		
		try {
			// A programacao do servlet deve ser colocada neste bloco try.
			// Apague o conte�do deste bloco try e escreva seu c�digo.
			
			page.println("<html lang='pt-br'><head><title>Exemplo</title></head><body>");
			page.println("<h1>Enviar parametro</h1>");
			page.println("<form method='post' action='/end/point'>");
			page.println("  <input type='text' name='nomeparametro'>");
			page.println("  <input type='submit' name='Enviar'></input>");
			page.println("</form>");
			page.println("</body></html>");
			page.close();
			
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
}
