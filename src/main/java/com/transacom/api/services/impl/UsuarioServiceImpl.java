package com.transacom.api.services.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.transacom.api.entity.Rol;
import com.transacom.api.entity.Usuario;
import com.transacom.api.exception.ResourceNotFoundException;
import com.transacom.api.payload.request.SignUpRequest;
import com.transacom.api.repository.RolRepository;
import com.transacom.api.repository.UsuarioRepository;
import com.transacom.api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void registrar(SignUpRequest signUpRequest) {
		Usuario usuario = new Usuario();
		usuario.setNombre(signUpRequest.getNombre());
		usuario.setApellidoPaterno(signUpRequest.getApellidoPaterno());
		usuario.setApellidoMaterno(signUpRequest.getApellidoMaterno());
		usuario.setTelefono(signUpRequest.getTelefono());
		usuario.setRuc(signUpRequest.getRuc());
		usuario.setCorreo(signUpRequest.getCorreo());
		usuario.setContrasena(passwordEncoder.encode(signUpRequest.getContrasena()));
		usuario.setFechaRegistro(LocalDateTime.now());
		usuario.setFechaActualizado(LocalDateTime.now());
		usuario.setActivo(true);

		// Obtenemos el role ingresados en el signuprequest y realizamos una busqueda
		// de rol por nombre para obtener el rol completo y registrar los roles al
		// usuario
		Set<Rol> roles = new HashSet<Rol>();
		
		Rol rolEncontrado = rolRepository.findByNombre(signUpRequest.getRol())
				.orElseThrow(() -> new ResourceNotFoundException("Recurso Rol no encontrado"));
		
		roles.add(rolEncontrado);
		
		usuario.setRoles(roles);
		
		//Registramos al usuario
		usuarioRepository.save(usuario);
	}

}
