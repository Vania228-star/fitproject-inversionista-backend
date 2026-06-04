package com.fitproject.inversionista.controller;

import com.fitproject.inversionista.model.User;
import com.fitproject.inversionista.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> listarUsuarios() {
        return new ResponseEntity<>(userService.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> buscarPorId(@PathVariable UUID id) {
        return new ResponseEntity<>(userService.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> crearUsuario(@RequestBody User user) {
        return new ResponseEntity<>(userService.guardar(user), HttpStatus.CREATED);
    }
}