package br.com.urbainski.microservices.produtos.dto;

import org.springframework.lang.NonNull;

import br.com.urbainski.microservices.produtos.util.RequestUtils;

public class ErrorDto {

    private final String codigo;
    private final String message;
    private final String urlDoc;

    public ErrorDto(@NonNull String codigo, @NonNull String message) {

        this.codigo = codigo;
        this.message = message;
        this.urlDoc = RequestUtils.getUrlDocumentation();
    }

    public String getCodigo() {

        return codigo;
    }

    public String getMessage() {

        return message;
    }

    public String getUrlDoc() {

        return urlDoc;
    }

}