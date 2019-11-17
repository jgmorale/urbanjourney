package models;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.GoogleMapsUtil;

public class Route {
	private int id;
	private int id_usuario;
	private String nombre;
	private int num_lugares;
	private Duration duracion;
	private float distancia;
	private Date fecha_creacion;
	private String status;
	private List<Place> lugares;
	private List<Trajectory> trayectorias;
	
	public Route() {}
	
	public Route(int id, int id_usuario, String nombre, int num_lugares, Duration duracion, float distancia,
			Date fecha_creacion, String status, List<Place> lugares, List<Trajectory> trayectorias) {
		this.id = id;
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.num_lugares = num_lugares;
		this.duracion = duracion;
		this.distancia = distancia;
		this.fecha_creacion = fecha_creacion;
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

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNum_lugares() {
		return num_lugares;
	}

	public void setNum_lugares(int num_lugares) {
		this.num_lugares = num_lugares;
	}

	public Duration getDuracion() {
		return duracion;
	}

	public void setDuracion(Duration duracion) {
		this.duracion = duracion;
	}

	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
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
		//TODO calcula la ruta más rapida para recorrer todos los puntos
		// Se obtienen la longitud y latitud de todos los lugares
		// Se obtiene
	}
	
	public boolean isThereAFasterRoute() {
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
		String time = "";
		
		for(Trajectory t: this.trayectorias) {
			time += t.getTiempo(); //Arreglar esta parte
		}
		
		for(Place p: this.getLugares()) {
			time += p.getTiempo_usuario(); //Arreglar esta parte
		}
		
		//this.duracion = time; //Arreglar esta parte
	}
	
	public void calculateRouteDistance() {
		float distance = 0.15f;
		
		for(Trajectory t: this.trayectorias) {
			//distance += t.getTiempo(); //Arreglar esta parte
		}
		//this.duracion = distance; //Arreglar esta parte
	}
}
