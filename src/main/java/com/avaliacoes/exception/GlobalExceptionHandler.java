package com.avaliacoes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * MANIPULADOR GLOBAL DE EXCEÇÕES
 * 
 * Esta classe captura exceções lançadas em qualquer lugar da aplicação
 * e retorna respostas HTTP adequadas com mensagens de erro amigáveis.
 * 
 * @RestControllerAdvice marca esta classe como um interceptador global
 * de exceções para todos os controllers REST.
 * 
 * Benefícios:
 * - Centraliza o tratamento de erros
 * - Retorna respostas padronizadas
 * - Melhora a experiência do cliente da API
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * MANIPULAR RECURSO NÃO ENCONTRADO
     * 
     * Captura a exceção ResourceNotFoundException e retorna HTTP 404 (Not Found)
     * 
     * @param ex Exceção capturada
     * @return Resposta com status 404 e mensagem de erro
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("error", "Recurso não encontrado");
        errorResponse.put("message", ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    
    /**
     * MANIPULAR DADOS INVÁLIDOS
     * 
     * Captura a exceção InvalidDataException e retorna HTTP 400 (Bad Request)
     * 
     * @param ex Exceção capturada
     * @return Resposta com status 400 e mensagem de erro
     */
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidData(InvalidDataException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "Dados inválidos");
        errorResponse.put("message", ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    
    /**
     * MANIPULAR VALIDAÇÕES DO BEAN VALIDATION
     * 
     * Captura erros de validação dos DTOs (anotações @NotNull, @Min, @Max, etc.)
     * e retorna HTTP 400 com detalhes de todos os campos inválidos
     * 
     * @param ex Exceção de validação capturada
     * @return Resposta com status 400 e lista de erros de validação
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "Erro de validação");
        errorResponse.put("errors", errors);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    
    /**
     * MANIPULAR EXCEÇÕES GENÉRICAS
     * 
     * Captura qualquer exceção não tratada pelos outros handlers
     * e retorna HTTP 500 (Internal Server Error)
     * 
     * @param ex Exceção genérica capturada
     * @return Resposta com status 500 e mensagem de erro
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.put("error", "Erro interno do servidor");
        errorResponse.put("message", ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
