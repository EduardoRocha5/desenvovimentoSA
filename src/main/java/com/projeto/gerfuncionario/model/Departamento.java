package com.projeto.gerfuncionario.model;



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
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDepartamento;

    // nome do departamento
    private String nmDepartamento;

    // teledone do departamento
    private String telDepartamento;

    // categoria do departamento(adm, financeiro, rh, produção, TI,
    // vendas/comercial, marketing, juridico)
    private String catDepartamento;


}
