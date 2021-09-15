package br.com.urbainski.microservices.produtos.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.urbainski.microservices.produtos.config.ProjectConfig;

public final class RequestUtils {

    private RequestUtils() {

    }

    public static String getAddresRequest() {

        var request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        var url = request.getRequestURL().toString().replace(request.getRequestURI(), "");

        return url;
    }

    public static String getUrlDocumentation() {

        var projectConfig = BeanUtil.getBean(ProjectConfig.class);

        return getAddresRequest() + projectConfig.getSpringdocSwaggerUiPath();
    }

}