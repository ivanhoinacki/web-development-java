package com.ivanhoinacki.tarefa.service;

import com.ivanhoinacki.tarefa.dto.TarefaRequest;
import com.ivanhoinacki.tarefa.exception.TarefaNotFoundException;
import com.ivanhoinacki.tarefa.model.Tarefa;
import com.ivanhoinacki.tarefa.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Serviço para gerenciamento de tarefas
 * 
 * @author Ivan Augusto Hoinacki - RU 4620954
 * @version 1.0
 */
@Service
@Transactional
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    /**
     * Lista todas as tarefas
     */
    @Transactional(readOnly = true)
    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    /**
     * Busca tarefa por ID
     */
    @Transactional(readOnly = true)
    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new TarefaNotFoundException(id));
    }

    /**
     * Cria uma nova tarefa
     */
    public Tarefa criar(TarefaRequest request) {
        Tarefa tarefa = new Tarefa(request.getNome(), request.getDataEntrega(), request.getResponsavel());
        tarefa.setConcluida(request.getConcluida() != null ? request.getConcluida() : false);

        return tarefaRepository.save(tarefa);
    }

    /**
     * Atualiza uma tarefa existente
     */
    public Tarefa atualizar(Long id, TarefaRequest request) {
        Tarefa tarefa = buscarPorId(id);
        
        tarefa.setNome(request.getNome());
        tarefa.setDataEntrega(request.getDataEntrega());
        tarefa.setResponsavel(request.getResponsavel());
        
        if (request.getConcluida() != null) {
            tarefa.setConcluida(request.getConcluida());
        }

        return tarefaRepository.save(tarefa);
    }

    /**
     * Remove uma tarefa
     */
    public void deletar(Long id) {
        if (!tarefaRepository.existsById(id)) {
            throw new TarefaNotFoundException(id);
        }
        
        tarefaRepository.deleteById(id);
    }

    /**
     * Lista tarefas pendentes (não concluídas)
     */
    @Transactional(readOnly = true)
    public List<Tarefa> listarPendentes() {
        return tarefaRepository.findTarefasPendentes();
    }

    /**
     * Lista tarefas por responsável
     */
    @Transactional(readOnly = true)
    public List<Tarefa> listarPorResponsavel(String responsavel) {
        return tarefaRepository.findByResponsavelContainingIgnoreCase(responsavel);
    }

    /**
     * Lista tarefas por data de entrega
     */
    @Transactional(readOnly = true)
    public List<Tarefa> listarPorDataEntrega(LocalDate dataEntrega) {
        return tarefaRepository.findByDataEntrega(dataEntrega);
    }

    /**
     * Lista tarefas por status de conclusão
     */
    @Transactional(readOnly = true)
    public List<Tarefa> listarPorStatus(Boolean concluida) {
        return tarefaRepository.findByConcluida(concluida);
    }

    /**
     * Marca tarefa como concluída
     */
    public Tarefa marcarComoConcluida(Long id) {
        Tarefa tarefa = buscarPorId(id);
        tarefa.setConcluida(true);
        
        return tarefaRepository.save(tarefa);
    }

    /**
     * Lista tarefas com prazo vencido
     */
    @Transactional(readOnly = true)
    public List<Tarefa> listarVencidas() {
        LocalDate hoje = LocalDate.now();
        return tarefaRepository.findByDataEntregaLessThanEqual(hoje);
    }

    /**
     * Conta tarefas por status
     */
    @Transactional(readOnly = true)
    public long contarPorStatus(Boolean concluida) {
        return tarefaRepository.countByConcluida(concluida);
    }
} 