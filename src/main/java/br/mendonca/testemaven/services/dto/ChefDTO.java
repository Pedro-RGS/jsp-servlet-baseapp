package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.Chef;

public class ChefDTO {


    private String nome;
    private Integer idade;
    private Boolean ativo;

    public Integer getIdade() {
        return idade;
    }
    public void setIdade(Integer idade) {
        this.idade = idade;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    public static ChefDTO chefMapper(Chef chef) {
        ChefDTO dto = new ChefDTO();
        dto.setNome(chef.getNome());
        dto.setIdade(chef.getIdade());

        return dto;
    }

}
