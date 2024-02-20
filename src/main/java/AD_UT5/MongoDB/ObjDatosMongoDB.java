package AD_UT5.MongoDB;

/**
 * Objeto para usar como reflejo de los registros de la colección de MongoDB, solo hace falta que
 * los atributos tengan el mismo nombre que las claves y sean el mismo tipo de dato.
 * Solo hay que añadir setter y getter de todos.
 */
public class ObjDatosMongoDB {
	private String nombre;
	private int edad;
	private String ciudad;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	

}
