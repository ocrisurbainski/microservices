package br.com.urbainski.microservices.produtos.exception;

public class ProdutoNotFound extends GenericNotFound {

    public ProdutoNotFound(Long id) {

        super(String.format("Produto não encontrado com o id: %d", id));
    }

}