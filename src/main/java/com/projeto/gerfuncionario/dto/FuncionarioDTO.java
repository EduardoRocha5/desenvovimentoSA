package com.projeto.gerfuncionario.dto;

import java.time.LocalDate;

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
        Long idDepartamento,
        Long idProjeto

) {
}
