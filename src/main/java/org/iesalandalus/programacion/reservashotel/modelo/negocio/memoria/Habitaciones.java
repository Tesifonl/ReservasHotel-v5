package org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria;

import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Doble;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Simple;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Suite;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Triple;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHabitaciones;


public class Habitaciones implements IHabitaciones{
	
	private static ArrayList<Habitacion> coleccionHabitaciones;

	
	public Habitaciones() {
		coleccionHabitaciones=new ArrayList<>();
	}
	
	@Override
	public ArrayList<Habitacion> get() {
		ArrayList<Habitacion> copia=copiaProfundaHabitaciones();
		return copia;
	}
	
	
	private ArrayList<Habitacion> copiaProfundaHabitaciones() {
		ArrayList<Habitacion> copiahabitaciones=new ArrayList<>();
		
		for (int i=0;i<coleccionHabitaciones.size();i++) {
			if (coleccionHabitaciones.get(i) instanceof Simple) {
				copiahabitaciones.add(new Simple((Simple)coleccionHabitaciones.get(i)));
			}
			else if (coleccionHabitaciones.get(i) instanceof Doble) {
				copiahabitaciones.add(new Doble((Doble)coleccionHabitaciones.get(i)));
			}
			else if (coleccionHabitaciones.get(i) instanceof Triple) {
				copiahabitaciones.add(new Triple((Triple)coleccionHabitaciones.get(i)));
			}
			else if (coleccionHabitaciones.get(i) instanceof Suite) {
				copiahabitaciones.add(new Suite((Suite)coleccionHabitaciones.get(i)));
			}
		}
		
		return copiahabitaciones;
	}

	@Override
	public ArrayList<Habitacion> get (TipoHabitacion tipoHabitacion) {
		
		if(tipoHabitacion!=null) {
			ArrayList<Habitacion> nuevoArray= new ArrayList<>();

			for (int i=0;i<coleccionHabitaciones.size();i++) {

				if (coleccionHabitaciones.get(i) instanceof Simple
					&& tipoHabitacion.equals(TipoHabitacion.SIMPLE)) {
					nuevoArray.add(new Simple((Simple)coleccionHabitaciones.get(i)));
				}
				else if (coleccionHabitaciones.get(i) instanceof Doble
					&& tipoHabitacion.equals(TipoHabitacion.DOBLE)) {
					nuevoArray.add(new Doble((Doble)coleccionHabitaciones.get(i)));
				}
				else if (coleccionHabitaciones.get(i) instanceof Triple
					&& tipoHabitacion.equals(TipoHabitacion.TRIPLE)) {
					nuevoArray.add(new Triple((Triple)coleccionHabitaciones.get(i)));
				}
				else if (coleccionHabitaciones.get(i) instanceof Suite
					&& tipoHabitacion.equals(TipoHabitacion.SUITE)) {
					nuevoArray.add(new Suite((Suite)coleccionHabitaciones.get(i)));
				}
			
			}return nuevoArray;
		}else {throw new  NullPointerException("ERROR: No se puede insertar un tipo de habitacion nulo");}
	}
	
	@Override
	public int getTamano() {
		return coleccionHabitaciones.size();
	}

	@Override
	public void insertar (Habitacion habitacion) throws OperationNotSupportedException {
		boolean encontrado=false;
		
		if(habitacion!=null) {
			for (int i=0;i<coleccionHabitaciones.size();i++) {
				if(coleccionHabitaciones.get(i).getIdentificador().equals(habitacion.getIdentificador())) {
					encontrado = true;
					throw new OperationNotSupportedException("ERROR: Ya existe una habitaci�n con ese identificador.");
				}
			}
			
			if(encontrado==false){
				coleccionHabitaciones.add(habitacion);
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede insertar una habitaci�n nula.");
		}
	}

	@Override
	public Habitacion buscar(Habitacion habitacion) {	
		Habitacion buscarHabitacion=null;
		int indice=0;
		
		if(habitacion!=null) {

			if (coleccionHabitaciones.contains(habitacion)) {
				indice=coleccionHabitaciones.indexOf(habitacion);
				buscarHabitacion=coleccionHabitaciones.get(indice);
				return buscarHabitacion;
				}else return null;
		}else {
			throw new NullPointerException("ERROR: No se puede buscar una habitaci�n nula.");}
	}
	
	@Override
	public void borrar (Habitacion habitacion) throws OperationNotSupportedException {
		boolean encontrado=false;
		
		if(habitacion!=null) {
		int indice=0;
			for (int i=0;i<coleccionHabitaciones.size();i++) {
				if(coleccionHabitaciones.get(i).equals(habitacion)) {
				encontrado=true;indice=i;
				}
			}
				
			if(encontrado==true){
				coleccionHabitaciones.remove(indice);
			}
			else {throw new OperationNotSupportedException("ERROR: No existe ninguna habitaci�n como la indicada.");}	
		
		}else {throw new NullPointerException("ERROR: No se puede borrar una habitaci�n nula.");}
	}
	
	@Override
	public void comenzar() {};
	
	@Override
	public void terminar() {};
	

}


