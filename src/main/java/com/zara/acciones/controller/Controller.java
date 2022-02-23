package com.zara.acciones.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.zara.acciones.dao.EscrituraFicheroDao;
import com.zara.acciones.dao.LecturaFicheroDao;
import com.zara.acciones.lb.Calculos;
import com.zara.acciones.model.Accion;
import com.zara.acciones.model.Accionista;

public class Controller {
	

	public List<Accion> leerFicheroController(String ruta) throws Exception {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "app.properties";
		Properties appProps = new Properties();
		appProps.load(new FileInputStream(appConfigPath));
		LecturaFicheroDao leer = new LecturaFicheroDao();
		List<Accion> lista = leer.leerFicheroDao(ruta, appProps);
		return lista;
	}
	
	public void borrarFicheroController() throws FileNotFoundException, IOException {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "app.properties";
		Properties appProps = new Properties();
		appProps.load(new FileInputStream(appConfigPath));
		String ruta = appProps.getProperty("ruta");
		EscrituraFicheroDao borrar = new EscrituraFicheroDao();
		borrar.borrarCSV(ruta);
	}
	
	public void escribirFicheroController(List<Accion> acciones) throws Exception {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "app.properties";
		Properties appProps = new Properties();
		appProps.load(new FileInputStream(appConfigPath));
		String ruta = appProps.getProperty("ruta");
		EscrituraFicheroDao escribir = new EscrituraFicheroDao();
		escribir.exportarCSV(acciones, ruta);
	}
	
	public List<LocalDate> buscaUltimoJueves(List<Accion> acciones){
		List <LocalDate> listaFechas = new ArrayList <LocalDate>();
		boolean primero=true;
		for(Accion accion: acciones) {
			LocalDate fecha = accion.getFecha();
			LocalDate ultimoJueves = fecha.with(TemporalAdjusters.lastInMonth(DayOfWeek.THURSDAY));
			LocalDate FechaCompra = ultimoJueves.plusDays(1);
			if(!primero && !FechaCompra.isEqual(listaFechas.get(listaFechas.size()-1))) {
				listaFechas.add(FechaCompra);
			} else {
				if(primero) {
					listaFechas.add(FechaCompra);
					primero=false;
				}
			}
		}
		return listaFechas;
	}
	
	public List <Accion> encuentraFechaCompra(List <LocalDate> listaFechas, List<Accion> acciones) {
		List <Accion> ListaDiasComprados = new ArrayList <Accion>();
		int maxDias=20;
		for(LocalDate fechaCompra: listaFechas) {
			int contador=0;
			boolean encontrado = false;
			while(!encontrado && contador<=maxDias) {
				for(Accion accion: acciones) {
					LocalDate fecha = accion.getFecha();
					if(fechaCompra.equals(fecha)) {
						ListaDiasComprados.add(accion);
						encontrado=true;
					} 
				}
				if(!encontrado) {
					fechaCompra= fechaCompra.plusDays(1);
				}
				contador++;
			}			
		}
		return ListaDiasComprados;
	}
	
	public List <Accion> calcularTotales(List<Accion> acciones) {
		List <LocalDate> listaFechas = buscaUltimoJueves(acciones);
		List <Accion> Listadefinitiva= encuentraFechaCompra(listaFechas,acciones);
		Calculos c = new Calculos();
		Accionista accionista = new Accionista();
		for(Accion accion: Listadefinitiva) {
			c.incrementarAcciones(50,accion,accionista);
			System.out.println(accion.getFecha()+" Acciones compradas: "+c.calcularAcciones(50,accion.getValorApertura(),accion)+ " Acumulado acciones: "+c.formatearDecimales(accionista.getCantidadAcciones()));
			c.incrementarInversion(50,accionista);
			
		}
		System.out.println("Total invertido: "+accionista.getCantidadInvertida());
		System.out.println("Total acciones: "+c.formatearDecimales(accionista.getCantidadAcciones()));
		double beneficio = c.calcularBeneficio(acciones, accionista);
		System.out.println("Total beneficio: "+beneficio);
		double ganancia = c.calcularGanancia(acciones, accionista);
		System.out.println("La ganancia total es: "+ganancia);
		return Listadefinitiva;
	}



}