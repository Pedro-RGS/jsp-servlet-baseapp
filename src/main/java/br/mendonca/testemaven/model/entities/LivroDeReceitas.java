package br.mendonca.testemaven.model.entities;

public class LivroDeReceitas {
    private String UUID;
    private String titulo;
    private int numeroDePaginas;
    private boolean ehBom;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public void setNumeroDePaginas(int numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    public boolean isEhBom() {
        return ehBom;
    }

    public void setEhBom(boolean ehBom) {
        this.ehBom = ehBom;
    }
}
