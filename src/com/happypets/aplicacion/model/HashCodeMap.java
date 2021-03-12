package com.happypets.aplicacion.model;

import java.util.ArrayList;
import java.util.List;

public class HashCodeMap {

	public static void main(String[] args) {
		Mascota m1,m2;
		m1= new Mascota();
		m1.setNombre("Pongo");
		
		m2= new Mascota();
		m2.setNombre("Figaro");

		//System.out.println(m1.hashCode());
		//System.out.println(m2.hashCode());
		List<Mascota> mascotas= new ArrayList<Mascota>();
		mascotas.add(m1);
		mascotas.add(m2);
		mascotas.remove(m2);
	}

}
