package com.fitproject.inversionista.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitproject.inversionista.model.Inversion;
import com.fitproject.inversionista.repository.InversionRepository;

@Service
public class InversionService {
    
    @Autowired
    private InversionRepository inversionRepository;

    public Inversion creaInversion(Inversion inversion){
        return inversionRepository.save(inversion);
    }

    public List<Inversion> obtenerTodas(){
        return inversionRepository.findAll();
    }

    public List<Inversion> obtenerPorUsuario(Long idUsuario){
        return inversionRepository.findAll().stream()
                .filter(inv -> inv.getIdUsuario().equals(idUsuario))
                .collect(Collectors.toList());
    }

    public double calcularTotalInversion(Long idUsuario){
        return obtenerPorUsuario(idUsuario).stream()
                .mapToDouble(inv -> inv.getMontoInvertido().doubleValue())
                .sum();
    }
}
