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
                    <li class="nav-item"><a class="nav-link" href="/dashboard/chefs">Listar Chefs</a></li>
                    <li class="nav-item"><a class="nav-link" href="/register/chef">Cadastrar Chefs</a></li>
                    <li class="nav-item"><a class="nav-link" href="/dashboard/livros">Listar Livros</a></li>
                    <li class="nav-item"><a class="nav-link" href="/register/livro">Cadastrar Livros</a></li>
                    <li class="nav-item"><a class="nav-link" href="/ingredientes/register">Ingredientes</a></li>
                    <li class="nav-item"><a class="nav-link" href="/dashboard/about.jsp">About</a></li>
                </ul>
                <span class="navbar-text">
						<a class="btn btn-success" href="/auth/logoff">Logoff</a>
					</span>
            </div>
        </div>
    </nav>
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