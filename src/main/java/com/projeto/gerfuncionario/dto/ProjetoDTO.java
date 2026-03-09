package com.projeto.gerfuncionario.dto;

import java.time.LocalDate;

public record ProjetoDTO(

    Long idProjeto,
    String nmProjeto,
    String descProjeto,
    String stsProjeto,
    LocalDate dtInicio,
    LocalDate dtPrevista,
    LocalDate dtFim

) {}
