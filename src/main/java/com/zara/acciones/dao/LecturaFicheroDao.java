package com.zara.acciones.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opencsv.CSVReader;
import com.zara.acciones.model.Accion;
import com.zara.acciones.utilities.FechaAccionesComparator;
import com.zara.acciones.utilities.FileResourceUtils;

public class LecturaFicheroDao {
	
	private static final Logger logger = LogManager.getLogger(LecturaFicheroDao.class);
	
	public List<Accion> leerFicheroDao(String ruta, Properties appProps) throws Exception {;
		logger.debug("Llamada al metodo leerFicheroDao con parametros: {}", ruta);
		String locale = appProps.getProperty("Locale");
		String formato = appProps.getProperty("date");
		char separador = appProps.getProperty("Separator").charAt(0);
		CSVReader reader = null;
		List<Accion> lista = new ArrayList<Accion>();
		DateTimeFormatter formateo = DateTimeFormatter.ofPattern(formato, Locale.forLanguageTag(locale));
		FileResourceUtils app = new FileResourceUtils();
		InputStream is = app.getFileFromResourceAsStream(ruta);
		try {
			reader = new CSVReader(new InputStreamReader(is,"UTF-8"),separador);
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
		} catch (IOException e) {
			logger.error("Error en la lectura del fichero con parámetro: {}", ruta);
			e.printStackTrace();
		} finally {
			if(null!=reader) {
				reader.close();
			}
		}
		Collections.sort(lista, new FechaAccionesComparator());
		logger.debug("Devolución del metodo leerFicheroDao size de la lista devuelta: {}",lista.size());
		return lista;
	}
}
