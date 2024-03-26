package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;

public interface IHuespedes {

	public ArrayList<Huesped> get();
	public int getTamano();
	public void insertar(Huesped huesped) throws OperationNotSupportedException;
	public Huesped buscar(Huesped huesped);
	public void borrar(Huesped huesped) throws OperationNotSupportedException;
}
