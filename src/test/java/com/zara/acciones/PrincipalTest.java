package com.zara.acciones;

import static org.junit.Assert.assertEquals;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class PrincipalTest<ResourceFile> {

	private Accion accion = new Accion();
	private Accionista accionista = new Accionista();
	private Principal principalTest = new Principal(accion, accionista);

	@Test
	@Parameters({
    	"50, 3.664, 13.373",
    	"50, 3.256, 15.049"})
	public void testCalcularAcciones(int a, double b, double expectedValue) {
		assertEquals(Double.doubleToLongBits(expectedValue), Double.doubleToLongBits(principalTest.calcularAcciones(a,b)));	
	}

	@Test
	@Parameters({
    	"3.567452, 3.567",
    	"3.878975, 3.879"})
	public void testFormatearDecimales(double a, double expectedValue) {
		assertEquals(Double.doubleToLongBits(expectedValue),Double.doubleToLongBits(principalTest.formatearDecimales(a)));
	}

	@Test
	@Parameters({
		"50, 50",
    	"200, 200"})
	public void testIncrementarInversion(int a, int expectedValue) {
		principalTest.incrementarInversion(a);
		assertEquals(expectedValue, accionista.getCantidadInvertida());
	}

	@Test
	@Parameters({
		"3.664, 50, 3.505, 27.353",
		"3.256, 50, 3.210, 30.314"})
	public void testIncrementarAcciones(double a, int b, double c, double expectedValue) {
		accion.setValorApertura(a);
		principalTest.incrementarAcciones(b, accion);
		accion.setValorApertura(c);
		principalTest.incrementarAcciones(b, accion);
		assertEquals(Double.doubleToLongBits(expectedValue),Double.doubleToLongBits(accionista.getCantidadAcciones()));
	}

	@Test
	@Parameters({
		"27.353, 100, 29.17, 697.887"})
	public void testCalcularBeneficio(double a, int b, double c, double expectedValue) {
		List<Accion> acciones = new ArrayList<Accion>();
		accion.setValorCierre(c);
		acciones.add(accion);
		accionista.setCantidadAcciones(a);
		accionista.setCantidadInvertida(b);
		assertEquals(Double.doubleToLongBits(expectedValue),Double.doubleToLongBits(principalTest.calcularBeneficio(acciones)));
	}
	
	@Test
	@Parameters({
		"27.353, 29.17, 797.887"})
	public void testCalcularGanancia(double a, double b, double expectedValue) {
		List<Accion> acciones = new ArrayList<Accion>();
		accion.setValorCierre(b);
		acciones.add(accion);
		accionista.setCantidadAcciones(a);
		assertEquals(Double.doubleToLongBits(expectedValue),Double.doubleToLongBits(principalTest.calcularGanancia(acciones)));
	}

	@Test
	@Parameters({
	"./src/test/java/,stocks-ITXTest.csv, 152"})
	public void testLeerFichero(String a, String b, int expectedValue) {
		List<Accion> acciones;
		try {
			acciones = principalTest.leerFichero(a,b);
			assertEquals(expectedValue, acciones.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Parameters({
	"./src/test/java/,stocks-ITXTest.csv, 439.308"})
	public void testCalcularTotales(String a, String b, double expectedValue) {
		List<Accion> acciones;
		try {
			acciones = principalTest.leerFichero(a,b);
			assertEquals(Double.doubleToLongBits(expectedValue), Double.doubleToLongBits(principalTest.calcularTotales(acciones)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@Test
	@Parameters({
	"./src/test/java/,stocks-ITXTest.csv, 8"})
	public void testBuscaUltimoJueves(String a, String b, int expectedValue) {
		List<Accion> acciones;
		try {
			acciones = principalTest.leerFichero(a,b);
			assertEquals(expectedValue, principalTest.buscaUltimoJueves(acciones).size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Parameters({
	"6"})
	public void testEncuentraFechaCompra(int expectedValue) {
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



