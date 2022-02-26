package com.zara.acciones.lb;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zara.acciones.model.Accion;
import com.zara.acciones.model.Accionista;


public class Calculos {
	
	private static final Logger logger = LogManager.getLogger(Calculos.class);
	
	public double calcularAcciones(int compra, double valorApertura, Accion accion) {
		logger.debug("Llamada al metodo calcularAcciones con parametros: {} , accion fecha: {}, valorApertura: {} y valorCierre: {}",compra,valorApertura, accion.getFecha(),
				accion.getValorApertura(), accion.getValorCierre());
		double accionesCompradas;
		accionesCompradas = formatearDecimales((compra - (compra * accion.comision)) / valorApertura);
		logger.debug("Devolución del metodo calcularAcciones parametro de salida: {}",accionesCompradas);
		return accionesCompradas;
	}

	public double formatearDecimales(Double numero) {
		logger.debug("Llamada al metodo formatearDecimales con parametro: {}",numero);
		double num = Math.round(numero * Math.pow(10, 3)) / Math.pow(10, 3);
		logger.debug("Devolución del metodo formatearDecimales parametro de salida: {}",num);
		return num;
	}

	public void incrementarInversion(int cantidad, Accionista accionista) {
		logger.debug("Llamada al metodo incrementarInversion con parametros: {}, accionista cantidadAcciones: {} y cantidadInvertida: {} "
				,cantidad, accionista.getCantidadAcciones(), accionista.getCantidadInvertida());
		accionista.setCantidadInvertida(accionista.getCantidadInvertida() + cantidad);
	}

	public void incrementarAcciones(int cantidad, Accion accion, Accionista accionista) {
		logger.debug("Llamada al metodo incrementarAcciones con parametros: {}, accion fecha: {}, valorApertura: {} y valorCierre: {} , "
				+ "accionista cantidadAcciones: {}, y cantidadInvertida: {}",cantidad, accion.getFecha(),
				accion.getValorApertura(), accion.getValorCierre(), accionista.getCantidadAcciones(), accionista.getCantidadInvertida());
		accionista.setCantidadAcciones(
				formatearDecimales(accionista.getCantidadAcciones()) + calcularAcciones(cantidad, accion.getValorApertura(), accion ));
	}

	public double calcularBeneficio(List<Accion> acciones, Accionista accionista ) {
		logger.debug("Llamada al metodo calcularBeneficio con parametro size lista Acciones: {}, accionista cantidadAcciones: {} y cantidadInvertida: {}",
				acciones.size(), accionista.getCantidadAcciones(), accionista.getCantidadInvertida());
		double benefico = formatearDecimales(
				(accionista.getCantidadAcciones() * acciones.get(acciones.size()-1).getValorCierre()) - accionista.getCantidadInvertida());
		logger.debug("Devolución del metodo calcularBeneficio con parametro de salida: {}",benefico);
		return benefico;
	}
	
	public double calcularGanancia(List<Accion> acciones, Accionista accionista) {
		logger.debug("Llamada al metodo calcularGanancia con parametro size lista Acciones: {}, accionista cantidadAcciones: {} y cantidadInvertida: {}",
				acciones.size(), accionista.getCantidadAcciones(), accionista.getCantidadInvertida());
		double ganancia = formatearDecimales(
				(accionista.getCantidadAcciones() * acciones.get(acciones.size()-1).getValorCierre()));
		logger.debug("Devolución del metodo calcularGanancia con parametro de salida: {}",ganancia);
		return ganancia;
	}


}
