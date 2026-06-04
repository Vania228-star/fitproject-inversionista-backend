package com.fitproject.inversionista.service;

import com.fitproject.inversionista.model.Contenedor;
import com.fitproject.inversionista.repository.ContenedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContenedorService {

    private final ContenedorRepository contenedorRepository;

    public List<Contenedor> obtenerTodos() {
        return contenedorRepository.findAll();
    }

    public Contenedor obtenerPorId(UUID id) {
        return contenedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contenedor no encontrado con el ID: " + id));
    }

    public Contenedor guardar(Contenedor contenedor) {
        return contenedorRepository.save(contenedor);
    }

    public void eliminar(UUID id) {
        Contenedor contenedor = obtenerPorId(id);
        contenedorRepository.delete(contenedor);
    }
}