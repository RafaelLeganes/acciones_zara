package com.zara.acciones;

import java.util.Date;

public class Accion {

	public final double comision = 0.02;
	private Date fecha;
	private double valorApertura;
	private double valorCierre;
	

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
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
