<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.services.dto.ChefDTO" %>

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

    <h1 class="h3 mb-3 fw-normal">Chefs ocultos</h1>
    <table class="table">
        <thead>
        <tr>
            <th scope="col"></th>
            <th scope="col">Nome</th>
            <th scope="col">Idade</th>
            <th scope="col">Ativo</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <%
            List<ChefDTO> lista = (List<ChefDTO>) request.getAttribute("lista");
            for (ChefDTO chef : lista) {
        %>
        <tr>
            <td>Editar</td>
            <td><%= chef.getNome() %></td>
            <td><%= chef.getIdade() %></td>
            <td><%= chef.getAtivo() %></td>
            <td>Apagar</td>
        </tr>
        <% } %>
        </tbody>
    </table>


</main>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>

<% } %>