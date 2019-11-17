package models;

import java.time.Duration;

public class Place {
	private int id;
	private int id_ruta;
	private String nombre;
	private String direccion;
	private float latitud;
	private float longitud;
	private Duration tiempo_usuario;
	private String urlFoto;
	private float calificacion;
	private String costo;
	private String tipo;
	
	public Place() {}

	public Place(int id, int id_ruta, String nombre, String direccion, float latitud, float longitud,
			Duration tiempo_usuario, String urlFoto, float calificacion, String costo, String tipo) {
		super();
		this.id = id;
		this.id_ruta = id_ruta;
		this.nombre = nombre;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.tiempo_usuario = tiempo_usuario;
		this.urlFoto = urlFoto;
		this.calificacion = calificacion;
		this.costo = costo;
		this.tipo = tipo;
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

	public int getId_ruta() {
		return id_ruta;
	}

	public void setId_ruta(int id_ruta) {
		this.id_ruta = id_ruta;
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

	public Duration getTiempo_usuario() {
		return tiempo_usuario;
	}

	public void setTiempo_usuario(Duration tiempo_usuario) {
		this.tiempo_usuario = tiempo_usuario;
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
