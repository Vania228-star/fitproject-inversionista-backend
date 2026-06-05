package com.fitproject.inversionista.service;

import com.fitproject.inversionista.model.Contenedor;
import com.fitproject.inversionista.model.Evidencia;
import com.fitproject.inversionista.repository.EvidenciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EvidenciaServiceTest {

    @Mock
    private EvidenciaRepository evidenciaRepository;

    @InjectMocks
    private EvidenciaService evidenciaService;

    private Evidencia evidencia;
    private UUID idContenedor;

    @BeforeEach
    void setUp() {
        idContenedor = UUID.randomUUID();
        
        Contenedor contenedorMock = new Contenedor();
        contenedorMock.setIdContenedor(idContenedor);

        evidencia = new Evidencia();
        evidencia.setIdEvidence(UUID.randomUUID());
        evidencia.setNombreEvidencia("Reporte Techumbre Modulo A");
        evidencia.setAutor("Paula");
        evidencia.setDescripcion("Estructura metálica instalada correctamente.");
        evidencia.setUrlRespaldo("https://bucket-s3.aws/evidencia1.jpg");
        evidencia.setEstadoAprobacion("P");
        evidencia.setFechaRegistro(LocalDateTime.now());
        evidencia.setContenedor(contenedorMock);
    }

    @Test
    void cuandoBuscarPorContenedor_entoncesRetornaListaDeEvidencias() {

        when(evidenciaRepository.findByContenedorIdContenedor(idContenedor)).thenReturn(List.of(evidencia));

        List<Evidencia> resultado = evidenciaService.obtenerPorContenedor(idContenedor);

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("Paula", resultado.get(0).getAutor());
        verify(evidenciaRepository, times(1)).findByContenedorIdContenedor(idContenedor);
    }

    @Test
    void cuandoEliminarEvidenciaInexistente_entoncesLanzaException() {

        UUID idInexistente = UUID.randomUUID();
        when(evidenciaRepository.existsById(idInexistente)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            evidenciaService.eliminar(idInexistente);
        });

        assertTrue(exception.getMessage().contains("Evidencia no encontrada"));
        verify(evidenciaRepository, times(1)).existsById(idInexistente);
        verify(evidenciaRepository, never()).deleteById(any());
    }
}