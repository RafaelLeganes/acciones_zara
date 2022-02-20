package com.zara.acciones;

import java.util.Comparator;

public class FechaAccionesComparator implements Comparator<Object> {
	
    public int compare(Object o1, Object o2) {
    	Accion accion1 = (Accion)o1;
        Accion accion2 = (Accion)o2;
        return accion1.getFecha().
                compareTo(accion2.getFecha());
               
    }
}
