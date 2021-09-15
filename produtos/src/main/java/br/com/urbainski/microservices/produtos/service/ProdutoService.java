package br.com.urbainski.microservices.produtos.service;

import java.util.Optional;

import br.com.urbainski.microservices.produtos.model.Produto;

public interface ProdutoService {

    Produto save(Produto produto);

    Produto update(Long id, Produto produto);

    void delete(Produto produto);

    Optional<Produto> findByID(Long id);

}