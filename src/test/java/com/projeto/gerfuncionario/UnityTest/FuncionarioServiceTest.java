package com.projeto.gerfuncionario.UnityTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.gerfuncionario.controller.FuncionarioController;
import com.projeto.gerfuncionario.dto.FuncionarioDTO;
import com.projeto.gerfuncionario.service.FuncionarioService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FuncionarioController.class)
class FuncionarioControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private FuncionarioService funcionarioService;

        @Autowired
        private ObjectMapper objectMapper;

        // ------------------------------------------------
        // MÉTODO AUXILIAR PARA CRIAR DTO PADRÃO
        // ------------------------------------------------
        private FuncionarioDTO criarDTO(Long id, String nome, String cpf) {
                return new FuncionarioDTO(
                                id,
                                nome,
                                cpf,
                                null,
                                null,
                                null,
                                null,
                                2500.0,
                                'M',
                                "Analista",
                                null,
                                null);
        }

        // -------------------------------------
        // TESTE POST /funcionarios
        // -------------------------------------
        @Test
        void deveCriarFuncionario() throws Exception {

                FuncionarioDTO dto = criarDTO(1L, "Eduardo", "12345678909");

                when(funcionarioService.criarFuncionario(any(FuncionarioDTO.class)))
                                .thenReturn(dto);

                mockMvc.perform(post("/funcionarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.nmFuncionario").value("Eduardo"))
                                .andExpect(jsonPath("$.cpfFuncionario").value("12345678909"));
        }

        // -------------------------------------
        // TESTE GET /funcionarios
        // -------------------------------------
        @Test
        void deveListarFuncionarios() throws Exception {

                FuncionarioDTO dto = criarDTO(1L, "João", "12345678900");

                when(funcionarioService.listarFuncionarios())
                                .thenReturn(List.of(dto));

                mockMvc.perform(get("/funcionarios"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].nmFuncionario").value("João"));
        }

        // -------------------------------------
        // TESTE GET /funcionarios/{id}
        // -------------------------------------
        @Test
        void deveBuscarPorId() throws Exception {

                FuncionarioDTO dto = criarDTO(1L, "Carlos", "11111111111");

                when(funcionarioService.buscarPorId(1L)).thenReturn(dto);

                mockMvc.perform(get("/funcionarios/1"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.nmFuncionario").value("Carlos"));
        }

        // -------------------------------------
        // TESTE BUSCAR POR NOME
        // -------------------------------------
        @Test
        void deveBuscarPorNome() throws Exception {

                FuncionarioDTO dto = criarDTO(1L, "Marcos", "22222222222");

                when(funcionarioService.buscarPorNome("Marcos"))
                                .thenReturn(List.of(dto));

                mockMvc.perform(get("/funcionarios/nome/Marcos"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].nmFuncionario").value("Marcos"));
        }

        // -------------------------------------
        // TESTE DELETE CPF
        // -------------------------------------
        @Test
        void deveExcluirPorCpf() throws Exception {

                doNothing().when(funcionarioService).excluirPorCpf("12345678909");

                mockMvc.perform(delete("/funcionarios/cpf/12345678909"))
                                .andExpect(status().isNoContent());
        }
}