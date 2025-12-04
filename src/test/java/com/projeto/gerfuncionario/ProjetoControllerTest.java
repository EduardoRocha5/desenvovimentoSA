package com.projeto.gerfuncionario;


import com.projeto.gerfuncionario.controller.ProjetoController;
import com.projeto.gerfuncionario.model.Projeto;
import com.projeto.gerfuncionario.service.ProjetoService;

import tools.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjetoController.class)
public class ProjetoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProjetoService projetoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarProjeto() throws Exception {
        Projeto projeto = new Projeto(
                1L,
                "Sistema X",
                "Projeto de teste",
                "iniciado",
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                null
        );

        when(projetoService.salvar(any())).thenReturn(projeto);

        mockMvc.perform(
                post("/projetos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projeto))
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.nmProjeto").value("Sistema X"));
    }

    @Test
    void deveListarTodosProjetos() throws Exception {

        List<Projeto> lista = Arrays.asList(
                new Projeto(1L, "P1", "D1", "iniciado", LocalDate.now(), null, null),
                new Projeto(2L, "P2", "D2", "andamento", LocalDate.now(), null, null)
        );

        when(projetoService.exibirTodosProjetos()).thenReturn(lista);

        mockMvc.perform(get("/projetos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nmProjeto").value("P1"))
                .andExpect(jsonPath("$[1].nmProjeto").value("P2"));
    }

    @Test
    void deveBuscarPorNome() throws Exception {

        List<Projeto> lista = Collections.singletonList(
                new Projeto(1L, "Sistema X", "desc", "iniciado", LocalDate.now(), null, null)
        );

        when(projetoService.exibirPorNome("Sistema X")).thenReturn(lista);

        mockMvc.perform(get("/projetos/nome/Sistema X"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nmProjeto").value("Sistema X"));
    }

    @Test
    void deveRetornar404QuandoNaoEncontrarNome() throws Exception {

        when(projetoService.exibirPorNome("Inexistente")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/projetos/nome/Inexistente"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Nenhum projeto encontrado com o nome: Inexistente"));
    }

    @Test
    void deveRetornarStatusDoProjeto() throws Exception {

        when(projetoService.stsProjeto("Sistema X")).thenReturn("iniciado");

        mockMvc.perform(get("/projetos/status/Sistema X"))
                .andExpect(status().isOk())
                .andExpect(content().string("iniciado"));
    }

    @Test
    void deveExcluirProjeto() throws Exception {

        when(projetoService.excluirProjeto("Sistema X"))
                .thenReturn("Projeto removido com sucesso");

        mockMvc.perform(delete("/projetos/Sistema X"))
                .andExpect(status().isOk())
                .andExpect(content().string("Projeto removido com sucesso"));
    }
}
