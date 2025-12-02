package com.projeto.gerfuncionario.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.gerfuncionario.model.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

     // Busca uma lista de projetos pelo nome (pode haver mais de um projeto com o mesmo nome)
    List<Projeto> findBynmProjeto(String nmProjeto);

     // Busca todos os projetos pelo status (ex.: iniciado, andamento, encerrado)
    List<Projeto> findBystsProjeto(String stsProjeto);

     // Deleta todos os projetos com o nome informado e retorna a quantidade removida
    long deleteBynmProjeto(String nmProjeto);

}
