<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.services.dto.UserDTO"%>
<%@ page import="br.mendonca.testemaven.services.dto.LivroDeReceitasDTO" %>

<% if (request.getAttribute("lista") != null) { %>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Gerência de Configuração</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link href="style.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="w-100 m-auto form-container">

  <jsp:include page="/cabecalho.html" />

  <h1 class="h3 mb-3 fw-normal">Livros ocultos</h1>
  <table class="table">
    <thead>
    <tr>
      <th scope="col">Título</th>
    </tr>
    </thead>
    <tbody>
    <%
      List<LivroDeReceitasDTO> lista = (List<LivroDeReceitasDTO>) request.getAttribute("lista");
      for (LivroDeReceitasDTO livro : lista) {
    %>
    <tr>
      <td>
        <a href="#" onclick="openLivroModal('<%= livro.getTitulo() %>', '<%= livro.getNumeroDePaginas() %>', '<%= livro.isEhBom() %>')">
          <%= livro.getTitulo() %>
        </a>
      </td>
    </tr>
    <% } %>
    </tbody>
  </table>

  <%
    int currentPage = (int) request.getAttribute("currentPage");
  %>

  <div class="pagination">
    <form action="/dashboard/ocultos" method="get" style="display: inline;">
      <input type="hidden" name="page" value="<%= currentPage > 1 ? currentPage - 1 : 1 %>">
      <button type="submit" class="btn btn-primary" <%= currentPage == 1 ? "disabled" : "" %>>Página Anterior</button>
    </form>

    <form action="/dashboard/ocultos" method="get" style="display: inline;">
      <input type="hidden" name="page" value="<%= currentPage + 1 %>">
      <button type="submit" class="btn btn-primary">Próxima Página</button>
    </form>
  </div>


  <form action="/dashboard/livros" method="get">
    <button class="btn btn-success" type="submit">Voltar</button>
  </form>

</main>
<div class="modal fade" id="livroModal" tabindex="-1" aria-labelledby="livroModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="livroModalLabel">Detalhes do livro</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p><strong>Título:</strong> <span id="modalTitulo"></span></p>
        <p><strong>Número de páginas:</strong> <span id="modalNumero"></span></p>
        <p><strong>É bom?:</strong> <span id="modalEhBom"></span></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>
<script>
  function openLivroModal(titulo, numeroDePaginas, ehBom) {
    document.getElementById("modalTitulo").textContent = titulo;
    document.getElementById("modalNumero").textContent = numeroDePaginas;
    document.getElementById("modalEhBom").textContent = ehBom === "true" ? "Sim" : "Não";

    var modal = new bootstrap.Modal(document.getElementById("livroModal"));
    modal.show();
  }
</script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>

<% } %>