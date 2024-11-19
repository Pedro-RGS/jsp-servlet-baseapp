<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

    <form action="/register/livro" method="post">
        <h1 class="h3 mb-3 fw-normal">Cadastrar novo livro:</h1>
        <div class="form-floating">
            <input type="text" name="title" class="form-control" id="a" placeholder="Ex. livro1" />
            <label for="a">Título</label>
        </div>
        <div class="form-floating">
            <input type="text" name="paginas" class="form-control" id="b" placeholder="Ex. 200" />
            <label for="b">Quantidade de páginas</label>
        </div>
        <div>
            <label for="c">É bom?</label>
            <select id="c" name="isGood">
                <option value="true">Sim</option>
                <option value="false">Não</option>
            </select>
        </div>
        <button class="btn btn-primary w-100 py-2 mt-2">Cadastrar</button>
    </form>
</main>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>