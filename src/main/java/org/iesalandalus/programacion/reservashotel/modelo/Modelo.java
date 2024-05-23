package org.iesalandalus.programacion.reservashotel.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHabitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHuespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IReservas;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb.Habitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb.Huespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb.Reservas;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb.utilidades.MongoDB;

public class Modelo implements IModelo{
	
	private IHuespedes huespedes;
	private IHabitaciones habitaciones;
	private IReservas reservas;
	private IFuenteDatos fuenteDatos;

	
	public Modelo(FactoriaFuenteDatos factoriaFuenteDatos) {
		setFuenteDatos(factoriaFuenteDatos.crear());
		comenzar();
	}
	
	protected void setFuenteDatos(IFuenteDatos fuenteDatos) {
		
		this.fuenteDatos = fuenteDatos;
	}

	

	public void comenzar() {
		
		
		huespedes = fuenteDatos.crearHuespedes();
		
		habitaciones = fuenteDatos.crearHabitaciones();
		
		reservas = fuenteDatos.crearReservas();
		
		
		
	}
	
	public void terminar() {
		
		huespedes.terminar();
		habitaciones.terminar();
		reservas.terminar();
	}
	
	public void insertar(Huesped huesped)  {
		try {
			huespedes.insertar(huesped);
			}catch (OperationNotSupportedException ex) {
			System.out.println(ex.getMessage());}
	}
	
	public Huesped buscar(Huesped huesped) {
		return huespedes.buscar(huesped);
	}
	
	public void borrar (Huesped huesped) {
		try {
			huespedes.borrar(huesped);
			}catch (OperationNotSupportedException ex) {
			System.out.println(ex.getMessage());}
		}
	
	public ArrayList<Huesped> getHuespedes(){
		ArrayList<Huesped> nuevoArray1=huespedes.get();
		return nuevoArray1;
	}
	
	public void insertar(Habitacion habitacion)  {
		try {
			habitaciones.insertar(habitacion);
			}catch (OperationNotSupportedException ex) {
			System.out.println(ex.getMessage());}
	}
	
	public Habitacion buscar(Habitacion habitacion) {
		return habitaciones.buscar(habitacion);
	}
	
	public void borrar (Habitacion habitacion) {
		
		try {
			habitaciones.borrar(habitacion);
			}catch (OperationNotSupportedException ex) {
				System.out.println(ex.getMessage());}
		
	}
	
	public ArrayList<Habitacion> getHabitaciones(){
		ArrayList<Habitacion> nuevoArray1=habitaciones.get();
		return nuevoArray1;
	}
	
	public ArrayList<Habitacion> getHabitaciones(TipoHabitacion tipoHabitacion){
		ArrayList<Habitacion> nuevoArray1=habitaciones.get(tipoHabitacion);
		return nuevoArray1;
	}
	
	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		try {
			reservas.insertar(reserva);
			}catch (OperationNotSupportedException ex) {
			System.out.println(ex.getMessage());}
	}
	
	public Reserva buscar(Reserva reserva) {
		return reservas.buscar(reserva);
	}
	
	public void borrar (Reserva reserva) {
		
		try {
			reservas.borrar(reserva);
			}catch (OperationNotSupportedException ex) {
			System.out.println(ex.getMessage());}	
	}
	
	public ArrayList<Reserva> getReservas(){
		ArrayList<Reserva> nuevoArray1=reservas.get();
		return nuevoArray1;
	}
	
	public ArrayList<Reserva> getReservas(Huesped huesped){
		ArrayList<Reserva> nuevoArray1=reservas.getReservas(huesped);
		return nuevoArray1;
	}
	
	public ArrayList<Reserva> getReservas(TipoHabitacion tipoHabitacion){
		ArrayList<Reserva> nuevoArray1=reservas.getReservas(tipoHabitacion);
		return nuevoArray1;
	}
	
	public ArrayList<Reserva> getReservas(Habitacion habitacion){
		ArrayList<Reserva> nuevoArray1=reservas.getReservas(habitacion);
		return nuevoArray1;
	}
	
	public ArrayList<Reserva> getReservasFuturas(Habitacion habitacion){
		ArrayList<Reserva> nuevoArray1=reservas.getReservasFuturas(habitacion);
		return nuevoArray1;
	}
	
	public void realizarCheckin(Reserva reserva, LocalDateTime fecha)  {

		try {
			reservas.realizarCheckin(reserva, fecha);
			}catch (NullPointerException ex) {
			System.out.println(ex.getMessage());}	
		
	}
	
	public void realizarCheckout(Reserva reserva, LocalDateTime fecha)  {

		try {
			reservas.realizarCheckout(reserva, fecha);
			}catch (NullPointerException ex) {
			System.out.println(ex.getMessage());}	

	}
}
