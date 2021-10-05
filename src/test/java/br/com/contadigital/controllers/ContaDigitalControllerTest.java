package br.com.contadigital.controllers;

import br.com.contadigital.dto.ContaDigitalRequest;
import br.com.contadigital.dto.DebitoCreditoRequest;
import br.com.contadigital.models.ContaDigital;
import br.com.contadigital.repositories.ContaDigitalRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ContaDigitalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ContaDigitalRepository contaDigitalRepository;

    private ContaDigitalRequest contaDigitalRequest;

    private DebitoCreditoRequest debitoCreditoRequest;

    private ContaDigital contaDigital;

    @BeforeEach
    void setUp() {
        contaDigitalRequest = new ContaDigitalRequest(1L, 1L);
        debitoCreditoRequest = new DebitoCreditoRequest(1L, new BigDecimal("100"));
        contaDigital = new ContaDigital(1L, 1L);
        when(contaDigitalRepository.save(contaDigital)).thenReturn(contaDigital);
        when(contaDigitalRepository.findByConta(1L)).thenReturn(Optional.of(contaDigital));
        when(contaDigitalRepository.findByConta(2L)).thenReturn(Optional.empty());
    }

    @Test
    void deveriaCriarUmaNovaConta() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(contaDigitalRequest);
        mockMvc.perform(post("/api/contas/novo")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deveriaCreditarSaldo() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(debitoCreditoRequest);
        mockMvc.perform(put("/api/contas/credito")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void naoDeveriaCreditarSaldoContaNaoExiste() throws Exception {
        DebitoCreditoRequest contaInvalida = new DebitoCreditoRequest(2L, new BigDecimal("100"));
        String jsonBody = objectMapper.writeValueAsString(contaInvalida);
        mockMvc.perform(put("/api/contas/credito")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveriaDebitarSaldo() throws Exception {
        contaDigital.creditarvalor(new BigDecimal("1000"));
        String jsonBody = objectMapper.writeValueAsString(debitoCreditoRequest);
        mockMvc.perform(put("/api/contas/debito")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void naoDeveriaDebitarSaldoContaInexistente() throws Exception {
        DebitoCreditoRequest contaInvalida = new DebitoCreditoRequest(2L, new BigDecimal("100"));
        String jsonBody = objectMapper.writeValueAsString(contaInvalida);
        mockMvc.perform(put("/api/contas/debito")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void naoDeveriaDebitarSaldoContaQuandoDebitoMaiorQueSaldo() throws Exception {
        DebitoCreditoRequest request = new DebitoCreditoRequest(1L, new BigDecimal("100"));

        String jsonBody = objectMapper.writeValueAsString(request);
        mockMvc.perform(put("/api/contas/debito")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

}