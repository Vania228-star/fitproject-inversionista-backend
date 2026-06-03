package com.fitproject.inversionista.service;

import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.fitproject.inversionista.model.Inversion;
import com.fitproject.inversionista.repository.InversionRepository;

@Service
@RequiredArgsConstructor
public class InversionService {
    
    private final InversionRepository inversionRepository;

    public Inversion creaInversion(Inversion inversion){
        return inversionRepository.save(inversion);
    }

    public List<Inversion> obtenerTodas(){
        return inversionRepository.findAll();
    }

    public Inversion obtenerPorId(Long id){
        return inversionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inversión no encontrada con la ID: " + id));
    }

    public List<Inversion> obtenerPorUsuario(Long idUsuario){
        return inversionRepository.findByIdUsuario(idUsuario);
    }

    public List<Inversion> obtenerPorProyecto(Long idProyecto){
        return inversionRepository.findByIdProyecto(idProyecto);
    }

    public BigDecimal calcularTotalInversion(Long idUsuario){
        return obtenerPorUsuario(idUsuario).stream()
                .map(Inversion::getMontoInvertido)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void eliminarInversion(Long id) {
        Inversion inv = inversionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se puede eliminar, inversión no encontrada"));
        
        inversionRepository.delete(inv);
    }
}