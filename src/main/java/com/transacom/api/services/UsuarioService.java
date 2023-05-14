package com.transacom.api.services;

import com.transacom.api.payload.request.SignUpRequest;

public interface UsuarioService {
	void registrar(SignUpRequest signUpRequest);
}
