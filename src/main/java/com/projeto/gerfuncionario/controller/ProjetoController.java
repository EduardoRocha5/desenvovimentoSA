package com.projeto.gerfuncionario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projeto.gerfuncionario.dto.ProjetoDTO;
import com.projeto.gerfuncionario.service.ProjetoService;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    // Criar projeto
    @PostMapping
    public ResponseEntity<ProjetoDTO> criar(@RequestBody ProjetoDTO dto) {

        ProjetoDTO novo = projetoService.salvar(dto);

        return ResponseEntity.status(201).body(novo);
    }

    // Listar todos os projetos
    @GetMapping
    public ResponseEntity<List<ProjetoDTO>> listarTodos() {

        List<ProjetoDTO> projetos = projetoService.exibirTodosProjetos();

        return ResponseEntity.ok(projetos);
    }

    // Buscar projeto por nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<?> buscarPorNome(@PathVariable String nome) {

        List<ProjetoDTO> projetos = projetoService.exibirPorNome(nome);

        if (projetos.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("Nenhum projeto encontrado com o nome: " + nome);
        }

        return ResponseEntity.ok(projetos);
    }

    // Mostrar status do projeto
    @GetMapping("/status/{nomeProjeto}")
    public ResponseEntity<String> statusProjeto(@PathVariable String nomeProjeto) {

        String status = projetoService.stsProjeto(nomeProjeto);

        return ResponseEntity.ok(status);
    }


    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> excluirPorId(@PathVariable Long id) {
        String resultado = projetoService.excluirPorId(id);
        return ResponseEntity.ok(resultado);
        
    }
}