package org.iesalandalus.programacion.reservashotel.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;


public interface IModelo {



	public void comenzar();
	public void terminar();
	public void insertar(Huesped huesped);
	public Huesped buscar(Huesped huesped);
	public void borrar (Huesped huesped);
	public ArrayList<Huesped> getHuespedes();
	public void insertar(Habitacion habitacion);
	public Habitacion buscar(Habitacion habitacion);
	public void borrar (Habitacion habitacion);
	public ArrayList<Habitacion> getHabitaciones();
	public ArrayList<Habitacion> getHabitaciones(TipoHabitacion tipoHabitacion);
	public void insertar(Reserva reserva) throws OperationNotSupportedException ;
	public Reserva buscar(Reserva reserva);
	public void borrar (Reserva reserva);
	public ArrayList<Reserva> getReservas();
	public ArrayList<Reserva> getReservas(Huesped huesped);
	public ArrayList<Reserva> getReservas(TipoHabitacion tipoHabitacion);
	public ArrayList<Reserva> getReservas(Habitacion habitacion);
	public ArrayList<Reserva> getReservasFuturas(Habitacion habitacion);
	public void realizarCheckin(Reserva reserva, LocalDateTime fecha);
	public void realizarCheckout(Reserva reserva, LocalDateTime fecha);
	
}
