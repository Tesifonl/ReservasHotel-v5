package org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria;


import java.util.ArrayList;


import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHuespedes;

public class Huespedes implements IHuespedes{

	private static ArrayList<Huesped> coleccionHuespedes;
	
	public Huespedes() {
		coleccionHuespedes=new ArrayList<>();
	}
		
	@Override
	public ArrayList<Huesped> get() {
		ArrayList<Huesped> copia=copiaProfundaHuespedes();
		return copia;
	}
	
	private ArrayList<Huesped> copiaProfundaHuespedes() {
		ArrayList<Huesped> copiahuespedes= new ArrayList<>();
		
		for (int i=0;i<coleccionHuespedes.size();i++) {
			copiahuespedes.add(new Huesped(coleccionHuespedes.get(i)));
		}
	
		return copiahuespedes;
	}

	@Override
	public int getTamano () {
		return coleccionHuespedes.size();
	}

		
	@Override
	public void insertar (Huesped huesped) throws OperationNotSupportedException {
		boolean encontrado=false;
		
		if(huesped!=null) {
			for (int i=0;i<coleccionHuespedes.size();i++) {
				if(coleccionHuespedes.get(i).getDni().equals(huesped.getDni())) {
					encontrado = true;
					throw new OperationNotSupportedException("ERROR: Ya existe un hu�sped con ese dni.");
				}
			}	
		
			if(encontrado==false) {
				coleccionHuespedes.add(huesped);
			}
			
		}else {throw new NullPointerException("ERROR: No se puede insertar un hu�sped nulo.");}
	}

	@Override
	public Huesped buscar(Huesped huesped) {	
		int posicion = 0;
	
		
		if(huesped!=null) {
			boolean encontrado=false;
			
			for (int i=0;i<coleccionHuespedes.size();i++) {
				if(coleccionHuespedes.get(i).getDni().equals(huesped.getDni())) {
					posicion = i;
					encontrado=true;
				}
			}
			
			if (encontrado==true) {
				return coleccionHuespedes.get(posicion);
			}
			else {return null;}
		}else {throw new NullPointerException("ERROR: No se puede buscar un hu�sped nulo.");}
	}
		
	@Override
	public void borrar (Huesped huesped) throws OperationNotSupportedException  {
		boolean encontrado=false;
		
		if(huesped!=null) {
		int indice=0;
			for (int i=0;i<coleccionHuespedes.size();i++) {
				if(coleccionHuespedes.get(i).equals(huesped)) {
				encontrado=true;
				indice=i;
				}
			}	
			
			if (encontrado==true) {
				coleccionHuespedes.remove(indice);
			}
			else {
				throw new OperationNotSupportedException("ERROR: No existe ning�n hu�sped como el indicado.");}
		
		}else {throw new NullPointerException("ERROR: No se puede borrar un hu�sped nulo.");}
	}
	
	@Override
	public void comenzar() {};
	
	@Override
	public void terminar() {};
	
}
