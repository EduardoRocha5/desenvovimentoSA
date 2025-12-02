package com.projeto.gerfuncionario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.gerfuncionario.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    // Busca um funcionário pelo CPF (retorna somente um, pois CPF é único)
    Funcionario findBycpfFuncionario(String cpfFuncionario);

    // Busca uma lista de funcionários pelo nome (pode retornar vários com mesmo nome
    List<Funcionario> findBynmFuncionario(String nmFuncionarios);

    // Deleta um funcionário pelo CPF e retorna quantos registros foram removidos
    long deleteBycpfFuncionario(String cpfFuncionario);

    // Verifica se existe um funcionário cadastrado com o CPF informado
    boolean existsBycpfFuncionario(String cpfFuncionario);

}
