package com.fitproject.inversionista.controller;

import com.fitproject.inversionista.model.Evidencia;
import com.fitproject.inversionista.service.EvidenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/evidencias")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EvidenciaController {

    private final EvidenciaService evidenciaService;

    @GetMapping
    public ResponseEntity<List<Evidencia>> listarTodas() {
        return new ResponseEntity<>(evidenciaService.obtenerTodas(), HttpStatus.OK);
    }

    @GetMapping("/contenedor/{idContenedor}")
    public ResponseEntity<List<Evidencia>> listarPorContenedor(@PathVariable UUID idContenedor) {
        return new ResponseEntity<>(evidenciaService.obtenerPorContenedor(idContenedor), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Evidencia> crearEvidencia(@RequestBody Evidencia evidencia) {
        return new ResponseEntity<>(evidenciaService.guardar(evidencia), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvidencia(@PathVariable UUID id) {
        evidenciaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}