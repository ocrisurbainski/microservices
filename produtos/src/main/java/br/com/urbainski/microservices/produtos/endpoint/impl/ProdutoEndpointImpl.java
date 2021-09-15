package br.com.urbainski.microservices.produtos.endpoint.impl;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import br.com.urbainski.microservices.produtos.dto.ProdutoPersistDto;
import br.com.urbainski.microservices.produtos.dto.ProdutoResponseDto;
import br.com.urbainski.microservices.produtos.endpoint.IProdutoEndpoint;
import br.com.urbainski.microservices.produtos.exception.ProdutoNotFound;
import br.com.urbainski.microservices.produtos.model.Produto;
import br.com.urbainski.microservices.produtos.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoEndpointImpl implements IProdutoEndpoint {

    private final ProdutoService produtoService;
    private final ModelMapper modelMapper;

    public ProdutoEndpointImpl(ProdutoService produtoService, ModelMapper modelMapper) {

        this.produtoService = produtoService;
        this.modelMapper = modelMapper;
    }

    @Override
    @PostMapping
    public ResponseEntity<ProdutoResponseDto> insert(@Valid @RequestBody ProdutoPersistDto produtoPersistDto) {

        var produto = modelMapper.map(produtoPersistDto, Produto.class);

        produto = produtoService.save(produto);

        var produtoResponseDto = modelMapper.map(produto, ProdutoResponseDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoResponseDto);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> update(@PathVariable("id") Long id, @RequestBody JsonPatch jsonPatch)
            throws ProdutoNotFound, JsonPatchException, JsonProcessingException {

        var produto = produtoService.findByID(id).orElseThrow(() -> new ProdutoNotFound(id));

        var objectMapper = new ObjectMapper()
                .disable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                .enable(Feature.WRITE_BIGDECIMAL_AS_PLAIN)
                .setNodeFactory(JsonNodeFactory.withExactBigDecimals(true));

        var jsonProduto = objectMapper.convertValue(produto, JsonNode.class);

        jsonProduto = jsonPatch.apply(jsonProduto);

        produto = objectMapper.treeToValue(jsonProduto, Produto.class);

        produto = produtoService.save(produto);

        var produtoResponseDto = modelMapper.map(produto, ProdutoResponseDto.class);

        return ResponseEntity.ok(produtoResponseDto);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> update(Long id, ProdutoPersistDto dto) throws ProdutoNotFound {

        var produto = produtoService.update(id, dto);

        var produtoResponseDto = modelMapper.map(produto, ProdutoResponseDto.class);

        return ResponseEntity.ok(produtoResponseDto);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(Long id) throws ProdutoNotFound {

        var produto = produtoService.findByID(id).orElseThrow(() -> new ProdutoNotFound(id));

        produtoService.delete(produto);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> findByID(@PathVariable("id") Long id) throws ProdutoNotFound {

        var produto = produtoService.findByID(id).orElseThrow(() -> new ProdutoNotFound(id));

        var produtoResponseDto = modelMapper.map(produto, ProdutoResponseDto.class);

        return ResponseEntity.ok(produtoResponseDto);
    }
}