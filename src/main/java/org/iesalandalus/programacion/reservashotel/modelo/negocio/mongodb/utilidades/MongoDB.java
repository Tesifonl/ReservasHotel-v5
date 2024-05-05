package org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb.utilidades;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.bson.Document;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Doble;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Regimen;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Simple;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Suite;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Triple;
import org.iesalandalus.programacion.utilidades.Entrada;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

		public static final DateTimeFormatter FORMATO_DIA=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		public static final DateTimeFormatter FORMATO_DIA_HORA=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		
		private static final String SERVIDOR="cluster0.unvj2sg.mongodb.net";
		private static final int PUERTO=27017;
		private static final String BD="reservashotel";
		private static final String USUARIO="reservashotel";
		private static final String CONTRASENA="reservashotel-2024";
		
		public static final String HUESPED="huesped";
		public static final String NOMBRE="nombre";
		public static final String DNI="dni";
		public static final String TELEFONO="telefono";
		public static final String CORREO="correo";
		public static final String FECHA_NACIMIENTO="fecha_nacimiento";
		public static final String HUESPED_DNI=HUESPED+"."+DNI;
		public static final String HABITACION="habitacion";
		public static final String IDENTIFICADOR="identificador";
		public static final String PLANTA="planta";
		public static final String PUERTA="puerta";
		public static final String PRECIO="precio";
		public static final String HABITACION_IDENTIFICADOR=HABITACION+"."+IDENTIFICADOR;
		public static final String TIPO="tipo";
		public static final String HABITACION_TIPO=HABITACION+"."+IDENTIFICADOR;
		public static final String TIPO_SIMPLE="SIMPLE";
		public static final String TIPO_DOBLE="DOBLE";
		public static final String TIPO_TRIPLE="TRIPLE";
		public static final String TIPO_SUITE="SUITE";
		public static final String CAMAS_INDIVIDUALES="camas_individuales";
		public static final String CAMAS_DOBLES="camas_dobles";
		public static final String BANOS="banos";
		public static final String JACUZZI="jacuzzi";
		public static final String REGIMEN="regimen";
		public static final String FECHA_INICIO_RESERVA="fecha_inicio_reseva";
		public static final String FECHA_FIN_RESERVA="fecha_fin_reserva";
		public static final String CHECKIN="checkin";
		public static final String CHECOUT="checkout";
		public static final String PRECIO_RESERVA="precio_reserva";
		public static final String NUMERO_PERSONAS="numero_personas";
		
		private static MongoClient conexion = null;
		
		private MongoDB() {
			// Evitamos que se cree el constructor por defecto
		}
		
		public static MongoDatabase getBD() {
			if (conexion == null) {
				establecerConexion();
			}
			
			return conexion.getDatabase(BD);
		
		}
		
		
		public static void establecerConexion() {
			
	        String connectionString;
	        ServerApi serverApi;
	        MongoClientSettings settings;
			
			connectionString = "mongodb+srv://"+ USUARIO+ ":" + CONTRASENA + "@"+ SERVIDOR +"/?retryWrites=true&w=majority";
			serverApi = ServerApi.builder()
	                .version(ServerApiVersion.V1)
	                .build();
			
			settings = MongoClientSettings.builder()
	                .applyConnectionString(new ConnectionString(connectionString))
	                .serverApi(serverApi)
	                .build();
			
			//Creamos la conexión con el serveridos según el setting anterior
	        conexion = MongoClients.create(settings);
	        
	        //Intentamos hacer un ping para probar la conexion
	        try 
	        {
	        	if (!SERVIDOR.equals("localhost"))
	        	{
	        		MongoDatabase database = conexion.getDatabase(BD);
	        		database.runCommand(new Document("ping", 1));
	        	}
	        } 
	        catch (MongoException e) 
	        {
	                e.printStackTrace();
	            
	        }
	        
			System.out.println("Conexión a MongoDB realizada correctamente.");
			
			
		}
		
		public static void cerrarConexion() {
			if (conexion != null) {
				conexion.close();
				conexion = null;
				System.out.println("Conexión a MongoDB cerrada.");
			}
		}

		
		public static Document getDocumento(Huesped huesped) {
			if(huesped==null) { 
				return null;
			}
			String nombre=huesped.getNombre();
			String dni=huesped.getDni();
			String correo=huesped.getCorreo();
			String telefono= huesped.getTelefono();
			LocalDate fechaNacimiento=huesped.getFechaNacimiento();
			return new Document().append(NOMBRE, nombre).append(DNI, dni).append(CORREO,correo).append(TELEFONO, telefono).append(FECHA_NACIMIENTO, fechaNacimiento);
		}
		
		public static Document getDocumento(Habitacion habitacion) {
			
			if(habitacion==null) {
				return null;
			}
			int planta=habitacion.getPlanta();
			int puerta=habitacion.getPuerta();
			double precio=habitacion.getPrecio();
			String identificador=habitacion.getIdentificador();
			Document dHabitacion=new Document().append(PLANTA, planta).append(PUERTA, puerta).append(PRECIO, precio).append(IDENTIFICADOR, identificador);
			
			if(habitacion instanceof Simple) {
				dHabitacion.append(TIPO, TIPO_SIMPLE);
			}
			else if (habitacion instanceof Doble){
				int numCamasIndividuales=((Doble)habitacion).getNumCamasIndividuales();
				int numCamasDobles=((Doble)habitacion).getNumCamasDobles();
				dHabitacion.append(TIPO, TIPO_DOBLE).append(CAMAS_INDIVIDUALES, numCamasIndividuales).append(CAMAS_DOBLES, numCamasDobles);
			}
			else if (habitacion instanceof Triple) {
				int numBanos=((Triple)habitacion).getNumBanos();
				int numCamasIndividuales=((Triple)habitacion).getNumCamasIndividuales();
				int numCamasDobles=((Triple)habitacion).getNumCamasDobles();
				dHabitacion.append(TIPO, TIPO_TRIPLE).append(BANOS,numBanos).append(CAMAS_INDIVIDUALES, numCamasIndividuales).append(CAMAS_DOBLES, numCamasDobles);
			}
			else if(habitacion instanceof Suite) {
				int numBanos=((Suite)habitacion).getNumBanos();
				boolean tieneJacuzzi= ((Suite)habitacion).isTieneJacuzzi();
				dHabitacion.append(TIPO, TIPO_SUITE).append(BANOS,numBanos).append(JACUZZI, tieneJacuzzi);
			}
		
			return dHabitacion;
		}
		public static Document getDocumento(Reserva reserva) {
			
			if (reserva==null) {
				return null;
			}
			
			Huesped huesped=reserva.getHuesped();
			Habitacion habitacion=reserva.getHabitacion();
			Regimen regimen=reserva.getRegimen();
			LocalDate fechaInicioReserva=reserva.getFechaInicioReserva();
			LocalDate fechaFinReserva=reserva.getFechaFinReserva();
			int numeroPersonas=reserva.getNumeroPersonas();
			
			Document dHuesped=getDocumento(huesped);
			Document dHabitacion=getDocumento(habitacion);
			
			return new Document().append(HUESPED,dHuesped).append(HABITACION,dHabitacion).append(REGIMEN, regimen).append(FECHA_INICIO_RESERVA, fechaInicioReserva).append(FECHA_FIN_RESERVA, fechaFinReserva).append(NUMERO_PERSONAS,numeroPersonas);
			
		}
		
		public static Huesped getHuesped(Document documentoHuesped) {
			
			if(documentoHuesped==null) {
				return null;
			}
			
			
			
			return new Huesped(documentoHuesped.getString(NOMBRE),
							   documentoHuesped.getString(DNI),
							   documentoHuesped.getString(CORREO),
							   documentoHuesped.getString(TELEFONO),
							   (documentoHuesped.getDate(FECHA_NACIMIENTO).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
						
			}
		
		
		
		
		public static Habitacion getHabitacion(Document documentoHabitacion) {
			
			Habitacion habitacion=null;
			
			if(documentoHabitacion==null) {
				return null;
			}
			
			String tipo=documentoHabitacion.getString(TIPO);
			if(tipo.equals(TIPO_SIMPLE)){
				habitacion=new Simple(documentoHabitacion.getInteger(PLANTA),
									  documentoHabitacion.getInteger(PUERTA),
									  documentoHabitacion.getDouble(PRECIO));
			}
			if(tipo.equals(TIPO_DOBLE)){
				habitacion=new Doble(documentoHabitacion.getInteger(PLANTA),
									  documentoHabitacion.getInteger(PUERTA),
									  documentoHabitacion.getDouble(PRECIO),
									  documentoHabitacion.getInteger(CAMAS_INDIVIDUALES),
									  documentoHabitacion.getInteger(CAMAS_DOBLES));
			}
			
			if(tipo.equals(TIPO_TRIPLE)){
				habitacion=new Triple(documentoHabitacion.getInteger(PLANTA),
									  documentoHabitacion.getInteger(PUERTA),
									  documentoHabitacion.getDouble(PRECIO),
									  documentoHabitacion.getInteger(BANOS),
									  documentoHabitacion.getInteger(CAMAS_INDIVIDUALES),
									  documentoHabitacion.getInteger(CAMAS_DOBLES));
			}
			
			if(tipo.equals(TIPO_SUITE)){
				habitacion=new Suite(documentoHabitacion.getInteger(PLANTA),
									  documentoHabitacion.getInteger(PUERTA),
									  documentoHabitacion.getDouble(PRECIO),
									  documentoHabitacion.getInteger(BANOS),
									  documentoHabitacion.getBoolean(JACUZZI));
			}
			
			return habitacion;
			
			
		}
		
		
		public static Reserva getReserva(Document documentoReserva) {
			
			if(documentoReserva==null) {
				return null;
			}
			
			return new Reserva((Huesped)documentoReserva.get(HUESPED),
							   (Habitacion)documentoReserva.get(HABITACION),
							   (Regimen) documentoReserva.get(REGIMEN),
							   LocalDate.parse(documentoReserva.getString(FECHA_INICIO_RESERVA),FORMATO_DIA),
							   LocalDate.parse(documentoReserva.getString(FECHA_FIN_RESERVA),FORMATO_DIA),
							   documentoReserva.getInteger(NUMERO_PERSONAS));
							   
		}
		
		
		}
		
		
		
		