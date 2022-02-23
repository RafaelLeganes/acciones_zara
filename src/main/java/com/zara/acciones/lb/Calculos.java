package com.zara.acciones.lb;

import java.util.List;
import com.zara.acciones.model.Accion;
import com.zara.acciones.model.Accionista;

public class Calculos {
	
	
	
	public double calcularAcciones(int compra, double valorApertura, Accion accion) {
		double accionesCompradas;
		accionesCompradas = formatearDecimales((compra - (compra * accion.comision)) / valorApertura);
		return accionesCompradas;
	}

	public double formatearDecimales(Double numero) {
		return Math.round(numero * Math.pow(10, 3)) / Math.pow(10, 3);
	}

	public void incrementarInversion(int cantidad, Accionista accionista) {
		accionista.setCantidadInvertida(accionista.getCantidadInvertida() + cantidad);
	}

	public void incrementarAcciones(int cantidad, Accion accion, Accionista accionista) {
		accionista.setCantidadAcciones(
				formatearDecimales(accionista.getCantidadAcciones()) + calcularAcciones(cantidad, accion.getValorApertura(), accion ));
	}

	public double calcularBeneficio(List<Accion> acciones, Accionista accionista ) {
		double ganancia = formatearDecimales(
				(accionista.getCantidadAcciones() * acciones.get(acciones.size()-1).getValorCierre()) - accionista.getCantidadInvertida());
		return ganancia;
	}
	
	public double calcularGanancia(List<Accion> acciones, Accionista accionista) {
		double ganancia = formatearDecimales(
				(accionista.getCantidadAcciones() * acciones.get(acciones.size()-1).getValorCierre()));
		return ganancia;
	}


}
