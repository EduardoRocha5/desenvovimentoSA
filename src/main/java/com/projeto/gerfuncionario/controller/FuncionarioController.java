package com.projeto.gerfuncionario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projeto.gerfuncionario.model.Funcionario;
import com.projeto.gerfuncionario.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    // Criar funcionário
    @PostMapping
    public ResponseEntity<?> criarFunc(@RequestBody Funcionario funcionario) {
        try {
            Funcionario salvo = funcionarioService.criarFuncionario(funcionario);
            return ResponseEntity.status(201).body(salvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar funcionário: " + e.getMessage());
        }
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<Funcionario>> listarFunc() {
        return ResponseEntity.ok(funcionarioService.listarFuncionarios());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Funcionario funcionario = funcionarioService.buscarPorId(id);
            return ResponseEntity.ok(funcionario);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Funcionário não encontrado: " + e.getMessage());
        }
    }

    // Buscar por nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<?> buscarPorNome(@PathVariable String nome) {
        List<Funcionario> funcionarios = funcionarioService.buscarPorNome(nome);

        if (funcionarios.isEmpty()) {
            return ResponseEntity.status(404).body("Nenhum funcionário encontrado com o nome: " + nome);
        }

        return ResponseEntity.ok(funcionarios);
    }

    // Excluir por CPF
    @DeleteMapping("/cpf/{cpf}")
    public ResponseEntity<?> excluirPorCpf(@PathVariable String cpf) {
        try {
            funcionarioService.excluirPorCpf(cpf);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao excluir: " + e.getMessage());
        }
    }
}
