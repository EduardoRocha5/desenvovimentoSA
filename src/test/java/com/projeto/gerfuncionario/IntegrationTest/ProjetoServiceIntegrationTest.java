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
        Projeto projeto = new Projeto();
        projeto.setNmProjeto("Automação");
        projeto.setStsProjeto("Finalizado"); // todos as colunas que estao @NotNull precisa ser colocado aqui. no

        projeto.setDtInicio(LocalDate.now());

        // act
        Projeto projetoServ = projetoService.salvar(projeto);

        // asserts
        assertNotNull(projetoServ.getIdProjeto());
        assertEquals("Automação", projetoServ.getNmProjeto());
        assertEquals("Finalizado", projetoServ.getStsProjeto());

    }

    @Test
    public void deletarProjeto() {

        // arrange
        Projeto projeto = new Projeto();
        projeto.setNmProjeto("Projeto Mecânico");
        projeto.setStsProjeto("Cancelado");
        projeto.setDtInicio(LocalDate.now());

        projetoRepository.save(projeto);

        // assert + act excluir projeto
        assertEquals("1 projeto(s) excluído(s).", projetoService.excluirProjeto("Projeto Mecânico"));

    }

    @Test
    public void deveListarTodosProjetos() {

        Projeto projeton1 = new Projeto();
        projeton1.setDtInicio(LocalDate.now());
        projeton1.setNmProjeto("Projeto numero 1");
        projeton1.setStsProjeto("Em Andamento");

        Projeto projeton2 = new Projeto();
        projeton2.setDtInicio(LocalDate.now());
        projeton2.setNmProjeto("Projeto numero 2");
        projeton2.setStsProjeto("Finalizado");

        projetoRepository.save(projeton1);
        projetoRepository.save(projeton2);

        List<Projeto> listaProjetos = projetoService.exibirTodosProjetos();
        assertEquals(4, listaProjetos.size());
    }

}
