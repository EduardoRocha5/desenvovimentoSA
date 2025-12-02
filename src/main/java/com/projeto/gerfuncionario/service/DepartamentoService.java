package com.projeto.gerfuncionario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.gerfuncionario.model.Departamento;
import com.projeto.gerfuncionario.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    // Criar novo departamento
    public Departamento criarDepartamento(Departamento departamento) {

        // Validação: nome não pode ser nulo ou vazio
        if (departamento.getNmDepartamento() == null || departamento.getNmDepartamento().isBlank()) {
            throw new IllegalArgumentException("O nome do departamento é obrigatório.");
        }

        // Validação: categoria não pode ser nula
        if (departamento.getCatDepartamento() == null || departamento.getCatDepartamento().isBlank()) {
            throw new IllegalArgumentException("A categoria do departamento é obrigatória.");
        }

        return departamentoRepository.save(departamento);
    }

    // Listar todos os departamentos
    public List<Departamento> listarDepartamentos() {
        return departamentoRepository.findAll();
    }

    // Buscar departamento pelo nome
    public Departamento buscarPorNome(String nome) {
        return departamentoRepository.findBynmDepartamento(nome);
    }

    // Atualizar departamento
    public Departamento atualizarDepartamento(Long id, Departamento novoDepartamento) {

        Departamento existente = departamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Departamento não encontrado."));

        // Atualiza campos
        existente.setNmDepartamento(novoDepartamento.getNmDepartamento());
        existente.setTelDepartamento(novoDepartamento.getTelDepartamento());
        existente.setCatDepartamento(novoDepartamento.getCatDepartamento());

        return departamentoRepository.save(existente);
    }

    // Deletar pelo ID
    public void deletarDepartamento(Long id) {

        if (!departamentoRepository.existsById(id)) {
            throw new IllegalArgumentException("Departamento não encontrado para exclusão.");
        }

        departamentoRepository.deleteById(id);
    }

    // Deletar pelo nome (opcional)
    public void deletarPorNome(String nome) {

        Departamento dept = departamentoRepository.findBynmDepartamento(nome);

        if (dept == null) {
            throw new IllegalArgumentException("Nenhum departamento encontrado com este nome.");
        }

        departamentoRepository.delete(dept);
    }
}
