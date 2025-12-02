package com.projeto.gerfuncionario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projeto.gerfuncionario.model.Projeto;
import com.projeto.gerfuncionario.service.ProjetoService;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    // Criar projeto
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Projeto projeto) {
        Projeto novo = projetoService.salvar(projeto);
        return ResponseEntity.status(201).body(novo);
    }

    // Listar todos os projetos
    @GetMapping
    public ResponseEntity<List<Projeto>> listarTodos() {
        return ResponseEntity.ok(projetoService.exibirTodosProjetos());
    }

    // Buscar por nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<?> buscarPorNome(@PathVariable String nome) {
        List<Projeto> projetos = projetoService.exibirPorNome(nome);

        if (projetos.isEmpty()) {
            return ResponseEntity.status(404).body("Nenhum projeto encontrado com o nome: " + nome);
        }

        return ResponseEntity.ok(projetos);
    }

    // Mostrar o status de um projeto
    @GetMapping("/status/{nomeProjeto}")
    public ResponseEntity<String> statusProjeto(@PathVariable String nomeProjeto) {
        String resposta = projetoService.stsProjeto(nomeProjeto);
        return ResponseEntity.ok(resposta);
    }

    // Excluir por nome
    @DeleteMapping("/{nome}")
    public ResponseEntity<String> excluir(@PathVariable String nome) {
        String resultado = projetoService.excluirProjeto(nome);
        return ResponseEntity.ok(resultado);
    }
}
