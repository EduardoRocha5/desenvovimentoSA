package com.projeto.gerfuncionario.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.gerfuncionario.dto.ProjetoDTO;
import com.projeto.gerfuncionario.model.Projeto;
import com.projeto.gerfuncionario.repository.ProjetoRepository;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    // --------------------------------
    // STATUS DO PROJETO
    // --------------------------------
    public String stsProjeto(String nomeProjeto) {

        List<Projeto> projetos = projetoRepository.findBynmProjeto(nomeProjeto);

        if (projetos.isEmpty()) {
            return "Projeto não encontrado.";
        }

        Projeto projeto = projetos.get(0);

        return "Status do projeto '" + nomeProjeto + "': " + projeto.getStsProjeto();
    }

    // --------------------------------
    // EXCLUIR PROJETO
    // --------------------------------
    public String excluirProjeto(String nome) {

        long qtd = projetoRepository.deleteBynmProjeto(nome);

        return validarDelete(qtd);
    }

    // --------------------------------
    // BUSCAR POR NOME
    // --------------------------------
    public List<ProjetoDTO> exibirPorNome(String nmProjeto) {

        return projetoRepository.findBynmProjeto(nmProjeto)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // --------------------------------
    // LISTAR TODOS
    // --------------------------------
    public List<ProjetoDTO> exibirTodosProjetos() {

        return projetoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // --------------------------------
    // SALVAR PROJETO
    // --------------------------------
    public ProjetoDTO salvar(ProjetoDTO dto) {

        Projeto projeto = toEntity(dto);

        Projeto salvo = projetoRepository.save(projeto);

        return toDTO(salvo);
    }

    // --------------------------------
    // VALIDA DELETE
    // --------------------------------
    private String validarDelete(long qtd) {

        if (qtd == 0)
            return "Nenhum projeto encontrado.";

        return qtd + " projeto(s) excluído(s).";
    }

    // --------------------------------
    // ENTITY → DTO
    // --------------------------------
    private ProjetoDTO toDTO(Projeto projeto) {

        return new ProjetoDTO(
                projeto.getIdProjeto(),
                projeto.getNmProjeto(),
                projeto.getDescProjeto(),
                projeto.getStsProjeto(),
                projeto.getDtInicio(),
                projeto.getDtPrevista(),
                projeto.getDtFim());
    }

    // --------------------------------
    // DTO → ENTITY
    // --------------------------------
    private Projeto toEntity(ProjetoDTO dto) {

        Projeto projeto = new Projeto();

        projeto.setIdProjeto(dto.idProjeto());
        projeto.setNmProjeto(dto.nmProjeto());
        projeto.setDescProjeto(dto.descProjeto());
        projeto.setStsProjeto(dto.stsProjeto());
        projeto.setDtInicio(dto.dtInicio());
        projeto.setDtPrevista(dto.dtPrevista());
        projeto.setDtFim(dto.dtFim());

        return projeto;
    }
}