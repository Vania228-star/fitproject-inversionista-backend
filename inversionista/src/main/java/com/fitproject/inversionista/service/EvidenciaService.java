package com.fitproject.inversionista.service;

import com.fitproject.inversionista.model.Evidencia;
import com.fitproject.inversionista.repository.EvidenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EvidenciaService {

    private final EvidenciaRepository evidenciaRepository;

    public List<Evidencia> obtenerTodas() {
        return evidenciaRepository.findAll();
    }

    public List<Evidencia> obtenerPorContenedor(UUID idContenedor) {
        return evidenciaRepository.findByContenedorIdContenedor(idContenedor);
    }

    public Evidencia guardar(Evidencia evidencia) {
        return evidenciaRepository.save(evidencia);
    }

    public void eliminar(UUID id) {
        if (!evidenciaRepository.existsById(id)) {
            throw new RuntimeException("Evidencia no encontrada con ID: " + id);
        }
        evidenciaRepository.deleteById(id);
    }
}