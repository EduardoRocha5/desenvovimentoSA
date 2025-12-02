package com.projeto.gerfuncionario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.gerfuncionario.model.Funcionario;
import com.projeto.gerfuncionario.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // Criar funcionário (com validações manuais + do Bean Validation)
    public Funcionario criarFuncionario(Funcionario funcionario) {

        // Regra de negócio 1: Nome não pode ser vazio
        if (funcionario.getNmFuncionario() == null || funcionario.getNmFuncionario().isBlank()) {
            throw new IllegalArgumentException("O nome do funcionário é obrigatório.");
        }

        // Regra de negócio 2: Salário deve ser maior que zero
        if (funcionario.getSalFuncionario() == null || funcionario.getSalFuncionario() <= 0) {
            throw new IllegalArgumentException("O salário deve ser maior que ZERO.");
        }

        // Regra de negócio 3: CPF deve ser único
        if (funcionarioRepository.existsBycpfFuncionario(funcionario.getCpfFuncionario())) {
            throw new IllegalArgumentException("Já existe um funcionário com este CPF.");
        }

        // Salvando
        return funcionarioRepository.save(funcionario);
    }

    // Listar todos
    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    // Buscar por ID
    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado."));
    }

    // Atualizar
    public Funcionario atualizarFuncionario(Long id, Funcionario funcionarioAtualizado) {

        Funcionario funcionario = buscarPorId(id);

        funcionario.setNmFuncionario(funcionarioAtualizado.getCarFuncionario());
        funcionario.setCarFuncionario(funcionarioAtualizado.getCpfFuncionario());
        funcionario.setSalFuncionario(funcionarioAtualizado.getSalFuncionario());

        return criarFuncionario(funcionario); // reaproveita validações
    }

    public void deletar(Long id) {
        Funcionario funcionario = buscarPorId(id);
        funcionarioRepository.delete(funcionario);
    }

    // deletar por cpf
    public void excluirPorCpf(String cpf) {

        if (!funcionarioRepository.existsBycpfFuncionario(cpf)) {
            throw new IllegalArgumentException("CPF não encontrado no sistema.");
        }

        funcionarioRepository.deleteBycpfFuncionario(cpf);
    }

    // buscar por nome
    public List<Funcionario> buscarPorNome(String nome) {
        return funcionarioRepository.findBynmFuncionario(nome);
    }
}
