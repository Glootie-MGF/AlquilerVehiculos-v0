package org.iesalandalus.programacion.alquilervehiculos;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class MainApp {

	public static void main(String[] args) {
		
		/*
		 * Crea la clase MainApp con un único método main que será el método de entrada
		 * a nuestra aplicación. Este método simplemente creará una vista, un modelo y
		 * un controlador, pasándoles las instancias antes creadas. Luego simplemente
		 * invocará al método comenzar del controlador. Realiza las pruebas que estimes
		 * oportunas y cuando consideres que todo es correcto, realiza el último commit
		 * y seguidamente realiza el push a tu repositorio remoto.
		 */
		Modelo modelo = new Modelo();
		Vista vista = new Vista();
		Controlador controlador = new Controlador(modelo, vista);
		controlador.comenzar();
	}

}
