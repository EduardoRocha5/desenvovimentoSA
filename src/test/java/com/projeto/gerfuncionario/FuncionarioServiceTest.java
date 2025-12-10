package com.projeto.gerfuncionario;

// -----------------------------------------------
// IMPORTS NECESSÁRIOS PARA TESTE DO CONTROLLER
// -----------------------------------------------

import com.fasterxml.jackson.databind.ObjectMapper;
// Converte objetos Java para JSON durante os testes

import com.projeto.gerfuncionario.controller.FuncionarioController;
// Controller que será testado

import com.projeto.gerfuncionario.model.Funcionario;
// Modelo usado nos testes

import com.projeto.gerfuncionario.service.FuncionarioService;
// Service será mockado (simulado) nos testes

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// Anotação que carrega somente o Controller na aplicação de teste

import org.springframework.boot.test.mock.mockito.MockBean;
// Permite criar mocks do service

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
// Biblioteca principal para simular chamadas HTTP

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
// Importa funções do Mockito para simular comportamentos

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// Permite criar chamadas GET, POST, DELETE etc

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
// Permite validar respostas das requisições

// -------------------------------------------------------
// TESTE DE CONTROLLER USANDO MockMvc
// -------------------------------------------------------
@WebMvcTest(FuncionarioController.class)
// Carrega somente o FuncionarioController
class FuncionarioServiceTest {

        @Autowired
        private MockMvc mockMvc;
        // Objeto responsável por simular requisições HTTP

        @MockBean
        private FuncionarioService funcionarioService;
        // Service é mockado porque o controller depende dele

        @Autowired
        private ObjectMapper objectMapper;
        // Converte objetos Java para JSON

        // -------------------------------------------------------
        // TESTE POST /funcionarios (Criar funcionário)
        // -------------------------------------------------------
        @Test
        void deveCriarFuncionario() throws Exception {

                // Criando objeto funcionário que será enviado no JSON
                Funcionario func = new Funcionario();
                func.setNmFuncionario("Eduardo");
                func.setCpfFuncionario("12345678909");
                func.setSalFuncionario(2500.00);

                // Mock: quando o service for chamado → retorna o funcionário criado
                when(funcionarioService.criarFuncionario(any(Funcionario.class)))
                                .thenReturn(func);

                // Faz a requisição POST simulada
                mockMvc.perform(post("/funcionarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(func)))
                                // Espera status 201 CREATED
                                .andExpect(status().isCreated())
                                // Valida campos do JSON retornado
                                .andExpect(jsonPath("$.nmFuncionario").value("Eduardo"))
                                .andExpect(jsonPath("$.cpfFuncionario").value("12345678909"));
        }

        // -------------------------------------------------------
        // TESTE POST COM ERRO (CPF inválido)
        // -------------------------------------------------------
        @Test
        void deveRetornarBadRequestAoCriarFuncionarioComErro() throws Exception {

                Funcionario func = new Funcionario();
                func.setNmFuncionario("Eduardo");

                // Mock: Força o service a lançar exceção
                when(funcionarioService.criarFuncionario(any()))
                                .thenThrow(new RuntimeException("CPF inválido"));

                // Envia POST e valida retorno 400 BAD REQUEST
                mockMvc.perform(post("/funcionarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(func)))
                                .andExpect(status().isBadRequest())
                                .andExpect(content().string("Erro ao criar funcionário: CPF inválido"));
        }

        // -------------------------------------------------------
        // TESTE GET /funcionarios (Listar todos)
        // -------------------------------------------------------
        @Test
        void deveListarFuncionarios() throws Exception {

                Funcionario f = new Funcionario();
                f.setNmFuncionario("João");

                // Mock: quando listarFuncionarios() for chamado → retorna lista com João
                when(funcionarioService.listarFuncionarios()).thenReturn(List.of(f));

                // Validação do retorno
                mockMvc.perform(get("/funcionarios"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].nmFuncionario").value("João"));
        }

        // -------------------------------------------------------
        // TESTE GET /funcionarios/{id} (Buscar por ID)
        // -------------------------------------------------------
        @Test
        void deveBuscarPorId() throws Exception {

                Funcionario f = new Funcionario();
                f.setIdFuncionario(1L);
                f.setNmFuncionario("Carlos");

                when(funcionarioService.buscarPorId(1L)).thenReturn(f);

                mockMvc.perform(get("/funcionarios/1"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.nmFuncionario").value("Carlos"));
        }

        // Caso ID não exista
        @Test
        void deveRetornar404QuandoIdNaoEncontrado() throws Exception {

                when(funcionarioService.buscarPorId(1L))
                                .thenThrow(new RuntimeException("ID inexistente"));

                mockMvc.perform(get("/funcionarios/1"))
                                .andExpect(status().isNotFound())
                                .andExpect(content().string("Funcionário não encontrado: ID inexistente"));
        }

        // -------------------------------------------------------
        // TESTE GET /funcionarios/nome/{nome}
        // -------------------------------------------------------
        @Test
        void deveBuscarPorNome() throws Exception {

                Funcionario f = new Funcionario();
                f.setNmFuncionario("Marcos");

                when(funcionarioService.buscarPorNome("Marcos")).thenReturn(List.of(f));

                mockMvc.perform(get("/funcionarios/nome/Marcos"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].nmFuncionario").value("Marcos"));
        }

        @Test
        void deveRetornar404QuandoBuscarNomeInexistente() throws Exception {

                // Mock retorna lista vazia
                when(funcionarioService.buscarPorNome("Fulano")).thenReturn(List.of());

                mockMvc.perform(get("/funcionarios/nome/Fulano"))
                                .andExpect(status().isNotFound())
                                .andExpect(content().string("Nenhum funcionário encontrado com o nome: Fulano"));
        }

        // -------------------------------------------------------
        // TESTE DELETE /funcionarios/cpf/{cpf}
        // -------------------------------------------------------
        @Test
        void deveExcluirPorCpf() throws Exception {

                // Mock do método void - usa doNothing()
                doNothing().when(funcionarioService).excluirPorCpf("12345678909");

                // DELETE deve retornar apenas status 204 (No Content)
                mockMvc.perform(delete("/funcionarios/cpf/12345678909"))
                                .andExpect(status().isNoContent());
        }

        @Test
        void deveRetornarErroAoExcluirCpfInvalido() throws Exception {

                // Mock: se tentar excluir CPF inválido → lança erro
                doThrow(new RuntimeException("CPF não encontrado"))
                                .when(funcionarioService).excluirPorCpf("00000000000");

                mockMvc.perform(delete("/funcionarios/cpf/00000000000"))
                                .andExpect(status().isBadRequest())
                                .andExpect(content().string("Erro ao excluir: CPF não encontrado"));
        }
}
