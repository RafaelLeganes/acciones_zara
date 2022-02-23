package com.zara.acciones.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;
import com.zara.acciones.lb.Calculos;
import com.zara.acciones.model.Accion;
import com.zara.acciones.model.Accionista;

public class EscrituraFicheroDao {

	public void borrarCSV(String ruta) {
		boolean existe = new File(ruta).exists();

		if (existe) {
			File archivoAcciones = new File(ruta);
			archivoAcciones.delete();
		}
	}

	public void exportarCSV(List<Accion> acciones, String salidaArchivo) throws Exception {
		Calculos cal = new Calculos();
		Accionista accionista = new Accionista();
		double acAcumuladasAnio = 0;
		String accionesAcumuladas = null;
		try (CSVWriter salidaCSV = new CSVWriter(new FileWriter(salidaArchivo, true), ';')) {
			String[] cabecera = { "Fecha", "Cierre", "Apertura", "Acciones Compradas", "Acciones Acumuladas" };
			salidaCSV.writeNext(cabecera);
			int contador = 0;
			for (Accion accion : acciones) {
				if (contador != 0 && accion.getFecha().getYear() != acciones.get(contador - 1).getFecha().getYear()) {
					String[] datosAño = { "Total Acciones Año:",String.valueOf(cal.formatearDecimales(acAcumuladasAnio)) };
					salidaCSV.writeNext(datosAño);
					String[] datosAños = { "Total Acciones Acumuladas:", String.valueOf(accionesAcumuladas) };
					salidaCSV.writeNext(datosAños);
					String[] datosEspacio1 = {};
					salidaCSV.writeNext(datosEspacio1);
					String[] datosEspacio2 = {};
					salidaCSV.writeNext(datosEspacio2);
					acAcumuladasAnio = 0;
				}
				String fecha = String.valueOf(accion.getFecha());
				String cierre = String.valueOf(accion.getValorCierre());
				String apertura = String.valueOf(accion.getValorApertura());
				String accionesCompradas = String.valueOf(cal.calcularAcciones(50, accion.getValorApertura(), accion));
				cal.incrementarAcciones(50, accion, accionista);
				acAcumuladasAnio += cal.formatearDecimales(cal.calcularAcciones(50, accion.getValorApertura(), accion));
				accionesAcumuladas = String.valueOf(cal.formatearDecimales(accionista.getCantidadAcciones()));
				String[] datos = { fecha, cierre, apertura, accionesCompradas, accionesAcumuladas };
				salidaCSV.writeNext(datos);
				contador++;
			}
			String[] datosAño = { "Total Acciones Año:",String.valueOf(cal.formatearDecimales(acAcumuladasAnio)) };
			salidaCSV.writeNext(datosAño);
			String[] datosAños = { "Total Acciones Acumuladas:", String.valueOf(accionesAcumuladas) };
			salidaCSV.writeNext(datosAños);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
