package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;

public class Alquileres {

	private List<Alquiler> coleccionAlquileres;

	// Constructor por defecto
	public Alquileres() {
		coleccionAlquileres = new ArrayList<>();
	}

	// Métodos
	public List<Alquiler> get() {
		return new ArrayList<>(coleccionAlquileres);
	}

	public List<Alquiler> get(Cliente cliente) {
		List<Alquiler> listaAuxCliente = new ArrayList<>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getCliente().equals(cliente)) {
				listaAuxCliente.add(alquiler);
			}
		}
		return listaAuxCliente;
	}

	public List<Alquiler> get(Turismo turismo) {
		List<Alquiler> listaAuxTurismo = new ArrayList<>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getTurismo().equals(turismo)) {
				listaAuxTurismo.add(alquiler);
			}
		}
		return listaAuxTurismo;
	}

	public int getCantidad() {
		return coleccionAlquileres.size();
	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		if (coleccionAlquileres.contains(alquiler)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");
		} else {
			coleccionAlquileres.add(alquiler);
			// Cuando tengamos que crear nuevas instancias entonces:
			// coleccionClientes.add(new Cliente(cliente));
		}
	}

	// TO DO: método comprobarAlquiler y devolver

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}
		if (!coleccionAlquileres.contains(alquiler)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}
		coleccionAlquileres.remove(alquiler);
	}

	public Alquiler buscar(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}

		int indice = coleccionAlquileres.indexOf(alquiler);
		// Alquiler auxiliar
		Alquiler aux = null;

		if (indice != -1) {
			aux = coleccionAlquileres.get(indice);
			// aux = new Cliente(coleccionClientes.get(indice));
		}
		return aux;
	}

}
