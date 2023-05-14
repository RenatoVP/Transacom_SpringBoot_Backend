package com.transacom.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transacom.api.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Boolean existsByCorreo(String correo);
	Usuario findByCorreo(String correo);
}
