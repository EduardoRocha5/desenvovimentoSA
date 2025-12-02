package com.projeto.gerfuncionario.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Long idFuncionario;

    // Nome do funcionário
    @NotNull
    @Column(name = "nm_funcionario", length = 120, nullable = false)
    private String nmFuncionario;

    // CPF do funcionário (único)
    @NotNull
    @Column(name = "cpf_funcionario", length = 11, nullable = false, unique = true)
    private String cpfFuncionario;

    // Data de admissão
    @NotNull
    @Column(name = "dt_admissao", nullable = false)
    private LocalDate dtAdmissao;

    // Data de demissão (pode ser nula)
    @Column(name = "dt_demissao")
    private LocalDate dtDemissao;

    // Data de nascimento
    @NotNull
    @Column(name = "dt_nascimento", nullable = false)
    private LocalDate dtNascimento;

    // Endereço do funcionário
    @NotNull
    @Column(name = "end_funcionario", length = 200, nullable = false)
    private String endFuncionario;

    // Salário do funcionário
    @Positive
    @Column(name = "sal_funcionario", nullable = false)
    private Double salFuncionario;

    // Sexo do funcionário (F ou M)
    @Column(name = "gen_funcionario", length = 1, nullable = false)
    private char genFuncionario;

    // Cargo do funcionário
    @NotNull
    @Column(name = "car_funcionario", length = 80, nullable = false)
    private String carFuncionario;

    // RELACIONAMENTO COM DEPARTAMENTO (N:1)
    @ManyToOne
    @JoinColumn(name = "id_departamento", nullable = false)
    private Departamento departamento;

    // RELACIONAMENTO COM PROJETO (N:1)
    @ManyToOne
    @JoinColumn(name = "id_projeto", nullable = false)
    private Projeto projeto;

}
