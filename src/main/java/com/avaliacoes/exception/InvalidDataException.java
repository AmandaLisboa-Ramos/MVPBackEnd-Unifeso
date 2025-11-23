package com.avaliacoes.exception;

/**
 * EXCEÇÃO CUSTOMIZADA - DADOS INVÁLIDOS
 * 
 * Esta exceção é lançada quando os dados fornecidos são inválidos.
 * Exemplo: nota fora do intervalo 1-5, IDs negativos, etc.
 * 
 * É usada para validações de regras de negócio na camada Service.
 */
public class InvalidDataException extends RuntimeException {
    
    /**
     * Construtor que recebe apenas a mensagem de erro
     * 
     * @param message Mensagem descritiva do erro
     */
    public InvalidDataException(String message) {
        super(message);
    }
    
    /**
     * Construtor que recebe mensagem e causa raiz do erro
     * 
     * @param message Mensagem descritiva do erro
     * @param cause Exceção que causou este erro
     */
    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
