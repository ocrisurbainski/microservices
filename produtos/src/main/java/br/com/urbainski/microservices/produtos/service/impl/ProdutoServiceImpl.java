package br.com.urbainski.microservices.produtos.service.impl;

import br.com.urbainski.microservices.produtos.dto.ProdutoPersistDto;
import br.com.urbainski.microservices.produtos.event.ProdutoPersistEvent;
import br.com.urbainski.microservices.produtos.exception.ProdutoNotFound;
import br.com.urbainski.microservices.produtos.model.Produto;
import br.com.urbainski.microservices.produtos.repository.IProdutoRepository;
import br.com.urbainski.microservices.produtos.service.IProdutoService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoServiceImpl implements IProdutoService {

    private final IProdutoRepository produtoRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public ProdutoServiceImpl(IProdutoRepository produtoRepository, ApplicationEventPublisher applicationEventPublisher) {

        this.produtoRepository = produtoRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Produto save(Produto produto) {

        var produtoPersist = produtoRepository.save(produto);

        applicationEventPublisher.publishEvent(new ProdutoPersistEvent(this, produtoPersist));

        return produtoPersist;
    }

    @Override
    public Produto update(Long id, ProdutoPersistDto dto) throws ProdutoNotFound {

        var produto = findByID(id).orElseThrow(() -> new ProdutoNotFound(id));
        produto.setId(id);
        produto.setDescricao(dto.getDescricao());
        produto.setValor(dto.getValor());
        produto.setFgAtivo(dto.isFgAtivo());

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

    @Override
    public Page<Produto> findAll(Pageable pageable) {

        return produtoRepository.findAll(pageable);
    }

}