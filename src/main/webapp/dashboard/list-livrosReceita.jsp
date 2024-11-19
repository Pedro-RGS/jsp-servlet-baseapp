<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.services.dto.UserDTO"%>
<%@ page import="br.mendonca.testemaven.services.dto.LivroDeReceitasDTO" %>

<% if (session.getAttribute("user") != null && request.getAttribute("lista") != null) { %>

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

  <h1 class="h3 mb-3 fw-normal">Livros</h1>
  <table class="table">
    <thead>
    <tr>
      <th scope="col"></th>
      <th scope="col">Título</th>
      <th scope="col">Número de Páginas</th>
      <th scope="col">É bom?</th>
      <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <%
      List<LivroDeReceitasDTO> lista = (List<LivroDeReceitasDTO>) request.getAttribute("lista");
      for (LivroDeReceitasDTO livro : lista) {
    %>
    <tr>
      <td>Editar</td>
      <td><%= livro.getTitulo() %></td>
      <td><%= livro.getNumeroDePaginas() %></td>
      <td><%= livro.isEhBom() %></td>
      <td>
        <form action="/dashboard/livros" method="post">
            <input type="hidden" name="_method" value="DELETE">
            <input type="hidden" name="titulo" value="<%=livro.getTitulo()%>">
            <input type="hidden" name="paginas" value="<%= livro.getNumeroDePaginas() %>">
            <button class="btn btn-success" type="submit">Apagar</button>
        </form>
      </td>
    </tr>
    <% } %>
    </tbody>
  </table>
  <form action="/dashboard/ocultos" method="get">
    <button class="btn btn-success" type="submit">Ver Livros Ocultos</button>
  </form>


</main>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>

<% } %>