package br.com.urbainski.microservices.produtos.exception;

public abstract class GenericNotFound extends Exception {

    public GenericNotFound(String message) {

        super(message);
    }

}