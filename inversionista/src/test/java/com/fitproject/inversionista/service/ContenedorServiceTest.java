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
import java.time.LocalDate;
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
        contenedor.setNombreModelo("Modular Fit Standard");
        contenedor.setNombreSupervisor("Diego Gonzales");
        contenedor.setPresupuestoAsignado(new BigDecimal("15000000.00"));
        contenedor.setFechaLimite(LocalDate.now().plusMonths(3));
    }

    @Test
    void cuandoBuscarPorId_entoncesRetornaContenedor() {
        when(contenedorRepository.findById(idPrueba)).thenReturn(Optional.of(contenedor));

        Contenedor resultado = contenedorService.obtenerPorId(idPrueba);

        assertNotNull(resultado);
        assertEquals("Modular Fit Standard", resultado.getNombreModelo());
        verify(contenedorRepository, times(1)).findById(idPrueba);
    }

    @Test
    void cuandoBuscarIdInexistente_entoncesLanzaException() {

        UUID idFalso = UUID.randomUUID();
        when(contenedorRepository.findById(idFalso)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            contenedorService.obtenerPorId(idFalso);
        });

        assertTrue(exception.getMessage().contains("Contenedor no encontrado"));
        verify(contenedorRepository, times(1)).findById(idFalso);
    }
}