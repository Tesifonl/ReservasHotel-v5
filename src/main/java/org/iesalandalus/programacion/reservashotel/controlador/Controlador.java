package org.iesalandalus.programacion.reservashotel.controlador;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservashotel.modelo.IModelo;
import org.iesalandalus.programacion.reservashotel.modelo.Modelo;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.vista.Vista;

public class Controlador {
	
	private IModelo modelo;
	private Vista vista;
	
	public Controlador (IModelo modelo,Vista vista) {
	if (modelo!=null && vista!=null) {
		this.modelo = modelo;
		this.vista = vista;
		}else {throw new NullPointerException("ERROR: No es posible por no llegar un obejto");}
	}
	public void comenzar() throws OperationNotSupportedException {
		modelo.comenzar();
		vista.comenzar();
	}
	
	public void terminar() {
		modelo.terminar();
		vista.terminar();
	}
	
	public void insertar(Huesped huesped) throws OperationNotSupportedException {
		modelo.insertar(huesped);
	}
	
	public Huesped buscar(Huesped huesped) {
		return modelo.buscar(huesped);
	}
	
	public void borrar (Huesped huesped) throws OperationNotSupportedException {
		modelo.borrar(huesped);
	}
	
	public ArrayList<Huesped> getHuespedes(){
		return modelo.getHuespedes();
	}
	
	public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
		modelo.insertar(habitacion);
	}
	
	public Habitacion buscar(Habitacion habitacion) {
		return modelo.buscar(habitacion);
	}
	
	public void borrar (Habitacion habitacion) throws OperationNotSupportedException {
		modelo.borrar(habitacion);
	}
	
	public ArrayList<Habitacion> getHabitaciones() {
		return modelo.getHabitaciones();
	}
	
	public ArrayList<Habitacion> getHabitaciones(TipoHabitacion tipoHabitacion) {
		return modelo.getHabitaciones(tipoHabitacion);
	}
	
	public void insertar(Reserva reserva) {
		try {
			modelo.insertar(reserva);
		    }catch (OperationNotSupportedException ex) {
		    System.out.println(ex.getMessage());}
    }
	
	public Reserva buscar(Reserva reserva) {
		return modelo.buscar(reserva);
	}
	
	public void borrar (Reserva reserva) throws OperationNotSupportedException {
		modelo.borrar(reserva);
		}
	
	public ArrayList<Reserva> getReservas() {
		return modelo.getReservas();
	}
	
	public ArrayList<Reserva> getReservas(Huesped huesped) {
		return modelo.getReservas(huesped);
	}
	
	public ArrayList<Reserva> getReservas(TipoHabitacion tipoHabitacion) {
		return modelo.getReservas(tipoHabitacion);
	}
	
	public void realizarCheckin(Reserva reserva, LocalDateTime fecha) throws OperationNotSupportedException {
		modelo.realizarCheckin(reserva, fecha);
	}
	
	public void realizarCheckout(Reserva reserva, LocalDateTime fecha) throws OperationNotSupportedException {
		modelo.realizarCheckout(reserva, fecha);
	}
}

