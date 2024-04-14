package org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb;

import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Doble;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Simple;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Suite;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Triple;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHabitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb.utilidades.MongoDB;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;


public class Habitaciones implements IHabitaciones{
	
	private MongoCollection<org.bson.Document> coleccionHabitaciones;
	private static final String COLECCION="habitaciones";

	
	public Habitaciones() {
		
	}
	
	
	@Override
	public ArrayList<Habitacion> get() {
		
		ArrayList<Habitacion> habitaciones=new ArrayList<>();
		
		FindIterable<org.bson.Document> coleccionHabitacionesOrdenada=coleccionHabitaciones.find().sort(Sorts.ascending(MongoDB.IDENTIFICADOR));
		
		for (org.bson.Document documentoHabitaciones: coleccionHabitacionesOrdenada)
		{
			Habitacion habitacion=MongoDB.getHabitacion(documentoHabitaciones);
			habitaciones.add(habitacion);
		}
		
		return habitaciones;
	}
	
	

	@Override
	public ArrayList<Habitacion> get (TipoHabitacion tipoHabitacion) {
		
		ArrayList<Habitacion> coleccionHabitaciones2=get();
		
		if(tipoHabitacion!=null) {
			
			ArrayList<Habitacion> nuevoArray= new ArrayList<>();

			for (int i=0;i<coleccionHabitaciones2.size();i++) {

				if (coleccionHabitaciones2.get(i) instanceof Simple
					&& tipoHabitacion.equals(TipoHabitacion.SIMPLE)) {
					nuevoArray.add(new Simple((Simple)coleccionHabitaciones2.get(i)));
				}
				else if (coleccionHabitaciones2.get(i) instanceof Doble
					&& tipoHabitacion.equals(TipoHabitacion.DOBLE)) {
					nuevoArray.add(new Doble((Doble)coleccionHabitaciones2.get(i)));
				}
				else if (coleccionHabitaciones2.get(i) instanceof Triple
					&& tipoHabitacion.equals(TipoHabitacion.TRIPLE)) {
					nuevoArray.add(new Triple((Triple)coleccionHabitaciones2.get(i)));
				}
				else if (coleccionHabitaciones2.get(i) instanceof Suite
					&& tipoHabitacion.equals(TipoHabitacion.SUITE)) {
					nuevoArray.add(new Suite((Suite)coleccionHabitaciones2.get(i)));
				}
			
			}return nuevoArray;
		}else {throw new  NullPointerException("ERROR: No se puede insertar un tipo de habitacion nulo");}
	}
	
	@Override
	public int getTamano() {
		return (int) coleccionHabitaciones.countDocuments();
	}

	@Override
	public void insertar (Habitacion habitacion) throws OperationNotSupportedException {
		
		if(habitacion==null) 
		{
		throw new NullPointerException("ERROR: No se puede insertar una habitacion nula.");
		
		}
	
	if (buscar(habitacion)==null) 
		{
		coleccionHabitaciones.insertOne(MongoDB.getDocumento(habitacion));
		}
	else new OperationNotSupportedException("ERROR: Ya existe una habitacion con ese IDENTIFICADOR");
	}

	@Override
	public Habitacion buscar(Habitacion habitacion) {	
		
		if(habitacion!=null) {
			
		org.bson.Document documentoHabitacion=coleccionHabitaciones.find(Filters.eq(MongoDB.IDENTIFICADOR,habitacion.getIdentificador())).first();
		return MongoDB.getHabitacion(documentoHabitacion);
			
		}else {throw new NullPointerException("ERROR: No se puede buscar un hu�sped nulo.");}
	}
	
	@Override
	public void borrar (Habitacion habitacion) throws OperationNotSupportedException {
		
		if(habitacion==null) {
			
			throw new NullPointerException("ERROR: No se puede borrar una habitacion nula.");
		}
		
		
		if (buscar(habitacion)!=null) 
		{
		coleccionHabitaciones.deleteOne(Filters.eq(MongoDB.IDENTIFICADOR,habitacion.getIdentificador()));
		}
	else new OperationNotSupportedException("ERROR: No existe ninguna habitacion como la indicada.");
	}
	
	@Override
	public void comenzar() {
		
		MongoDB.establecerConexion();
		coleccionHabitaciones=MongoDB.getBD().getCollection(COLECCION);

	 }
	
	@Override
	public void terminar() {
		
		MongoDB.cerrarConexion();
	
	}
	

}


