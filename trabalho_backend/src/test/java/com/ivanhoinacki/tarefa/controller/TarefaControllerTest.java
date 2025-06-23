package com.ivanhoinacki.tarefa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivanhoinacki.tarefa.dto.TarefaRequest;
import com.ivanhoinacki.tarefa.model.Tarefa;
import com.ivanhoinacki.tarefa.service.TarefaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testes unitários para o TarefaController
 * 
 * @author Ivan Augusto Hoinacki - RU 4620954
 * @version 1.0
 */
@WebMvcTest(TarefaController.class)
class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarefaService tarefaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListarTodas() throws Exception {
        // Arrange
        Tarefa tarefa1 = new Tarefa("Estudar Java", LocalDate.now().plusDays(7), "Ivan Hoinacki");
        tarefa1.setId(1L);
        
        Tarefa tarefa2 = new Tarefa("Fazer exercícios", LocalDate.now().plusDays(3), "Ivan Hoinacki");
        tarefa2.setId(2L);

        when(tarefaService.listarTodas()).thenReturn(Arrays.asList(tarefa1, tarefa2));

        // Act & Assert
        mockMvc.perform(get("/tarefas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Estudar Java"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nome").value("Fazer exercícios"));
    }

    @Test
    void testBuscarPorId() throws Exception {
        // Arrange
        Tarefa tarefa = new Tarefa("Estudar Java", LocalDate.now().plusDays(7), "Ivan Hoinacki");
        tarefa.setId(1L);

        when(tarefaService.buscarPorId(1L)).thenReturn(tarefa);

        // Act & Assert
        mockMvc.perform(get("/tarefas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Estudar Java"))
                .andExpect(jsonPath("$.responsavel").value("Ivan Hoinacki"));
    }

    @Test
    void testCriarTarefa() throws Exception {
        // Arrange
        TarefaRequest request = new TarefaRequest();
        request.setNome("Estudar Java - Ivan Augusto Hoinacki");
        request.setDataEntrega(LocalDate.of(2025, 6, 30));
        request.setResponsavel("Ivan Augusto Hoinacki - RU 4620954");

        Tarefa tarefaSalva = new Tarefa(request.getNome(), request.getDataEntrega(), request.getResponsavel());
        tarefaSalva.setId(1L);

        when(tarefaService.criar(any(TarefaRequest.class))).thenReturn(tarefaSalva);

        // Act & Assert
        mockMvc.perform(post("/tarefas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Estudar Java - Ivan Augusto Hoinacki"))
                .andExpect(jsonPath("$.responsavel").value("Ivan Augusto Hoinacki - RU 4620954"));
    }

    @Test
    void testHealthCheck() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/tarefas/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("API de Tarefas funcionando! - Ivan Augusto Hoinacki - RU 4620954"));
    }
} 