package br.com.urbainski.microservices.produtos.event;

import br.com.urbainski.microservices.produtos.model.Produto;
import org.springframework.context.ApplicationEvent;

/**
 * @author Cristian Urbainski
 * @since 15/09/2021
 */
public class ProdutoPersistEvent extends ApplicationEvent {

    private final Produto produto;

    public ProdutoPersistEvent(Object source, Produto produto) {
        super(source);
        this.produto = produto;
    }

    public Produto getProduto() {
        return produto;
    }

}
