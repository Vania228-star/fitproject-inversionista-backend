package com.fitproject.inversionista.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitproject.inversionista.model.Inversion;
import com.fitproject.inversionista.service.InversionService;

@WebMvcTest(InversionController.class)
class InversionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InversionService inversionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/v1/inversiones - Debería retornar 201 Created al registrar una inversión")
    void debeCrearInversionYRetornarStatusCreated() throws Exception {
        Inversion inversionInput = new Inversion();
        inversionInput.setIdUsuario(10L);
        inversionInput.setIdProyecto(20L);
        inversionInput.setMontoInvertido(new BigDecimal("500000"));

        Inversion inversionOutput = new Inversion();
        inversionOutput.setId(1L);
        inversionOutput.setIdUsuario(10L);
        inversionOutput.setIdProyecto(20L);
        inversionOutput.setMontoInvertido(new BigDecimal("500000"));
        inversionOutput.setFechaInversion(LocalDateTime.now());

        when(inversionService.creaInversion(any(Inversion.class))).thenReturn(inversionOutput);

        mockMvc.perform(post("/api/v1/inversiones")
                .contentType(MediaType.APPLICATION_JSON_VALUE) 
                .content(objectMapper.writeValueAsString(inversionInput)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.montoInvertido").value(500000));
    }

    @Test
    @DisplayName("GET /api/v1/inversiones/usuario/{idUsuario}/total - Debería retornar código 200 OK")
    void debeRetornarTotalInversionUsuario() throws Exception {
        when(inversionService.calcularTotalInversion(10L)).thenReturn(new BigDecimal("1200000"));

        mockMvc.perform(get("/api/v1/inversiones/usuario/10/total")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("1200000"));
    }

    @Test
    @DisplayName("DELETE /api/v1/inversiones/{id} - Debería retornar estado 204 No Content")
    void debeEliminarInversionYRetornarNoContent() throws Exception {
        doNothing().when(inversionService).eliminarInversion(1L);

        mockMvc.perform(delete("/api/v1/inversiones/1"))
                .andExpect(status().isNoContent());

        verify(inversionService, times(1)).eliminarInversion(1L);
    }
}