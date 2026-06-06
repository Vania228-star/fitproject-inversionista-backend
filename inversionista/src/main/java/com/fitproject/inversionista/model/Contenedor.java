package com.fitproject.inversionista.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "contenedor")
@Data
public class Contenedor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_contenedor", updatable = false, nullable = false)
    private UUID idContenedor;

    @Column(name = "nombre_modelo", nullable = false, length = 50)
    private String nombreModelo;

    @Column(name = "nombre_supervisor", nullable = false, length = 100)
    private String nombreSupervisor;

    @Column(name = "presupuesto_asignado", nullable = false, precision = 12, scale = 2)
    private BigDecimal presupuestoAsignado;

    @Column(name = "fecha_limite", nullable = false)
    private LocalDate fechaLimite;

    @Column(name = "imagen_diseno_url", length = 255)
    private String imagenDisenoUrl;

    @Column(name = "descripcion", length = 1000)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_user")
    private User supervisor;

    @Column(name = "progreso", nullable = true)
    private Double progreso = 0.0;
}