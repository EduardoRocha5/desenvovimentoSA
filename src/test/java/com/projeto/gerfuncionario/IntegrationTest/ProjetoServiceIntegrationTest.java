package com.projeto.gerfuncionario.IntegrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.gerfuncionario.dto.ProjetoDTO;
import com.projeto.gerfuncionario.model.Projeto;
import com.projeto.gerfuncionario.repository.ProjetoRepository;
import com.projeto.gerfuncionario.service.ProjetoService;

@SpringBootTest
@Transactional
public class ProjetoServiceIntegrationTest {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Test
    @DisplayName("Deve salvar um projeto com sucesso!")
    public void deveSalvarOProjeto() {

        // arrange
        ProjetoDTO dto = new ProjetoDTO(
                null,
                "Automação",
                "Projeto de automação industrial",
                "Finalizado",
                LocalDate.now(),
                null,
                null
        );

        // act
        ProjetoDTO projetoSalvo = projetoService.salvar(dto);

        // assert
        assertNotNull(projetoSalvo.idProjeto());
        assertEquals("Automação", projetoSalvo.nmProjeto());
        assertEquals("Finalizado", projetoSalvo.stsProjeto());
    }

    @Test
    public void deletarProjeto() {

        // arrange
        Projeto projeto = new Projeto();
        projeto.setNmProjeto("Projeto Mecânico");
        projeto.setStsProjeto("Cancelado");
        projeto.setDtInicio(LocalDate.now());

        projetoRepository.save(projeto);

        // act
        String resultado = projetoService.excluirProjeto("Projeto Mecânico");

        // assert
        assertEquals("1 projeto(s) excluído(s).", resultado);
    }

    @Test
    public void deveListarTodosProjetos() {

        Projeto projeto1 = new Projeto();
        projeto1.setNmProjeto("Projeto numero 1");
        projeto1.setStsProjeto("Em Andamento");
        projeto1.setDtInicio(LocalDate.now());

        Projeto projeto2 = new Projeto();
        projeto2.setNmProjeto("Projeto numero 2");
        projeto2.setStsProjeto("Finalizado");
        projeto2.setDtInicio(LocalDate.now());

        projetoRepository.save(projeto1);
        projetoRepository.save(projeto2);

        // act
        List<ProjetoDTO> listaProjetos = projetoService.exibirTodosProjetos();

        // assert
        assertEquals(2, listaProjetos.size());
    }
}