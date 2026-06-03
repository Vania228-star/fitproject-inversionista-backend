package com.fitproject.inversionista.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fitproject.inversionista.model.Inversion;
import com.fitproject.inversionista.repository.InversionRepository;

@ExtendWith(MockitoExtension.class)
class InversionServiceTest {

    @Mock
    private InversionRepository inversionRepository;

    @InjectMocks
    private InversionService inversionService;

    private Inversion inversionMock;

    @BeforeEach
    void setUp() {
        inversionMock = new Inversion();
        inversionMock.setId(1L);
        inversionMock.setIdUsuario(100L);
        inversionMock.setIdProyecto(50L);
        inversionMock.setMontoInvertido(new BigDecimal("1500000"));
    }

    @Test
    @DisplayName("Debería guardar una inversión exitosamente")
    void debeGuardarInversionExitosamente() {
        when(inversionRepository.save(any(Inversion.class))).thenReturn(inversionMock);

        Inversion resultado = inversionService.creaInversion(new Inversion());

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(inversionRepository, times(1)).save(any(Inversion.class));
    }

    @Test
    @DisplayName("Debería calcular el total acumulado de inversiones de un usuario")
    void debeCalcularTotalInversionCorrectamente() {
        Inversion inv2 = new Inversion();
        inv2.setMontoInvertido(new BigDecimal("2500000"));
        
        List<Inversion> listaInversiones = Arrays.asList(inversionMock, inv2);
        when(inversionRepository.findByIdUsuario(100L)).thenReturn(listaInversiones);

        BigDecimal totalCalculado = inversionService.calcularTotalInversion(100L);

        assertNotNull(totalCalculado);
        assertEquals(new BigDecimal("4000000"), totalCalculado);
        verify(inversionRepository, times(1)).findByIdUsuario(100L);
    }

    @Test
    @DisplayName("Debería retornar cero si el usuario no posee transacciones")
    void debeRetornarCeroCuandoUsuarioNoTieneInversiones() {
        when(inversionRepository.findByIdUsuario(200L)).thenReturn(Collections.emptyList());

        BigDecimal totalCalculado = inversionService.calcularTotalInversion(200L);

        assertEquals(BigDecimal.ZERO, totalCalculado);
    }

    @Test
    @DisplayName("Debería lanzar EntityNotFoundException si la ID de inversión no existe")
    void debeLanzarExceptionCuandoInversionNoExistePorId() {
        when(inversionRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> inversionService.obtenerPorId(999L));
    }

    @Test
    @DisplayName("Debería denegar la eliminación si el registro no existe")
    void debeLanzarExceptionAlEliminarInversionInexistente() {
        when(inversionRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> inversionService.eliminarInversion(999L));
        verify(inversionRepository, never()).delete(any(Inversion.class));
    }
}