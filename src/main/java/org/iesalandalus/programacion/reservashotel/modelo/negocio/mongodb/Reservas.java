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
import org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb.utilidades.MongoDB;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

public class Reservas implements IReservas{

	
	private MongoCollection<org.bson.Document> coleccionReservas;
	private static final String COLECCION="reservas";
	
	public Reservas () {

	
	}
	
	@Override
	public ArrayList<Reserva> get() {
		
		ArrayList<Reserva> reservas=new ArrayList<>();
		
		FindIterable<org.bson.Document> coleccionReservasOrdenada=coleccionReservas.find().sort(Sorts.ascending(MongoDB.FECHA_INICIO_RESERVA));
		
		for (org.bson.Document documentoReserva: coleccionReservasOrdenada)
		{
			Reserva reserva=MongoDB.getReserva(documentoReserva);
			reservas.add(reserva);
		}
		
		return reservas;
	}
	
	
	
	@Override
	public int getTamano() {
	
		return (int) coleccionReservas.countDocuments();
	}
	


	@Override
	public void insertar (Reserva reserva) throws OperationNotSupportedException {
		
		if(reserva==null) 
		{
		throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
		
		}
	
	if (buscar(reserva)==null) 
		{
		coleccionReservas.insertOne(MongoDB.getDocumento(reserva));
		}
	else new OperationNotSupportedException("ERROR: Ya existe una reserva con ese IDENTIFICADOR");
	}
	
	
	@Override
	public Reserva buscar(Reserva reserva) {
		
		if(reserva!=null) {
			
		org.bson.Document documentoReserva=coleccionReservas.find(Filters.eq(MongoDB.FECHA_INICIO_RESERVA,reserva.getFechaInicioReserva())).first();
		return MongoDB.getReserva((org.bson.Document) documentoReserva);
			
		}else {throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");}

	}
	
	
	@Override
	public void borrar (Reserva reserva) throws OperationNotSupportedException {
		
		
		if(reserva==null) {
			
			throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
		}
		
		
		if (buscar(reserva)!=null) 
		{
		coleccionReservas.deleteOne(Filters.eq(MongoDB.IDENTIFICADOR,reserva.getFechaInicioReserva()));
		}
	else new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");
	}
	

	@Override
	public ArrayList<Reserva> getReservas (Huesped huesped) {
		
		ArrayList <Reserva>coleccionReservas2=get();
	
		if(huesped!=null) {
			ArrayList<Reserva> nuevoArray=new ArrayList<>();
			boolean encontrado=false;
	
			for (int i=0;i<coleccionReservas2.size();i++) {
				if(coleccionReservas2.get(i).getHuesped().getDni().equals(huesped.getDni())) {
					encontrado=true;
					nuevoArray.add(coleccionReservas2.get(i));
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
		
		ArrayList <Reserva>coleccionReservas2=get();
		
		if(tipoHabitacion!=null) {
			ArrayList<Reserva> nuevoArray=new ArrayList<>();
			boolean encontrado=false;

			for (int i=0;i<coleccionReservas2.size();i++) {

				if (coleccionReservas2.get(i).getHabitacion() instanceof Simple
						&& tipoHabitacion.equals(TipoHabitacion.SIMPLE)) {
						nuevoArray.add(new Reserva(coleccionReservas2.get(i)));
						encontrado = true;
					}
					else if (coleccionReservas2.get(i).getHabitacion()  instanceof Doble
						&& tipoHabitacion.equals(TipoHabitacion.DOBLE)) {
						nuevoArray.add(new Reserva(coleccionReservas2.get(i)));
						encontrado = true;
					}
					else if (coleccionReservas2.get(i).getHabitacion()  instanceof Triple
						&& tipoHabitacion.equals(TipoHabitacion.TRIPLE)) {
						nuevoArray.add(new Reserva(coleccionReservas2.get(i)));
						encontrado = true;
					}
					else if (coleccionReservas2.get(i).getHabitacion()  instanceof Suite
						&& tipoHabitacion.equals(TipoHabitacion.SUITE)) {
						nuevoArray.add(new Reserva(coleccionReservas2.get(i)));
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
	public ArrayList<Reserva> getReservas (Habitacion habitacion) {
		
		ArrayList <Reserva>coleccionReservas2=get();

		if(habitacion!=null) {
			ArrayList<Reserva>  nuevoArray=new ArrayList<>();
			boolean encontrado=false;
			int posicion=0;
			
			for (int i=0;i<coleccionReservas2.size();i++) 
				if(coleccionReservas2.get(i).getHabitacion().equals(habitacion) ) {
				encontrado=true;
				nuevoArray.add(coleccionReservas2.get(i));
			}
				
			if (encontrado==false) {
				return null;
			}
			return nuevoArray;
	
		}else {throw new  NullPointerException("ERROR: No se pueden buscar reservas de una habitaci�n nula.");}
	}
	
	@Override
	public ArrayList<Reserva> getReservasFuturas (Habitacion habitacion) {
		
		ArrayList <Reserva>coleccionReservas2=get();

		if(habitacion!=null) {
			ArrayList<Reserva>  nuevoArray=new ArrayList<>();
			boolean encontrado=false;
			int posicion=0;
			
			for (int i=0;i<coleccionReservas2.size();i++) 
				if(coleccionReservas2.get(i).getHabitacion().equals(habitacion) 
						&& coleccionReservas2.get(i).getFechaInicioReserva().isAfter(LocalDate.now())) {
				encontrado=true;
				nuevoArray.add(coleccionReservas2.get(i));
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
	public void comenzar() {
		
		MongoDB.establecerConexion();
		coleccionReservas=MongoDB.getBD().getCollection(COLECCION);

	 }
	
	@Override
	public void terminar() {
		
		MongoDB.cerrarConexion();
	
	}
	
}

