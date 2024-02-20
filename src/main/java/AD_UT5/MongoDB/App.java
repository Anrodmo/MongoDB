package AD_UT5.MongoDB;


import com.mongodb.ConnectionString;
import com.mongodb.client.*;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	AccesoMongoBD conexionMongoDB = new AccesoMongoBD("mi_base_de_datos", "mi_coleccion");
    	
    	System.out.println("========== Muestro los registros ================\n");
    	
    	conexionMongoDB.mostrarRegistros();
    	
//    	System.out.println("\n========== Inserto 1 registro ================\n");
//    	
//    	
//    	Map<String, String> miMapa = new HashMap<>();
//        miMapa.put("Nombre", "Angel");
//        miMapa.put("edad", "40");
//        miMapa.put("ciudad", "Valencia");
        
//        conexionMongoDB.insertarDatos(miMapa);
    	
    	
    	System.out.println("\n========== Muestro registro nombre:Angel ================\n");
    	conexionMongoDB.mostrarRegistro("Nombre","Angel");
        
        System.out.println("\n========== Muestro los registros ================\n");
        conexionMongoDB.mostrarRegistros();
    	
//        String cadenaconexion= "mongodb+srv://anrodmo:VLdFNW9vIO1XLSdM@cluster0.tlj1tk0.mongodb.net/?retryWrites=true&w=majority";
//
//        try(MongoClient mongoClient = MongoClients.create(new ConnectionString(cadenaconexion))){
//        	
//        	MongoDatabase dataBase = mongoClient.getDatabase("mi_base_de_datos");
//        	
//        	MongoCollection<Document> coleccion = dataBase.getCollection("mi_coleccion");
//        	
//        	Document documento = new Document("nombre","Andrés")
//        			.append("edad", 25)
//        			.append("procincia","Albacete")
//        			.append("ciudad", "Albacete");
//        	
//        	coleccion.insertOne(documento);
//        	
//        	MongoCursor<Document> cursor = coleccion.find().iterator();
//        	
//        	try {
//        		while (cursor.hasNext()) {
//        			System.out.println(cursor.next().toJson());
//        			
//        		}
//        	}finally {
//        		cursor.close();
//        	}
//        	
//        	
//        }catch( Exception ex) {
//        	ex.printStackTrace();
//        }
        
        
    }  
        
}
