package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Alquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Clientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Turismos;

public class Modelo {

	private Clientes clientes;
	private Alquileres alquileres;
	private Turismos turismos;

	//Constructor por defecto
	public Modelo() {
	}
	
	//Métodos
	public void comenzar() {
		//creará la instancia de las clases de negocio anteriores
		clientes = new Clientes();
		alquileres = new Alquileres();
		turismos = new Turismos();
	}
	public void terminar() {
		System.out.println("El modelo ha terminado.");
	}
	
	//Crea los diferentes métodos insertar, teniendo en cuenta que ahora ya 
	//si insertaremos nuevas instancias utilizando los constructores copia y 
	//que en el caso de los alquileres, primero debe buscar el cliente y el 
	//turismo y utilizar dichas instancias encontradas.
	public void insertar (Cliente cliente) throws OperationNotSupportedException {
		clientes.insertar(new Cliente(cliente));
	}
	public void insertar (Turismo turismo) throws OperationNotSupportedException {
		turismos.insertar(new Turismo(turismo));
	}
	public void insertar (Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		Cliente clienteBuscado = clientes.buscar(alquiler.getCliente());
		Turismo turismoBuscado = turismos.buscar(alquiler.getTurismo());
		
		if (clienteBuscado == null) {
			throw new NullPointerException("ERROR: Cliente NULO.");
		}
		if (turismoBuscado == null) {
			throw new NullPointerException("ERROR: Turismo NULO.");
		}
		alquileres.insertar(new Alquiler(clienteBuscado, turismoBuscado, alquiler.getFechaAlquiler()));
	}
	
	//Crea los diferentes métodos buscar, que devolverá una nueva instancia del 
	//elemento encontrado si éste existe.
	public Cliente buscar (Cliente cliente) {
		return new Cliente(clientes.buscar(cliente));
	}
	public Turismo buscar (Turismo turismo) {
		return new Turismo(turismos.buscar(turismo));
	}
	public Alquiler buscar (Alquiler alquiler) {
		return new Alquiler(alquileres.buscar(alquiler));
	}
	
	//Crea el método modificar que invocará a su homólogo en la clase de negocio.
	public void modificar (Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		clientes.modificar(cliente, nombre, telefono);
	}
	
	//Crea el método devolver que  realizará la devolución, si es posible, del alquiler pasado.
	public void devolver (Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		Alquiler alquilerBuscado = buscar(alquiler);
		if (alquilerBuscado == null) {
			throw new NullPointerException("ERROR: Alquiler NULO.");
		}
		alquileres.devolver(alquilerBuscado, fechaDevolucion);
	}
	
	//Crea los diferentes métodos borrar, teniendo en cuenta que los borrados 
	//se realizarán en cascada, es decir, si borramos un cliente también borraremos 
	//todos sus alquileres y lo mismo pasará con los turismos.
	public void borrar (Cliente cliente) {
		
	}
	
	
	
	
	
	
	
}
