package com.projeto.gerfuncionario.model;

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
@Table(name = "DEPARTAMENTO")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento")
    private Long idDepartamento;

    // Nome do departamento
    @NotNull
    @Column(name = "nm_departamento", length = 150, nullable = false)
    private String nmDepartamento;

    // Telefone do departamento
    @Column(name = "tel_departamento", length = 11)
    private String telDepartamento;

    // Categoria do departamento (ADM, Financeiro, RH, Produção, TI, Comercial,
    // Marketing, Jurídico)
    @NotNull
    @Column(name = "cat_departamento", length = 50, nullable = false)
    private String catDepartamento;

}
