package me.vitorcsouza.jornada_milhas_api.controller;

import me.vitorcsouza.jornada_milhas_api.domain.dto.destinoDtoReq;
import me.vitorcsouza.jornada_milhas_api.domain.dto.destinoDtoRes;
import me.vitorcsouza.jornada_milhas_api.domain.dto.destinoDtoResWParamGet;
import me.vitorcsouza.jornada_milhas_api.domain.model.Destino;
import me.vitorcsouza.jornada_milhas_api.domain.service.destinoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class destinoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private destinoService service;

    private destinoDtoReq dtoReq;
    private destinoDtoRes dtoRes;
    private destinoDtoResWParamGet dtoResGet;

    private Long id;
    String json;

    private Destino destino1;
    private Destino destino2;

    private List<Destino> destinoList;

    private PageRequest paginacao;

    private Page<Destino> destinoPage;


    @BeforeEach
    void setUp() {
        json = """
                  {
                     "foto1": "https://example.com/foto1.jpg",
                     "foto2": "https://example.com/foto2.jpg",
                     "nome": "Cubatão",
                     "meta": "Explorar os melhores pontos turísticos de Cubatão",
                     "textoDescritivo": "Uma descrição detalhada sobre o destino Cubatão, mencionando suas belezas naturais e locais históricos."
                   }
                """;
        dtoReq = new destinoDtoReq(
                "https://example.com/foto1.jpg",
                "https://example.com/foto2.jpg",
                "Cubatão",
                "Explorar os melhores pontos turísticos de Cubatão",
                "Uma descrição detalhada sobre o destino Cubatão, mencionando suas belezas naturais e locais históricos."
                );
        destino1 = new Destino(dtoReq);
        destino2 = new Destino(dtoReq);
        id = 1L;
        dtoRes = new destinoDtoRes(destino1);

        destinoList = Arrays.asList(destino1, destino2);
        paginacao = PageRequest.of(0, 15);

        destinoPage = new PageImpl<>(destinoList, paginacao, destinoList.size());
    }

    @Test
    void DeveRetornarCodigo201ParaCriarUmDestino() throws Exception {
        //ARRANGE
        when(service.create(any(destinoDtoReq.class))).thenReturn(dtoRes);

        //ACT
        var response = mockMvc.perform(post("/destinos")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(201, response.getStatus());
    }

    @Test
    void DeveRetornarCodigo200ParaRetornarUmDestino() throws Exception {
        //ARRANGE
        when(service.findById(id)).thenReturn(dtoResGet);

        //ACT
        var response = mockMvc.perform(get("/destinos/" + id)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(200, response.getStatus());
    }

    @Test
    void DeveRetornarCodigo200ParaAtualizarUmDestino() throws Exception {
        //ARRANGE
        when(service.update(id, dtoReq)).thenReturn(dtoRes);

        //ACT
        var response = mockMvc.perform(
                put("/destinos/" + id)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(200, response.getStatus());
    }

    @Test
    void DeveRetornarCodigo204ParaDeletarUmDestino() throws Exception {
        //ARRANGE

        //ACT
        var response = mockMvc.perform(
                delete("/destinos/" + id)
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(204, response.getStatus());
    }

    @Test
    void DeveRetornarCodigo200ParaRetornarUmaListaDeDestinos() throws Exception {
        //ARRANGE
        given(service.findAll(paginacao)).willReturn(destinoPage.map(destinoDtoRes::new));

        //ACT
        var response = mockMvc.perform(get("/destinos")
                .param("page", "0")
                .param("size", "15")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        System.out.println("Response: " + response.getContentAsString());

        //ASSERT
        assertEquals(200, response.getStatus());
    }

    @Test
    void DeveRetornarCodigo200ParaRetornarUmaListaDeDestinosEnviandoNomeComoParametro() throws Exception {
        //ARRANGE
        given(service.findBySearch(anyString(), eq(paginacao))).willReturn(destinoPage.map(destinoDtoRes::new));

        //ACT
        var response = mockMvc.perform(get("/destinos")
                .param("page", "0")
                .param("size", "15")
                .param("nome", "cubatão")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        System.out.println("Response: " + response.getContentAsString());

        //ASSERT
        assertEquals(200, response.getStatus());
    }
}