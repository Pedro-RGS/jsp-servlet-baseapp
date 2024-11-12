package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.LivroDeReceitas;

public class LivroDeReceitasDTO {

    private String titulo;
    private int numeroDePaginas;
    private boolean ehBom;
    private boolean ativo;

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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public static LivroDeReceitasDTO livroMapper(LivroDeReceitas livroDeReceitas) {
        LivroDeReceitasDTO dto = new LivroDeReceitasDTO();
        dto.setTitulo(livroDeReceitas.getTitulo());
        dto.setNumeroDePaginas(livroDeReceitas.getNumeroDePaginas());
        dto.setEhBom(livroDeReceitas.isEhBom());
        dto.setAtivo(livroDeReceitas.isAtivo());
        return dto;
    }
}