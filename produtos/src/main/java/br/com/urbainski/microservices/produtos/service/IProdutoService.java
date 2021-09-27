package br.com.urbainski.microservices.produtos.service;

import java.util.Optional;

import br.com.urbainski.microservices.produtos.dto.ProdutoPersistDto;
import br.com.urbainski.microservices.produtos.exception.ProdutoNotFound;
import br.com.urbainski.microservices.produtos.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProdutoService {

    Produto save(Produto produto);

    Produto update(Long id, ProdutoPersistDto dto) throws ProdutoNotFound;

    void delete(Produto produto);

    Optional<Produto> findByID(Long id);

    Page<Produto> findAll(Pageable pageable);

}