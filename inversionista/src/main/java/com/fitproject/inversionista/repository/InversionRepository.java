package com.fitproject.inversionista.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fitproject.inversionista.model.Inversion;

public interface InversionRepository extends JpaRepository<Inversion, Long> {
    
    List<Inversion> findByIdUsuario(Long idUsuario);

    List<Inversion> findByIdProyecto(Long idProyecto);

    List<Inversion> findByMontoInvertidoGreaterThan(BigDecimal monto);

    List<Inversion> findByFechaInversionBetween(java.time.LocalDateTime inicio, java.time.LocalDateTime fin);
}