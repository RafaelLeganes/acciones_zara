package com.zara.acciones;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;

public class AccionesCSV {
	
	private static final String salidaArchivo = "C:/Users/Administrador.LIFERAY/Desktop/examen/Acciones.csv";
	
	public static void exportarCSV(List<Accion> acciones) {
		boolean existe = new File(salidaArchivo).exists();
		double acAcumuladasAnio = 0;

		//Si existe un archivo llamado asi lo borra
		if(existe) {
			File archivoAcciones = new File(salidaArchivo);
			archivoAcciones.delete();
		}
		
		try {
			CSVWriter salidaCSV = new CSVWriter(new FileWriter(salidaArchivo,true), ';');
			String[] cabecera = {"Fecha","Cierre","Apertura","Acciones Compradas", "Acciones Acumuladas"};
			salidaCSV.writeNext(cabecera);
			Accion acc = new Accion();
			Accionista accionista = new Accionista();
			Principal prin = new Principal(acc, accionista);
			int contador = 0;
			for(Accion accion: acciones) {
				String fecha = String.valueOf(accion.getFecha());
				String cierre = String.valueOf(accion.getValorCierre());
				String apertura = String.valueOf(accion.getValorApertura());
				String accionesCompradas = String.valueOf(prin.calcularAcciones(50,accion.getValorApertura()));
				prin.incrementarAcciones(50,accion);
				acAcumuladasAnio+=prin.formatearDecimales(prin.calcularAcciones(50,accion.getValorApertura()));
				String accionesAcumuladas = String.valueOf(prin.formatearDecimales(accionista.getCantidadAcciones()));
				String[] datos = {fecha, cierre, apertura, accionesCompradas, accionesAcumuladas};
				salidaCSV.writeNext(datos);	
				if(acciones.get(contador).equals(acciones.get(acciones.size()-1)) ) {
					String[] datosAño = {"Total Acciones Año:",String.valueOf(prin.formatearDecimales(acAcumuladasAnio))};
					salidaCSV.writeNext(datosAño);
					String[] datosAños = {"Total Acciones Acumuladas:",String.valueOf(accionesAcumuladas)};
					salidaCSV.writeNext(datosAños);
				} else if(accion.getFecha().getYear()!=acciones.get(contador+1).getFecha().getYear()) {
					String[] datosAño = {"Total Acciones Año:",String.valueOf(prin.formatearDecimales(acAcumuladasAnio))};
					salidaCSV.writeNext(datosAño);
					String[] datosAños = {"Total Acciones Acumuladas:",String.valueOf(accionesAcumuladas)};
					salidaCSV.writeNext(datosAños);
					String[] datosEspacio1 = {};
					salidaCSV.writeNext(datosEspacio1);
					String[] datosEspacio2 = {};
					salidaCSV.writeNext(datosEspacio2);
					acAcumuladasAnio=0;
				}
				contador++;
			}
			salidaCSV.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
