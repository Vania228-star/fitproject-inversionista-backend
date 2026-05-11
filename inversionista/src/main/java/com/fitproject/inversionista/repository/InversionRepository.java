package com.fitproject.inversionista.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitproject.inversionista.model.Inversion;

@Repository
public interface InversionRepository extends JpaRepository<Inversion, Long> {
    
    List<Inversion> findByIdUsuario(Long idUsuario);

    List<Inversion> findByIdProyecto(Long idProyecto);

    List<Inversion> findByMontoInvertidoGreaterThan(Double monto);

    List<Inversion> findByFechaInversionBetween(java.time.LocalDateTime inicio, java.time.LocalDateTime fin);
}
