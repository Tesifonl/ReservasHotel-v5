package org.iesalandalus.programacion.reservashotel.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;

public class Huesped implements Comparable <Huesped> {
	
	private static final String ER_TELEFONO="[0-9]{9}";
	private static final String ER_CORREO="[a-zA-Z1-9áéíóú.]*[@]{1}[a-zA-Z]{1,15}[.]{1}[a-z]*";
	private static final String ER_DNI="[1-9]{8}[a-zA-Z]{1}";
	public static final String FORMATO_FECHA="[1-2][0-9][0-9][0-9]-[0-1]+[0-9]-[0-1]+[0-9]";
	
	private DateTimeFormatter formatoFechaString = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private String nombre;
	private String telefono;
	private String correo;
	private String dni;
	private LocalDate fechaNacimiento;
	
	private String formateaNombre(String nombre) {
		
		String[] formatoNombre=nombre.split(" ");
		nombre="";
		
		for (int i=0;i<formatoNombre.length;i++) 
		if(formatoNombre[i].equals("")) {
			formatoNombre[i].trim();}	
		else {formatoNombre[i]=formatoNombre[i].substring(0, 1).toUpperCase()+formatoNombre[i].substring(1).toLowerCase();
			if (i<formatoNombre.length-1) {nombre=nombre+formatoNombre[i]+ " ";}
			else {nombre=nombre+formatoNombre[i];}
			}
		return nombre;}
	
	private boolean comprobarLetraDni(String dni) {
		
		int resto=0;
		char letra = 0;
		String letrasDni="TRWAGMYFPDXBNJZSQVHLCKE";

		String numerado=dni.substring(0, 8);
		String dni2=dni.substring(0,8)+dni.substring(8).toUpperCase();
		int numeracion= Integer.parseInt(numerado);
		resto=numeracion % 23;
		letra=letrasDni.charAt(resto);
		
		if (letra==dni2.charAt(8)) {return true;}
		else {return false;}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null) { 
			throw new NullPointerException("ERROR: El nombre de un hu�sped no puede ser nulo.");}
		if (nombre.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El nombre de un hu�sped no puede estar vac�o.");}
		
		this.nombre = formateaNombre(nombre);
		}		
	

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono == null) { 
			throw new NullPointerException("ERROR: El tel�fono de un hu�sped no puede ser nulo.");}
		else if (telefono.trim().equals("")) { 
			throw new IllegalArgumentException("ERROR: El tel�fono del hu�sped no tiene un formato v�lido.");}
		else if (!telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("ERROR: El tel�fono del hu�sped no tiene un formato v�lido.");}
		else {this.telefono = telefono;}
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		if (correo==null) {
			throw new NullPointerException("ERROR: El correo de un hu�sped no puede ser nulo.");}
		else if (correo.trim().equals("")) { 
			throw new IllegalArgumentException("ERROR: El correo del hu�sped no tiene un formato v�lido.");}
		else if (!correo.trim().matches(ER_CORREO)) {
			throw new IllegalArgumentException("ERROR: El correo del hu�sped no tiene un formato v�lido.");}
		else {this.correo = correo;}
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		if (dni== null) {
			throw new NullPointerException("ERROR: El dni de un hu�sped no puede ser nulo.");}
		else if (!dni.trim().matches(ER_DNI)) {
			throw new IllegalArgumentException("ERROR: El dni del hu�sped no tiene un formato v�lido.");}
		else if (comprobarLetraDni(dni)==true){
			this.dni = dni;}
		else {
			throw new IllegalArgumentException("ERROR: La letra del dni del hu�sped no es correcta.");} 
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		if (fechaNacimiento==null) {
			throw new NullPointerException("ERROR: La fecha de nacimiento de un hu�sped no puede ser nula.");}
		else if (fechaNacimiento.isAfter(LocalDate.now())) {
			throw new NullPointerException("ERROR: La fecha de nacimiento hu�sped no puede posterior a la de hoy.");
		}else {this.fechaNacimiento=fechaNacimiento;}
	}

				
	
	public String getIniciales(String nombre) {
		String ini="";
		nombre=formateaNombre(nombre);
		String [] iniciales=nombre.split(" ");
		for (int i=0;i<iniciales.length;i++)
		{iniciales[i]=iniciales[i].substring(0,1);	
		ini=ini+iniciales[i];}
		return ini;
}
	public Huesped(String nombre,String dni,String correo,String telefono,LocalDate fechaNacimiento) {
		setNombre(nombre);
		setDni(dni);
		setCorreo(correo);
		setTelefono(telefono);
		setFechaNacimiento(fechaNacimiento);
	}

	
	public Huesped(Huesped huesped) {
	
		if (huesped==null) {throw new NullPointerException("ERROR: No es posible copiar un hu�sped nulo.");}
		else{
			setNombre(huesped.getNombre());
			setDni(huesped.getDni());
			setCorreo(huesped.getCorreo());
			setTelefono(huesped.getTelefono());
			setFechaNacimiento(huesped.getFechaNacimiento());
		}
	}


	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Huesped other = (Huesped) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return "nombre=" + nombre+ " ("+getIniciales(nombre) +")" + ", DNI=" + dni+ ", correo=" + correo + ", tel�fono=" + telefono+ ", fecha nacimiento=" + fechaNacimiento.format(formatoFechaString) ;
	}

	@Override
	public int compareTo(Huesped huesped) {
		// TODO Auto-generated method stub
		return nombre.compareTo(huesped.nombre);
	}



}
