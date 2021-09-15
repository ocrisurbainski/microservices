package br.com.urbainski.microservices.produtos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public final class ProjectConfig {

    @Value("${springdoc.swagger-ui.path}")
    private String springdocSwaggerUiPath;

    public String getSpringdocSwaggerUiPath() {

        return springdocSwaggerUiPath;
    }

}