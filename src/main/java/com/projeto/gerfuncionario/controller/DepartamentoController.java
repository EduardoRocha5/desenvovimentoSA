package com.projeto.gerfuncionario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projeto.gerfuncionario.model.Departamento;
import com.projeto.gerfuncionario.service.DepartamentoService;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    // Criar departamento
    @PostMapping
    public ResponseEntity<Departamento> criarDep(@RequestBody Departamento departamento) {
        try {
            Departamento novo = departamentoService.criarDepartamento(departamento);
            return ResponseEntity.status(201).body(novo); // 201 Created
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<Departamento>> listarTodosDepartamento() {
        List<Departamento> lista = departamentoService.listarDepartamentos();
        return ResponseEntity.ok(lista); // 200 OK
    }

    // Buscar por nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Departamento> buscarPorNomeDep(@PathVariable String nome) {
        Departamento dept = departamentoService.buscarPorNome(nome);
        if (dept == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        return ResponseEntity.ok(dept); // 200 OK
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Departamento> buscarPorId(@PathVariable Long id) {
        try {
            Departamento dept = departamentoService.buscarPorIdDepartamento(id);
            return ResponseEntity.ok(dept);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Atualizar departamento
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarDepartamento(@PathVariable Long id, @RequestBody Departamento departamento) {
        try {
            Departamento atualizado = departamentoService.atualizarDepartamento(id, departamento);
            return ResponseEntity.ok(atualizado); // 200 OK
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 400 Bad Request + mensagem
        }
    }

    // Deletar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarID(@PathVariable Long id) {
        try {
            departamentoService.deletarDepartamento(id);
            return ResponseEntity.ok("Departamento deletado com sucesso!"); // 200 OK
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 400 Bad Request
        }
    }

    // Deletar por nome
    @DeleteMapping("/nome/{nome}")
    public ResponseEntity<?> deletarPorNome(@PathVariable String nome) {
        try {
            departamentoService.deletarPorNome(nome);
            return ResponseEntity.ok("Departamento deletado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
