package com.avaliacoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AvaliacoesApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(AvaliacoesApplication.class, args);
        
        System.out.println("\n========================================");
        System.out.println("MÓDULO DE AVALIAÇÕES INICIADO!");
        System.out.println("========================================");
        System.out.println("Servidor: http://localhost:8080");
        System.out.println("Console H2: http://localhost:8080/h2-console");
        System.out.println("========================================");
        System.out.println("\nEndpoints:");
        System.out.println("POST   /avaliacoes");
        System.out.println("GET    /avaliacoes");
        System.out.println("GET    /avaliacoes/{id}");
        System.out.println("GET    /avaliacoes/ponto/{pontoId}");
        System.out.println("PUT    /avaliacoes/{id}");
        System.out.println("DELETE /avaliacoes/{id}");
        System.out.println("========================================\n");
    }
}
