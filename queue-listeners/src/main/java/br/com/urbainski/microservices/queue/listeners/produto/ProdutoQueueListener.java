package br.com.urbainski.microservices.queue.listeners.produto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author Cristian Urbainski
 * @since 27/09/2021
 */
@Component
public class ProdutoQueueListener {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoQueueListener.class);
    private ObjectMapper objectMapper;

    public ProdutoQueueListener(ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;
    }

    @JmsListener(destination = "produto.persit.queue", containerFactory = "jmsFactory")
    public void receiveProdutoQueueListener(String jsonProduto) {

        logger.info("Produto recebido: {}", jsonProduto);
    }

}
