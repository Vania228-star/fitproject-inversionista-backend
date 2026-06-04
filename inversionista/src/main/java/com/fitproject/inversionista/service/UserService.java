package com.fitproject.inversionista.service;

import com.fitproject.inversionista.model.User;
import com.fitproject.inversionista.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> obtenerTodos() {
        return userRepository.findAll();
    }

    public User obtenerPorId(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    public User guardar(User user) {
        return userRepository.save(user);
    }

    public User obtenerPorEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
    }
}