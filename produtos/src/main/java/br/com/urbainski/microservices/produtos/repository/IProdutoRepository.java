package br.com.urbainski.microservices.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.urbainski.microservices.produtos.model.Produto;

public interface IProdutoRepository extends JpaRepository<Produto, Long> {

}