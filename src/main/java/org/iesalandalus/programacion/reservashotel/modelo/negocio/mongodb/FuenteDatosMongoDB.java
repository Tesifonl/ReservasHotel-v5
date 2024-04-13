package org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb;

import org.iesalandalus.programacion.reservashotel.modelo.negocio.IFuenteDatos;

public class FuenteDatosMongoDB implements IFuenteDatos {

	@Override
	public void crearHuespedes() {
		
		Huespedes coleccionHuespedes=new Huespedes();
		coleccionHuespedes.get();
		
	}

	@Override
	public void crearHabitaciones() {
		
		
	}

	@Override
	public void crearReservas() {
		
		
	}
	
	

}
