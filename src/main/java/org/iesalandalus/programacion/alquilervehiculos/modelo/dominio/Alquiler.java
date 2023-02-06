package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Alquiler {

	static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final int PRECIO_DIA = 20;

	private LocalDate fechaAlquiler;
	private LocalDate fechaDevolucion;
	private Turismo turismo;
	private Cliente cliente;

	//Constructor con parámetros
	public Alquiler(LocalDate fechaAlquiler, Turismo turismo, Cliente cliente) {
		setFechaAlquiler(fechaAlquiler);
		setTurismo(turismo);
		setCliente(cliente);
	}
	//Constructor copia
	public Alquiler(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se pueden copiar datos nulos.");
		}
		setFechaAlquiler(alquiler.getFechaAlquiler());
		setTurismo(alquiler.getTurismo());
		setCliente(alquiler.getCliente());
	}
	//Métodos
	
	//Métodos get y set
	public LocalDate getFechaAlquiler() {
		return fechaAlquiler;
	}

	public void setFechaAlquiler(LocalDate fechaAlquiler) {
		if (fechaAlquiler == null) {
			throw new NullPointerException("La fecha NO puede ser nula.");
		}
		this.fechaAlquiler = fechaAlquiler;
	}
	
	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		if (fechaDevolucion == null) {
			throw new NullPointerException("La fecha NO puede ser nula.");
		}
		this.fechaDevolucion = fechaDevolucion;
	}

	public Turismo getTurismo() {
		return turismo;
	}

	public void setTurismo(Turismo turismo) {
		this.turismo = turismo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



}
