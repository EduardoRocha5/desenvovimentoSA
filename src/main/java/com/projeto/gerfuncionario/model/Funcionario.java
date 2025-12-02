package com.projeto.gerfuncionario.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncionario;

    // nome do funcionario
    private String nmFuncionario;

    // cpf funcionario
    private String cpfFuncionario;

    // data de Admicao
    private LocalDate dtAdmissao;

    // data de demição
    private LocalDate dtDemissao;

    // data de nascimento do funcionario
    private LocalDate dtNascimento;

    // endereço do funcionario
    private String endFuncionario;

    // salario do funcionario
    private double salFuncionario;

    // sexo do usuario F ou M (Masculino, Feminino)
    private char genFuncionario;

    // cargo do funcionario
    private String carFuncionario;

       // RELACIONAMENTO COM DEPARTAMENTO (N:1)
    @ManyToOne
    @JoinColumn(name = "id_departamento") 
    private Departamento departamento;

    // RELACIONAMENTO COM PROJETO (N:1)
    @ManyToOne
    @JoinColumn(name = "id_projeto")
    private Projeto projeto;

}
