package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;

public class Clientes {

	private List<Cliente> coleccionClientes;

	// Constructor por defecto
	public Clientes() {
		coleccionClientes = new ArrayList<>();
	}

	public List<Cliente> get() {
		return coleccionClientes;
	}

	public int getCantidad() {
		return coleccionClientes.size();
	}

	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new IllegalArgumentException("No se puede insertar un cliente nulo.");
		}
		if (coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("El cliente ya existe.");
		} else {
			coleccionClientes.add(new Cliente(cliente));
		}
	}

	public Cliente buscar(Cliente cliente) {
		int indice = coleccionClientes.indexOf(cliente);
		if (indice != -1) {
			return new Cliente(coleccionClientes.get(indice));
		} else {
			return null;
		}
	}

	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("No se puede BORRAR un cliente nulo.");
		}
		if (!coleccionClientes.remove(cliente)) {
			throw new OperationNotSupportedException("El cliente a borrar NO existe.");
		}
	}
	
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		//Crea el método modificar que permitirá cambiar el nombre o el teléfono 
		//(si estos parámetros no son nulos ni blancos) de un cliente existente y 
		//si no lanzará la correspondiente excepción
		if (buscar(cliente) != null) {
			if (nombre != null || !nombre.isBlank()) {
				cliente.setNombre(nombre);	
			}
			if (telefono != null || !telefono.isBlank()) {
				cliente.setTelefono(telefono);	
			}
		}else {
			throw new OperationNotSupportedException("El cliente no se puede MODIFICAR.");
		}
	}
	
	
}
