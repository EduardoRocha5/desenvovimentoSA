package com.projeto.gerfuncionario.model;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Projeto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProjeto;

    //nome do projeto
    private String nmProjeto;

    //descrição do projeto
    private String descProjeto;

    //status do projeto (iniciado, andamento, encerrado)
    private String stsProjeto;

    //data de inicio do projeto
    private LocalDate dtInicio;

    //data Prevista de entrega
    private LocalDate dtPrevista;

    //data fim e entrega
    private LocalDate dtFim;

    
}
