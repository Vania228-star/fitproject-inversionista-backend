package com.fitproject.inversionista.repository;

import com.fitproject.inversionista.model.Contenedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.UUID;

public interface ContenedorRepository extends JpaRepository<Contenedor, UUID> {

    @Query("SELECT SUM(c.presupuestoAsignado) FROM Contenedor c")
    java.math.BigDecimal sumarPresupuestos();

    @Query("SELECT AVG(c.progreso) FROM Contenedor c")
    Double calcularProgresoPromedio();
}