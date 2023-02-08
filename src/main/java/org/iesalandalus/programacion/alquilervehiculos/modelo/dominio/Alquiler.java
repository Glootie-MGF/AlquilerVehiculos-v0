package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import javax.naming.OperationNotSupportedException;

public class Alquiler {

	static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final int PRECIO_DIA = 20;

	private LocalDate fechaAlquiler;
	private LocalDate fechaDevolucion;
	private Turismo turismo;
	private Cliente cliente;

	//Constructor con parámetros
	public Alquiler(Cliente cliente, Turismo turismo,  LocalDate fechaAlquiler) {
		setFechaAlquiler(fechaAlquiler);
		setTurismo(turismo);
		setCliente(cliente);
	}
	//Constructor copia
	public Alquiler(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No es posible copiar un alquiler nulo.");
		}
		setFechaAlquiler(alquiler.getFechaAlquiler());
		setFechaDevolucion(alquiler.getFechaDevolucion());
		setTurismo(new Turismo(alquiler.getTurismo()));
		setCliente(new Cliente(alquiler.getCliente()));
		
	}
	//Métodos
	public void devolver(LocalDate fechaDevolucion) throws OperationNotSupportedException {
		//se encargará de asignar la fecha de devoluión si ésta es correcta
		if (this.fechaDevolucion != null) {
			throw new OperationNotSupportedException("ERROR: La devolución ya estaba registrada.");
		}
		setFechaDevolucion(fechaDevolucion);
	}
	
	public int getPrecio() {
		//devolverá el precio del alquiler conforme a la fórmula establecida por nuestro cliente
		//(precioDia + factorCilindrada) * numDias
		//factorCilindrada = cilindrada del turismo / 10 
		//numDias = fechaDevolucion-fechaAlquiler
		int precio = -1;
		
		if (fechaDevolucion == null) {
			precio = 0;
		}else {
			int factorCilindrada = turismo.getCilindrada()/10;
			Duration numDias = Duration.between(fechaAlquiler.atStartOfDay(),fechaDevolucion.atStartOfDay());
			precio = (int)((PRECIO_DIA + factorCilindrada) * numDias.toDays());
		}
		return precio;
	}
	//Métodos get y set
	public LocalDate getFechaAlquiler() {
		return fechaAlquiler;
	}

	public void setFechaAlquiler(LocalDate fechaAlquiler) {
		if (fechaAlquiler == null) {
			throw new NullPointerException("ERROR: La fecha de alquiler no puede ser nula.");
		}
		if (fechaAlquiler.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("ERROR: La fecha de alquiler no puede ser futura.");
		}
		this.fechaAlquiler = fechaAlquiler;
	}
	
	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		if (fechaDevolucion == null) {
			throw new NullPointerException("ERROR: La fecha de devolución no puede ser nula.");
		}
		//La fecha de devolución no puede ser igual o anterior a la fecha de alquiler 
		//y tampoco puede ser posterior a hoy
		if (fechaDevolucion.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("ERROR: La fecha de devolución no puede ser futura.");
		}
		if (fechaDevolucion.isEqual(fechaAlquiler)||fechaDevolucion.isBefore(fechaAlquiler)) {
			throw new IllegalArgumentException("ERROR: La fecha de devolución debe ser posterior a la fecha de alquiler.");
		}
		this.fechaDevolucion = fechaDevolucion;
	}

	public Turismo getTurismo() {
		return turismo;
	}

	public void setTurismo(Turismo turismo) {
		if (turismo == null) {
			throw new NullPointerException("ERROR: El turismo no puede ser nulo.");
		}
		this.turismo = turismo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: El cliente no puede ser nulo.");
		}
		this.cliente = cliente;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cliente, fechaAlquiler, fechaDevolucion, turismo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alquiler other = (Alquiler) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(fechaAlquiler, other.fechaAlquiler)
				&& Objects.equals(fechaDevolucion, other.fechaDevolucion) && Objects.equals(turismo, other.turismo);
	}
	@Override
	public String toString() {
		if (fechaDevolucion == null) {
			return String.format("%s <---> %s, %s - %s (%d€)", cliente, turismo, fechaAlquiler.format(FORMATO_FECHA), "Aún no devuelto", 0);
		}
		return String.format("%s <---> %s, %s - %s (%d€)", cliente, turismo, fechaAlquiler.format(FORMATO_FECHA),
				fechaDevolucion.format(FORMATO_FECHA), getPrecio());
	}


}
