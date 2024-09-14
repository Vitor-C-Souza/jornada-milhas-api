package me.vitorcsouza.jornada_milhas_api.controller;

import me.vitorcsouza.jornada_milhas_api.domain.dto.depoimentosDtoReq;
import me.vitorcsouza.jornada_milhas_api.domain.dto.depoimentosDtoRes;
import me.vitorcsouza.jornada_milhas_api.domain.model.Depoimento;
import me.vitorcsouza.jornada_milhas_api.domain.service.depoimentosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class depoimentosControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private depoimentosService service;

    private depoimentosDtoReq dtoReq;
    private depoimentosDtoRes dtoRes;

    private Long id;
    String json;

    private Depoimento depoimento1;
    private Depoimento depoimento2;

    private List<Depoimento> depoimentosList;

    private Pageable paginacao;
    private Page<Depoimento> depoimentosPage;

    @BeforeEach
    void setUp() {
        json = """
                {
                    "texto": "texto exemplo",
                    "autor": "autor exemplo",
                    "avatar": "avatar exemplo"
                }
                """;
        dtoReq = new depoimentosDtoReq("exemplo texto", "exemplo autor", "exemplo avatar");
        id = 1L;
        dtoRes = new depoimentosDtoRes(id, "exemplo texto", "exemplo autor", "exemplo avatar");

        depoimento1 = new Depoimento(dtoReq);
        depoimento2 = new Depoimento(dtoReq);

        depoimentosList = Arrays.asList(depoimento1, depoimento2);
        paginacao = PageRequest.of(0, 15);

        depoimentosPage = new PageImpl<>(depoimentosList, paginacao, depoimentosList.size());
    }

    @Test
    void DeveRetornarCodigo201ParaCriarUmDepoimento() throws Exception {
        //ARRANGE
        when(service.create(any(depoimentosDtoReq.class))).thenReturn(dtoRes);

        //ACT
        var response = mockMvc.perform(post("/depoimentos")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(201, response.getStatus());
    }

    @Test
    void DeveRetornarCodigo200ParaRetornarUmDepoimento() throws Exception {
        //ARRANGE
        when(service.findById(id)).thenReturn(dtoRes);

        //ACT
        var response = mockMvc.perform(get("/depoimentos/" + id)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(200, response.getStatus());
    }

    @Test
    void DeveRetornarCodigo200ParaRetornarUmaListaDeDepoimentos() throws Exception {
        //ARRANGE
        given(service.findAll(paginacao)).willReturn(depoimentosPage.map(depoimentosDtoRes::new));

        //ACT
        var response = mockMvc.perform(get("/depoimentos")
                .param("page", "0")
                .param("size", "15")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(200, response.getStatus());
    }

    @Test
    void DeveRetornarCodigo200ParaAtualizarUmDepoimento() throws Exception {
        //ARRANGE
        when(service.update(id, dtoReq)).thenReturn(dtoRes);

        //ACT
        var response = mockMvc.perform(
                put("/depoimentos/" + id)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(200, response.getStatus());
    }

    @Test
    void DeveRetornarCodigo204ParaDeletarUmDepoimento() throws Exception {
        //ARRANGE

        //ACT
        var response = mockMvc.perform(
                delete("/depoimentos/" + id)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(204, response.getStatus());
    }

    @Test
    void DeveRetornarCodigo200ParaRetornarUmaListaDeDepoimentosAleatorios() throws Exception {
        //ARRANGE
        given(service.findRandom(paginacao)).willReturn(depoimentosPage.map(depoimentosDtoRes::new));

        //ACT
        var response = mockMvc.perform(get("/depoimentos-home")
                .param("page", "0")
                .param("size", "3")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(200, response.getStatus());
    }
}