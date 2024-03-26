package org.iesalandalus.programacion.reservashotel.vista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


import javax.naming.OperationNotSupportedException;


import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Doble;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Simple;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Suite;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Triple;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Vista {
	
	private Controlador controlador;
	private Opcion opcion;
	
	public void setControlador(Controlador controlador) {
		if(controlador!=null) {this.controlador=controlador;
		}else {throw new NullPointerException("ERROR: No con un contralador nulo");}
	}
	
	public void comenzar(){
		
		do {
			Consola.mostrarMenu();
			opcion = Consola.elegirOpcion();
			ejecutarOpcion(opcion);
		}while (!opcion.equals(Opcion.SALIR));
		
	}
	
	public void terminar() {
		System.out.println("Gracias");
	}
	
	private void ejecutarOpcion(Opcion opcion) {

		if(opcion.equals(Opcion.INSERTAR_HUESPED)) {
			insertarHuesped();}

		if(opcion.equals(Opcion.BUSCAR_HUESPED)) {
			buscarHuesped();}
		
		if(opcion.equals(Opcion.BORRAR_HUESPED)) {
			borrarHuesped();}
		
		if(opcion.equals(Opcion.MOSTRAR_HUESPEDES)) {
			mostrarHuespedes();}
		
		if(opcion.equals(Opcion.INSERTAR_HABITACION)) {
			insertarHabitacion();}
		
		if(opcion.equals(Opcion.BUSCAR_HABITACION)) {
			buscarHabitacion();}
		
		if(opcion.equals(Opcion.BORRAR_HABITACION)) {
			borrarHabitacion();}
		
		if(opcion.equals(Opcion.MOSTRAR_HABITACIONES)) {
			mostrarHabitaciones();}
		
		if(opcion.equals(Opcion.INSERTAR_RESERVA)) {
			insertarReserva();}
		
		if(opcion.equals(Opcion.ANULAR_RESERVA)) {
			anularReserva();}
		
		if(opcion.equals(Opcion.MOSTRAR_RESERVAS)) {
			mostrarReservas();}	
		
		if (opcion.equals(Opcion.LISTAR_RESERVAS_HUESPED)) {
			Huesped huesped = Consola.getHuespedPorDni();
			listarReservas(huesped);
		}
		
		if (opcion.equals(Opcion.LISTAR_RESERVAS_TIPO_HABITACION)) {
			TipoHabitacion tipoHabitacion = Consola.leerTipoHabitacion();
			listarReservas(tipoHabitacion);
		}
		
		if(opcion.equals(Opcion.CONSULTAR_DISPONIBILIDAD)) {
			System.out.println("Introduce el tipo de habitacion");
			TipoHabitacion tipoHabitacion=Consola.leerTipoHabitacion();
			System.out.println("Introduce la fecha de inicio de la reserva");
			String fecha1=Entrada.cadena();
			LocalDate fechaInicioReserva=Consola.leerFecha(fecha1);
			System.out.println("Introduce la fecha de fin de la reserva");
			String fecha2=Entrada.cadena();
			LocalDate fechaFinReserva=Consola.leerFecha(fecha2);
			
			Habitacion habitacion = consultarDisponibilidad (tipoHabitacion,fechaInicioReserva,fechaFinReserva);
			System.out.println(habitacion);
		}	
		
		if(opcion.equals(Opcion.REALIZAR_CHECKIN)) {
			realizarChechin();}
		
		if(opcion.equals(Opcion.REALIZAR_CHECKOUT)) {
			realizarChechout();}

	}


	private void insertarHuesped() {
		try {
			controlador.insertar(Consola.leerHuesped());
		}
		catch(OperationNotSupportedException e){
			System.out.println(e.getMessage());
		}
		catch(NullPointerException e){
			System.out.println(e.getMessage());
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void buscarHuesped() {
		try {
			Huesped huesped = controlador.buscar(Consola.getHuespedPorDni());
			
			if (huesped != null) {
				System.out.println(huesped);
			}
			else {
				System.out.println("No existe un huesped con ese DNI");
			}
		}
		catch(NullPointerException e){
			System.out.println(e.getMessage());
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void borrarHuesped() {
		try {
			controlador.borrar(Consola.getHuespedPorDni());
		}
		catch(NullPointerException e){
			System.out.println(e.getMessage());
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void mostrarHuespedes() {

		if(controlador.getHuespedes().size()>0) {
			
			ArrayList<Huesped> huespedes = controlador.getHuespedes();
			Collections.sort(huespedes);
			Iterator<Huesped> iterador = huespedes.iterator();
			
			while (iterador.hasNext()) {
				Huesped huesped = iterador.next();
				System.out.println(huesped);
			}
		}
		else {
			System.out.println("No hay ningun huesped");
		} 
	}
			
	
	private void insertarHabitacion() {
		try {
			controlador.insertar(Consola.leerHabitacion());
		}
		catch(NullPointerException e){
			System.out.println(e.getMessage());}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}
	
	
	
	private void buscarHabitacion() {
		try {	
			Habitacion habitacion = controlador.buscar(Consola.leerHabitacionPorIdentificador());
			
			if (habitacion != null) {
				System.out.println(habitacion);
			}
			else {
				System.out.println("No existe una habitación para esa planta y puerta");
			}
		}
		catch(NullPointerException e){
			System.out.println(e.getMessage());}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void borrarHabitacion() {
		try {
			controlador.borrar(Consola.leerHabitacionPorIdentificador());
		}
		catch(NullPointerException e){
			System.out.println(e.getMessage());}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}
	
	
	private void mostrarHabitaciones() {
	
		if(controlador.getHabitaciones().size()>0) {
			
			ArrayList<Habitacion> habitaciones = controlador.getHabitaciones();
			Collections.sort(habitaciones);
			Iterator<Habitacion> iterador = habitaciones.iterator();
			
			while (iterador.hasNext()) {
				Habitacion habitacion = iterador.next();
				System.out.println(habitacion);
			}
		}
		else {
			System.out.println("No hay ninguna habitacion");
		} 
	}
	
	private void insertarReserva() {
		try {
			Reserva nuevaReserva = Consola.leerReserva();
			TipoHabitacion tipo = null;
			
			if (nuevaReserva.getHabitacion() instanceof Simple) {
				tipo = TipoHabitacion.SIMPLE;
			}
			else if (nuevaReserva.getHabitacion() instanceof Doble) {
				tipo = TipoHabitacion.DOBLE;
			}
			else if (nuevaReserva.getHabitacion() instanceof Triple) {
				tipo = TipoHabitacion.TRIPLE;
			}
			else if (nuevaReserva.getHabitacion() instanceof Suite) {
				tipo = TipoHabitacion.SUITE;
			}
			

			if (consultarDisponibilidad(tipo, 
					                    nuevaReserva.getFechaInicioReserva(),
					                    nuevaReserva.getFechaFinReserva()) != null){
				controlador.insertar(nuevaReserva);
			}
			else {
				System.out.println("No hay disponibilida para las fechas solicitadas en la reserva.");
			}
		}
		catch(NullPointerException e){
			System.out.println(e.getMessage());}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}
	
	
	private void listarReservas(Huesped huesped) {

		if(controlador.getReservas(huesped) != null && controlador.getReservas(huesped).size()>0) {
			
			ArrayList<Reserva> reservas = controlador.getReservas(huesped);
			Collections.sort(reservas, new Comparator<Reserva>() {

				@Override
				public int compare(Reserva o1, Reserva o2) {
					return o1.getHabitacion().compareTo(o2.getHabitacion());
				}
			});
			
			Iterator<Reserva> iterador = reservas.iterator();
			
			while (iterador.hasNext()) {
				Reserva reserva = iterador.next();
				System.out.println(reserva);
			}
		}else {
			System.out.println("No existen reservas para este huesped.");
		}
	}
	
	private void listarReservas (TipoHabitacion tipoHabitacion) {

		if (controlador.getReservas(tipoHabitacion) != null && controlador.getReservas(tipoHabitacion).size() > 0) {
		
			ArrayList<Reserva> reservas = controlador.getReservas(tipoHabitacion);
			Collections.sort(reservas, new Comparator<Reserva>() {
				@Override
				public int compare(Reserva o1, Reserva o2) {
					return o1.getHuesped().compareTo(o2.getHuesped());
				}
			});	
			
			Iterator<Reserva> iterador = reservas.iterator();
			
			while (iterador.hasNext()) {
				Reserva reserva = iterador.next();
				System.out.println(reserva);
			}
		}else {
			System.out.println("No existen reservas para este tipo de habitacion.");
		}
	}

	private ArrayList<Reserva> getReservasAnulables (ArrayList<Reserva> reservasAAnular) {
		
		boolean reservasAnulables=false;
		ArrayList<Reserva> nuevoArray=new ArrayList<Reserva>();
		
		 for (int i=0; i<reservasAAnular.size();i++) {
			 if (reservasAAnular.get(i).getFechaInicioReserva().isAfter(LocalDate.now())) {
				 reservasAnulables=true;
				 nuevoArray.add(reservasAAnular.get(i));
			   }
		 }
		 
		 if (reservasAnulables==true) {return nuevoArray;
		 }else {return null;}
		 
	}
	
	private void anularReserva() {
		ArrayList<Reserva> nuevoArray1=controlador.getReservas(Consola.getHuespedPorDni());
		Reserva reservaAnular=null;
		
		if (nuevoArray1 != null && nuevoArray1.size() > 0) {	
			ArrayList<Reserva> nuevoArray2=getReservasAnulables(nuevoArray1);
			int posicion=0;
			
			if (nuevoArray2.size() > 0) {
				for (int i=0;i<nuevoArray2.size();i++) {
					System.out.println("Puede anular la reserva nº"
					+i 
					+"detallada seguidamente"
					+nuevoArray2.get(i));
				}
			}
			
			System.out.println("Introduce el numero de reserva a anular");
			posicion=Entrada.entero();
			reservaAnular=nuevoArray2.get(posicion);
			controlador.borrar(reservaAnular);
		}
		else {
			System.out.println("No existe ninguna reserva anulable para este huesped");
		}
	}

	
	private void mostrarReservas() {

		if(controlador.getReservas().size()>0) {
			
			ArrayList<Reserva> reservas = controlador.getReservas();
			Collections.sort(reservas, new Comparator<Reserva>() {

				@Override
				public int compare(Reserva o1, Reserva o2) {
					return o1.getHabitacion().compareTo(o2.getHabitacion());
				}
			});
			
			Iterator<Reserva> iterador = reservas.iterator();
			
			while (iterador.hasNext()) {
				Reserva habitacion = iterador.next();
				System.out.println(habitacion);
			}
		}else {
			System.out.println("No existen reservas para este huesped.");
		}
	}
	
	private Habitacion consultarDisponibilidad (TipoHabitacion tipoHabitacion, LocalDate fechaInicioReserva, LocalDate fechaFinReserva){
		
		ArrayList<Reserva> nuevoArray=controlador.getReservas(tipoHabitacion);
		boolean habitacionesLibres=false;
		Habitacion habitacionDisponible=null;
		
		if (nuevoArray != null) {
			for (int i=0;i<nuevoArray.size();i++) {
				if(nuevoArray.get(i).getFechaFinReserva().isBefore(fechaInicioReserva)
					|| nuevoArray.get(i).getFechaInicioReserva().isAfter(fechaFinReserva)){
					habitacionesLibres=true; 
					habitacionDisponible=nuevoArray.get(i).getHabitacion();
				}
			}
			
			if (habitacionesLibres==true){
				return habitacionDisponible; 
			}else {
				return null;
			}
		}
		else {
			ArrayList<Habitacion> habitacionesTipo = controlador.getHabitaciones(tipoHabitacion);
			if (habitacionesTipo.size() > 0) {
				return habitacionesTipo.get(0);
			}
			else {
				return null;
			}
		}
	}
	
	
	private void realizarChechin() {
		ArrayList<Reserva> nuevoArray= controlador.getReservas(Consola.getHuespedPorDni());
		Reserva reservaCheck=null;
		
		int posicion=0;
		
		if (nuevoArray != null) {

			if (nuevoArray.size()>0) {		
				for (int j=0;j<nuevoArray.size();j++) {
					System.out.println("Puede realizar checking de la reserva nº"
					+j 
					+"detallada seguidamente"
					+nuevoArray.get(j));
				}

				System.out.println("Introduce el numero de reserva a checar");
				posicion=Entrada.entero();
				reservaCheck=nuevoArray.get(posicion);
				
				controlador.realizarCheckin(reservaCheck,reservaCheck.getFechaInicioReserva().atStartOfDay());
			}else {System.out.println("No existen reservas para este huesped");}
			
		}
		else {System.out.println("Lista de reservas vacia");}
	}
	
	private void realizarChechout() {
		ArrayList<Reserva> nuevoArray= controlador.getReservas(Consola.getHuespedPorDni());
		Reserva reservaCheck=null;
		
		int posicion=0;
		
		if (nuevoArray != null) {

			if (nuevoArray.size()>0) {		
				for (int j=0;j<nuevoArray.size();j++) {
					System.out.println("Puede realizar checking de la reserva nº"
					+j 
					+"detallada seguidamente"
					+nuevoArray.get(j));
				}
				
				System.out.println("Introduce el numero de reserva a checar");
				posicion=Entrada.entero();
				reservaCheck=nuevoArray.get(posicion);
				
			controlador.realizarCheckout(reservaCheck, reservaCheck.getFechaFinReserva().atStartOfDay());
				}else {System.out.println("No existen reservas para este huesped");}
			
		}
		else {System.out.println("Lista de reservas vacia");}	
	}
}
