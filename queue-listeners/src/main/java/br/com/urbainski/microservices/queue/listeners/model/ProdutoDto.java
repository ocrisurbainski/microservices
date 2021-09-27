package br.com.urbainski.microservices.queue.listeners.model;

public class ProdutoDto {

    private Long id;
    private String descricao;
    private boolean fgAtivo;

    @Deprecated
    public ProdutoDto() {

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

    public boolean isFgAtivo() {

        return fgAtivo;
    }

    public void setFgAtivo(boolean fgAtivo) {

        this.fgAtivo = fgAtivo;
    }
}