package com.fitproject.inversionista.repository;

import com.fitproject.inversionista.model.Contenedor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ContenedorRepository extends JpaRepository<Contenedor, UUID> {

}