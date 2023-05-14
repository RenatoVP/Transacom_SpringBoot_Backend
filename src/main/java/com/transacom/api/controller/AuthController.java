package com.transacom.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transacom.api.payload.request.LoginRequest;
import com.transacom.api.payload.request.SignUpRequest;
import com.transacom.api.payload.response.AuthenticationResponse;
import com.transacom.api.repository.UsuarioRepository;
import com.transacom.api.security.jwt.JwtUtils;
import com.transacom.api.security.services.CustomUserDetails;
import com.transacom.api.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:4200")
public class AuthController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> ingresar(@RequestBody @Valid LoginRequest loginRequest) {
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getCorreo(), loginRequest.getContrasena()));

		SecurityContextHolder.getContext().setAuthentication(auth);

		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

		// Obtenemos el token del jwt
		String token = jwtUtils.generateJwtToken(userDetails);

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new AuthenticationResponse(token, userDetails.getUsuario().getId(),
				userDetails.getUsuario().getNombre(),
				userDetails.getUsuario().getApellidoPaterno() + " " + userDetails.getUsuario().getApellidoMaterno(),
				userDetails.getUsuario().getCorreo(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registrar(@RequestBody @Valid SignUpRequest signUpRequest) {

		if (usuarioRepository.existsByCorreo(signUpRequest.getCorreo())) {
			return new ResponseEntity<>("El correo ingresado ya esta en uso.", HttpStatus.BAD_REQUEST);
		}

		usuarioService.registrar(signUpRequest);

		return ResponseEntity.ok("Usuario registrado exitosamente");
	}
}
