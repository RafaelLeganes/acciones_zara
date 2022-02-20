package com.zara.acciones;

import static org.junit.Assert.assertTrue;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;


public class PrincipalTest<ResourceFile> {



	@Test
	public void testCalcularAcciones() {
		Accion accion = new Accion();
		accion.setValorApertura(3.664);
		accion.setValorCierre(3.66);
		Accionista accionista = new Accionista();
		Principal p = new Principal(accion, accionista);
		assertTrue(p.calcularAcciones(50, 3.664)==13.373);
		
	}

	@Test
	public void testFormatearDecimales() {
		Accion accion = new Accion();
		accion.setValorApertura(3.664);
		accion.setValorCierre(3.66);
		Accionista accionista = new Accionista();
		Principal p = new Principal(accion, accionista);
		assertTrue(p.formatearDecimales(3.567452)==3.567);
	}

	@Test
	public void testIncrementarInversion() {
		Accion accion = new Accion();
		accion.setValorApertura(3.664);
		accion.setValorCierre(3.66);
		Accionista accionista = new Accionista();
		Principal p = new Principal(accion, accionista);
		p.incrementarInversion(50);
		assertTrue(accionista.getCantidadInvertida()==50);
	}

	@Test
	public void testIncrementarAcciones() {
		Accion accion = new Accion();
		accion.setValorApertura(3.664);
		accion.setValorCierre(3.66);
		Accionista accionista = new Accionista();
		Principal p = new Principal(accion, accionista);
		p.incrementarAcciones(50, accion);
		accion.setValorApertura(3.505);
		p.incrementarAcciones(50, accion);
		assertTrue(accionista.getCantidadAcciones()==27.353);
	}

	@Test
	public void testCalcularGanancia() {
		Accion accion = new Accion();
		accion.setValorApertura(3.664);
		accion.setValorCierre(3.66);
		Accionista accionista = new Accionista();
		accionista.setCantidadAcciones(27.353);
		accionista.setCantidadInvertida(100);
		Principal p = new Principal(accion, accionista);
		assertTrue(p.calcularGanancia()==697.887);
	}

	@Test
	public void testLeerFichero() {
		Accion accion = new Accion();
		accion.setValorApertura(3.664);
		accion.setValorCierre(3.66);
		Accionista accionista = new Accionista();
		Principal p = new Principal(accion, accionista);
		try {
			List<Accion> acciones=p.leerFichero();
			assertTrue(acciones.size()>0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testCalcularTotal() {
		List<Accion> acciones = new ArrayList<Accion>();
		Accion a1= new Accion();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.MAY);
		cal.set(Calendar.DAY_OF_MONTH, 23);		
		Date fecha1 = cal.getTime();
		a1.setFecha(fecha1);
		a1.setValorApertura(3.704);
		acciones.add(a1);
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.MAY);
		cal.set(Calendar.DAY_OF_MONTH, 24);		
		fecha1 = cal.getTime();
		Accion a2= new Accion();
		a2.setFecha(fecha1);
		a2.setValorApertura(3.6);
		acciones.add(a2);
		Accion a3= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.MAY);
		cal.set(Calendar.DAY_OF_MONTH, 25);		
		fecha1 = cal.getTime();
		a3.setFecha(fecha1);
		a3.setValorApertura(3.6);
		acciones.add(a3);
		Accion a4= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.MAY);
		cal.set(Calendar.DAY_OF_MONTH, 28);		
		fecha1 = cal.getTime();
		a4.setFecha(fecha1);
		a4.setValorApertura(3.56);
		acciones.add(a4);
		Accion a5= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.MAY);
		cal.set(Calendar.DAY_OF_MONTH, 29);		
		fecha1 = cal.getTime();
		a5.setFecha(fecha1);
		a5.setValorApertura(3.562);
		acciones.add(a5);
		Accion a6= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.MAY);
		cal.set(Calendar.DAY_OF_MONTH, 30);		
		fecha1 = cal.getTime();
		a6.setFecha(fecha1);
		a6.setValorApertura(3.606);
		acciones.add(a6);
		Accion a7= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.MAY);
		cal.set(Calendar.DAY_OF_MONTH, 31);		
		fecha1 = cal.getTime();
		a7.setFecha(fecha1);
		a7.setValorApertura(3.62);
		acciones.add(a7);
		Accion a8= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.set(Calendar.DAY_OF_MONTH, 1);		
		fecha1 = cal.getTime();
		a8.setFecha(fecha1);
		a8.setValorApertura(3.664);
		acciones.add(a8);
		Accion a9= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.set(Calendar.DAY_OF_MONTH, 4);		
		fecha1 = cal.getTime();
		a9.setFecha(fecha1);
		a9.setValorApertura(3.642);
		acciones.add(a9);
		Accion a10= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.set(Calendar.DAY_OF_MONTH, 5);		
		fecha1 = cal.getTime();
		a10.setFecha(fecha1);
		a10.setValorApertura(3.802);
		acciones.add(a10);
		Accion a11= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.set(Calendar.DAY_OF_MONTH, 6);		
		fecha1 = cal.getTime();
		a11.setFecha(fecha1);
		a11.setValorApertura(3.846);
		acciones.add(a11);
		Accion a12= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.set(Calendar.DAY_OF_MONTH, 15);		
		fecha1 = cal.getTime();
		a12.setFecha(fecha1);
		a12.setValorApertura(3.64);
		acciones.add(a12);
		Accion a13= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.set(Calendar.DAY_OF_MONTH, 29);		
		fecha1 = cal.getTime();
		a13.setFecha(fecha1);
		a13.setValorApertura(3.7);
		acciones.add(a13);
		Accion a14= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JULY);
		cal.set(Calendar.DAY_OF_MONTH, 5);		
		fecha1 = cal.getTime();
		a14.setFecha(fecha1);
		a14.setValorApertura(3.796);
		acciones.add(a14);
		Accion a15= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JULY);
		cal.set(Calendar.DAY_OF_MONTH, 9);		
		fecha1 = cal.getTime();
		a15.setFecha(fecha1);
		a15.setValorApertura(3.7);
		acciones.add(a15);
		Accion a16= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JULY);
		cal.set(Calendar.DAY_OF_MONTH, 10);		
		fecha1 = cal.getTime();
		a16.setFecha(fecha1);
		a16.setValorApertura(3.796);
		acciones.add(a16);
		Accion a17= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JULY);
		cal.set(Calendar.DAY_OF_MONTH, 20);		
		fecha1 = cal.getTime();
		a17.setFecha(fecha1);
		a17.setValorApertura(3.6);
		acciones.add(a17);
		Accion a18= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JULY);
		cal.set(Calendar.DAY_OF_MONTH, 31);		
		fecha1 = cal.getTime();
		a18.setFecha(fecha1);
		a18.setValorApertura(3.616);
		acciones.add(a18);
		Accion a19= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.AUGUST);
		cal.set(Calendar.DAY_OF_MONTH, 1);		
		fecha1 = cal.getTime();
		a19.setFecha(fecha1);
		a19.setValorApertura(3.61);
		acciones.add(a19);
		Accion a20= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.AUGUST);
		cal.set(Calendar.DAY_OF_MONTH, 10);		
		fecha1 = cal.getTime();
		a20.setFecha(fecha1);
		a20.setValorApertura(3.788);
		acciones.add(a20);
		Accion a21= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.AUGUST);
		cal.set(Calendar.DAY_OF_MONTH, 20);		
		fecha1 = cal.getTime();
		a21.setFecha(fecha1);
		a21.setValorApertura(3.862);
		acciones.add(a21);
		Accion a22= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.AUGUST);
		cal.set(Calendar.DAY_OF_MONTH, 30);		
		fecha1 = cal.getTime();
		a22.setFecha(fecha1);
		a22.setValorApertura(3.858);
		acciones.add(a22);
		Accion a23= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 3);		
		fecha1 = cal.getTime();
		a23.setFecha(fecha1);
		a23.setValorApertura(3.804);
		acciones.add(a23);
		Accion a24= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 10);		
		fecha1 = cal.getTime();
		a24.setFecha(fecha1);
		a24.setValorApertura(3.7);
		acciones.add(a24);
		Accion a25= new Accion();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 20);		
		fecha1 = cal.getTime();
		a25.setFecha(fecha1);
		a25.setValorApertura(3.344);
		acciones.add(a25);
		Accion a26= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 28);		
		fecha1 = cal.getTime();
		a26.setFecha(fecha1);
		a26.setValorApertura(3.59);
		acciones.add(a26);
		Accion a27= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.OCTOBER);
		cal.set(Calendar.DAY_OF_MONTH, 2);		
		fecha1 = cal.getTime();
		a27.setFecha(fecha1);
		a27.setValorApertura(3.682);
		acciones.add(a27);
		Accion a28= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.OCTOBER);
		cal.set(Calendar.DAY_OF_MONTH, 8);		
		fecha1 = cal.getTime();
		a28.setFecha(fecha1);
		a28.setValorApertura(3.502);
		acciones.add(a28);
		Accion a29= new Accion();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.OCTOBER);
		cal.set(Calendar.DAY_OF_MONTH, 15);		
		fecha1 = cal.getTime();
		a29.setFecha(fecha1);
		a29.setValorApertura(3.662);
		acciones.add(a29);
		Accion a30= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.OCTOBER);
		cal.set(Calendar.DAY_OF_MONTH, 22);		
		fecha1 = cal.getTime();
		a30.setFecha(fecha1);
		a30.setValorApertura(3.8);
		acciones.add(a30);
		Accion a31= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.OCTOBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);		
		fecha1 = cal.getTime();
		a31.setFecha(fecha1);
		a31.setValorApertura(3.872);
		acciones.add(a31);
		Accionista accionista = new Accionista();
		Accion a = new Accion();
		Principal p = new Principal(a, accionista);
		double ganancia = p.calcularTotal(acciones);
		assertTrue(ganancia==2014.698);
		
	}

	@Test
	public void testEcuentraFecha() {
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
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.MAY);
		cal.set(Calendar.DAY_OF_MONTH, 23);		
		Date fecha1 = cal.getTime();
		a1.setFecha(fecha1);
		acciones.add(a1);
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.MAY);
		cal.set(Calendar.DAY_OF_MONTH, 24);		
		fecha1 = cal.getTime();
		Accion a2= new Accion();
		a2.setFecha(fecha1);
		acciones.add(a2);
		Accion a3= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.MAY);
		cal.set(Calendar.DAY_OF_MONTH, 25);		
		fecha1 = cal.getTime();
		a3.setFecha(fecha1);
		acciones.add(a3);
		Accion a4= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.MAY);
		cal.set(Calendar.DAY_OF_MONTH, 28);		
		fecha1 = cal.getTime();
		a4.setFecha(fecha1);
		acciones.add(a4);
		Accion a5= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.MAY);
		cal.set(Calendar.DAY_OF_MONTH, 29);		
		fecha1 = cal.getTime();
		a5.setFecha(fecha1);
		acciones.add(a5);
		Accion a6= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.MAY);
		cal.set(Calendar.DAY_OF_MONTH, 30);		
		fecha1 = cal.getTime();
		a6.setFecha(fecha1);
		acciones.add(a6);
		Accion a7= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.MAY);
		cal.set(Calendar.DAY_OF_MONTH, 31);		
		fecha1 = cal.getTime();
		a7.setFecha(fecha1);
		acciones.add(a7);
		Accion a8= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.set(Calendar.DAY_OF_MONTH, 1);		
		fecha1 = cal.getTime();
		a8.setFecha(fecha1);
		acciones.add(a8);
		Accion a9= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.set(Calendar.DAY_OF_MONTH, 4);		
		fecha1 = cal.getTime();
		a9.setFecha(fecha1);
		acciones.add(a9);
		Accion a10= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.set(Calendar.DAY_OF_MONTH, 5);		
		fecha1 = cal.getTime();
		a10.setFecha(fecha1);
		acciones.add(a10);
		Accion a11= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.set(Calendar.DAY_OF_MONTH, 6);		
		fecha1 = cal.getTime();
		a11.setFecha(fecha1);
		acciones.add(a11);
		Accion a12= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.set(Calendar.DAY_OF_MONTH, 15);		
		fecha1 = cal.getTime();
		a12.setFecha(fecha1);
		acciones.add(a12);
		Accion a13= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.set(Calendar.DAY_OF_MONTH, 29);		
		fecha1 = cal.getTime();
		a13.setFecha(fecha1);
		acciones.add(a13);
		Accion a14= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JULY);
		cal.set(Calendar.DAY_OF_MONTH, 5);		
		fecha1 = cal.getTime();
		a14.setFecha(fecha1);
		acciones.add(a14);
		Accion a15= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JULY);
		cal.set(Calendar.DAY_OF_MONTH, 5);		
		fecha1 = cal.getTime();
		a15.setFecha(fecha1);
		acciones.add(a15);
		Accion a16= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JULY);
		cal.set(Calendar.DAY_OF_MONTH, 10);		
		fecha1 = cal.getTime();
		a16.setFecha(fecha1);
		acciones.add(a16);
		Accion a17= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JULY);
		cal.set(Calendar.DAY_OF_MONTH, 20);		
		fecha1 = cal.getTime();
		a17.setFecha(fecha1);
		acciones.add(a17);
		Accion a18= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.JULY);
		cal.set(Calendar.DAY_OF_MONTH, 31);		
		fecha1 = cal.getTime();
		a18.setFecha(fecha1);
		acciones.add(a18);
		Accion a19= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.AUGUST);
		cal.set(Calendar.DAY_OF_MONTH, 1);		
		fecha1 = cal.getTime();
		a19.setFecha(fecha1);
		acciones.add(a19);
		Accion a20= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.AUGUST);
		cal.set(Calendar.DAY_OF_MONTH, 10);		
		fecha1 = cal.getTime();
		a20.setFecha(fecha1);
		acciones.add(a20);
		Accion a21= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.AUGUST);
		cal.set(Calendar.DAY_OF_MONTH, 20);		
		fecha1 = cal.getTime();
		a21.setFecha(fecha1);
		acciones.add(a21);
		Accion a22= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.AUGUST);
		cal.set(Calendar.DAY_OF_MONTH, 30);		
		fecha1 = cal.getTime();
		a22.setFecha(fecha1);
		acciones.add(a22);
		Accion a23= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 1);		
		fecha1 = cal.getTime();
		a23.setFecha(fecha1);
		acciones.add(a23);
		Accion a24= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 10);		
		fecha1 = cal.getTime();
		a24.setFecha(fecha1);
		acciones.add(a24);
		Accion a25= new Accion();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 20);		
		fecha1 = cal.getTime();
		a25.setFecha(fecha1);
		acciones.add(a25);
		Accion a26= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 28);		
		fecha1 = cal.getTime();
		a26.setFecha(fecha1);
		acciones.add(a26);
		Accion a27= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.OCTOBER);
		cal.set(Calendar.DAY_OF_MONTH, 2);		
		fecha1 = cal.getTime();
		a27.setFecha(fecha1);
		acciones.add(a27);
		Accion a28= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.OCTOBER);
		cal.set(Calendar.DAY_OF_MONTH, 8);		
		fecha1 = cal.getTime();
		a28.setFecha(fecha1);
		acciones.add(a28);
		Accion a29= new Accion();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.OCTOBER);
		cal.set(Calendar.DAY_OF_MONTH, 12);		
		fecha1 = cal.getTime();
		a29.setFecha(fecha1);
		acciones.add(a29);
		Accion a30= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.OCTOBER);
		cal.set(Calendar.DAY_OF_MONTH, 22);		
		fecha1 = cal.getTime();
		a30.setFecha(fecha1);
		acciones.add(a30);
		Accion a31= new Accion();
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2001);
		cal.set(Calendar.MONTH, Calendar.OCTOBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);		
		fecha1 = cal.getTime();
		a31.setFecha(fecha1);
		acciones.add(a31);
		Accionista accionista = new Accionista();
		Accion a = new Accion();
		Principal p = new Principal(a, accionista);
		List <Accion> fechasCompraEncontradas=p.ecuentraFecha(listaFechas, acciones);
		assertTrue(fechasCompraEncontradas.size()==6);
	}

}