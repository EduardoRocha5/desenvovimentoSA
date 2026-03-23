package com.projeto.gerfuncionario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.gerfuncionario.dto.DepartamentoDTO;
import com.projeto.gerfuncionario.model.Departamento;
import com.projeto.gerfuncionario.repository.DepartamentoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    // Criar novo departamento
    public DepartamentoDTO criarDepartamento(DepartamentoDTO dto) {

        if (dto.nmDepartamento() == null || dto.nmDepartamento().isBlank()) {
            throw new IllegalArgumentException("O nome do departamento é obrigatório.");
        }

        if (dto.catDepartamento() == null || dto.catDepartamento().isBlank()) {
            throw new IllegalArgumentException("A categoria do departamento é obrigatória.");
        }

        Departamento departamento = toEntity(dto);

        Departamento salvo = departamentoRepository.save(departamento);

        return toDTO(salvo);
    }

    // Listar todos
    public List<DepartamentoDTO> listarDepartamentos() {

        return departamentoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // Buscar por nome
    public DepartamentoDTO buscarPorNome(String nome) {

        Departamento dept = departamentoRepository.findBynmDepartamento(nome);

        if (dept == null) {
            return null;
        }

        return toDTO(dept);
    }

    // Atualizar
    public DepartamentoDTO atualizarDepartamento(Long id, DepartamentoDTO dto) {

        Departamento existente = departamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Departamento não encontrado."));

        existente.setNmDepartamento(dto.nmDepartamento());
        existente.setTelDepartamento(dto.telDepartamento());
        existente.setCatDepartamento(dto.catDepartamento());

        Departamento atualizado = departamentoRepository.save(existente);

        return toDTO(atualizado);
    }

    // Deletar
    public String excluirPorId(Long id) {
        if (departamentoRepository.existsById(id)) {
            departamentoRepository.deleteById(id);
            return "Departamento excluído com sucesso.";
        } else {
            return "Departamento não encontrado.";
        }
    }
    

    // Buscar por ID
    public DepartamentoDTO buscarPorIdDepartamento(Long id) {

        Departamento departamento = departamentoRepository.findByidDepartamento(id);

        if (departamento == null) {
            throw new IllegalArgumentException("Departamento não encontrado com ID: " + id);
        }

        return toDTO(departamento);
    }

    public void deletarPorNome(String nome) {

        Departamento dept = departamentoRepository.findBynmDepartamento(nome);

        if (dept == null) {
            throw new IllegalArgumentException("Nenhum departamento encontrado com este nome.");
        }

        departamentoRepository.delete(dept);
    }

    // Converter DTO → Entity
    private Departamento toEntity(DepartamentoDTO dto) {

        Departamento departamento = new Departamento();

        departamento.setNmDepartamento(dto.nmDepartamento());
        departamento.setTelDepartamento(dto.telDepartamento());
        departamento.setCatDepartamento(dto.catDepartamento());

        return departamento;
    }

    // Converter Entity → DTO
    private DepartamentoDTO toDTO(Departamento departamento) {

        return new DepartamentoDTO(
                departamento.getIdDepartamento(),
                departamento.getNmDepartamento(),
                departamento.getTelDepartamento(),
                departamento.getCatDepartamento());
    }
}