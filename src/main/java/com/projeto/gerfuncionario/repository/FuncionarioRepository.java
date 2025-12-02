package com.projeto.gerfuncionario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.gerfuncionario.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Funcionario findBycpfFuncionario(String cpfFuncionario);

    List<Funcionario> findBynmFuncionario(String nmFuncionarios);

    long deleteBycpfFuncionario(String cpfFuncionario);

}
