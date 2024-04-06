package org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Doble;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Simple;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Suite;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Triple;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IReservas;

public class Reservas implements IReservas{

	private static ArrayList<Reserva> coleccionReservas;
	
	public Reservas () {
		
		coleccionReservas=new ArrayList<>();
	}
	
	@Override
	public ArrayList<Reserva> get() {
		ArrayList<Reserva> copia=copiaProfundaReservas();
		return copia;
	}
	
	@Override
	public int getTamano() {
		
		int tamano=0;
		ArrayList<Reserva> copia=copiaProfundaReservas();
		return tamano=copia.size();
	}
	
	
	private ArrayList<Reserva> copiaProfundaReservas() {
		ArrayList<Reserva> copiaReservas=new ArrayList<>();
		
		for (int i=0;i<coleccionReservas.size();i++) {
			copiaReservas.add(new Reserva(coleccionReservas.get(i)));
		}
		
		return copiaReservas;
	}

	@Override
	public void insertar (Reserva reserva) throws OperationNotSupportedException {
		
		if(reserva!=null) {
			if(coleccionReservas.contains(reserva)) {
				throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
			}	
			else {coleccionReservas.add(reserva);}
			
		}else {throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");}
	}
	
	@Override
	public Reserva buscar(Reserva reserva) {	
		Reserva buscarReserva=null;
		int indice=0;
		
		if(reserva!=null) {
				if(coleccionReservas.contains(reserva)) {
					indice=coleccionReservas.indexOf(reserva);
					buscarReserva=coleccionReservas.get(indice);
					return buscarReserva;
				}else {return null;}
			}else {throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");}
	}
	
	@Override
	public void borrar (Reserva reserva) throws OperationNotSupportedException {
		int indice=0;
		
		if(reserva!=null) {

			if(coleccionReservas.contains(reserva)){
				indice=coleccionReservas.indexOf(reserva);
				coleccionReservas.remove(indice);
			}else {
				throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");}	
		
		}else {throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");}
	}

	@Override
	public ArrayList<Reserva> getReservas (Huesped huesped) {
	
		if(huesped!=null) {
			ArrayList<Reserva> nuevoArray=new ArrayList<>();
			boolean encontrado=false;
	
			for (int i=0;i<coleccionReservas.size();i++) {
				if(coleccionReservas.get(i).getHuesped().getDni().equals(huesped.getDni())) {
					encontrado=true;
					nuevoArray.add(coleccionReservas.get(i));
				}
			}	
				
			if (encontrado==false) {
				return null;
			}
	
			return nuevoArray;
			
		}else {throw new  NullPointerException("ERROR: No se pueden buscar reservas de un hu�sped nulo.");}
	}
	
	@Override
	public ArrayList<Reserva>  getReservas (TipoHabitacion tipoHabitacion) {
		
		if(tipoHabitacion!=null) {
			ArrayList<Reserva> nuevoArray=new ArrayList<>();
			boolean encontrado=false;

			for (int i=0;i<coleccionReservas.size();i++) {

				if (coleccionReservas.get(i).getHabitacion() instanceof Simple
						&& tipoHabitacion.equals(TipoHabitacion.SIMPLE)) {
						nuevoArray.add(new Reserva(coleccionReservas.get(i)));
						encontrado = true;
					}
					else if (coleccionReservas.get(i).getHabitacion()  instanceof Doble
						&& tipoHabitacion.equals(TipoHabitacion.DOBLE)) {
						nuevoArray.add(new Reserva(coleccionReservas.get(i)));
						encontrado = true;
					}
					else if (coleccionReservas.get(i).getHabitacion()  instanceof Triple
						&& tipoHabitacion.equals(TipoHabitacion.TRIPLE)) {
						nuevoArray.add(new Reserva(coleccionReservas.get(i)));
						encontrado = true;
					}
					else if (coleccionReservas.get(i).getHabitacion()  instanceof Suite
						&& tipoHabitacion.equals(TipoHabitacion.SUITE)) {
						nuevoArray.add(new Reserva(coleccionReservas.get(i)));
						encontrado = true;
					}
			}
			
			if (encontrado==false) {
				return null;
			}
							
			return nuevoArray;
			
		}else {throw new  NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitaci�n nula.");}
	}
	
	@Override
	public ArrayList<Reserva> getReservasFuturas (Habitacion habitacion) {

		if(habitacion!=null) {
			ArrayList<Reserva>  nuevoArray=new ArrayList<>();
			boolean encontrado=false;
			int posicion=0;
			
			for (int i=0;i<coleccionReservas.size();i++) 
				if(coleccionReservas.get(i).getHabitacion().equals(habitacion) 
						&& coleccionReservas.get(i).getFechaInicioReserva().isAfter(LocalDate.now())) {
				encontrado=true;
				nuevoArray.add(coleccionReservas.get(i));
			}
				
			if (encontrado==false) {
				return null;
			}
			return nuevoArray;
	
		}else {throw new  NullPointerException("ERROR: No se pueden buscar reservas de una habitaci�n nula.");}
	}
	
	@Override
	public void realizarCheckin(Reserva reserva, LocalDateTime fecha) {
		Reserva reservaBuscada=null;
		if(reserva==null || fecha==null) {
			throw new  NullPointerException("ERROR: No se puede hacer checkin de una reserva nula o sin fecha");
		}else {
			reservaBuscada=buscar(reserva);
			reservaBuscada.setCheckIn(fecha);}
	}
	
	@Override
	public void realizarCheckout(Reserva reserva, LocalDateTime fecha) {
		Reserva reservaBuscada=null;
		
		if(reserva==null || fecha==null) {
			throw new  NullPointerException("ERROR: No se puede hacer checkin de una reserva nula o sin fecha");
		}else if (reserva.getCheckIn()==null){
			System.out.println("ERROR: No se puede realizar el checkout a una reseva no checkin");
		}else{reservaBuscada=buscar(reserva);
			reservaBuscada.setCheckOut(fecha);}
	}
	
	@Override
	public void comenzar() {};
	
	@Override
	public void terminar() {};
	
}

