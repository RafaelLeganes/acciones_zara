package com.zara.acciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Principal {

	public static final String SEPARATOR = ";";
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

	public double calcularGanancia() {
		double ganancia = formatearDecimales(
				(accionista.getCantidadAcciones() * 29.17) - accionista.getCantidadInvertida());
		return ganancia;
	}

	public List<Accion> leerFichero() throws IOException {
		File f1 = new File("./src/main/java/stocks-ITX.csv");
		FileReader fin = new FileReader(f1);
		List<Accion> lista = new ArrayList<Accion>();
		BufferedReader br = new BufferedReader(fin);
		String line = br.readLine();
		line = br.readLine();
		SimpleDateFormat sfIn = new SimpleDateFormat("dd-MMM-yyyy", Locale.forLanguageTag("es-ES"));
		while (null != line) {
			String[] fields = line.split(SEPARATOR);
			Accion accion = new Accion();
			for (int i = 0; i < fields.length; i++) {
				switch (i) {
				case 0:
					/*if (fields[i].contains("sep")) {
						fields[i] = fields[i].replaceAll("sep", "sept");
					}*/
					// Por algún motivo aquí se lee sep y en cambio no lee sept
					Date date;
					try {
						date = sfIn.parse(fields[i]);
						accion.setFecha(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;
				case 1:
					accion.setValorCierre(Double.parseDouble(fields[i]));
					break;
				case 2:
					accion.setValorApertura(Double.parseDouble(fields[i]));
					break;
				}
			}
			lista.add(accion);
			line = br.readLine();
		}
		br.close();
		Collections.sort(lista, new FechaAccionesComparator());
		return lista;
	}
	
	public double calcularTotal(List<Accion> acciones) {
		List <LocalDate> listaFechas = new ArrayList <LocalDate>();
		boolean primero=true;
		for(Accion accion: acciones) {
			LocalDate fecha = accion.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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
		List <Accion> Listadefinitiva= ecuentraFecha(listaFechas,acciones);
		for(Accion accion: Listadefinitiva) {
			incrementarAcciones(50,accion);
			System.out.println(accion.getFecha()+" Acciones compradas: "+calcularAcciones(50,accion.getValorApertura())+ " Acumulado acciones: "+formatearDecimales(accionista.getCantidadAcciones()));
			incrementarInversion(50);
			
		}
		System.out.println("Total invertido: "+accionista.getCantidadInvertida());
		System.out.println("Total acciones: "+formatearDecimales(accionista.getCantidadAcciones()));
		double ganancia = calcularGanancia();
		return ganancia;

	}
	
	public List <Accion> ecuentraFecha(List <LocalDate> listaFechas, List<Accion> acciones) {
		List <Accion> ListaDiasComprados = new ArrayList <Accion>();
		int maxDias=20;
		for(LocalDate fechaCompra: listaFechas) {
			int contador=0;
			boolean encontrado = false;
			while(!encontrado && contador<=maxDias) {
				for(Accion accion: acciones) {
					LocalDate fecha = accion.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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
	


	public static void main(String[] args) {
		Accion accion = new Accion();
		Accionista accionista = new Accionista();
		Principal p = new Principal(accion, accionista);
		try {
			List<Accion> acciones = p.leerFichero();
			double ganancia = p.calcularTotal(acciones);
			System.out.println("la ganancia total es: "+ganancia);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}