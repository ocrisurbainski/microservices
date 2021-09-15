package br.com.urbainski.microservices.produtos.util;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public final class BeanUtil {

    private static ApplicationContext context;

    public BeanUtil(ApplicationContext applicationContext) {

        context = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {

        if (context == null) {

            throw new IllegalStateException();
        }

        return context.getBean(clazz);
    }

}