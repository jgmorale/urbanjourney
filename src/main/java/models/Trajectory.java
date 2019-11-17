package models;

import java.sql.Date;
import java.time.Duration;

public class Trajectory {
	
	private int id;
	private int idRuta;
	private int idOrigen;
	private int idDestino;
	private int tiempo;
	private int distancia;
	private Date hora;
	private String direcciones;
	
	public Trajectory() {}

	public Trajectory(int id, int idRuta, int idOrigen, int idDestino, int tiempo, int distancia, Date hora,
			String direcciones) {
		this.id = id;
		this.idRuta = idRuta;
		this.idOrigen = idOrigen;
		this.idDestino = idDestino;
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

	public int getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}

	public int getIdOrigen() {
		return idOrigen;
	}

	public void setIdOrigen(int idOrigen) {
		this.idOrigen = idOrigen;
	}

	public int getIdDestino() {
		return idDestino;
	}

	public void setIdDestino(int idDestino) {
		this.idDestino = idDestino;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
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
