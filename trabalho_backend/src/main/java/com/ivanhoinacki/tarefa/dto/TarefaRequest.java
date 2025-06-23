package com.ivanhoinacki.tarefa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO para requisições de criação e atualização de tarefas
 * 
 * @author Ivan Augusto Hoinacki - RU 4620954
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaRequest {

    @NotBlank(message = "O nome da tarefa é obrigatório")
    private String nome;

    @NotNull(message = "A data de entrega é obrigatória")
    private LocalDate dataEntrega;

    @NotBlank(message = "O responsável é obrigatório")
    private String responsavel;

    private Boolean concluida = false;

    // Getters e Setters manuais para garantir compatibilidade
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataEntrega() { return dataEntrega; }
    public void setDataEntrega(LocalDate dataEntrega) { this.dataEntrega = dataEntrega; }

    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }

    public Boolean getConcluida() { return concluida; }
    public void setConcluida(Boolean concluida) { this.concluida = concluida; }
} 