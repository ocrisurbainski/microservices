package br.com.urbainski.microservices.produtos.dto;

public class ProdutoResponseDto {

    private Long id;
    private String descricao;

    @Deprecated
    public ProdutoResponseDto() {

    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

}