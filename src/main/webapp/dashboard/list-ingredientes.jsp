<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.services.dto.IngredientesDTO"%>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Lista de Ingredientes</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- Navbar -->
<main class="w-100 m-auto form-container">
  <jsp:include page="/cabecalho.html" />
</main>
<!-- Main Content -->
<main class="container mt-4">
  <h1 class="h3 mb-3">Ingredientes</h1>
  <a href="/form-register-ingrediente.jsp" class="btn btn-primary mb-3">Novo Ingrediente</a>
  <a href="/ingredientes/ocultar" class="btn btn-primary mb-3">Lixeira</a>

  <!-- Tabela de ingredientes -->
  <table class="table">
    <thead>
    <tr>
      <th>Nome</th>
    </tr>
    </thead>
    <tbody>
    <%
      List<IngredientesDTO> listaIngredientes = (List<IngredientesDTO>) request.getAttribute("listaIngredientes");
      if (listaIngredientes != null && !listaIngredientes.isEmpty()) {
        for (IngredientesDTO ingrediente : listaIngredientes) {
    %>
    <tr onclick="openIngredientModal({
            nome: '<%= ingrediente.getNome() %>',
            descricao: '<%= ingrediente.getDescricao() %>',
            categoria: '<%= ingrediente.getCategoria() %>',
            quantidade: '<%= ingrediente.getQuantidade() %>',
            gramas: '<%= ingrediente.getGramas() %>',
            preco: '<%= ingrediente.getPreco() %>',
            disponivel: <%= ingrediente.isDisponivel() %>
            })">
      <td><%= ingrediente.getNome() %></td>
      <td>
        <form action="/ingredientes/ocultar" method="post">
          <input type="hidden" name="uidIngrediente" value="<%= ingrediente.getNome() %>">
          <button type="submit" class="btn btn-danger">Excluir</button>
        </form>
      </td>
    </tr>
    <%
      }
    } else {
    %>
    <tr><td colspan="1">Nenhum ingrediente encontrado.</td></tr>
    <% } %>
    </tbody>
  </table>

  <!-- Paginação -->
  <nav aria-label="Page navigation">
    <ul class="pagination">
      <%
        int currentPage = (int) request.getAttribute("currentPage");
        int totalPages = (int) request.getAttribute("totalPages");

        if (currentPage > 1) {
      %>
      <li class="page-item">
        <a class="page-link" href="?page=<%= currentPage - 1 %>">Anterior</a>
      </li>
      <% } %>

      <% for (int i = 1; i <= totalPages; i++) { %>
      <li class="page-item <%= i == currentPage ? "active" : "" %>">
        <a class="page-link" href="?page=<%= i %>"><%= i %></a>
      </li>
      <% } %>

      <% if (currentPage < totalPages) { %>
      <li class="page-item">
        <a class="page-link" href="?page=<%= currentPage + 1 %>">Próxima</a>
      </li>
      <% } %>
    </ul>
  </nav>

</main>

<!-- Modal de Detalhes do Ingrediente -->
<div class="modal fade" id="ingredientModal" tabindex="-1" aria-labelledby="ingredientModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="ingredientModalLabel">Detalhes do Ingrediente</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p><strong>Nome:</strong> <span id="modalNome"></span></p>
        <p><strong>Descrição:</strong> <span id="modalDescricao"></span></p>
        <p><strong>Categoria:</strong> <span id="modalCategoria"></span></p>
        <p><strong>Quantidade:</strong> <span id="modalQuantidade"></span></p>
        <p><strong>Gramas:</strong> <span id="modalGramas"></span></p>
        <p><strong>Preço:</strong> <span id="modalPreco"></span></p>
        <p><strong>Disponível:</strong> <span id="modalDisponivel"></span></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
  function openIngredientModal(ingrediente) {
    document.getElementById("modalNome").textContent = ingrediente.nome;
    document.getElementById("modalDescricao").textContent = ingrediente.descricao;
    document.getElementById("modalCategoria").textContent = ingrediente.categoria;
    document.getElementById("modalQuantidade").textContent = ingrediente.quantidade;
    document.getElementById("modalGramas").textContent = ingrediente.gramas;
    document.getElementById("modalPreco").textContent = ingrediente.preco;
    document.getElementById("modalDisponivel").textContent = ingrediente.disponivel ? "Sim" : "Não";

    var modal = new bootstrap.Modal(document.getElementById('ingredientModal'));
    modal.show();
  }
</script>

</body>
</html>
