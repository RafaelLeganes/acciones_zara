package com.zara.acciones;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.zara.acciones.controller.Controller;
import com.zara.acciones.model.Accion;

public class Principal {
	
	private static final Logger logger = LogManager.getLogger(Principal.class);

	public static void main(String[] args) {
		Controller c = new Controller();
		try {
			List<Accion> acciones = c.leerFicheroController("stocks-ITX.csv");
			List<Accion> Listadefinitiva = c.calcularTotales(acciones);
			c.borrarFicheroController();
			c.escribirFicheroController(Listadefinitiva);
		} catch (Exception e) {
			logger.error("error lanzado al main:  {}", e);
			e.printStackTrace();
		}

	}

}
