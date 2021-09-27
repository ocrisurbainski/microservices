package br.com.urbainski.microservices.produtos.endpoint;

import br.com.urbainski.microservices.produtos.dto.ProdutoPersistDto;
import br.com.urbainski.microservices.produtos.dto.ProdutoResponseDto;
import br.com.urbainski.microservices.produtos.exception.ProdutoNotFound;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IProdutoEndpoint {

    @PostMapping
    ResponseEntity<ProdutoResponseDto> insert(@Valid @RequestBody ProdutoPersistDto produtoPersistDto);

    @PatchMapping("/{id}")
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
    ResponseEntity<ProdutoResponseDto> update(@PathVariable("id") Long id, @RequestBody JsonPatch jsonPatch)
            throws ProdutoNotFound, JsonPatchException, JsonProcessingException;

    @PutMapping("/{id}")
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
    ResponseEntity<ProdutoResponseDto> update(@PathVariable("id") Long id, @RequestBody ProdutoPersistDto dto)
            throws ProdutoNotFound;

    @DeleteMapping("/{id}")
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

    @Operation(summary = "Retorna uma lista de produtos paginada, conforme os parâmetros fornecidos.")
    @GetMapping
    ResponseEntity<Page<ProdutoResponseDto>> findAll(@PageableDefault(page = 0, size = 20) Pageable pageable);

}