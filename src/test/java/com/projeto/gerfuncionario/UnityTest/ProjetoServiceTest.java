package com.projeto.gerfuncionario.UnityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.projeto.gerfuncionario.dto.ProjetoDTO;
import com.projeto.gerfuncionario.model.Projeto;
import com.projeto.gerfuncionario.repository.ProjetoRepository;
import com.projeto.gerfuncionario.service.ProjetoService;

public class ProjetoServiceTest {

    @Mock
    private ProjetoRepository projetoRepository;

    @InjectMocks
    private ProjetoService projetoService;

    @BeforeEach // beforeEach para inicar primero este antes de começar a rodar os testes.
    void setup() {
        MockitoAnnotations.openMocks(this); // inicializa os moks
    }

    @Test
    void deveRetornarStatusProjeto() {

        Projeto projeto = new Projeto();
        projeto.setNmProjeto("Sistema Escolar");
        projeto.setStsProjeto("EM ANDAMENTO");

        when(projetoRepository.findBynmProjeto("Sistema Escolar"))
                .thenReturn(List.of(projeto));

        String resultado = projetoService.stsProjeto("Sistema Escolar");

        assertEquals("Status do projeto 'Sistema Escolar': EM ANDAMENTO", resultado);
    }

    @Test
    void deveRetornarProjetoNaoEncontrado() {

        when(projetoRepository.findBynmProjeto("Sistema Escolar"))
                .thenReturn(List.of());

        String resultado = projetoService.stsProjeto("Sistema Escolar");

        assertEquals("Projeto não encontrado.", resultado);
    }

    @Test
    void deveExcluirProjeto() {

        when(projetoRepository.deleteBynmProjeto("Sistema"))
                .thenReturn(1L);

        String resultado = projetoService.excluirProjeto("Sistema");

        assertEquals("1 projeto(s) excluído(s).", resultado);
    }

    @Test
    void naoDeveExcluirProjeto() {

        when(projetoRepository.deleteBynmProjeto("Sistema"))
                .thenReturn(0L);

        String resultado = projetoService.excluirProjeto("Sistema");

        assertEquals("Nenhum projeto encontrado.", resultado);
    }

    @Test
    void deveBuscarProjetoPorNome() {

        Projeto projeto = new Projeto();
        projeto.setIdProjeto(1L);
        projeto.setNmProjeto("Sistema Escolar");

        when(projetoRepository.findBynmProjeto("Sistema Escolar"))
                .thenReturn(List.of(projeto));

        List<ProjetoDTO> resultado = projetoService.exibirPorNome("Sistema Escolar");

        assertEquals(1, resultado.size());
        assertEquals("Sistema Escolar", resultado.get(0).nmProjeto());
    }

    @Test
    void deveListarTodosProjetos() {

        Projeto projeto = new Projeto();
        projeto.setIdProjeto(1L);
        projeto.setNmProjeto("Sistema Escolar");

        when(projetoRepository.findAll())
                .thenReturn(List.of(projeto));

        List<ProjetoDTO> lista = projetoService.exibirTodosProjetos();

        assertEquals(1, lista.size());
    }

    @Test
    void deveSalvarProjeto() {

        ProjetoDTO dto = new ProjetoDTO(
                null,
                "Sistema Escolar",
                "Projeto de gestão",
                "EM ANDAMENTO",
                LocalDate.now(),
                LocalDate.now().plusDays(30),
                null);

        Projeto projeto = new Projeto();
        projeto.setIdProjeto(1L);
        projeto.setNmProjeto("Sistema Escolar");

        when(projetoRepository.save(any(Projeto.class)))
                .thenReturn(projeto);

        ProjetoDTO resultado = projetoService.salvar(dto);

        assertEquals("Sistema Escolar", resultado.nmProjeto());
    }

}
