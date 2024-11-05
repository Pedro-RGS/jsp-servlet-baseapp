package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.LivroDeReceitas;
import br.mendonca.testemaven.model.entities.User;

public class LivroDTO {
    private String titulo;
    private int numeroDePaginas;
    private boolean ehBom;

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

    public static LivroDTO livroMapper(LivroDeReceitas livroDeReceitas) {
        LivroDTO dto = new LivroDTO();
        dto.setTitulo(livroDeReceitas.getTitulo());
        dto.setNumeroDePaginas(livroDeReceitas.getNumeroDePaginas());
        dto.setEhBom(livroDeReceitas.isEhBom());
        return dto;
    }
}