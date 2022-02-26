package com.zara.acciones;

import static org.junit.Assert.assertEquals;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.zara.acciones.controller.Controller;
import com.zara.acciones.lb.Calculos;
import com.zara.acciones.model.Accion;
import com.zara.acciones.model.Accionista;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class PrincipalTest<ResourceFile> {
	
	private static final Logger logger = LogManager.getLogger(Controller.class);

	private Accion accion = new Accion();
	private Accionista accionista = new Accionista();
	private Controller principalTest = new Controller();
	private Calculos cal = new Calculos();

	@Test
	@Parameters({
    	"50, 3.664, 13.373",
    	"50, 3.256, 15.049"})
	public void testCalcularAcciones(int cantidadInvertida, double precioApertura, double expectedValue) {
		logger.debug("Llamada al metodo testCalcularAcciones con parametros: \"50, 3.664, 13.373\", \"50, 3.256, 15.049\"");
		assertEquals(Double.doubleToLongBits(expectedValue), Double.doubleToLongBits(cal.calcularAcciones(cantidadInvertida,precioApertura,accion)));	
	}

	@Test
	@Parameters({
    	"3.567452, 3.567",
    	"3.878975, 3.879"})
	public void testFormatearDecimales(double num1, double expectedValue) {
		logger.debug("Llamada al metodo testFormatearDecimales con parametros: \"3.567452, 3.567\", \"53.878975, 3.879\"");
		assertEquals(Double.doubleToLongBits(expectedValue),Double.doubleToLongBits(cal.formatearDecimales(num1)));
	}

	@Test
	@Parameters({
		"50, 50",
    	"200, 200"})
	public void testIncrementarInversion(int cantidadInvertida, int expectedValue) {
		logger.debug("Llamada al metodo testIncrementarInversion con parametros: \"50, 50\", \"200, 200\"");
		cal.incrementarInversion(cantidadInvertida, accionista);
		assertEquals(expectedValue, accionista.getCantidadInvertida());
	}

	@Test
	@Parameters({
		"3.664, 50, 3.505, 27.353",
		"3.256, 50, 3.210, 30.314"})
	public void testIncrementarAcciones(double accionesA, int cantidadInvertida, double accionesB, double expectedValue) {
		logger.debug("Llamada al metodo testIncrementarAcciones con parametros: \"3.664, 50, 3.505, 27.353\", \"3.256, 50, 3.210, 30.314\"");
		accion.setValorApertura(accionesA);
		cal.incrementarAcciones(cantidadInvertida, accion, accionista);
		accion.setValorApertura(accionesB);
		cal.incrementarAcciones(cantidadInvertida, accion, accionista);
		assertEquals(Double.doubleToLongBits(expectedValue),Double.doubleToLongBits(accionista.getCantidadAcciones()));
	}

	@Test
	@Parameters({
		"27.353, 100, 29.17, 697.887"})
	public void testCalcularBeneficio(double cantidadAcciones, int cantidadInvertida, double valorCierre, double expectedValue) {
		logger.debug("Llamada al metodo testCalcularBeneficio con parametros: \"27.353, 100, 29.17, 697.887\"");
		List<Accion> acciones = new ArrayList<Accion>();
		accion.setValorCierre(valorCierre);
		acciones.add(accion);
		accionista.setCantidadAcciones(cantidadAcciones);
		accionista.setCantidadInvertida(cantidadInvertida);
		assertEquals(Double.doubleToLongBits(expectedValue),Double.doubleToLongBits(cal.calcularBeneficio(acciones, accionista)));
	}
	
	@Test
	@Parameters({
		"27.353, 29.17, 797.887"})
	public void testCalcularGanancia(double cantidadAcciones, double valorCierre, double expectedValue) {
		logger.debug("Llamada al metodo testCalcularGanancia con parametros: \"27.353, 29.17, 797.887\"");
		List<Accion> acciones = new ArrayList<Accion>();
		accion.setValorCierre(valorCierre);
		acciones.add(accion);
		accionista.setCantidadAcciones(cantidadAcciones);
		assertEquals(Double.doubleToLongBits(expectedValue),Double.doubleToLongBits(cal.calcularGanancia(acciones, accionista)));
	}

	@Test
	@Parameters({
	"stocks-ITXTest.csv, 152"})
	public void testLeerFichero(String fichero,  int expectedValue) {
		logger.debug("Llamada al metodo testLeerFichero con parametros: \"stocks-ITXTest.csv, 152\"");
		List<Accion> acciones;
		try {
			acciones = principalTest.leerFicheroController(fichero);
			assertEquals(expectedValue, acciones.size());
		} catch (Exception e) {
			logger.error("Error en la lectura del fichero del método testLeerFichero con parámetro: {} y expectedValue: {}", fichero, expectedValue);
			e.printStackTrace();
		}
	}

	@Test
	@Parameters({
	"stocks-ITXTest.csv, 8"})
	public void testCalcularTotales(String fichero,  int expectedValue) {
		logger.debug("Llamada al metodo testCalcularTotales con parametros: \"stocks-ITXTest.csv, 8\"");
		List<Accion> accionesIn;
		List<Accion> accionesOut;
		try {
			accionesIn = principalTest.leerFicheroController(fichero);
			accionesOut = principalTest.calcularTotales(accionesIn);
			assertEquals(expectedValue, accionesOut.size());
		} catch (Exception e) {
			logger.error("Error en método testCalcularTotales con parámetro fichero: {} y expectedValue: {}", fichero, expectedValue);
			e.printStackTrace();
		}
		
	}
	

	@Test
	@Parameters({
	"stocks-ITXTest.csv, 8"})
	public void testBuscaUltimoJueves(String fichero, int expectedValue) {
		logger.debug("Llamada al metodo testBuscaUltimoJueves con parametros: \"stocks-ITXTest.csv, 8\"");
		List<Accion> acciones;
		try {
			acciones = principalTest.leerFicheroController(fichero);
			assertEquals(expectedValue, principalTest.buscaUltimoJueves(acciones).size());
		} catch (Exception e) {
			logger.error("Error en método testBuscaUltimoJueves con parámetro fichero: {} y expectedValue: {}", fichero, expectedValue);
			e.printStackTrace();
		}
	}
	
	@Test
	@Parameters({
	"6"})
	public void testEncuentraFechaCompra(int expectedValue) {
		logger.debug("Llamada al metodo testEncuentraFechaCompra con parametros: \"6\"");
		List <LocalDate> listaFechas = new ArrayList <LocalDate>();
		LocalDate fecha = LocalDate.parse("2001-06-01");
		listaFechas.add(fecha);
		fecha = LocalDate.parse("2001-06-29");
		listaFechas.add(fecha);
		fecha = LocalDate.parse("2001-07-27");
		listaFechas.add(fecha);
		fecha = LocalDate.parse("2001-08-31");
		listaFechas.add(fecha);
		fecha = LocalDate.parse("2001-09-28");
		listaFechas.add(fecha);
		fecha = LocalDate.parse("2001-10-26");
		listaFechas.add(fecha);
		List<Accion> acciones = new ArrayList<Accion>();
		Accion a1= new Accion();	
		LocalDate fecha1 = LocalDate.parse("2001-05-23");
		a1.setFecha(fecha1);
		acciones.add(a1);	
		fecha1 = LocalDate.parse("2001-05-24");
		Accion a2= new Accion();
		a2.setFecha(fecha1);
		acciones.add(a2);
		Accion a3= new Accion();
		fecha1 = LocalDate.parse("2001-05-25");
		a3.setFecha(fecha1);
		acciones.add(a3);
		Accion a4= new Accion();	
		fecha1 = LocalDate.parse("2001-05-28");
		a4.setFecha(fecha1);
		acciones.add(a4);
		Accion a5= new Accion();
		fecha1 = LocalDate.parse("2001-05-29");
		a5.setFecha(fecha1);
		acciones.add(a5);
		Accion a6= new Accion();	
		fecha1 = LocalDate.parse("2001-05-30");
		a6.setFecha(fecha1);
		acciones.add(a6);
		Accion a7= new Accion();	
		fecha1 = LocalDate.parse("2001-05-31");
		a7.setFecha(fecha1);
		acciones.add(a7);
		Accion a8= new Accion();	
		fecha1 = LocalDate.parse("2001-06-01");
		a8.setFecha(fecha1);
		acciones.add(a8);
		Accion a9= new Accion();	
		fecha1 = LocalDate.parse("2001-06-04");
		a9.setFecha(fecha1);
		acciones.add(a9);
		Accion a10= new Accion();	
		fecha1 = LocalDate.parse("2001-06-05");
		a10.setFecha(fecha1);
		acciones.add(a10);
		Accion a11= new Accion();	
		fecha1 =  LocalDate.parse("2001-06-06");
		a11.setFecha(fecha1);
		acciones.add(a11);
		Accion a12= new Accion();
		fecha1 = LocalDate.parse("2001-06-15");
		a12.setFecha(fecha1);
		acciones.add(a12);
		Accion a13= new Accion();	
		fecha1 = LocalDate.parse("2001-06-29");
		a13.setFecha(fecha1);
		acciones.add(a13);
		Accion a14= new Accion();	
		fecha1 = LocalDate.parse("2001-07-05");
		a14.setFecha(fecha1);
		acciones.add(a14);
		Accion a15= new Accion();	
		fecha1 = LocalDate.parse("2001-07-08");
		a15.setFecha(fecha1);
		acciones.add(a15);
		Accion a16= new Accion();	
		fecha1 = LocalDate.parse("2001-07-10");
		a16.setFecha(fecha1);
		acciones.add(a16);
		Accion a17= new Accion();	
		fecha1 = LocalDate.parse("2001-07-20");
		a17.setFecha(fecha1);
		acciones.add(a17);
		Accion a18= new Accion();
		fecha1 = LocalDate.parse("2001-07-31");
		a18.setFecha(fecha1);
		acciones.add(a18);
		Accion a19= new Accion();		
		fecha1 = LocalDate.parse("2001-08-01");
		a19.setFecha(fecha1);
		acciones.add(a19);
		Accion a20= new Accion();	
		fecha1 = LocalDate.parse("2001-08-10");
		a20.setFecha(fecha1);
		acciones.add(a20);
		Accion a21= new Accion();
		fecha1 = LocalDate.parse("2001-08-20");
		a21.setFecha(fecha1);
		acciones.add(a21);
		Accion a22= new Accion();	
		fecha1 = LocalDate.parse("2001-08-30");
		a22.setFecha(fecha1);
		acciones.add(a22);
		Accion a23= new Accion();	
		fecha1 = LocalDate.parse("2001-09-01");
		a23.setFecha(fecha1);
		acciones.add(a23);
		Accion a24= new Accion();	
		fecha1 = LocalDate.parse("2001-09-10");
		a24.setFecha(fecha1);
		acciones.add(a24);
		Accion a25= new Accion();	
		fecha1 = LocalDate.parse("2001-09-20");
		a25.setFecha(fecha1);
		acciones.add(a25);
		Accion a26= new Accion();	
		fecha1 = LocalDate.parse("2001-09-28");
		a26.setFecha(fecha1);
		acciones.add(a26);
		Accion a27= new Accion();		
		fecha1 = LocalDate.parse("2001-10-02");
		a27.setFecha(fecha1);
		acciones.add(a27);
		Accion a28= new Accion();		
		fecha1 = LocalDate.parse("2001-10-08");
		a28.setFecha(fecha1);
		acciones.add(a28);
		Accion a29= new Accion();
		fecha1 = LocalDate.parse("2001-10-12");
		a29.setFecha(fecha1);
		acciones.add(a29);
		Accion a30= new Accion();	
		fecha1 = LocalDate.parse("2001-10-22");
		a30.setFecha(fecha1);
		acciones.add(a30);
		Accion a31= new Accion();	
		fecha1 = LocalDate.parse("2001-10-31");
		a31.setFecha(fecha1);
		acciones.add(a31);
		List <Accion> fechasCompraEncontradas=principalTest.encuentraFechaCompra(listaFechas, acciones);
		assertEquals(expectedValue,fechasCompraEncontradas.size());
	}

}



