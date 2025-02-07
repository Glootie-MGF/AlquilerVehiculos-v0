package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		
		Cliente clienteBuscado = clientes.buscar(alquiler.getCliente());
		Turismo turismoBuscado = turismos.buscar(alquiler.getTurismo());
		
		if (clienteBuscado == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		}
		if (turismoBuscado == null) {
			throw new OperationNotSupportedException("ERROR: No existe el turismo del alquiler.");
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
		Alquiler alquilerBuscado = alquileres.buscar(alquiler);
		
		if (alquilerBuscado == null) {
			throw new OperationNotSupportedException("ERROR: No existe el alquiler a devolver.");
		}
		alquilerBuscado.devolver(fechaDevolucion);
	}
	
	//Crea los diferentes métodos borrar, teniendo en cuenta que los borrados 
	//se realizarán en cascada, es decir, si borramos un cliente también borraremos 
	//todos sus alquileres y lo mismo pasará con los turismos.
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		for (Alquiler alquilerAux : alquileres.get(cliente)) {
			borrar(alquilerAux);
		}
		clientes.borrar(cliente);
	}

	public void borrar(Turismo turismo) throws OperationNotSupportedException {
		for (Alquiler alquilerAux : alquileres.get(turismo)) {
			borrar(alquilerAux);
		}
		turismos.borrar(turismo);
	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		alquileres.borrar(alquiler);
	}

	//Crea los diferentes métodos get, que deben devolver una nueva lista pero que 
	//contenga nuevas instancias no una copia de los elementos.
	public List<Cliente> getClientes() {
		List<Cliente> listaClientes = new ArrayList<>();
		for (Cliente cliente : clientes.get()) {
			listaClientes.add(new Cliente(cliente));
		}
		return listaClientes;
	}

	public List<Turismo> getTurismos() {
		List<Turismo> listaTurismos = new ArrayList<>();
		for (Turismo turismo : turismos.get()) {
			listaTurismos.add(new Turismo(turismo));
		}
		return listaTurismos;
	}

	public List<Alquiler> getAlquileres() {
		List<Alquiler> listaAlquileres = new ArrayList<>();
		for (Alquiler alquiler : alquileres.get()) {
			listaAlquileres.add(new Alquiler(alquiler));
		}
		return listaAlquileres;
	}

	public List<Alquiler> getAlquileres(Cliente cliente) {
		List<Alquiler> listaAlquileresConCliente = new ArrayList<>();
		for (Alquiler alquilerAux : alquileres.get(cliente)) {
			listaAlquileresConCliente.add(new Alquiler(alquilerAux));
		}
		return listaAlquileresConCliente;
	}

	public List<Alquiler> getAlquileres(Turismo turismo) {
		List<Alquiler> listaAlquileresConTurismo = new ArrayList<>();
		for (Alquiler alquilerAux : alquileres.get(turismo)) {
			listaAlquileresConTurismo.add(new Alquiler(alquilerAux));
		}
		return listaAlquileresConTurismo;
	}
	
}
