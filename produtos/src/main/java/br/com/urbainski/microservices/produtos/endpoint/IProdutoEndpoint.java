package br.com.urbainski.microservices.produtos.endpoint;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import br.com.urbainski.microservices.produtos.dto.ProdutoPersistDto;
import br.com.urbainski.microservices.produtos.dto.ProdutoResponseDto;
import br.com.urbainski.microservices.produtos.exception.ProdutoNotFound;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface IProdutoEndpoint {

    @PostMapping
    ResponseEntity<ProdutoResponseDto> insert(@Valid @RequestBody ProdutoPersistDto produtoPersistDto);

    @PatchMapping("/{id}")
    ResponseEntity<ProdutoResponseDto> update(@PathVariable("id") Long id, @RequestBody JsonPatch jsonPatch)
            throws ProdutoNotFound, JsonPatchException, JsonProcessingException;

    @PutMapping("/{id}")
    ResponseEntity<ProdutoResponseDto> update(@PathVariable("id") Long id, @RequestBody ProdutoPersistDto dto)
            throws ProdutoNotFound;

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id) throws ProdutoNotFound;

    @Operation(summary = "Retorna o produto correspondente ao identificador informado")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "codigo": "X_100",
                                                 "message": "Produto não encontrado com o id: 7",
                                                 "urlDoc": null
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("/{id}")
    ResponseEntity<ProdutoResponseDto> findByID(@PathVariable("id") Long id) throws ProdutoNotFound;
}