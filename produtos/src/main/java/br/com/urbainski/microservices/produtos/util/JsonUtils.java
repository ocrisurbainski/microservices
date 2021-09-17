package br.com.urbainski.microservices.produtos.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

/**
 * Classe utilitária para fornecer um {@link ObjectMapper} limpo sem as configurações feitas no {@link ObjectMapper} do Spring.
 *
 * @author Cristian Urbainski
 * @since 15/09/2021
 */
public abstract class JsonUtils {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper()
                .disable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                .enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)
                .setNodeFactory(JsonNodeFactory.withExactBigDecimals(true));
    }

    public static <T> T convertValue(Object object, Class<T> clazz) {

        return mapper.convertValue(object, clazz);
    }

    public static <T> T treeToValue(TreeNode node, Class<T> clazz) throws JsonProcessingException {

        return mapper.treeToValue(node, clazz);
    }

    public static String toJson(Object object) throws JsonProcessingException {

        return mapper.writeValueAsString(object);
    }

}
