package com.fitproject.inversionista.controller;

import com.fitproject.inversionista.service.EvidenciaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.Mockito.*;
import java.util.Collections;

@WebMvcTest(EvidenciaController.class)
class EvidenciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EvidenciaService evidenciaService;

    @Test
    void testListarTodas() throws Exception {
        when(evidenciaService.obtenerTodas()).thenReturn(Collections.emptyList());
        
        mockMvc.perform(get("/api/v1/evidencias"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().contentType(APPLICATION_JSON_VALUE));
    }
}