package br.com.urbainski.microservices.produtos.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.urbainski.microservices.produtos.dto.ErrorDto;
import br.com.urbainski.microservices.produtos.exception.GenericNotFound;

import io.swagger.v3.oas.annotations.Hidden;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @Value("${springdoc.swagger-ui.path}")
    private String pathDocumentation;

    @Hidden
    @ResponseBody
    @ExceptionHandler(GenericNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto onResourceNotFound(GenericNotFound genericNotFound) {

        return new ErrorDto("X_100", genericNotFound.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto onMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {

        return new ErrorDto("X_200", methodArgumentTypeMismatchException.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorDto> onMethod(MethodArgumentNotValidException methodArgumentNotValidException) {

        final List<ErrorDto> listError = methodArgumentNotValidException
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ErrorDto("X_200",
                        String.format("%s %s", fieldError.getField(), fieldError.getDefaultMessage())))
                .collect(Collectors.toList());
        return listError;
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto onException(Exception ex) {

        return new ErrorDto("X_300", ex.getMessage());
    }

}