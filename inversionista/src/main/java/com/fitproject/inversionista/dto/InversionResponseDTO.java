package com.fitproject.inversionista.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InversionResponseDTO {
    private Long id;
    private Long idUsuario;
    private Long idProyecto;
    private BigDecimal montoInvertido;
    private LocalDateTime fechaInversion;
}