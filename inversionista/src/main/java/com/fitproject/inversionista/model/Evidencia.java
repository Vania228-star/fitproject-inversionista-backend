package com.fitproject.inversionista.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "evidencia")
@Data
public class Evidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_evidence", updatable = false, nullable = false)
    private UUID idEvidence;

    @Column(name = "nombre_evidencia", nullable = false, length = 50)
    private String nombreEvidencia;

    @Column(name = "autor", nullable = false, length = 50)
    private String autor;

    @Column(name = "descripcion", nullable = false, length = 1000)
    private String descripcion;

    @Column(name = "url_respaldo", length = 255)
    private String urlRespaldo;

    @Column(name = "estado_aprobacion", nullable = false, length = 1)
    private String estadoAprobacion = "P";

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contenedor_id_contenedor", nullable = false)
    private Contenedor contenedor;

    @PrePersist
    protected void onCreate() {
        this.fechaRegistro = LocalDateTime.now();
    }
}