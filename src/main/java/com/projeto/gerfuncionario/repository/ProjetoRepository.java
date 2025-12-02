package com.projeto.gerfuncionario.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.gerfuncionario.model.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    List<Projeto> findBynmProjeto(String nmProjeto);

    List<Projeto> findBystsProjeto(String stsProjeto);

    long deleteBynmProjeto(String nmProjeto);

}
