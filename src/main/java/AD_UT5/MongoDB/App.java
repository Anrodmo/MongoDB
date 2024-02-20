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
    	
        System.out.println("\n========== Creo objeto y añado a partir del objeto ======\n");
        ObjDatosMongoDB obj = new ObjDatosMongoDB();
        obj.setCiudad("Madrid");
        obj.setEdad("25");
        obj.setNombre("Maria José");
        
        conexionMongoDB.insertarRegistro(obj);
        
        System.out.println("Objeto creado => "+obj.toString());
        
        System.out.println("\n========== Muestro los registros ================\n");
        conexionMongoDB.mostrarRegistros();
        
        System.out.println("\n========== Muestro registro  y obtebgo objeto nombre:Angel ================\n");
        
        obj = conexionMongoDB.encontrarRegistro("Nombre","Angel");
        System.out.println("Objeto encontrado => "+obj.toString());
        
        
        
    } 
        
}
