package com.ivanhoinacki.tarefa.repository;

import com.ivanhoinacki.tarefa.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repositório para operações de persistência da entidade Tarefa
 * 
 * @author Ivan Augusto Hoinacki - RU 4620954
 * @version 1.0
 */
@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    /**
     * Busca tarefas por responsável
     */
    List<Tarefa> findByResponsavelContainingIgnoreCase(String responsavel);

    /**
     * Busca tarefas por status de conclusão
     */
    List<Tarefa> findByConcluida(Boolean concluida);

    /**
     * Busca tarefas por data de entrega
     */
    List<Tarefa> findByDataEntrega(LocalDate dataEntrega);

    /**
     * Busca tarefas com data de entrega até uma data específica
     */
    List<Tarefa> findByDataEntregaLessThanEqual(LocalDate data);

    /**
     * Busca tarefas por nome contendo o termo especificado
     */
    List<Tarefa> findByNomeContainingIgnoreCase(String nome);

    /**
     * Busca tarefas pendentes (não concluídas)
     */
    @Query("SELECT t FROM Tarefa t WHERE t.concluida = false ORDER BY t.dataEntrega ASC")
    List<Tarefa> findTarefasPendentes();

    /**
     * Busca tarefas por responsável e status
     */
    @Query("SELECT t FROM Tarefa t WHERE t.responsavel LIKE %:responsavel% AND t.concluida = :concluida")
    List<Tarefa> findByResponsavelAndStatus(@Param("responsavel") String responsavel, @Param("concluida") Boolean concluida);

    /**
     * Conta tarefas por status
     */
    long countByConcluida(Boolean concluida);

    /**
     * Verifica se existe tarefa com o nome especificado
     */
    boolean existsByNomeIgnoreCase(String nome);
} 