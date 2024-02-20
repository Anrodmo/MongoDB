package AD_UT5.MongoDB;

import java.util.Map;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import static com.mongodb.client.model.Filters.eq;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;



public class AccesoMongoBD {
	private String baseDatos;
	private String coleccion;
	
	
	public AccesoMongoBD(String baseDatos, String nombreColecion) {
		this.baseDatos = baseDatos;
		this.coleccion = nombreColecion;
	}
	
	public void insertarDatos(Map<String,String> datos) {
		
		try(MongoClient mongoClient = MongoClients.create(new ConnectionString(ConString.getConString()))){
        	
        	MongoDatabase dataBase = mongoClient.getDatabase(baseDatos);
        	
        	MongoCollection<Document> coleccion = dataBase.getCollection(this.coleccion);
        	
        	
        	Document documento = new Document();
        	
        	for (String clave : datos.keySet()) {
                // Utilizar get(clave) para obtener el valor asociado a la clave
                String valor = datos.get(clave);
                documento.append(clave, valor);
            }
        	      	       	
        	coleccion.insertOne(documento);
        	
        	
        	
        	
        }catch( Exception ex) {
        	ex.printStackTrace();
        }
		
	}
	
	
	public void mostrarRegistros(){
		try(MongoClient mongoClient = MongoClients.create(new ConnectionString(ConString.getConString()))){
        	
        	MongoDatabase dataBase = mongoClient.getDatabase(baseDatos);
        	
        	MongoCollection<Document> coleccion = dataBase.getCollection(this.coleccion);
        	
        	MongoCursor<Document> cursor = coleccion.find().iterator();
        	
        	try {
        		while (cursor.hasNext()) {
        			System.out.println(cursor.next().toJson());       			
        		}
        	}finally {
        		cursor.close();
        	}
		
		}
	}
	
	
	public void mostrarRegistro(String clave, String valor) {
		
		try(MongoClient mongoClient = MongoClients.create(new ConnectionString(ConString.getConString()))){
        	
        	MongoDatabase dataBase = mongoClient.getDatabase(baseDatos);
        	
        	MongoCollection<Document> col = dataBase.getCollection(this.coleccion);
        	
        	Document documento = col.find(eq(clave,valor)).first();
        	
        	if(documento != null) {
        		System.out.println(documento.toJson());
        	}else {
        		System.out.println("No hay registros con clave: "+clave+", valor: "+valor);
        	}        	
		}		
	}
	
	
	public ObjDatosMongoDB encontrarRegistro(String clave, String valor) {
		PojoCodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
		ObjDatosMongoDB retorno =null;
		
		try(MongoClient mongoClient = MongoClients.create(new ConnectionString(ConString.getConString()))){
        	
        	MongoDatabase dataBase = mongoClient.getDatabase(baseDatos).withCodecRegistry(pojoCodecRegistry);;
        	
        	MongoCollection<ObjDatosMongoDB> col = dataBase.getCollection(this.coleccion,ObjDatosMongoDB.class);
        	
        	ObjDatosMongoDB documento = col.find(eq(clave,valor)).first();
        	
        	if(documento != null) {
        		System.out.println(documento.toString());
        	}else {
        		System.out.println("No hay registros con clave: "+clave+", valor: "+valor);
        	}        	
		}
		return retorno;
	}
	
	
	
	
	
	

	public String getBaseDatos() {
		return baseDatos;
	}

	public void setBaseDatos(String baseDatos) {
		this.baseDatos = baseDatos;
	}

	public String getColeccion() {
		return coleccion;
	}

	public void setColeccion(String coleccion) {
		this.coleccion = coleccion;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
