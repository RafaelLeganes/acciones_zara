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

import com.opencsv.CSVReader;
import com.zara.acciones.model.Accion;
import com.zara.acciones.utilities.FechaAccionesComparator;
import com.zara.acciones.utilities.FileResourceUtils;

public class LecturaFicheroDao {
	
	
	public List<Accion> leerFicheroDao(String ruta, Properties appProps) throws Exception {;
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
			e.printStackTrace();
		} finally {
			if(null!=reader) {
				reader.close();
			}
		}
		Collections.sort(lista, new FechaAccionesComparator());
		return lista;
	}
}
