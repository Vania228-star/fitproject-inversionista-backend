package com.fitproject.inversionista.service;

import com.fitproject.inversionista.model.Contenedor;
import com.fitproject.inversionista.repository.ContenedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContenedorServiceTest {

    @Mock
    private ContenedorRepository contenedorRepository;

    @InjectMocks
    private ContenedorService contenedorService;

    private Contenedor contenedor;
    private UUID idPrueba;

    @BeforeEach
    void setUp() {
        idPrueba = UUID.randomUUID();
        contenedor = new Contenedor();
        contenedor.setIdContenedor(idPrueba);
    }

    @Test
    void eliminar_DebeLlamarAlRepositorio_CuandoElContenedorExiste() {
        when(contenedorRepository.findById(idPrueba)).thenReturn(Optional.of(contenedor));
        
        contenedorService.eliminar(idPrueba);
        
        verify(contenedorRepository).delete(contenedor);
    }

    @Test
    void calcularTotalInvertido_DebeRetornarValorDelRepositorio() {
        BigDecimal valorEsperado = new BigDecimal("15000000.00");
        when(contenedorRepository.sumarPresupuestos()).thenReturn(valorEsperado);
        
        BigDecimal resultado = contenedorService.calcularTotalInvertido();
        
        assertEquals(0, valorEsperado.compareTo(resultado));
    }

    @Test
    void calcularPromedioProgreso_DebeRetornarCero_CuandoEsNulo() {
        when(contenedorRepository.calcularProgresoPromedio()).thenReturn(null);
        
        Double resultado = contenedorService.calcularPromedioProgreso();
        
        assertEquals(0.0, resultado);
    }
}