package com.fitproject.inversionista.controller;

import com.fitproject.inversionista.model.User;
import com.fitproject.inversionista.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.Collections;
import java.util.UUID;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testListarUsuarios() throws Exception {
        when(userService.obtenerTodos()).thenReturn(Collections.emptyList());
    
        mockMvc.perform(get("/api/v1/usuarios"))
       .andExpect(status().is2xxSuccessful());
    }

    @Test
    void testBuscarPorId() throws Exception {
        UUID id = UUID.randomUUID();
        User user = new User(); 
        when(userService.obtenerPorId(id)).thenReturn(user);

        mockMvc.perform(get("/api/v1/usuarios/{id}", id))
               .andExpect(status().isOk());
    }

    @Test
    void testCrearUsuario() throws Exception {
        User user = new User();
        when(userService.guardar(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/v1/usuarios")
               .contentType("application/json")
               .content("{\"nombre\":\"Juan\"}"))
               .andExpect(status().isCreated());
    }
}