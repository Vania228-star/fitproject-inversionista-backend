package com.fitproject.inversionista.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import com.fitproject.inversionista.model.Inversion;
import com.fitproject.inversionista.dto.InversionResponseDTO;
import com.fitproject.inversionista.service.InversionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/inversiones")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class InversionController {

    private final InversionService inversionService;

    private InversionResponseDTO convertToDTO(Inversion inv) {
        return new InversionResponseDTO(
            inv.getId(),
            inv.getIdUsuario(),
            inv.getIdProyecto(),
            inv.getMontoInvertido(),
            inv.getFechaInversion()
        );
    }

    @PostMapping
    public ResponseEntity<InversionResponseDTO> creaInversion(@NonNull @RequestBody Inversion inversion) {
        Inversion nuevaInversion = inversionService.creaInversion(inversion);
        return new ResponseEntity<>(convertToDTO(nuevaInversion), HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<InversionResponseDTO>> obtenerPorUsuario(@NonNull @PathVariable Long idUsuario) {
        List<InversionResponseDTO> dtos = inversionService.obtenerPorUsuario(idUsuario)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/usuario/{idUsuario}/total")
    public ResponseEntity<BigDecimal> calcularTotalInversion(@NonNull @PathVariable Long idUsuario) {
        BigDecimal total = inversionService.calcularTotalInversion(idUsuario);
        return ResponseEntity.ok(total);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInversion(@NonNull @PathVariable Long id) {
        inversionService.eliminarInversion(id);
        return ResponseEntity.noContent().build();
    }
}