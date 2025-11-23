package com.avaliacoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CLASSE PRINCIPAL DA APLICAÇÃO SPRING BOOT
 * 
 * ATENÇÃO: Esta classe foi criada apenas para fins de TESTE e DEMONSTRAÇÃO.
 * 
 * Quando você integrar este módulo ao projeto principal da sua equipe,
 * você pode REMOVER esta classe, pois a classe principal já estará
 * no projeto deles.
 * 
 * Esta classe serve apenas para:
 * - Permitir executar e testar o módulo de forma independente
 * - Demonstrar que o código está funcionando corretamente
 * - Validar os endpoints REST antes da integração
 * 
 * @SpringBootApplication é uma anotação que combina três anotações:
 * - @Configuration: marca como classe de configuração
 * - @EnableAutoConfiguration: habilita configuração automática do Spring
 * - @ComponentScan: escaneia componentes no pacote com.avaliacoes e subpacotes
 */
@SpringBootApplication
public class AvaliacoesApplication {
    
    /**
     * MÉTODO MAIN - Ponto de entrada da aplicação
     * 
     * Este método inicia o servidor Spring Boot na porta 8080
     * 
     * @param args Argumentos da linha de comando
     */
    public static void main(String[] args) {
        SpringApplication.run(AvaliacoesApplication.class, args);
        
        System.out.println("\n========================================");
        System.out.println("MÓDULO DE AVALIAÇÕES INICIADO!");
        System.out.println("========================================");
        System.out.println("Servidor rodando em: http://localhost:8080");
        System.out.println("Console H2: http://localhost:8080/h2-console");
        System.out.println("========================================");
        System.out.println("\nEndpoints disponíveis:");
        System.out.println("POST   /avaliacoes          - Criar avaliação");
        System.out.println("GET    /avaliacoes          - Listar todas");
        System.out.println("GET    /avaliacoes/{id}     - Buscar por ID");
        System.out.println("GET    /avaliacoes/ponto/{pontoId} - Listar por ponto");
        System.out.println("PUT    /avaliacoes/{id}     - Atualizar");
        System.out.println("DELETE /avaliacoes/{id}     - Excluir");
        System.out.println("========================================\n");
    }
}
