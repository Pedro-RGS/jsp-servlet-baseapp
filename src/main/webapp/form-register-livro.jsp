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
  <form action="register" method="post">
    <h1 class="h3 mb-3 fw-normal">Cadastrar novo livro:</h1>
    <div class="form-floating">
      <input type="text" name="name" class="form-control" id="floatingInput" placehholder="Digite o título do livro" />
      <label for="floatingInput">Título</label>
    </div>
    <div class="form-floating">
      <input type="email" name="email" class="form-control" id="floatingInput" placehholder="Digite o número de páginas do livro" />
      <label for="floatingInput">Número de Páginas</label>
    </div>
    <div class="form-floating">
      <input type="password" name="password" class="form-control" id="floatingInput" placehholder="Digite se o livro é bom" />
      <label for="floatingInput">É bom?</label>
    </div>
    <button class="btn btn-primary w-100 py-2 mt-2">Cadastrar</button>
  </form>
</main>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>