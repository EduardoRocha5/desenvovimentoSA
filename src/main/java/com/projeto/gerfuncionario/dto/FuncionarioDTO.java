package com.projeto.gerfuncionario.dto;

import java.time.LocalDate;

import com.projeto.gerfuncionario.model.Departamento;
import com.projeto.gerfuncionario.model.Projeto;

public record FuncionarioDTO(

    Long idFuncionario,
    String nmFuncionario,
    String cpfFuncionario,
    LocalDate dtAdmissao,
    LocalDate dtDemissao,
    LocalDate dtNascimento,
    String endFuncionario,
    Double salFuncionario,
    char genFuncionario,
    String carFuncionario,
    Departamento departamento,
    Projeto projeto

) {}
