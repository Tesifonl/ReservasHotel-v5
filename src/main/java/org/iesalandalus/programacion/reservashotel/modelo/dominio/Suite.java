package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public class Suite extends Habitacion {


	private static final int NUM_MAXIMO_PERSONAS = 4;
	public static final int MIN_NUM_BANOS = 0;
	public static final int MAX_NUM_BANOS = 2;
	

	public int numBanos;
	public boolean tieneJacuzzi;
	

	public Suite(int planta, int puerta, double precio, int numBanos, boolean tieneJacuzzi) {
		super(planta, puerta, precio);
		setNumBanos(numBanos);
		setTieneJacuzzi(tieneJacuzzi);
	}
	
	public Suite(Suite habitacionSuite) {
		super(habitacionSuite);
		setNumBanos(habitacionSuite.numBanos);
		setTieneJacuzzi(habitacionSuite.tieneJacuzzi);
	}
	
	public int getNumBanos() {
		return numBanos;
	}
	
	public void setNumBanos(int numBanos) {
		if (numBanos < MIN_NUM_BANOS || numBanos > MAX_NUM_BANOS) {
			throw new IllegalArgumentException("ERROR: El n�mero de ba�os no puede ser inferior a " + MIN_NUM_BANOS +" ni superior a " + MAX_NUM_BANOS);
		}
		
		this.numBanos = numBanos;
	}
	
	public boolean isTieneJacuzzi() {
		return tieneJacuzzi;
	}
	
	public void setTieneJacuzzi(boolean tieneJacuzzi) {
		this.tieneJacuzzi = tieneJacuzzi;
	}
	
	@Override
	public int getNumeroMaximoPersonas() {
		return NUM_MAXIMO_PERSONAS;
	}
	
	@Override
	public String toString() {

		String texto = super.toString() + ", habitaci�n suite, capacidad=" + NUM_MAXIMO_PERSONAS + " personas, " +
				"ba�os=" + numBanos;
		
		if (tieneJacuzzi) {
			texto = texto + ", con Jacuzzi";
		}
		else {
			texto = texto + ", sin Jacuzzi";
		}
		
		return texto;
	}

}
