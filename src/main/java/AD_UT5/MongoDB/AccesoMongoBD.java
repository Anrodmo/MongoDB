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


import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


import com.mongodb.client.MongoClient;
//import com.mongodb.MongoClient;



public class AccesoMongoBD {
	private String baseDatos;
	private String coleccion;
	
	
	
	/**
	 * constructor para que el objeto actúe sobre la BBDD y la colección que se faciliten
	 * por parámetros.
	 * @param baseDatos
	 * @param nombreColecion
	 */
	public AccesoMongoBD(String baseDatos, String nombreColecion) {
		this.baseDatos = baseDatos;
		this.coleccion = nombreColecion;
	}
	
	/**
	 * Método que crea un registro en la colección a partir de un Map<String,String> con clave,valor
	 * Inserta todos los datos como un solo elemento de la colección. 
	 * @param datos
	 */
	public void insertarDatos(Map<String,String> datos) {
		   
		 // creo la conexión con el string de conexión (try con recursos para que se cierre solo)
		try(MongoClient mongoClient = MongoClients.create(new ConnectionString(ConString.getConString()))){
        	  // creo el objeto de la base de datos en la que trabajo
        	MongoDatabase dataBase = mongoClient.getDatabase(baseDatos);
        	  // creo el objeto de la coleccion sobre la que trabajo
        	MongoCollection<Document> coleccion = dataBase.getCollection(this.coleccion);
        	
        	// creo un documento que será cada uno de los elementos de la colección
        	Document documento = new Document();
        	
        	// itero por mi map para anexar los datos del map, para cada clave obtengo valor
        	for (String clave : datos.keySet()) {             
                String valor = datos.get(clave);
                documento.append(clave, valor);
            }
       	    // ahora que he construido el documento lo inserto en la colección, con esto se actualiza en la BBDD 	       	
        	coleccion.insertOne(documento);       	      	       	       	
        }catch( Exception ex) {  // por si hubiera alguna excepción, loas clases no la piden
        	ex.printStackTrace();
        }
		
	}
	
	
	/**
	 * Método que muestra todos los registros de la colección
	 */
	public void mostrarRegistros(){
		try(MongoClient mongoClient = MongoClients.create(new ConnectionString(ConString.getConString()))){      	
        	MongoDatabase dataBase = mongoClient.getDatabase(baseDatos);       	
        	MongoCollection<Document> coleccion = dataBase.getCollection(this.coleccion);
        	
        	// En este caso obtengo un cursor de la colección para ir recorriendo entrada a entrada
        	MongoCursor<Document> cursor = coleccion.find().iterator();
        	
        	try {   // y mientras haya un elemento nuevo va recorriendo y mostrando
        		while (cursor.hasNext()) {
        			System.out.println(cursor.next().toJson()); // este método lo pasa a Json que es texto plano      			
        		}
        	}finally {
        		cursor.close();  // lo cierro porque no abrí el cursor con try con recursos.
        	}
		
		}
	}
	
	
	/**
	 * Método que busca el primer registro que tenga la clave y el valor indicados por parámetro
	 * @param clave
	 * @param valor
	 */
	public void mostrarRegistro(String clave, String valor) {
		
		try(MongoClient mongoClient = MongoClients.create(new ConnectionString(ConString.getConString()))){        	
        	MongoDatabase dataBase = mongoClient.getDatabase(baseDatos);       	
        	MongoCollection<Document> col = dataBase.getCollection(this.coleccion);
        	
        	// con este método se busca la primera entrada que coincida y se guardad en un doc.
        	Document documento = col.find(eq(clave,valor)).first();
        	
        	if(documento != null) {  // su existe lo imprimo y si no pues doy un mensaje de error.
        		System.out.println(documento.toJson());
        	}else {
        		System.out.println("No hay registros con clave: "+clave+", valor: "+valor);
        	}        	
		}		
	}
	
	
	/**
	 * Método que busca el primer registro que tenga la clave y el valor facilitado por parámetro y devuelve un objeto
	 * con los datos
	 * @param clave
	 * @param valor
	 * @return   Objeto con los datos del registro
	 */
	public ObjDatosMongoDB encontrarRegistro(String clave, String valor) {
		
		// tengo que crear un proveedor de codec, pareciddo a una factory builder
		PojoCodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
		// de ese proveedor de codec creo un registro del codec, parecido a una factory
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        // incializo el objeto que voy a devolver.
		ObjDatosMongoDB retorno =null;
		// la conexión se crea normal
		try(MongoClient mongoClient = MongoClients.create(new ConnectionString(ConString.getConString()))){
			 // pero la BBDD se obtiene utilizando el codec 
        	MongoDatabase dataBase = mongoClient.getDatabase(baseDatos).withCodecRegistry(pojoCodecRegistry);;
        	 // la colección se obtiene indicando el tipo de objeto sobre el que se van a volcar los datos.
        	MongoCollection<ObjDatosMongoDB> col = dataBase.getCollection(this.coleccion,ObjDatosMongoDB.class);
        	 // finalmente saco el obejto que cumpla los requisitos de forma similar al mñetodo anterior.
        	ObjDatosMongoDB documento = col.find(eq(clave,valor)).first();
        	
        	if(documento != null) { // lo muestro por pantalla si existe
        		System.out.println(documento.toString());
        	}else {
        		System.out.println("No hay registros con clave: "+clave+", valor: "+valor);
        	}        	
		}       // y lo devuelvo
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
