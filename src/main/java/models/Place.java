package models;

public class Place {
	private int id;
	private int idFoo;
	private int idRuta;
	private int idRutaFoo;
	private String nombre;
	private String direccion;
	private float latitud;
	private float longitud;
	private int tiempoUsuario;
	private String urlFoto;
	private float calificacion;
	private String costo;
	private String tipo;
	
	public Place() {}

	public Place(int id, int idFoo, int idRuta, int idRutaFoo, String nombre, String direccion, float latitud, float longitud,
			int tiempoUsuario, String urlFoto, float calificacion, String costo, String tipo) {
		this.id = id;
		this.idFoo = idFoo;
		this.idRuta = idRuta;
		this.idRutaFoo = idRutaFoo;
		this.nombre = nombre;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.tiempoUsuario = tiempoUsuario;
		this.urlFoto = urlFoto;
		this.calificacion = calificacion;
		this.costo = costo;
		this.tipo = tipo;
	}
	
	public int getIdRutaFoo() {
		return idRutaFoo;
	}

	public void setIdRutaFoo(int idRutaFoo) {
		this.idRutaFoo = idRutaFoo;
	}

	public int getIdFoo() {
		return idFoo;
	}
	
	public void setIdFoo(int idFoo) {
		this.idFoo = idFoo;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTiempoUsuario() {
		return tiempoUsuario;
	}

	public void setTiempoUsuario(int tiempoUsuario) {
		this.tiempoUsuario = tiempoUsuario;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public float getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(float calificacion) {
		this.calificacion = calificacion;
	}

	public String getCosto() {
		return costo;
	}

	public void setCosto(String costo) {
		this.costo = costo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
