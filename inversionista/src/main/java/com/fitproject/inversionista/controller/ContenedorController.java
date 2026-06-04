package com.fitproject.inversionista.controller;

import com.fitproject.inversionista.model.Contenedor;
import com.fitproject.inversionista.service.ContenedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/contenedores")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ContenedorController {

    private final ContenedorService contenedorService;

    @GetMapping
    public ResponseEntity<List<Contenedor>> listarContenedores() {
        return new ResponseEntity<>(contenedorService.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contenedor> buscarPorId(@PathVariable UUID id) {
        return new ResponseEntity<>(contenedorService.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Contenedor> crearContenedor(@RequestBody Contenedor contenedor) {
        return new ResponseEntity<>(contenedorService.guardar(contenedor), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContenedor(@PathVariable UUID id) {
        contenedorService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}