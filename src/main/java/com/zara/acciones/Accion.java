package com.zara.acciones;

import java.time.LocalDate;


public class Accion {

	public final double comision = 0.02;
	private LocalDate fecha;
	private double valorApertura;
	private double valorCierre;
	

	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public double getValorApertura() {
		return valorApertura;
	}
	public void setValorApertura(double valorApertura) {
		this.valorApertura = valorApertura;
	}
	public double getValorCierre() {
		return valorCierre;
	}
	public void setValorCierre(double valorCierre) {
		this.valorCierre = valorCierre;
	}
	
}
