package com.ivanhoinacki.tarefa.controller;

import com.ivanhoinacki.tarefa.dto.TarefaRequest;
import com.ivanhoinacki.tarefa.dto.TarefaResponse;
import com.ivanhoinacki.tarefa.model.Tarefa;
import com.ivanhoinacki.tarefa.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller REST para gerenciamento de tarefas
 * 
 * @author Ivan Augusto Hoinacki - RU 4620954
 * @version 1.0
 */
@RestController
@RequestMapping("/api/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    /**
     * Lista todas as tarefas
     * GET /api/tarefas
     */
    @GetMapping
    public ResponseEntity<List<TarefaResponse>> listarTodas() {
        List<Tarefa> tarefas = tarefaService.listarTodas();
        List<TarefaResponse> responses = tarefas.stream()
                .map(TarefaResponse::fromEntity)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }

    /**
     * Busca tarefa por ID
     * GET /api/tarefas/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponse> buscarPorId(@PathVariable("id") Long id) {
        Tarefa tarefa = tarefaService.buscarPorId(id);
        return ResponseEntity.ok(TarefaResponse.fromEntity(tarefa));
    }

    /**
     * Cria uma nova tarefa
     * POST /api/tarefas
     */
    @PostMapping
    public ResponseEntity<TarefaResponse> criar(@Valid @RequestBody TarefaRequest request) {
        Tarefa tarefa = tarefaService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TarefaResponse.fromEntity(tarefa));
    }

    /**
     * Atualiza uma tarefa existente
     * PUT /api/tarefas/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponse> atualizar(@PathVariable("id") Long id, 
                                                   @Valid @RequestBody TarefaRequest request) {
        Tarefa tarefa = tarefaService.atualizar(id, request);
        return ResponseEntity.ok(TarefaResponse.fromEntity(tarefa));
    }

    /**
     * Remove uma tarefa
     * DELETE /api/tarefas/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Lista tarefas por responsável
     * GET /api/tarefas/responsavel/{responsavel}
     */
    @GetMapping("/responsavel/{responsavel}")
    public ResponseEntity<List<TarefaResponse>> listarPorResponsavel(@PathVariable("responsavel") String responsavel) {
        List<Tarefa> tarefas = tarefaService.listarPorResponsavel(responsavel);
        List<TarefaResponse> responses = tarefas.stream()
                .map(TarefaResponse::fromEntity)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }

    /**
     * Lista tarefas por data de entrega
     * GET /api/tarefas/entrega/{data}
     */
    @GetMapping("/entrega/{data}")
    public ResponseEntity<List<TarefaResponse>> listarPorDataEntrega(@PathVariable("data") String data) {
        LocalDate dataEntrega = LocalDate.parse(data);
        List<Tarefa> tarefas = tarefaService.listarPorDataEntrega(dataEntrega);
        List<TarefaResponse> responses = tarefas.stream()
                .map(TarefaResponse::fromEntity)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }

    /**
     * Lista tarefas pendentes
     * GET /api/tarefas/pendentes
     */
    @GetMapping("/pendentes")
    public ResponseEntity<List<TarefaResponse>> listarPendentes() {
        List<Tarefa> tarefas = tarefaService.listarPendentes();
        List<TarefaResponse> responses = tarefas.stream()
                .map(TarefaResponse::fromEntity)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }

    /**
     * Lista tarefas por status
     * GET /api/tarefas/status/{concluida}
     */
    @GetMapping("/status/{concluida}")
    public ResponseEntity<List<TarefaResponse>> listarPorStatus(@PathVariable("concluida") Boolean concluida) {
        List<Tarefa> tarefas = tarefaService.listarPorStatus(concluida);
        List<TarefaResponse> responses = tarefas.stream()
                .map(TarefaResponse::fromEntity)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }

    /**
     * Marca tarefa como concluída
     * PATCH /api/tarefas/{id}/concluir
     */
    @PatchMapping("/{id}/concluir")
    public ResponseEntity<TarefaResponse> marcarComoConcluida(@PathVariable("id") Long id) {
        Tarefa tarefa = tarefaService.marcarComoConcluida(id);
        return ResponseEntity.ok(TarefaResponse.fromEntity(tarefa));
    }

    /**
     * Lista tarefas vencidas
     * GET /api/tarefas/vencidas
     */
    @GetMapping("/vencidas")
    public ResponseEntity<List<TarefaResponse>> listarVencidas() {
        List<Tarefa> tarefas = tarefaService.listarVencidas();
        List<TarefaResponse> responses = tarefas.stream()
                .map(TarefaResponse::fromEntity)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }

    /**
     * Conta tarefas por status
     * GET /api/tarefas/contar/{concluida}
     */
    @GetMapping("/contar/{concluida}")
    public ResponseEntity<Long> contarPorStatus(@PathVariable("concluida") Boolean concluida) {
        long quantidade = tarefaService.contarPorStatus(concluida);
        return ResponseEntity.ok(quantidade);
    }

    /**
     * Endpoint de health check
     * GET /api/tarefas/health
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("API de Tarefas funcionando! - Ivan Augusto Hoinacki - RU 4620954");
    }
} 