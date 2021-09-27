package br.com.urbainski.microservices.produtos.listener;

import br.com.urbainski.microservices.produtos.event.ProdutoPersistEvent;
import br.com.urbainski.microservices.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Cristian Urbainski
 * @since 16/09/2021
 */
@Component
public class ProdutoPersistQueueListener implements ApplicationListener<ProdutoPersistEvent> {

    private final Logger logger = LoggerFactory.getLogger(ProdutoPersistQueueListener.class);
    private final JmsTemplate jmsTemplate;

    public ProdutoPersistQueueListener(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void onApplicationEvent(ProdutoPersistEvent produtoPersistEvent) {

        var produto = produtoPersistEvent.getProduto();

        try {

            jmsTemplate.convertAndSend("produto.persit.queue", JsonUtils.toJson(produto));
        } catch (JsonProcessingException e) {

            logger.error(e.getMessage(), e);
        }
    }
}
