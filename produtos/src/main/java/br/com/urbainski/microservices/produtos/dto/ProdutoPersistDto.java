package br.com.urbainski.microservices.produtos.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProdutoPersistDto {

    @NotEmpty
    private String descricao;
    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal valor;

    @Deprecated
    public ProdutoPersistDto() {

    }

    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

    public BigDecimal getValor() {

        return valor;
    }

    public void setValor(BigDecimal valor) {

        this.valor = valor;
    }

    @Override
    public String toString() {

        return "ProdutoPersistDto{" +
                "descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }

}