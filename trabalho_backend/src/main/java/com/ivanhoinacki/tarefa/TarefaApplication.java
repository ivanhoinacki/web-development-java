package com.ivanhoinacki.tarefa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Spring Boot para gerenciamento de tarefas
 * 
 * @author Ivan Augusto Hoinacki - RU 4620954
 * @version 1.0
 */
@SpringBootApplication
public class TarefaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TarefaApplication.class, args);
        System.out.println("API de Tarefas iniciada com sucesso!");
        System.out.println("‍Desenvolvido por: Ivan Augusto Hoinacki - RU 4620954");
        System.out.println("Acesse: http://localhost:8080/api/tarefas/health");
    }
} 