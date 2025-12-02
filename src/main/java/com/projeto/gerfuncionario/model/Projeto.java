package com.projeto.gerfuncionario.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projeto")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_projeto")
    private Long idProjeto;

    // Nome do projeto
    @NotNull
    @Column(name = "nm_projeto", length = 120, nullable = false)
    private String nmProjeto;

    // Descrição do projeto
    @Column(name = "desc_projeto", length = 255)
    private String descProjeto;

    // Status do projeto (iniciado, andamento, encerrado)
    @NotNull
    @Column(name = "sts_projeto", length = 40, nullable = false)
    private String stsProjeto;

    // Data de início do projeto
    @NotNull
    @Column(name = "dt_inicio", nullable = false)
    private LocalDate dtInicio;

    // Data prevista de entrega
    @Column(name = "dt_prevista")
    private LocalDate dtPrevista;

    // Data de conclusão / entrega
    @Column(name = "dt_fim")
    private LocalDate dtFim;
}
