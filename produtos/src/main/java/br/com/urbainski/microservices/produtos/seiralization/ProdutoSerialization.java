package br.com.urbainski.microservices.produtos.seiralization;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.urbainski.microservices.produtos.dto.ProdutoResponseDto;
import br.com.urbainski.microservices.produtos.model.Produto;

@JsonComponent
public class ProdutoSerialization extends JsonSerializer<Produto> {

    private final ModelMapper modelMapper;

    public ProdutoSerialization(ModelMapper modelMapper) {

        this.modelMapper = modelMapper;
    }

    @Override
    public void serialize(Produto produto, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        var produtoResponseDto = modelMapper.map(produto, ProdutoResponseDto.class);

        jsonGenerator.writeObject(produtoResponseDto);
    }

}