package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.Ingredientes;

public class IngredientesDTO {

    private String nome;
    private String descricao;
    private String categoria;
    private int quantidade;
    private int gramas;
    private int preco;
    private boolean disponivel;

    // Construtor que aceita todos os parâmetros necessários
    public IngredientesDTO(String nome, String descricao, String categoria, int quantidade, int gramas, int preco, boolean disponivel) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.gramas = gramas;
        this.preco = preco;
        this.disponivel = disponivel;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getGramas() {
        return gramas;
    }

    public void setGramas(int gramas) {
        this.gramas = gramas;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public static IngredientesDTO userMapper(Ingredientes ingredientes) {
        return new IngredientesDTO(
                ingredientes.getNome(),
                ingredientes.getDescricao(),
                ingredientes.getCategoria(),
                ingredientes.getQuantidade(),
                ingredientes.getGramas(),
                ingredientes.getPreco(),
                ingredientes.isDisponivel()
        );
    }
}
