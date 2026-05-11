package com.fitproject.inversionista.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "inversiones")
@Data
public class Inversion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "id_proyecto", nullable = false)
    private Long idProyecto;

    @Column(name = "monto_invertido", nullable = false)
    private BigDecimal montoInvertido;

    @Column(name = "fecha_inversion", nullable = false)
    private LocalDateTime fechaInversion;

    @PrePersist
    protected void onCreate(){
        this.fechaInversion = LocalDateTime.now();
    }
}
