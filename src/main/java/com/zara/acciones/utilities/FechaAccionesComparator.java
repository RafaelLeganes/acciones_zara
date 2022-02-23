package com.zara.acciones.utilities;

import java.util.Comparator;

import com.zara.acciones.model.Accion;

public class FechaAccionesComparator implements Comparator<Accion> {
	
    public int compare(Accion o1, Accion o2) {
    	Accion accion1 = o1;
        Accion accion2 = o2;
        return accion1.getFecha().
                compareTo(accion2.getFecha());
               
    }
}
