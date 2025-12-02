package com.projeto.gerfuncionario.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.gerfuncionario.model.Projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.projeto.gerfuncionario.repository.ProjetoRepository;

@Service
public class ProjetoService {


    @Autowired
    private ProjetoRepository projetoRepository;



    //cada porjeto com seu status
    public String stsProjeto(String nomeProjeto) {

        List<Projeto> projetos = projetoRepository.findBynmProjeto(nomeProjeto);

        if (projetos.isEmpty()) {
            return "Projeto não encontrado.";
        }

        // pega o primeiro projeto encontrado
        Projeto projeto = projetos.get(0);

        return "Status do projeto '" + nomeProjeto + "': " + projeto.getStsProjeto();
    }

    // deletar projeto.
    public String excluirProjeto(String nome) {
        long qtd = projetoRepository.deleteBynmProjeto(nome);
        return validarDelete(qtd);
    }

    // procurando Projeto por nomes
    public List<Projeto> exibirPorNome(String nmProjeto) {
        return projetoRepository.findBynmProjeto(nmProjeto);
    }

    // listando Projetos
    public List<Projeto> exibirTodosProjetos() {
        return projetoRepository.findAll();

    }

    // validação para poder deletar (primeiro verificando se tem projetos)
    private String validarDelete(long qtd) {
        if (qtd == 0)
            return "Nenhum projeto encontrado.";
        return qtd + " projeto(s) excluído(s).";
    }


}
