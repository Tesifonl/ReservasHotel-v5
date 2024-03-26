package org.iesalandalus.programacion.reservashotel.modelo.dominio;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Reserva implements Comparable<Reserva>{
	
	public static final int MAX_NUMERO_MESES_RESERVA=6;
	private static final int MAX_HORAS_POSTERIOR_CHECKOUT=12;
	public static final String FORMATO_FECHA_RESERVA="dd/MM/yyyy";
	public static final String FORMATO_FECHA_HORA_RESERVA="dd/MM/yyyy hh:mm:ss";

	
	private Huesped huesped;
	private Habitacion habitacion;
	private Regimen regimen;
	private LocalDate fechaInicioReserva;
	private LocalDate fechaFinReserva;
	private LocalDateTime checkIn;
	private LocalDateTime checkOut;
	private double precio;
	private int numeroPersonas;
	
	
	public Huesped getHuesped() {
		return new Huesped(huesped);
	}
	public void setHuesped(Huesped huesped) {
		if (huesped==null) {
			throw new NullPointerException("ERROR: El hu�sped de una reserva no puede ser nulo.");}
		else {
			this.huesped = new Huesped (huesped);}
	}
	public Habitacion getHabitacion() {
		if (habitacion instanceof Simple) {
			return new Simple((Simple)habitacion);
		}
		else if (habitacion instanceof Doble) {
			return new Doble((Doble) habitacion);
		}
		else if (habitacion instanceof Triple) {
			return new Triple((Triple) habitacion);
		}
		else {
			return new Suite((Suite) habitacion);
		}
	}
	
	public void setHabitacion(Habitacion habitacion) {
		if (habitacion==null) {
			throw new NullPointerException("ERROR: La habitaci�n de una reserva no puede ser nula.");}
		else {
			if (habitacion instanceof Simple) {
				this.habitacion = new Simple ((Simple) habitacion);
			}
			else if (habitacion instanceof Doble) {
				this.habitacion = new Doble ((Doble) habitacion);
			}
			else if (habitacion instanceof Triple) {
				this.habitacion = new Triple ((Triple) habitacion);
			}
			else {
				this.habitacion = new Suite ((Suite) habitacion);
			}
		}
	}
	
	public Regimen getRegimen() {
		return regimen;
	}
	public void setRegimen(Regimen regimen) {
		if (regimen==null) {
			throw new NullPointerException("ERROR: El r�gimen de una reserva no puede ser nulo.");}
		else {
			this.regimen = regimen;}
	}
	public LocalDate getFechaInicioReserva() {
		return fechaInicioReserva;
	}
	public void setFechaInicioReserva(LocalDate fechaInicioReserva) {
		if (fechaInicioReserva==null) {
			throw new NullPointerException("ERROR: La fecha de inicio de una reserva no puede ser nula.");}
		if (fechaInicioReserva.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("ERROR: La fecha de inicio de la reserva no puede ser anterior al d�a de hoy.");}
		else if(fechaInicioReserva.isAfter(LocalDate.now().plusMonths(MAX_NUMERO_MESES_RESERVA))){
			throw new IllegalArgumentException("ERROR: La fecha de inicio de la reserva no puede ser posterior a seis meses.");}
		else {this.fechaInicioReserva = fechaInicioReserva;}
	}
	public LocalDate getFechaFinReserva() {
		return fechaFinReserva;
	}
	public void setFechaFinReserva(LocalDate fechaFinReserva) {
		if (fechaFinReserva==null) {
			throw new NullPointerException("ERROR: La fecha de fin de una reserva no puede ser nula.");}
		if (fechaFinReserva.isBefore(getFechaInicioReserva())){
			throw new IllegalArgumentException("ERROR: La fecha de fin de la reserva debe ser posterior a la de inicio.");}
		else if(fechaFinReserva.equals(getFechaInicioReserva())){
			throw new IllegalArgumentException("ERROR: La fecha de fin de la reserva debe ser posterior a la de inicio.");}
		else{
			this.fechaFinReserva = fechaFinReserva;}
	}
	public LocalDateTime getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(LocalDateTime checkIn) {
		if (checkIn==null) {
			throw new NullPointerException("ERROR: El checkin de una reserva no puede ser nulo.");}
		if (checkIn.isBefore(getFechaInicioReserva().atStartOfDay())){
			throw new IllegalArgumentException("ERROR: El checkin de una reserva no puede ser anterior a la fecha de inicio de la reserva.");}
		else{
			this.checkIn = checkIn;}
	}
	public LocalDateTime getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(LocalDateTime checkOut) {
		if (checkOut==null) {
			throw new NullPointerException("ERROR: El checkout de una reserva no puede ser nulo.");}
		if (checkOut.isAfter(fechaFinReserva.atTime(MAX_HORAS_POSTERIOR_CHECKOUT, 0))) {
			throw new IllegalArgumentException("ERROR: El checkout de una reserva puede ser como m�ximo 12 horas despu�s de la fecha de fin de la reserva.");}
		else if (checkOut.isBefore(checkIn)){
			throw new IllegalArgumentException("ERROR: El checkout de una reserva no puede ser anterior al checkin.");}
		else {
			this.checkOut = checkOut;
			setPrecio();}
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio() {
		double precioRegimen=getRegimen().getIncrementoPrecio()*getNumeroPersonas();
		
		Period periodoReserva = Period.between(fechaInicioReserva, fechaFinReserva);
		precio=(precioRegimen+habitacion.getPrecio())*periodoReserva.getDays();
	}
	
	public int getNumeroPersonas() {
		return numeroPersonas;
	}
	public void setNumeroPersonas(int numeroPersonas) {
		
		if (numeroPersonas<=0) {
			throw new IllegalArgumentException("ERROR: El n�mero de personas de una reserva no puede ser menor o igual a 0.");}		
		else if(numeroPersonas > habitacion.getNumeroMaximoPersonas()) {
			throw new IllegalArgumentException("ERROR: El n�mero de personas de una reserva no puede superar al m�ximo de personas establacidas para el tipo de habitaci�n reservada.");
		}
		
		this.numeroPersonas = numeroPersonas;
	}
	
	public Reserva(Huesped huesped,Habitacion habitacion, Regimen regimen,LocalDate fechaInicioReserva,LocalDate fechaFinReserva,int numeroPersonas) {
		
		setHuesped(huesped);
		setHabitacion(habitacion);
		setRegimen(regimen);
		setFechaInicioReserva(fechaInicioReserva);
		setFechaFinReserva(fechaFinReserva);
		setNumeroPersonas(numeroPersonas);
		this.precio=0;
	}
	
	public Reserva(Reserva reserva) {
		
		if (reserva==null) {throw new NullPointerException("ERROR: No es posible copiar una reserva nula.");}
		else {
		this.huesped=new Huesped(reserva.getHuesped());
		
		if (reserva.getHabitacion() instanceof Simple) {
			this.habitacion=new Simple ((Simple) reserva.getHabitacion());
		}
		else if (reserva.getHabitacion() instanceof Doble) {
			this.habitacion=new Doble ((Doble) reserva.getHabitacion());
		}
		else if (reserva.getHabitacion() instanceof Triple) {
			this.habitacion=new Triple ((Triple) reserva.getHabitacion());
		}
		else {
			this.habitacion=new Suite ((Suite) reserva.getHabitacion());
		}
		
		setRegimen(reserva.getRegimen());
		setFechaInicioReserva(reserva.getFechaInicioReserva());
		setFechaFinReserva(reserva.getFechaFinReserva());
		setNumeroPersonas(reserva.getNumeroPersonas());
		
		if (reserva.getCheckIn() != null)
			setCheckIn(reserva.getCheckIn());
		
		if (reserva.getCheckOut() != null)
			setCheckOut(reserva.getCheckOut());
		
		setPrecio();
		}
	}
	@Override
	public int hashCode() {
		return Objects.hash(fechaInicioReserva, habitacion);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(fechaInicioReserva, other.fechaInicioReserva)
				&& Objects.equals(habitacion, other.habitacion);
	}
	@Override
	public String toString() {
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(FORMATO_FECHA_RESERVA);
		DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA_RESERVA);
		String formatoPrecio = String.format("%.2f", getPrecio());
		
		String texto = "Huesped: " + huesped.getNombre()+" "+huesped.getDni() + " Habitaci�n:"+ habitacion
				+ " Fecha Inicio Reserva: " + getFechaInicioReserva().format(formatoFecha) 
				+ " Fecha Fin Reserva: " + getFechaFinReserva().format(formatoFecha); 
				
				if (getCheckIn() != null) {
					texto = texto + " Checkin: " + getCheckIn().format(formatoFechaHora);
				}
				else {
					texto = texto + " Checkin: No registrado";
				}
				
				if (getCheckOut() != null) {
					texto = texto + " Checkout: " + getCheckOut().format(formatoFechaHora);
				}
				else {
					texto = texto + " Checkout: No registrado";
				}

				texto =texto+ " Precio: " + formatoPrecio + " Personas: " + numeroPersonas;
				
		return texto;
		
	}
	@Override
	public int compareTo(Reserva o) {
		if (fechaInicioReserva.isAfter(o.fechaInicioReserva)) {
			return 1;
		}
		else if (fechaInicioReserva.isBefore(o.fechaInicioReserva)) {
			return -1;
		}
		else {
			return 0;
		}
	}
}


