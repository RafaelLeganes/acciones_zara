package com.zara.acciones;

import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import com.opencsv.CSVReader;

public class Principal {

	public static final char SEPARATOR = ';';
	private Accion accion;
	private Accionista accionista;

	
	public Principal(Accion accion, Accionista accionista) {
		this.accion = accion;
		this.accionista = accionista;
	}

	public double calcularAcciones(int compra, double valorApertura) {
		double accionesCompradas;
		accionesCompradas = formatearDecimales((compra - (compra * accion.comision)) / valorApertura);
		return accionesCompradas;
	}

	public double formatearDecimales(Double numero) {
		return Math.round(numero * Math.pow(10, 3)) / Math.pow(10, 3);
	}

	public void incrementarInversion(int cantidad) {
		accionista.setCantidadInvertida(accionista.getCantidadInvertida() + cantidad);
	}

	public void incrementarAcciones(int cantidad, Accion accion) {
		accionista.setCantidadAcciones(
				formatearDecimales(accionista.getCantidadAcciones()) + calcularAcciones(cantidad, accion.getValorApertura()));
	}

	public double calcularBeneficio(List<Accion> acciones) {
		double ganancia = formatearDecimales(
				(accionista.getCantidadAcciones() * acciones.get(acciones.size()-1).getValorCierre()) - accionista.getCantidadInvertida());
		return ganancia;
	}
	
	public double calcularGanancia(List<Accion> acciones) {
		double ganancia = formatearDecimales(
				(accionista.getCantidadAcciones() * acciones.get(acciones.size()-1).getValorCierre()));
		return ganancia;
	}

	public List<Accion> leerFichero(String ruta, String nombreFichero) throws IOException {
		CSVReader reader = null;
		List<Accion> lista = new ArrayList<Accion>();
		DateTimeFormatter formateo = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.forLanguageTag("es-ES"));
		try {
			reader = new CSVReader(new FileReader(ruta+nombreFichero),SEPARATOR);
			String[] nextLine = null;
			reader.readNext();
			while ((nextLine = reader.readNext()) != null) {
				Accion accion = new Accion();
				LocalDate date= LocalDate.parse(nextLine[0],formateo);
				accion.setFecha(date);
				accion.setValorCierre(Double.parseDouble(nextLine[1]));
				accion.setValorApertura(Double.parseDouble(nextLine[2]));
				lista.add(accion);
			}
		} catch (Exception e) {
			
		} finally {
			if(null!=reader) {
				reader.close();
			}
		}
		Collections.sort(lista, new FechaAccionesComparator());
		return lista;
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
	
	public double calcularTotales(List<Accion> acciones) {
		List <LocalDate> listaFechas = buscaUltimoJueves(acciones);
		List <Accion> Listadefinitiva= encuentraFechaCompra(listaFechas,acciones);
		for(Accion accion: Listadefinitiva) {
			incrementarAcciones(50,accion);
			System.out.println(accion.getFecha()+" Acciones compradas: "+calcularAcciones(50,accion.getValorApertura())+ " Acumulado acciones: "+formatearDecimales(accionista.getCantidadAcciones()));
			incrementarInversion(50);
			
		}
		System.out.println("Total invertido: "+accionista.getCantidadInvertida());
		System.out.println("Total acciones: "+formatearDecimales(accionista.getCantidadAcciones()));
		double beneficio = calcularBeneficio(acciones);
		System.out.println("Total beneficio: "+beneficio);
		double ganancia = calcularGanancia(acciones);
		AccionesCSV.exportarCSV(Listadefinitiva);
		return ganancia;
	}

	public static void main(String[] args) {
		Accion accion = new Accion();
		Accionista accionista = new Accionista();
		Principal p = new Principal(accion, accionista);
		try {
			List<Accion> acciones = p.leerFichero("./src/main/java/","stocks-ITX.csv");
			double ganancia = p.calcularTotales(acciones);
			System.out.println("La ganancia total es: "+ganancia);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}