package com.zara.acciones;


import java.util.List;
import com.zara.acciones.controller.Controller;
import com.zara.acciones.model.Accion;

public class Principal {

	public static void main(String[] args) {
		Controller c = new Controller();
		try {
			List<Accion> acciones = c.leerFicheroController("stocks-ITX.csv");
			List<Accion> Listadefinitiva = c.calcularTotales(acciones);
			c.borrarFicheroController();
			c.escribirFicheroController(Listadefinitiva);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
