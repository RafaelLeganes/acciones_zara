package com.zara.acciones.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zara.acciones.dao.EscrituraFicheroDao;
import com.zara.acciones.dao.LecturaFicheroDao;
import com.zara.acciones.model.Accion;
import com.zara.acciones.model.Accionista;
import com.zara.acciones.utilities.FileResourceUtils;
import com.zara.acciones.lb.Calculos;

public class Controller {

	private static final Logger logger = LogManager.getLogger(Controller.class);

	public List<Accion> leerFicheroController(String ruta) throws Exception {
		logger.debug("Llamada al metodo leerFicheroController con parametros: {}", ruta);
		FileResourceUtils app = new FileResourceUtils();
		InputStream is = app.getFileFromResourceAsStream("app.properties");
		Properties appProps = new Properties();
		appProps.load(is);
		LecturaFicheroDao leer = new LecturaFicheroDao();
		List<Accion> lista = leer.leerFicheroDao(ruta, appProps);
		logger.debug("Devolución del metodo leerFicheroController con tamaño listaAcciones: {}", lista.size());
		return lista;
	}

	public void borrarFicheroController() throws FileNotFoundException, IOException {
		logger.debug("Llamada al metodo borrarFicheroController");
		FileResourceUtils app = new FileResourceUtils();
		InputStream is = app.getFileFromResourceAsStream("app.properties");
		Properties appProps = new Properties();
		appProps.load(is);
		String ruta = appProps.getProperty("ruta");
		EscrituraFicheroDao borrar = new EscrituraFicheroDao();
		borrar.borrarCSV(ruta);
	}

	public void escribirFicheroController(List<Accion> acciones) throws Exception {
		logger.debug("Llamada al metodo escribirFicheroController con parametro size lista Acciones: {}",
				acciones.size());
		FileResourceUtils app = new FileResourceUtils();
		InputStream is = app.getFileFromResourceAsStream("app.properties");
		Properties appProps = new Properties();
		appProps.load(is);
		String ruta = appProps.getProperty("ruta");
		EscrituraFicheroDao escribir = new EscrituraFicheroDao();
		escribir.exportarCSV(acciones, ruta);
	}

	public List<LocalDate> buscaUltimoJueves(List<Accion> acciones) {
		logger.debug("Llamada al metodo buscaUltimoJueves con parametro size lista Acciones: {}", acciones.size());
		List<LocalDate> listaFechasduplicadas = new ArrayList<LocalDate>();
		acciones.stream().forEach(accion -> listaFechasduplicadas
				.add(accion.getFecha().with(TemporalAdjusters.lastInMonth(DayOfWeek.THURSDAY)).plusDays(1)));
		List<LocalDate> listaFechas = listaFechasduplicadas.stream().distinct().collect(Collectors.toList());
		logger.debug("Devolución del metodo buscaUltimoJueves con parametro size lista LocalDate: {}",
				listaFechas.size());
		return listaFechas;
	}

	public List<Accion> encuentraFechaCompra(List<LocalDate> listaFechas, List<Accion> acciones) {
		logger.debug(
				"Llamada al metodo encuentraFechaCompra con parametro size lista LocalDate: {} y ListaAcciones: {}",
				listaFechas.size(), acciones.size());
		List<Accion> ListaDiasComprados = new ArrayList<Accion>();
		int maxDias = 20;
		listaFechas.stream().forEach(fecha -> {
			int contador = 0;
			while (contador <= maxDias) {
				LocalDate fechaBuscada = fecha.plusDays(contador);
				Optional<Accion> optionalAccion = acciones.stream()
						.filter(accion -> accion.getFecha().equals(fechaBuscada)).findFirst();
				if (optionalAccion.isPresent()) {
					ListaDiasComprados.add(optionalAccion.get());
					contador = 21;
				}
				contador++;
			}
		});
		logger.debug("Devolución del metodo encuentraFechaCompra con parametro size lista Acciones: {}",
				ListaDiasComprados.size());
		return ListaDiasComprados;
	}

	public List<Accion> calcularTotales(List<Accion> acciones) {
		logger.debug("Llamada al metodo calcularTotales con parametro size lista Acciones: {}", acciones.size());
		List<LocalDate> listaFechas = buscaUltimoJueves(acciones);
		List<Accion> Listadefinitiva = encuentraFechaCompra(listaFechas, acciones);
		Calculos c = new Calculos();
		StringBuilder sbuilder = new StringBuilder();
		Accionista accionista = new Accionista();
		Listadefinitiva.stream().forEach(accion -> {
			c.incrementarAcciones(50, accion, accionista);
			sbuilder.append(accion.getFecha()).append(" Acciones compradas: ")
					.append(c.calcularAcciones(50, accion.getValorApertura(), accion)).append(" Acumulado acciones: ")
					.append(c.formatearDecimales(accionista.getCantidadAcciones()))
					.append(System.getProperty("line.separator"));
			System.out.println(sbuilder);
			c.incrementarInversion(50, accionista);
		});
		StringBuilder sbuilder2 = new StringBuilder();
		sbuilder2.append("Total invertido: ").append(accionista.getCantidadInvertida())
				.append(System.getProperty("line.separator")).append("Total acciones: ")
				.append(c.formatearDecimales(accionista.getCantidadAcciones()))
				.append(System.getProperty("line.separator")).append("Total beneficio: ")
				.append(c.calcularBeneficio(acciones, accionista)).append(System.getProperty("line.separator"))
				.append("La ganancia total es: ").append(c.calcularGanancia(acciones, accionista));
		System.out.println(sbuilder2);
		logger.debug("Devolución del metodo calcularTotales con parametro size lista Acciones definitivas: {}",
				Listadefinitiva.size());
		return Listadefinitiva;
	}

}