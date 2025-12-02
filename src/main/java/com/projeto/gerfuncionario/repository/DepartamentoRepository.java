package com.projeto.gerfuncionario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.gerfuncionario.model.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    // Busca um departamento pelo nome (retorna um único departamento, caso exista)
    Departamento findBynmDepartamento(String nmDepartemento);

    // Deleta um departamento pelo nome e retorna o objeto removido
    Departamento deleteBynmDepartamento(String nmDepartemento);

}
