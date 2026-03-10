package com.projeto.gerfuncionario.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.gerfuncionario.dto.FuncionarioDTO;
import com.projeto.gerfuncionario.model.Funcionario;
import com.projeto.gerfuncionario.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // Criar funcionário
    public FuncionarioDTO criarFuncionario(FuncionarioDTO dto) {

        if (dto.nmFuncionario() == null || dto.nmFuncionario().isBlank()) {
            throw new IllegalArgumentException("O nome do funcionário é obrigatório.");
        }

        if (dto.salFuncionario() == null || dto.salFuncionario() <= 0) {
            throw new IllegalArgumentException("O salário deve ser maior que ZERO.");
        }

        if (funcionarioRepository.existsBycpfFuncionario(dto.cpfFuncionario())) {
            throw new IllegalArgumentException("Já existe um funcionário com este CPF.");
        }

        Funcionario funcionario = toEntity(dto);

        Funcionario salvo = funcionarioRepository.save(funcionario);

        return toDTO(salvo);
    }

    // Listar todos
    public List<FuncionarioDTO> listarFuncionarios() {
        return funcionarioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar por ID
    public FuncionarioDTO buscarPorId(Long id) {

        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado."));

        return toDTO(funcionario);
    }

    // Atualizar funcionário
    public FuncionarioDTO atualizarFuncionario(Long id, FuncionarioDTO dto) {

        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado."));

        funcionario.setNmFuncionario(dto.nmFuncionario());
        funcionario.setCpfFuncionario(dto.cpfFuncionario());
        funcionario.setDtAdmissao(dto.dtAdmissao());
        funcionario.setDtDemissao(dto.dtDemissao());
        funcionario.setDtNascimento(dto.dtNascimento());
        funcionario.setEndFuncionario(dto.endFuncionario());
        funcionario.setSalFuncionario(dto.salFuncionario());
        funcionario.setGenFuncionario(dto.genFuncionario());
        funcionario.setCarFuncionario(dto.carFuncionario());

        Funcionario atualizado = funcionarioRepository.save(funcionario);

        return toDTO(atualizado);
    }

    // Deletar por ID
    public void deletar(Long id) {

        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado."));

        funcionarioRepository.delete(funcionario);
    }

    // Deletar por CPF
    public void excluirPorCpf(String cpf) {

        if (!funcionarioRepository.existsBycpfFuncionario(cpf)) {
            throw new IllegalArgumentException("CPF não encontrado no sistema.");
        }

        funcionarioRepository.deleteBycpfFuncionario(cpf);
    }

    // Buscar por nome
    public List<FuncionarioDTO> buscarPorNome(String nome) {

        return funcionarioRepository.findBynmFuncionario(nome)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Converter Entity → DTO
    private FuncionarioDTO toDTO(Funcionario funcionario) {

        return new FuncionarioDTO(
                funcionario.getIdFuncionario(),
                funcionario.getNmFuncionario(),
                funcionario.getCpfFuncionario(),
                funcionario.getDtAdmissao(),
                funcionario.getDtDemissao(),
                funcionario.getDtNascimento(),
                funcionario.getEndFuncionario(),
                funcionario.getSalFuncionario(),
                funcionario.getGenFuncionario(),
                funcionario.getCarFuncionario(),
                funcionario.getDepartamento() != null ? funcionario.getDepartamento().getIdDepartamento() : null,
                funcionario.getProjeto() != null ? funcionario.getProjeto().getIdProjeto() : null);
    }

    // Converter DTO → Entity
    private Funcionario toEntity(FuncionarioDTO dto) {

        Funcionario funcionario = new Funcionario();

        funcionario.setIdFuncionario(dto.idFuncionario());
        funcionario.setNmFuncionario(dto.nmFuncionario());
        funcionario.setCpfFuncionario(dto.cpfFuncionario());
        funcionario.setDtAdmissao(dto.dtAdmissao());
        funcionario.setDtDemissao(dto.dtDemissao());
        funcionario.setDtNascimento(dto.dtNascimento());
        funcionario.setEndFuncionario(dto.endFuncionario());
        funcionario.setSalFuncionario(dto.salFuncionario());
        funcionario.setGenFuncionario(dto.genFuncionario());
        funcionario.setCarFuncionario(dto.carFuncionario());

        return funcionario;
    }
}