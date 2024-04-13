package org.iesalandalus.programacion.reservashotel;



import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.reservashotel.modelo.Modelo;
import org.iesalandalus.programacion.reservashotel.vista.Vista;

public class MainApp {


    public static void main(String[] args) throws OperationNotSupportedException{
        
		Vista vista=new Vista();
		Modelo modelo=procesarArgumentosFuenteDatos(args);
		Controlador controlador=new Controlador(modelo, vista);
	
		controlador.comenzar();
        
    }
    

	private static Modelo procesarArgumentosFuenteDatos(String[] args) {
		
		Modelo modelo=null;
		
		for (String argumento : args) {

			if (argumento.equalsIgnoreCase("-fdmemoria")) 
			{
				modelo = new Modelo(FactoriaFuenteDatos.MEMORIA);
			}
			else if (argumento.equalsIgnoreCase("-fdmongodb")) 
			{
				modelo = new Modelo(FactoriaFuenteDatos.MONGODB);
			} 

		}
		return modelo;
	}
	

}
