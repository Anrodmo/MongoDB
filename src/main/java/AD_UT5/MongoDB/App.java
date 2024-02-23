package AD_UT5.MongoDB;



import java.util.HashMap;
import java.util.Map;




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
    	
    	System.out.println("\n========== Inserto 1 registro ================\n");
   	
    	
    	Map<String, String> miMapa = new HashMap<>();
        	miMapa.put("Nombre", "Maria");
        	miMapa.put("edad", "40");
        	miMapa.put("ciudad", "Albacete");
        
        conexionMongoDB.insertarDatos(miMapa);
    	
    	
    	System.out.println("\n========== Muestro registro nombre:Angel ================\n");
    	conexionMongoDB.mostrarRegistro("Nombre","Angel");
        
        System.out.println("\n========== Muestro los registros ================\n");
        conexionMongoDB.mostrarRegistros();
    	
        System.out.println("\n========== Creo objeto y añado  3 veces a partir del objeto ======\n");
        ObjDatosMongoDB obj = new ObjDatosMongoDB();
        obj.setCiudad("Madrid");
        obj.setEdad("25");
        obj.setNombre("Maria José");
        
        conexionMongoDB.insertarRegistro(obj);
        conexionMongoDB.insertarRegistro(obj);
        conexionMongoDB.insertarRegistro(obj);
        
        System.out.println("Objeto creado => "+obj.toString());
        
        System.out.println("\n========== Muestro los registros ================\n");
        conexionMongoDB.mostrarRegistros();
        
        System.out.println("\n========== Muestro registro  y obtebgo objeto nombre:Angel ================\n");
        
        obj = conexionMongoDB.encontrarRegistro("Nombre","Angel");
        System.out.println("Objeto encontrado => "+obj.toString());
        
        System.out.println("\n========== elimino la primera entrada de  nombre:María José ================\n");
        conexionMongoDB.eliminarRegistro("nombre", "Maria José");
        
        System.out.println("\n========== Muestro los registros ================\n");
        conexionMongoDB.mostrarRegistros();
        
        System.out.println("\n========== elimino todas las entradas de  nombre:María José ================\n");
        conexionMongoDB.eliminarTodosRegistros("nombre", "Maria José");
        
        System.out.println("\n========== Muestro los registros ================\n");
        conexionMongoDB.mostrarRegistros();
        
        System.out.println("\n========== ahora muestro solo todos ciudad:Albacete ================\n");
        conexionMongoDB.mostrarTodosRegistros("ciudad","Albacete");
        
        System.out.println("\n========== Accedo a otra coleccion para introducir datos variados ================\n");
        conexionMongoDB = new AccesoMongoBD("mi_base_de_datos", "mi_coleccion_variada");
        
        Map<String, Object> miMapa2 = new HashMap<>();
    	miMapa2.put("Nombre", "Juan");
    	miMapa2.put("edad", 25);
    	miMapa2.put("ciudad", "Albacete");
    
    	conexionMongoDB.insertarDatosCualesquiera(miMapa2);
    	
    	System.out.println("\n========== Muestro los registros ================\n");
        conexionMongoDB.mostrarRegistros();
        
        System.out.println("\n========== Muestro registro  y muestro edad:35 (object) ================\n");
        conexionMongoDB.mostrarRegistro("edad",35);
        
        
    } 
        
}
