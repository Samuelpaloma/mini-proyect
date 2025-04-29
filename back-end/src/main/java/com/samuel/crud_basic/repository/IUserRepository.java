package com.samuel.crud_basic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samuel.crud_basic.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email); // Para verificar si un email ya está registrado
    Optional<User> findByEmail(String email); // Buscar usuario por correo electrónico
}
