package com.fitproject.inversionista.service;

import com.fitproject.inversionista.model.User;
import com.fitproject.inversionista.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
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
    }

    @Test
    void cuandoObtenerTodos_entoncesRetornaLista() {
        when(userRepository.findAll()).thenReturn(List.of(usuario));
        
        List<User> resultados = userService.obtenerTodos();
        
        assertFalse(resultados.isEmpty());
        assertEquals(1, resultados.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void cuandoObtenerPorId_entoncesRetornaUsuario() {
        when(userRepository.findById(idUsuario)).thenReturn(Optional.of(usuario));
        
        User resultado = userService.obtenerPorId(idUsuario);
        
        assertNotNull(resultado);
        assertEquals(idUsuario, resultado.getIdUser());
        verify(userRepository, times(1)).findById(idUsuario);
    }

    @Test
    void cuandoGuardar_entoncesRetornaUsuarioGuardado() {
        when(userRepository.save(any(User.class))).thenReturn(usuario);
        
        User resultado = userService.guardar(usuario);
        
        assertNotNull(resultado);
        assertEquals("carlos Mendoza", resultado.getUserName());
        verify(userRepository, times(1)).save(usuario);
    }

    @Test
    void cuandoBuscarPorEmailExistente_entoncesRetornaUsuario() {
        when(userRepository.findByEmail("carlos@fitproject.cl")).thenReturn(Optional.of(usuario));
        User resultado = userService.obtenerPorEmail("carlos@fitproject.cl");
        assertNotNull(resultado);
        verify(userRepository, times(1)).findByEmail("carlos@fitproject.cl");
    }

    @Test
    void cuandoBuscarEmailInexistente_entoncesLanzaException() {
        String emailFalso = "correo@inexistente.cl";
        when(userRepository.findByEmail(emailFalso)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.obtenerPorEmail(emailFalso));
    }
}