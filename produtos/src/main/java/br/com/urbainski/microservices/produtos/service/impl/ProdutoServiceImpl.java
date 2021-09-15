package br.com.urbainski.microservices.produtos.service.impl;

import java.util.Optional;

import br.com.urbainski.microservices.produtos.dto.ProdutoPersistDto;
import br.com.urbainski.microservices.produtos.exception.ProdutoNotFound;
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
    public Produto update(Long id, ProdutoPersistDto dto) throws ProdutoNotFound {

        var produto = findByID(id).orElseThrow(() -> new ProdutoNotFound(id));
        produto.setId(id);
        produto.setDescricao(dto.getDescricao());
        produto.setValor(dto.getValor());

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