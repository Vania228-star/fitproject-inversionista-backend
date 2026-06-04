package com.fitproject.inversionista.repository;

import com.fitproject.inversionista.model.Evidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface EvidenciaRepository extends JpaRepository<Evidencia, UUID> {

    List<Evidencia> findByContenedorIdContenedor(UUID idContenedor);
}