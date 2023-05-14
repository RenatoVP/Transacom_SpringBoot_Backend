package com.transacom.api.utils;

public class Validations {
	// El texto solo permite caracteres
	public static final String TEXT = "^[A-Za-z\\s]+$";

	// El texto solo permite caracteres pero con espacios
	public static final String TEXT_NOT_CONTAIN_SPACE = "^[A-Za-z]+$";

	// Valida el email
	public static final String EMAIL = "^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	// El numero de telefono debe contener 8 digitos
	public static final String PHONE = "^[\\d]{8}$";
	
	//El numero de ruc debe contener 11 digitos y debe iniciar con 10 o 20
	public static final String RUC = "^(10|20)[\\d]{9}$";

	// La contraseña debe ser minimo de 8 caracteres, contar con al menos 1 caracter
	// especial y al menos un numero
	public static final String PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[A-Za-z]).{8,}$";
}
