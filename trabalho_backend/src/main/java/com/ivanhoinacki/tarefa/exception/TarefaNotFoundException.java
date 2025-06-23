package com.ivanhoinacki.tarefa.exception;

/**
 * Exceção lançada quando uma tarefa não é encontrada
 * 
 * @author Ivan Augusto Hoinacki - RU 4620954
 * @version 1.0
 */
public class TarefaNotFoundException extends RuntimeException {

    public TarefaNotFoundException(String message) {
        super(message);
    }

    public TarefaNotFoundException(Long id) {
        super("Tarefa não encontrada com ID: " + id);
    }
} 