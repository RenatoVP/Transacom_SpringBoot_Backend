package com.transacom.api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_usuario")
@Getter @Setter
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	
	@Column(length = 30, nullable = false)
	private String nombre;
	
	@Column(length = 30, nullable = false)
	private String apellidoPaterno;
	@Column(length = 30, nullable = false)
	private String apellidoMaterno;
	
	@Column(length = 11)
	private String ruc;
	
	@Column(length = 8, nullable = false)
	private String telefono;
	
	@Column(length = 50, unique = true, nullable = false)
	private String correo;
	
	@Column(length = 255, nullable = false)
	private String contrasena;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private LocalDateTime fechaRegistro;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private LocalDateTime fechaActualizado;
	
	@Column(nullable = false)
	@Value(value = "true")
	private boolean activo;
	
	@ManyToMany
	@JoinTable(
			name = "tb_usuario_rol",
			joinColumns = @JoinColumn(columnDefinition = "idusuario", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(columnDefinition = "idrol", referencedColumnName = "id")
	)
	private Set<Rol> roles;
	
}
