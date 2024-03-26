package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public class Triple extends Habitacion {


	private static final int NUM_MAXIMO_PERSONAS = 3;
	public static final int MIN_NUM_BANOS = 0;
	public static final int MAX_NUM_BANOS = 2;
	public static final int MIN_NUM_CAMAS_INDIVIDUALES = 0;
	public static final int MAX_NUM_CAMAS_INDIVIDUALES = 3;
	public static final int MIN_NUM_CAMAS_DOBLES = 0;
	public static final int MAX_NUM_CAMAS_DOBLES = 1;
	

	private int numBanos;
	private int numCamasIndividuales;
	private int numCamasDobles;
	

	public Triple(int planta, int puerta, double precio, int numBanos, int numCamasIndividuales, int numCamasDobles) {
		super(planta, puerta, precio);
		setNumBanos(numBanos);
		setNumCamasIndividuales(numCamasIndividuales);
		setNumCamasDobles(numCamasDobles);
		validaNumCamas();
	}
	
	public Triple(Triple habitacionTriple) {
		super(habitacionTriple);
		setNumBanos(habitacionTriple.numBanos);
		setNumCamasIndividuales(habitacionTriple.numCamasIndividuales);
		setNumCamasDobles(habitacionTriple.numCamasDobles);
		validaNumCamas();
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
	public int getNumCamasIndividuales() {
		return numCamasIndividuales;
	}
	
	public void setNumCamasIndividuales(int numCamasIndividuales) {
		if (numCamasIndividuales < MIN_NUM_CAMAS_INDIVIDUALES || 
			numCamasIndividuales > MAX_NUM_CAMAS_INDIVIDUALES) {
				throw new IllegalArgumentException("ERROR: El n�mero de camas individuales de una habitaci�n triple no puede ser inferior a " + MIN_NUM_CAMAS_INDIVIDUALES + " ni mayor que " + MAX_NUM_CAMAS_INDIVIDUALES);
		}
		
		this.numCamasIndividuales = numCamasIndividuales;
	}
	
	public int getNumCamasDobles() {
		return numCamasDobles;
	}
	
	public void setNumCamasDobles(int numCamasDobles) {
		
		if (numCamasDobles < MIN_NUM_CAMAS_DOBLES || 
			numCamasDobles > MAX_NUM_CAMAS_DOBLES) {
				throw new IllegalArgumentException("ERROR: El n�mero de camas dobles de una habitaci�n triple no puede ser inferior a " + MIN_NUM_CAMAS_DOBLES + " ni mayor que " + MAX_NUM_CAMAS_DOBLES);
		}
		
		this.numCamasDobles = numCamasDobles;
	}
	
	public void validaNumCamas() {
		if (this.numCamasIndividuales < MIN_NUM_CAMAS_INDIVIDUALES || 
			this.numCamasIndividuales > MAX_NUM_CAMAS_INDIVIDUALES) {
			throw new IllegalArgumentException("ERROR: El n�mero de camas individuales de una habitaci�n triple no puede ser inferior a " + MIN_NUM_CAMAS_INDIVIDUALES + " ni mayor que " + MAX_NUM_CAMAS_INDIVIDUALES);
		}
		
		if (this.numCamasDobles < MIN_NUM_CAMAS_DOBLES || 
				this.numCamasDobles > MAX_NUM_CAMAS_DOBLES) {
				throw new IllegalArgumentException("ERROR: El n�mero de camas dobles de una habitaci�n triple no puede ser inferior a " + MIN_NUM_CAMAS_DOBLES + " ni mayor que " + MAX_NUM_CAMAS_DOBLES);
		}
		
		if (this.numCamasDobles == MAX_NUM_CAMAS_DOBLES &&
			this.numCamasIndividuales > MIN_NUM_CAMAS_INDIVIDUALES) {
				throw new IllegalArgumentException("ERROR: La distribuci�n de camas en una habitaci�n triple tiene que ser " + Triple.MAX_NUM_CAMAS_INDIVIDUALES +" camas individuales y " + Triple.MIN_NUM_CAMAS_DOBLES + " doble o " + Triple.MIN_NUM_CAMAS_INDIVIDUALES + " cama individual y " + Triple.MAX_NUM_CAMAS_DOBLES + " doble");
		}
		
		if (this.numCamasDobles == MIN_NUM_CAMAS_DOBLES &&
				this.numCamasIndividuales != MAX_NUM_CAMAS_INDIVIDUALES) {
					throw new IllegalArgumentException("ERROR: La distribuci�n de camas en una habitaci�n triple tiene que ser " + Triple.MAX_NUM_CAMAS_INDIVIDUALES +" camas individuales y " + Triple.MIN_NUM_CAMAS_DOBLES + " doble o " + Triple.MIN_NUM_CAMAS_INDIVIDUALES + " cama individual y " + Triple.MAX_NUM_CAMAS_DOBLES + " doble");
		}
	}
	
	@Override
	public int getNumeroMaximoPersonas() {
		return NUM_MAXIMO_PERSONAS;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", habitaci�n triple, capacidad=" + NUM_MAXIMO_PERSONAS + " personas, " +
			"ba�os=" + numBanos + ", camas individuales=" + numCamasIndividuales + ", camas dobles=" + numCamasDobles; 
	}
}
