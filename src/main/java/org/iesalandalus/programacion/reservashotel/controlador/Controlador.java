package org.iesalandalus.programacion.reservashotel.controlador;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservashotel.modelo.Modelo;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.vista.Vista;

public class Controlador {
	
	private Modelo modelo;
	private Vista vista;
	
	public Controlador (Modelo modelo,Vista vista) {
	if (modelo!=null && vista!=null) {
		this.modelo = modelo;
		this.vista = vista;
		vista.setControlador(this);
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
		try {
			modelo.insertar(huesped);
			}catch (OperationNotSupportedException ex) {
			System.out.println(ex.getMessage());}
	}
	
	public Huesped buscar(Huesped huesped) {
		return modelo.buscar(huesped);
	}
	
	public void borrar (Huesped huesped) {
		try {
			modelo.borrar(huesped);
			}catch (OperationNotSupportedException ex) {
			System.out.println(ex.getMessage());}
	}
	
	public ArrayList<Huesped> getHuespedes(){
		return modelo.getHuespedes();
	}
	
	public void insertar(Habitacion habitacion) {
		try {
			modelo.insertar(habitacion);
			}catch (OperationNotSupportedException ex) {
			System.out.println(ex.getMessage());}
	}
	
	public Habitacion buscar(Habitacion habitacion) {
		return modelo.buscar(habitacion);
	}
	
	public void borrar (Habitacion habitacion) {
		try {
			modelo.borrar(habitacion);
			}catch (OperationNotSupportedException ex) {
			System.out.println(ex.getMessage());}
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
	
	public void borrar (Reserva reserva) {
		try{
			modelo.borrar(reserva);
			}catch (OperationNotSupportedException ex) {
			System.out.println(ex.getMessage());}
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
	
	public void realizarCheckin(Reserva reserva, LocalDateTime fecha) {
		try {
			modelo.realizarCheckin(reserva, fecha);
			}catch (OperationNotSupportedException ex) {
			System.out.println(ex.getMessage());}
	}
	
	public void realizarCheckout(Reserva reserva, LocalDateTime fecha) {
		try {
			modelo.realizarCheckout(reserva, fecha);
			}catch (OperationNotSupportedException ex) {
			System.out.println(ex.getMessage());}
	}
}

