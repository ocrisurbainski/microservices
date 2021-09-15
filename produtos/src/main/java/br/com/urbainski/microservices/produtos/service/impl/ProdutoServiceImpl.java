package br.com.urbainski.microservices.produtos.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.urbainski.microservices.produtos.model.Produto;
import br.com.urbainski.microservices.produtos.repository.IProdutoRepository;
import br.com.urbainski.microservices.produtos.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final IProdutoRepository produtoRepository;

    public ProdutoServiceImpl(IProdutoRepository produtoRepository) {

        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto save(Produto produto) {

        return produtoRepository.save(produto);
    }

    @Override
    public Produto update(Long id, Produto produto) {

        produto.setId(id);

        return save(produto);
    }

    @Override
    public void delete(Produto produto) {

        produtoRepository.delete(produto);
    }

    @Override
    public Optional<Produto> findByID(Long id) {

        return produtoRepository.findById(id);
    }

}