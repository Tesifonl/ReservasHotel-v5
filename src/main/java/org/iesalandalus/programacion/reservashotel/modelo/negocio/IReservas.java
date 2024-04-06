package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

public interface IReservas {

	public ArrayList<Reserva> get();
	public int getTamano();
	public void insertar (Reserva reserva) throws OperationNotSupportedException;
	public Reserva buscar(Reserva reserva);
	public void borrar (Reserva reserva) throws OperationNotSupportedException;
	public ArrayList<Reserva> getReservas (Huesped huesped);
	public ArrayList<Reserva>  getReservas (TipoHabitacion tipoHabitacion);
	public ArrayList<Reserva> getReservasFuturas (Habitacion habitacion);
	public void realizarCheckin(Reserva reserva, LocalDateTime fecha);
	public void realizarCheckout(Reserva reserva, LocalDateTime fecha);
	public void comenzar();
	public void terminar();
}
