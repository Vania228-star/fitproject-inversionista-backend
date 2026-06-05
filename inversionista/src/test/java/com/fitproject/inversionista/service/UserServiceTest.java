package com.fitproject.inversionista.service;

import com.fitproject.inversionista.model.User;
import com.fitproject.inversionista.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User usuario;
    private UUID idUsuario;

    @BeforeEach
    void setUp() {
        idUsuario = UUID.randomUUID();
        usuario = new User();
        usuario.setIdUser(idUsuario);
        usuario.setUserName("carlos Mendoza");
        usuario.setEmail("carlos@fitproject.cl");
        usuario.setPassword("passwordSegura123");
        usuario.setRole("INVERSIONISTA");
    }

    @Test
    void cuandoBuscarPorEmailExistente_entoncesRetornaUsuario() {

        when(userRepository.findByEmail("carlos@fitproject.cl")).thenReturn(Optional.of(usuario));

        User resultado = userService.obtenerPorEmail("carlos@fitproject.cl");

        assertNotNull(resultado);
        assertEquals("carlos Mendoza", resultado.getUserName());
        assertEquals("INVERSIONISTA", resultado.getRole());
        verify(userRepository, times(1)).findByEmail("carlos@fitproject.cl");
    }

    @Test
    void cuandoBuscarEmailInexistente_entoncesLanzaException() {

        String emailFalso = "correo@inexistente.cl";
        when(userRepository.findByEmail(emailFalso)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.obtenerPorEmail(emailFalso);
        });

        assertTrue(exception.getMessage().contains("Usuario no encontrado con email"));
        verify(userRepository, times(1)).findByEmail(emailFalso);
    }
}