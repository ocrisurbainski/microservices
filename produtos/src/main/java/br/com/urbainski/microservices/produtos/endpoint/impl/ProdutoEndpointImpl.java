package br.com.urbainski.microservices.produtos.endpoint.impl;

import br.com.urbainski.microservices.produtos.dto.ProdutoPersistDto;
import br.com.urbainski.microservices.produtos.dto.ProdutoResponseDto;
import br.com.urbainski.microservices.produtos.endpoint.IProdutoEndpoint;
import br.com.urbainski.microservices.produtos.exception.ProdutoNotFound;
import br.com.urbainski.microservices.produtos.model.Produto;
import br.com.urbainski.microservices.produtos.service.IProdutoService;
import br.com.urbainski.microservices.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class ProdutoEndpointImpl implements IProdutoEndpoint {

    private final IProdutoService produtoService;
    private final ModelMapper modelMapper;

    public ProdutoEndpointImpl(IProdutoService produtoService, ModelMapper modelMapper) {

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

        var jsonProduto = JsonUtils.convertValue(produto, JsonNode.class);

        jsonProduto = jsonPatch.apply(jsonProduto);

        produto = JsonUtils.treeToValue(jsonProduto, Produto.class);

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

    @Override
    @GetMapping
    public ResponseEntity<Page<ProdutoResponseDto>> findAll(@PageableDefault(page = 0, size = 20) Pageable pageable) {

        Page<Produto> page = produtoService.findAll(pageable);

        List<ProdutoResponseDto> listProdutoResponse = page
                .map(produto -> modelMapper.map(produto, ProdutoResponseDto.class))
                .stream().collect(Collectors.toList());

        Page<ProdutoResponseDto> pageResponse = new PageImpl<>(listProdutoResponse, pageable, page.getTotalElements());

        return ResponseEntity.ok(pageResponse);
    }

}