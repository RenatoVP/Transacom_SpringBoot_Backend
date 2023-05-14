package com.transacom.api.payload.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.transacom.api.utils.Validations;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "El nombre no puede estar vacio.")
	@Length(min = 3, max = 50, message = "El nombre debe contener entre 3 a 50 caracteres.")
	@Pattern(regexp = Validations.TEXT_NOT_CONTAIN_SPACE, message = "El nombre solo permite el ingreso de caracteres y no se permite espacios ni caracteres especiales.")
	private String nombre;

	@NotBlank(message = "Los apellidos no pueden estar vacio.")
	@Length(min = 3, max = 50, message = "El nombre debe contener entre 3 a 50 caracteres.")
	@Pattern(regexp = Validations.TEXT_NOT_CONTAIN_SPACE, message = "El apellido paterno solo permite el ingreso de caracteres y no se permite espacios ni caracteres especiales.")
	private String apellidoPaterno;
	
	@NotBlank(message = "Los apellidos no pueden estar vacio.")
	@Length(min = 3, max = 50, message = "El nombre debe contener entre 3 a 50 caracteres.")
	@Pattern(regexp = Validations.TEXT_NOT_CONTAIN_SPACE, message = "El apellido materno solo permite el ingreso de caracteres y no se permite espacios ni caracteres especiales.")
	private String apellidoMaterno;

	@NotBlank(message = "El telefono no puede estar vacio.")
	@Pattern(regexp = Validations.PHONE, message = "El telefono solo permite el ingreso de 8 digitos.")
	private String telefono;
	
	@NotNull()
	@Pattern(regexp = Validations.RUC, message = "El numero de ruc debe empeza con 10 o 20 y debe contener 11 digitos.")
	private String ruc;

	@NotBlank(message = "El correo no puede estar vacio.")
	@Email(regexp = Validations.EMAIL, message = "El correo ingresado no es valido.")
	private String correo;

	@NotBlank(message = "La contraseña no puede estar vacia.")
	@Pattern(regexp = Validations.PASSWORD, message = "La contraseña debe ser minimo de 8 caracteres, contar con al menos 1 caracter especial y al menos un numero.")
	private String contrasena;

	@NotBlank(message = "El rol no puede estar vacio.")
	private String rol;

}
