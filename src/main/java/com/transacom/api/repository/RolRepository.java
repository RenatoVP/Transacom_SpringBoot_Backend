package com.transacom.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transacom.api.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
	Optional<Rol> findByNombre(String nombre);
}
