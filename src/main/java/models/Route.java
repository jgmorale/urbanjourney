package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.GoogleMapsUtil;

public class Route {
	private int id;
	private int idUsuario;
	private String nombre;
	private int numLugares;
	private int duracion;
	private int distancia;
	private Date fechaCreacion;
	private String status;
	private List<Place> lugares;
	private List<Trajectory> trayectorias;
	//private List<Points> puntos;
	
	public Route() {}
	
	public Route(int id, int idUsuario, String nombre, int numLugares, int duracion, int distancia,
			Date fechaCreacion, String status, List<Place> lugares, List<Trajectory> trayectorias) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.numLugares = numLugares;
		this.duracion = duracion;
		this.distancia = distancia;
		this.fechaCreacion = fechaCreacion;
		this.status = status;
		this.lugares = lugares;
		this.trayectorias = trayectorias;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumLugares() {
		return numLugares;
	}

	public void setNumLugares(int numLugares) {
		this.numLugares = numLugares;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Place> getLugares() {
		return lugares;
	}

	public void setLugares(List<Place> lugares) {
		this.lugares = lugares;
	}

	public List<Trajectory> getTrayectorias() {
		return trayectorias;
	}

	public void setTrayectorias(List<Trajectory> trayectorias) {
		this.trayectorias = trayectorias;
	}
	
	public void calculateFastRoute() {
		
	}
	
	public boolean isThereAFasterRoute() {
		//TODO calcula la ruta más rapida para recorrer todos los puntos
		// Se obtienen la longitud y latitud de todos los lugares
		// Se obtiene el orden en que se deben de recorrer todos los puntos
		// Se compara con el orden de los puntos actuales y si son iguales, no se hace nada, si no, se regresa true
		return false;
	}
	
	public void insertPlase(Place place, int pos) {
		// TODO inserta un lugar en la posicion indicada
		// Se tiene que eliminar la trayectoria que existe entre los dos puntos que antes eran adyacentes
		// Se tiene que hacer una llamada a google maps para obtener dos nuevas trayectorias
		// Se insertan las nuevas trayectorias y se actualiza el modelo
	}
	
	public void addPlace(Place place) {
		//TODO cuando se inserta el lugar, se tiene que hacer una llamada de google maps para obtener los datos y añadirlos al modelo
		List<Place> lugares = new ArrayList<Place>();
		List<String> parametros = new ArrayList<String>();
		lugares  = this.getLugares();
		lugares.add(place);
		
		if(lugares.size()>1) {
			// Hacer llamada a google maps para obtener los datos para el nuevo lugar agregado
			//parametros = GoogleMapsUtil.getTimeDistSteps(lugares.get(lugares.size()-2).getNombre(), lugares.get(lugares.size()-1).getNombre())
			// Agregar parametros al modelo
			this.calculateRouteTime();
			this.calculateRouteDistance();
		}
	}
	
	public void deletePlace(Place place) {
		//TODO cuando se elimina un lugar, se tiene que eliminar el lugar de la lista, asi como sus respectivos trayectos, despues de eso se tiene que hacer una llamada a google maps para obtener el nuevo trayecto
		
		if(this.getLugares().size()==0) {
			
		} else {
			List<String> parametros = new ArrayList<String>();
			//parametros = GoogleMapsUtil.getTimeDistSteps("", ""); // Terminar de implementar esta parte
			
			this.calculateRouteTime();
			this.calculateRouteDistance();
		}
	}
	
	public void modifyPlace(Place place) {
		//TODO checar cual es la nueva posicion en el array y calcular los nuevos tiempos y distancias de todos los lugares
		if(this.getLugares().contains(place)) {
			// Modificar el tiempo que va a pasar el usuario en el lugar
			this.calculateRouteTime();
			this.calculateRouteDistance();
		}
	}
	
	public void calculateRouteTime() {
		int time = 0;
		
		for(Trajectory t: this.trayectorias) {
			time += t.getTiempo();
		}
		
		for(Place p: this.getLugares()) {
			time += p.getTiempoUsuario();
		}
		
		this.duracion = time;
	}
	
	public void calculateRouteDistance() {
		int distance = 0;
		
		for(Trajectory t: this.trayectorias) {
			distance += t.getTiempo();
		}
		this.distancia = distance;
	}
}
