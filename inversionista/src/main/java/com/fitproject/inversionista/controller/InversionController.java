package com.fitproject.inversionista.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitproject.inversionista.model.Inversion;
import com.fitproject.inversionista.service.InversionService;

@RestController
@RequestMapping("/api/inversiones")
@CrossOrigin(origins = "*")
public class InversionController {
    
    @Autowired
    private InversionService inversionService;

    @PostMapping
    public Inversion guardar(@RequestBody Inversion inversion){
        return inversionService.creaInversion(inversion);
    }

    @GetMapping
    public List<Inversion> listarTodas(){
        return inversionService.obtenerTodas();
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Inversion> listarPorUsuario(@PathVariable Long idUsuario){
        return inversionService.obtenerPorUsuario(idUsuario);
    }

    @GetMapping("/total/{idUsuario}")
    public double obtenerTotalInversion(@PathVariable Long idUsuario){
        return inversionService.calcularTotalInversion(idUsuario);
    }

    @GetMapping("/proyecto/{idProyecto}")
    public List<Inversion> listaPorProyecto(@PathVariable Long idProyecto){
        return inversionService.obtenerPorProyecto(idProyecto);
    }

    @GetMapping("/status")
    public String status(){
        return "Microservicio de inversionista funcionando correctamente";
    }
}
