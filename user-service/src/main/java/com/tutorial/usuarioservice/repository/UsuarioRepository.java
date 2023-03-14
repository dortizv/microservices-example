package com.tutorial.usuarioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<com.tutorial.usuarioservice.entity.Usuario, Integer> {
}
