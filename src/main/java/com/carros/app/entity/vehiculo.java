package com.carros.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "vehiculo")

public class vehiculo {

	@Id
	private String idVehiculo;
	private String matricula;
	private String marca;
	private String modelo;
	private int año;
	private String estado;
	private String tipo; 
	private double kilometraje;
	
	
	public vehiculo() {
		super();
	}
	
	public vehiculo(String idVehiculo, String matricula, String marca, String modelo, int año, String estado,
			String tipo, double kilometraje) {
		super();
		this.idVehiculo = idVehiculo;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.año = año;
		this.estado = estado;
		this.tipo = tipo;
		this.kilometraje = kilometraje;
	}
	public String getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(String idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getAño() {
		return año;
	}
	public void setAño(int año) {
		this.año = año;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getKilometraje() {
		return kilometraje;
	}
	public void setKilometraje(double kilometraje) {
		this.kilometraje = kilometraje;
	}
	
}
