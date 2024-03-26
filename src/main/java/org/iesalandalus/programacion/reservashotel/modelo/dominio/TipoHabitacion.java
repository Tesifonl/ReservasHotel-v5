package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public enum TipoHabitacion {
	SIMPLE ("Single"), DOBLE("Doble"), TRIPLE("Triple"), SUITE ("Suite");
	
	private String cadenaAMostrar;

	private TipoHabitacion(String cadenaAMostrar) {
		this.cadenaAMostrar=cadenaAMostrar;
	}
	
    @Override
    public String toString() {
        return (this.ordinal() + 1) + ".-" + cadenaAMostrar;
    }
}
