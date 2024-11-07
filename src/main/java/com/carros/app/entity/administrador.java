package com.carros.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "administrador")

public class administrador {
	@Id
	
	
	private String id;


	private String usuario;

	private String contrasena;

	private String nombre;
	
	private String telefono;
	
	private String correo;
	
	
	public administrador() {
		super();
	}
	
	public administrador(String id, String usuario, String contrasena, String nombre, String telefono, String correo) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}


}
