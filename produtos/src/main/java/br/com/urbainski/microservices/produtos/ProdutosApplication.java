package br.com.urbainski.microservices.produtos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ProdutosApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProdutosApplication.class, args);
    }

}