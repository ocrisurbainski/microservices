package br.com.urbainski.microservices.produtos.listener;

import br.com.urbainski.microservices.produtos.event.ProdutoPersistEvent;
import br.com.urbainski.microservices.produtos.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Cristian Urbainski
 * @since 15/09/2021
 */
@Component
public class ProdutoPersistLogListener implements ApplicationListener<ProdutoPersistEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoPersistLogListener.class);

    @Override
    public void onApplicationEvent(ProdutoPersistEvent produtoPersistEvent) {

        var produto = produtoPersistEvent.getProduto();

        try {

            logger.info(String.format("Produto persistido: %s", JsonUtils.toJson(produto)));
        } catch (JsonProcessingException e) {

            logger.error(e.getMessage(), e);
        }
    }

}
