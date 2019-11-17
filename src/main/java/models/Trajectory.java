package models;

import java.sql.Date;
import java.time.Duration;

public class Trajectory {
	
	private int id;
	private int id_ruta;
	private int id_origen;
	private int id_destino;
	private Duration tiempo;
	private float distancia;
	private Date hora;
	private String direcciones;
	
	public Trajectory() {}

	public Trajectory(int id, int id_ruta, int id_origen, int id_destino, Duration tiempo, float distancia, Date hora,
			String direcciones) {
		this.id = id;
		this.id_ruta = id_ruta;
		this.id_origen = id_origen;
		this.id_destino = id_destino;
		this.tiempo = tiempo;
		this.distancia = distancia;
		this.hora = hora;
		this.direcciones = direcciones;
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

	public int getId_origen() {
		return id_origen;
	}

	public void setId_origen(int id_origen) {
		this.id_origen = id_origen;
	}

	public int getId_destino() {
		return id_destino;
	}

	public void setId_destino(int id_destino) {
		this.id_destino = id_destino;
	}

	public Duration getTiempo() {
		return tiempo;
	}

	public void setTiempo(Duration tiempo) {
		this.tiempo = tiempo;
	}

	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(String direcciones) {
		this.direcciones = direcciones;
	}
	
}
