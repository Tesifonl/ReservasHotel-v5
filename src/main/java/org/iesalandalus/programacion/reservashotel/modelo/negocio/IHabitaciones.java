package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

public interface IHabitaciones {
	
	public ArrayList<Habitacion> get();
	public ArrayList<Habitacion> get(TipoHabitacion tipoHabitacion);
	public int getTamano();
	public void insertar(Habitacion habitacion) throws OperationNotSupportedException;
	public Habitacion buscar(Habitacion habitacion);
	public void borrar(Habitacion habitacion) throws OperationNotSupportedException;

}
