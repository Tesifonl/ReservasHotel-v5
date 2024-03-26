package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public class Doble extends Habitacion {

	
	private static final int NUM_MAXIMO_PERSONAS = 2;
	public static final int MIN_NUM_CAMAS_INDIVIDUALES = 0;
	public static final int MAX_NUM_CAMAS_INDIVIDUALES = 2;
	public static final int MIN_NUM_CAMAS_DOBLES = 0;
	public static final int MAX_NUM_CAMAS_DOBLES = 1;
	
	
	private int numCamasIndividuales;
	private int numCamasDobles;
	
	
	public Doble(int planta, int puerta, double precio, int numCamasIndividuales, int numCamasDobles) {
		super(planta, puerta, precio);
		setNumCamasIndividuales(numCamasIndividuales);
		setNumCamasDobles(numCamasDobles);
		validaNumCamas();
	}
	
	public Doble(Doble habitacionDoble) {
		super(habitacionDoble);
		setNumCamasIndividuales(habitacionDoble.numCamasIndividuales);
		setNumCamasDobles(habitacionDoble.numCamasDobles);
		validaNumCamas();
	}
	
	public int getNumCamasIndividuales() {
		return numCamasIndividuales;
	}
	
	public void setNumCamasIndividuales(int numCamasIndividuales) {
		this.numCamasIndividuales = numCamasIndividuales;	
	}
	
	public int getNumCamasDobles() {
		return numCamasDobles;
	}
	
	public void setNumCamasDobles(int numCamasDobles) {
		this.numCamasDobles = numCamasDobles;
	}
	
	public void validaNumCamas() {
		if (this.numCamasIndividuales < MIN_NUM_CAMAS_INDIVIDUALES || 
			this.numCamasIndividuales > MAX_NUM_CAMAS_INDIVIDUALES) {
			throw new IllegalArgumentException("ERROR: El n�mero de camas individuales de una habitaci�n doble no puede ser inferior a " + MIN_NUM_CAMAS_INDIVIDUALES + " ni mayor que " + MAX_NUM_CAMAS_INDIVIDUALES);
		}
		
		if (this.numCamasDobles < MIN_NUM_CAMAS_DOBLES || 
				this.numCamasDobles > MAX_NUM_CAMAS_DOBLES) {
				throw new IllegalArgumentException("ERROR: El n�mero de camas dobles de una habitaci�n doble no puede ser inferior a " + MIN_NUM_CAMAS_DOBLES + " ni mayor que " + MAX_NUM_CAMAS_DOBLES);
			}
		
		if (this.numCamasDobles == MAX_NUM_CAMAS_DOBLES &&
			this.numCamasIndividuales > MIN_NUM_CAMAS_INDIVIDUALES) {
				throw new IllegalArgumentException("ERROR: La distribuci�n de camas en una habitaci�n doble tiene que ser " + Doble.MAX_NUM_CAMAS_INDIVIDUALES +" camas individuales y " + Doble.MIN_NUM_CAMAS_DOBLES + " doble o " + Doble.MIN_NUM_CAMAS_INDIVIDUALES + " camas individuales y " + Doble.MAX_NUM_CAMAS_DOBLES + " doble");
		}

		
		if (this.numCamasIndividuales == MAX_NUM_CAMAS_INDIVIDUALES &&
			this.numCamasDobles > MIN_NUM_CAMAS_DOBLES) {
				throw new IllegalArgumentException("ERROR: La distribuci�n de camas en una habitaci�n doble tiene que ser " + Doble.MAX_NUM_CAMAS_INDIVIDUALES +" camas individuales y " + Doble.MIN_NUM_CAMAS_DOBLES + " doble o " + Doble.MIN_NUM_CAMAS_INDIVIDUALES + " camas individuales y " + Doble.MAX_NUM_CAMAS_DOBLES + " doble");
		}
		
		if (this.numCamasIndividuales == MIN_NUM_CAMAS_INDIVIDUALES + 1  &&
			this.numCamasDobles != MAX_NUM_CAMAS_DOBLES) {
			throw new IllegalArgumentException("ERROR: La distribuci�n de camas en una habitaci�n doble tiene que ser " + Doble.MAX_NUM_CAMAS_INDIVIDUALES +" camas individuales y " + Doble.MIN_NUM_CAMAS_DOBLES + " doble o " + Doble.MIN_NUM_CAMAS_INDIVIDUALES + " camas individuales y " + Doble.MAX_NUM_CAMAS_DOBLES + " doble");
		}
	}
	
	@Override
	public int getNumeroMaximoPersonas() {
		return NUM_MAXIMO_PERSONAS;
	}
	
	@Override
	public String toString() {

		return super.toString() + ", habitaci�n doble, capacidad=" + NUM_MAXIMO_PERSONAS + " personas, " +
			"camas individuales=" + numCamasIndividuales + ", camas dobles=" + numCamasDobles; 
	}
}
