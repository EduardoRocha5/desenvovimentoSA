package com.projeto.gerfuncionario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.gerfuncionario.model.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{
    
    Departamento findBynmDepartamento(String nmDepartemento);
    
    Departamento deleteBynmDepartamento(String nmDepartemento);

}
