package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.GoogleMapsUtil;

public class Route {
	private int id;
	private int idFoo;
	private int idUsuario;
	private String nombre;
	private int numLugares;
	private int duracion;
	private int distancia;
	private Date fechaCreacion;
	private String status;
	private List<Place> lugares;
	private List<Trajectory> trayectorias;
	
	public Route() {}
	
	public Route(int id, int idFoo, int idUsuario, String nombre, int numLugares, int duracion, int distancia,
			Date fechaCreacion, String status, List<Place> lugares, List<Trajectory> trayectorias) {
		this.id = id;
		this.idFoo = idFoo;
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
	
	public int getIdFoo() {
		return idFoo;
	}

	public void setIdFoo(int idFoo) {
		this.idFoo = idFoo;
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
		// TODO implementar algoritmo
	}
	
	public boolean isThereAFasterRoute() {
		// TODO implementar algoritmo
		return false;
	}
	
	public void insertPlase(int pos, Place place) {
		List<Place> lugares = new ArrayList<Place>();
		lugares = this.getLugares();
		lugares.add(pos, place);
	}
	
	public void addPlace(Place place) {
		List<Place> lugares = new ArrayList<Place>();
		lugares = this.getLugares();
		lugares.add(place);
	}
	
	public Place getPlace(int idPlace) {
		List<Place> lugares = this.getLugares();
		for(Place p: lugares) {
			if(p.getId() == idPlace) {
				return p;
			}
		}
		return null;
	}
	
	public Place getPlace(String placeName) {
		List<Place> lugares = this.getLugares();
		for(Place p: lugares) {
			if(p.getNombre().compareTo(placeName) == 0) {
				return p;
			}
		}
		return null;
	}
	
	public void deletePlace(Place place) {
		List<Place> lugares = new ArrayList<Place>();
		lugares = this.getLugares();
		for(Place p: lugares) {
			if(p.getId() == place.getId()) {
				lugares.remove(p);
				break;
			}
		}
	}
	
	public void deletePlace(int idPlace) {
		List<Place> lugares = new ArrayList<Place>();
		lugares = this.getLugares();
		for(Place p: lugares) {
			if(p.getId() == idPlace) {
				lugares.remove(p);
				break;
			}
		}
	}
	
	public void deletePlace(String placeName) {
		List<Place> lugares = new ArrayList<Place>();
		lugares = this.getLugares();
		for(Place p: lugares) {
			if(p.getNombre().compareTo(placeName) == 0) {
				lugares.remove(p);
				break;
			}
		}
	}
	
	public void modifyPlace(Place place) {
		List<Place> lugares = this.getLugares();
		for(int i = 0; i<lugares.size(); i++) {
			if(lugares.get(i).getId() == place.getId()) {
				lugares.remove(i);
				lugares.add(i, place);
			}
		}
	}
	
	public void addTrajectory(Trajectory trayectoria) {
		List<Trajectory> trayectorias = this.getTrayectorias();
		trayectorias.add(trayectoria);
	}
	
	public void insertTrajectory(int pos, Trajectory trayectoria) {
		List<Trajectory> trayectorias = this.getTrayectorias();
		trayectorias.add(pos, trayectoria);
	}
	
	public Trajectory getTrajectory(int idTrayectoria) {
		List<Trajectory> trayectorias = this.getTrayectorias();
		for(Trajectory t: trayectorias) {
			if(t.getId() == idTrayectoria) {
				return t;
			}
		}
		
		return null;
	}
	
	public void modifyTrajectory(Trajectory trayectoria) {
		List<Trajectory> trayectorias = this.getTrayectorias();
		for(int i = 0; i<trayectorias.size(); i++) {
			if(trayectorias.get(i).getId() == trayectoria.getId()) {
				trayectorias.remove(i);
				trayectorias.add(i, trayectoria);
			}
		}
	}
	
	public void deleteTrajectory(Trajectory trayectoria) {
		List<Trajectory> trayectorias = this.getTrayectorias();
		for(Trajectory t: trayectorias) {
			if(t.getId() == trayectoria.getId()) {
				trayectorias.remove(t);
				break;
			}
		}
	}
	
	public void calculateRouteTime() {
		int time = 0;
		
		for(Trajectory t: this.getTrayectorias()) {
			time += t.getTiempo();
		}
		
		for(Place p: this.getLugares()) {
			time += p.getTiempoUsuario();
		}
		
		this.duracion = time;
	}
	
	public void calculateRouteDistance() {
		int distance = 0;
		
		for(Trajectory t: this.getTrayectorias()) {
			distance += t.getTiempo();
		}
		
		this.distancia = distance;
	}
}
