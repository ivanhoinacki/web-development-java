package com.ivanhoinacki.tarefa.dto;

import com.ivanhoinacki.tarefa.model.Tarefa;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO para respostas da API de tarefas
 * 
 * @author Ivan Augusto Hoinacki - RU 4620954
 * @version 1.0
 */
public class TarefaResponse {

    private Long id;
    private String nome;
    private LocalDate dataEntrega;
    private String responsavel;
    private Boolean concluida;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public TarefaResponse() {}

    public TarefaResponse(Long id, String nome, LocalDate dataEntrega, String responsavel, Boolean concluida, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.dataEntrega = dataEntrega;
        this.responsavel = responsavel;
        this.concluida = concluida;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public static TarefaResponse fromEntity(Tarefa tarefa) {
        TarefaResponse response = new TarefaResponse();
        response.setId(tarefa.getId());
        response.setNome(tarefa.getNome());
        response.setDataEntrega(tarefa.getDataEntrega());
        response.setResponsavel(tarefa.getResponsavel());
        response.setConcluida(tarefa.getConcluida());
        response.setDataCriacao(tarefa.getDataCriacao());
        response.setDataAtualizacao(tarefa.getDataAtualizacao());
        return response;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public LocalDate getDataEntrega() { return dataEntrega; }
    public void setDataEntrega(LocalDate dataEntrega) { this.dataEntrega = dataEntrega; }
    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }
    public Boolean getConcluida() { return concluida; }
    public void setConcluida(Boolean concluida) { this.concluida = concluida; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
} 