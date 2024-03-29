package org.iesalandalus.programacion.reservashotel.modelo.negocio.mongodb.utilidades;

import java.time.format.DateTimeFormatter;

import org.bson.Document;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
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
		
		private static final String SERVIDOR="mongodb+srv://reservashotel:<reservashotel-2024>@cluster0.unvj2sg.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
		private static final int PUERTO=27017;
		private static final String BD="reservashotel";
		private static final String USUARIO="reservashotel";
		private static final String CONTRASEÑA="reservashotel-2024";
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
		
		
		private static void establecerConexion() {
			
			String connectionString;
			ServerApi serverApi;
			MongoClientSettings settings;
			
			if (!SERVIDOR.equals("localhost"))
			{
				connectionString = "mongodb+srv://"+ USUARIO+ ":" + CONTRASENA + "@"+ SERVIDOR +"/?retryWrites=true&w=majority";
				serverApi = ServerApi.builder()
		                .version(ServerApiVersion.V1)
		                .build();
				
				settings = MongoClientSettings.builder()
		                .applyConnectionString(new ConnectionString(connectionString))
		                .serverApi(serverApi)
		                .build();
			}
			else
			{
				connectionString="mongodb://" + USUARIO + ":" + CONTRASENA + "@" + SERVIDOR + ":" + PUERTO ;
				MongoCredential credenciales = MongoCredential.createScramSha1Credential(USUARIO, BD, CONTRASENA.toCharArray());
				
				settings = MongoClientSettings.builder()
		                .applyConnectionString(new ConnectionString(connectionString))
		                .credential(credenciales)
		                .build();			
			}
			
	                
			//Creamos la conexión con el serveridos según el setting anterior
	        conexion = MongoClients.create(settings);
	        
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
		
		
		public static Documento getDocumento(Huesped huesped) {}
		public static Documento getDocumento(Habitacion habitacion) {}
		public static Documento getDocumento(Reserva reserva) {}
		public static Huesped getHuesped() {}
		public static Habitacion getHabitacion() {}
		public static Reserva getReserva() {}
		
		
		
		
		
		
		