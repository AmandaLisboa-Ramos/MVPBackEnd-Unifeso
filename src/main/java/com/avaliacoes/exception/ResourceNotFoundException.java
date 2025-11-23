package com.avaliacoes.exception;

/**
 * EXCEÇÃO CUSTOMIZADA - RECURSO NÃO ENCONTRADO
 * 
 * Esta exceção é lançada quando um recurso solicitado não existe no banco de dados.
 * Exemplo: tentar buscar uma avaliação com ID 999 que não existe.
 * 
 * Estende RuntimeException, que é uma exceção não verificada (unchecked).
 * Isso significa que não é obrigatório declarar ou capturar esta exceção.
 */
public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * Construtor que recebe apenas a mensagem de erro
     * 
     * @param message Mensagem descritiva do erro
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    /**
     * Construtor que recebe mensagem e causa raiz do erro
     * 
     * @param message Mensagem descritiva do erro
     * @param cause Exceção que causou este erro
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
