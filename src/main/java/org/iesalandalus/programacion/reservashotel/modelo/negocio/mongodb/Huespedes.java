package org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.swing.text.Document;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHuespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb.utilidades.MongoDB;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Filters;

public class Huespedes implements IHuespedes{

	private MongoCollection<org.bson.Document> coleccionHuespedes;
	private static final String COLECCION="huespedes";
	
	public Huespedes() {
		
	}

	@Override
	public void comenzar() {

		coleccionHuespedes=MongoDB.getBD().getCollection(COLECCION);

	        }
	
	@Override
	public void terminar() {MongoDB.cerrarConexion();}
	
	
	
	@Override
	public ArrayList<Huesped> get() {
		
		ArrayList<Huesped> huespedes=new ArrayList<>();
		
		FindIterable<org.bson.Document> coleccionHuespedesOrdenada=coleccionHuespedes.find().sort(Sorts.ascending(MongoDB.DNI));
		
		for (org.bson.Document documentoHuesped: coleccionHuespedesOrdenada)
		{
			Huesped huesped=MongoDB.getHuesped(documentoHuesped);
			huespedes.add(huesped);
		}
		
		return huespedes;
	}
	
	
	

	@Override
	public int getTamano () {
		
		return (int) coleccionHuespedes.countDocuments();
	}

		
	@Override
	public void insertar (Huesped huesped) throws OperationNotSupportedException {
		
		
		if(huesped==null) 
			{
			throw new NullPointerException("ERROR: No se puede insertar un hu�sped nulo.");
			
			}
		
		if (buscar(huesped)==null) 
			{
			coleccionHuespedes.insertOne(MongoDB.getDocumento(huesped));
			}
		else new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI");
	}

	
	@Override
	public Huesped buscar(Huesped huesped) {	
		
	
		if(huesped!=null) {
			
		org.bson.Document documentoHuesped=coleccionHuespedes.find(Filters.eq(MongoDB.DNI,huesped.getDni())).first();
		return MongoDB.getHuesped((org.bson.Document) documentoHuesped);
			
		}else {throw new NullPointerException("ERROR: No se puede buscar un hu�sped nulo.");}
	}
	
		
	@Override
	public void borrar (Huesped huesped) throws OperationNotSupportedException  {
		
		if(huesped!=null) {
			
			throw new NullPointerException("ERROR: No se puede borrar un hu�sped nulo.");
		}
		
		
		if (buscar(huesped)!=null) 
		{
		coleccionHuespedes.deleteOne(Filters.eq(MongoDB.DNI,huesped.getDni()));
		}
	else new OperationNotSupportedException("ERROR: No existe ning�n hu�sped como el indicado.");
		
		
	}
	
	
	

}
