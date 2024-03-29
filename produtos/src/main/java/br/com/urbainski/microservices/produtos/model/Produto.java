package br.com.urbainski.microservices.produtos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "PRODUTO")
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "DESCRICAO", nullable = false, length = 100)
    private String descricao;
    @Column(name = "VALOR", nullable = false, scale = 10, precision = 2)
    private BigDecimal valor;
    @Column(name = "fg_ativo")
    private boolean fgAtivo = true;

    @Deprecated
    public Produto() {

    }

    public Produto(@NonNull String descricao, @NonNull BigDecimal valor) {

        setDescricao(descricao);
        setValor(valor);
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

    public void setDescricao(@NonNull String descricao) {

        this.descricao = Objects.requireNonNull(descricao, "descricao não pode ser nula");
    }

    public BigDecimal getValor() {

        return valor;
    }

    public void setValor(@NonNull BigDecimal valor) {

        this.valor = Objects.requireNonNull(valor, "valor não pode ser nulo");
    }

    public boolean isFgAtivo() {
        return fgAtivo;
    }

    public void setFgAtivo(boolean fgAtivo) {
        this.fgAtivo = fgAtivo;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {

            return true;
        }

        if (o == null || getClass() != o.getClass()) {

            return false;
        }

        Produto produto = (Produto) o;

        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

}