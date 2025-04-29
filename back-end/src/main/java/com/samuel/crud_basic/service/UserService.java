package com.samuel.crud_basic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.samuel.crud_basic.DTO.UserDTO;
import com.samuel.crud_basic.DTO.responseDTO;
import com.samuel.crud_basic.model.User;
import com.samuel.crud_basic.repository.IUserRepository;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    public responseDTO registerUser(UserDTO userDTO) {
        // Verificar si el email ya está registrado
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El correo electrónico ya está registrado"
            );
        }

        // Crear y guardar el usuario
        User user = new User(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());
        userRepository.save(user);

        return new responseDTO(
            HttpStatus.OK.toString(),
            "Usuario registrado correctamente"
        );
    }

    public responseDTO loginUser(String email, String password) {
        // Buscar el usuario por correo electrónico
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (!userOptional.isPresent()) {
            return new responseDTO(
                HttpStatus.UNAUTHORIZED.toString(),
                "Correo electrónico o contraseña incorrectos"
            );
        }

        User user = userOptional.get();

        // Validar la contraseña
        if (!user.getPassword().equals(password)) {
            return new responseDTO(
                HttpStatus.UNAUTHORIZED.toString(),
                "Correo electrónico o contraseña incorrectos"
            );
        }

        return new responseDTO(
            HttpStatus.OK.toString(),
            "Inicio de sesión exitoso"
        );
    }
}