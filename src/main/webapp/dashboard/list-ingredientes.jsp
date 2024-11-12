<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.services.dto.IngredientesDTO"%>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Lista de Ingredientes</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link href="style.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="w-100 m-auto form-container">

  <!-- Navbar -->
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <a class="navbar-brand" href="/dashboard/dashboard.jsp">Gerência de Configuração</a>
      <button class="navbar-toggler" type="button"
              data-bs-toggle="collapse" data-bs-target="#navbarText"
              aria-controls="navbarText" aria-expanded="false"
              aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item"><a class="nav-link" href="/dashboard/dashboard.jsp">Home</a></li>
          <li class="nav-item"><a class="nav-link" href="/dashboard/users">Users</a></li>
          <li class="nav-item"><a class="nav-link" href="/dashboard/list-ingredientes.jsp">Ingredientes</a></li>
          <li class="nav-item"><a class="nav-link" href="/dashboard/about.jsp">About</a></li>
        </ul>
        <span class="navbar-text">
          <a class="btn btn-success" href="/auth/logoff">Logoff</a>
        </span>
      </div>
    </div>
  </nav>

  <!-- Título e botão para adicionar novo ingrediente -->
  <h1 class="h3 mb-3 fw-normal">Ingredientes</h1>
  <a href="/dashboard/register-ingrediente.jsp" class="btn btn-primary mb-3">Novo Ingrediente</a>

  <!-- Tabela de ingredientes -->
  <table class="table">
    <thead>
    <tr>
      <th scope="col">Nome</th>
      <th scope="col">Descrição</th>
      <th scope="col">Categoria</th>
      <th scope="col">Quantidade</th>
      <th scope="col">Gramas</th>
      <th scope="col">Preço</th>
      <th scope="col">Disponível</th>
    </tr>
    </thead>
    <tbody>
    <%
      // Obtém a lista de ingredientes da requisição
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
      <td><%= ingrediente.getDescricao() %></td>
      <td><%= ingrediente.getCategoria() %></td>
      <td><%= ingrediente.getQuantidade() %></td>
      <td><%= ingrediente.getGramas() %></td>
      <td><%= ingrediente.getPreco() %></td>
      <td><%= ingrediente.isDisponivel() ? "Sim" : "Não" %></td>
    </tr>
    <%
      }
    } else {
    %>
    <tr>
      <td colspan="8">Nenhum ingrediente encontrado.</td>
    </tr>
    <% } %>
    </tbody>
  </table>

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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
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
