package org.iesalandalus.programacion.reservashotel.modelo.dominio;

import java.util.Objects;


public abstract class Habitacion implements Comparable<Habitacion>{
	
	public static final double MIN_PRECIO_HABITACION=40;
	public static final double MAX_PRECIO_HABITACION=150;
	public static final int MIN_NUMERO_PUERTA=0;
	public static final int MAX_NUMERO_PUERTA=15;
	public static final int MIN_NUMERO_PLANTA=0;
	public static final int MAX_NUMERO_PLANTA=3;
	

	protected String identificador;
	protected int planta;
	protected int puerta;
	protected double precio;
	
	public Habitacion (int planta,int puerta,double precio) {
		setPlanta(planta);
		setPuerta(puerta);
		setPrecio(precio);
		setIdentificador();
	}
	
	public Habitacion (int planta,int puerta,double precio,String identificador) {
		setPlanta(planta);
		setPuerta(puerta);
		setPrecio(precio);
		setIdentificador(identificador);
	}
	
	public Habitacion (Habitacion habitacion) {
		if (habitacion==null) {
			throw new NullPointerException("ERROR: No es posible copiar una habitaci�n nula.");
		}
			else{
			setPlanta(habitacion.getPlanta());
			setPuerta(habitacion.getPuerta());
			setPrecio(habitacion.getPrecio());
			setIdentificador(habitacion.getIdentificador());}
	}
	

	public abstract int getNumeroMaximoPersonas();

	public String getIdentificador() {
		return identificador;
	}


	protected void setIdentificador() {
		String Planta=Integer.toString(getPlanta());
		String Puerta=Integer.toString(getPuerta());
		identificador=Planta+Puerta;
	}

	public void setIdentificador(String identificador) {
		if (identificador == null) {
			throw new NullPointerException("Error: El identificador no puede ser nulo.");
		}
		this.identificador = identificador;
	}

	public int getPlanta() {
		return planta;
	}

	protected void setPlanta(int planta) {
		if (planta<=MIN_NUMERO_PLANTA || planta>MAX_NUMERO_PLANTA) {
			throw new IllegalArgumentException("ERROR: No se puede establecer como planta de una habitaci�n un valor menor que 0 ni mayor que 3.");}
			else {this.planta = planta;}
	}

	public int getPuerta() {
		return puerta;
	}

	protected void setPuerta(int puerta) {
		if (puerta<MIN_NUMERO_PUERTA || puerta>=MAX_NUMERO_PUERTA) {
			throw new IllegalArgumentException("ERROR: No se puede establecer como puerta de una habitaci�n un valor menor que 0 ni mayor que 15.");}
			else {this.puerta = puerta;}
	}

	public double getPrecio() {
		return precio;
	}

	protected void setPrecio(double precio) {
		if (precio<MIN_PRECIO_HABITACION || precio> MAX_PRECIO_HABITACION) {
			throw new IllegalArgumentException("ERROR: No se puede establecer como precio de una habitaci�n un valor menor que 40.0 ni mayor que 150.0.");}
			else {this.precio = precio;}
	}

	@Override
	public int hashCode() {
		return Objects.hash(identificador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Habitacion other = (Habitacion) obj;
		return Objects.equals(identificador, other.identificador);
	}

	@Override
	public String toString() {
		return "identificador="+ identificador +" ("+getPlanta()+"-"+getPuerta()+"), precio habitaci�n="+ precio;
	}

	@Override
	public int compareTo(Habitacion o) {
		// TODO Auto-generated method stub
		return identificador.compareTo(o.identificador);
	}

}
