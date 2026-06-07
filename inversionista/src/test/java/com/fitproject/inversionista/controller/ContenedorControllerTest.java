package com.fitproject.inversionista.controller;

import com.fitproject.inversionista.service.ContenedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.Mockito.*;

@WebMvcTest(ContenedorController.class)
class ContenedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContenedorService contenedorService;

    @Test
    void testListarContenedores() throws Exception {
        when(contenedorService.obtenerTodos()).thenReturn(Collections.emptyList());
        
        mockMvc.perform(get("/api/v1/contenedores"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(APPLICATION_JSON_VALUE));
    }

    @Test
    void testObtenerIndicadores() throws Exception {
        when(contenedorService.calcularTotalInvertido()).thenReturn(java.math.BigDecimal.ZERO);
        when(contenedorService.calcularPromedioProgreso()).thenReturn(0.0);
        
        mockMvc.perform(get("/api/v1/contenedores/indicadores"))
               .andDo(print())
               .andExpect(status().isOk());
    }
}