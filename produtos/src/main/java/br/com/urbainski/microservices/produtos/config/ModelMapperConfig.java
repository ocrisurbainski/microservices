package br.com.urbainski.microservices.produtos.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper() {

        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

}