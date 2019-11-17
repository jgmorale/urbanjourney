package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Place;
import models.Route;
import models.Trajectory;

public class DbRouteManager {
	private Connection conn;
	
	public DbRouteManager(Connection conn) {
		this.conn = conn;
	}
	
	//TODO this class does the CRUD operations to have persistence in the DB
	
	public int insertRoute(Route route) {
		// TODO arreglar lo de la conversion de tiempo
		int result = -1;
		
		try {
			String queryRuta = "INSERT INTO ruta(id_usuario, nombre, num_lugares, duracion, distancia, status) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmntRuta = conn.prepareStatement(queryRuta);
			
			// Obtiene los valores de una ruta y los pone en un stmnt para mandarlos en un query de sql
			stmntRuta.setInt(1, route.getIdUsuario());
			stmntRuta.setString(2, route.getNombre());
			stmntRuta.setInt(3, route.getNumLugares());
			stmntRuta.setInt(4, route.getDuracion());
			stmntRuta.setInt(5, route.getDistancia());
			stmntRuta.setString(6, route.getStatus());
			
			stmntRuta.executeUpdate(); // Used to perform INSERT, UPDATE or DELETE
			
			for(Trajectory t: route.getTrayectorias()) {
				String queryTr = "INSERT INTO trayectoria(id_ruta, id_origen, id_destino, duracion, distancia, direcciones) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement stmntTr = conn.prepareStatement(queryTr);
				
				stmntTr.setInt(1, t.getIdRuta());
				stmntTr.setInt(2, t.getIdOrigen());
				stmntTr.setInt(3, t.getIdDestino());
				stmntTr.setInt(4, t.getTiempo());
				stmntTr.setInt(5, t.getDistancia());
				stmntTr.setString(6, t.getDirecciones());
				
				stmntTr.executeUpdate(); // Used to perform INSERT, UPDATE or DELETE
			}
			
			for(Place p: route.getLugares()) {
				String queryPl = "INSERT INTO lugar(id_ruta, nombre, direccion, tiempoUsuario, urlfoto, calificacion, costo, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement stmntPl = conn.prepareStatement(queryPl);
				
				stmntPl.setInt(1, p.getIdRuta());
				stmntPl.setString(2, p.getNombre());
				stmntPl.setString(3, p.getDireccion());
				stmntPl.setInt(4, p.getTiempoUsuario());
				stmntPl.setString(5, p.getUrlFoto());
				stmntPl.setFloat(6, p.getCalificacion());
				stmntPl.setString(7, p.getCosto());
				stmntPl.setString(8, p.getTipo());
				
				stmntPl.executeUpdate(); // Used to perform INSERT, UPDATE or DELETE
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Route getRoute(int idRuta) {
		
		try {
			Route route = new Route();
			String queryPl = "SELECT * FROM lugar WHERE id_ruta=?";
			PreparedStatement stmntPl = null;
			ResultSet rsPl = null;
			
			stmntPl = conn.prepareStatement(queryPl);
			stmntPl.setInt(1, idRuta);
			rsPl = stmntPl.executeQuery();
			
			while (rsPl.next()) {
				Place place = new Place();
				int id = rsPl.getInt(1);
				/*
				String nombre
				String direccion 
				int tiempoUsuario
				String urlFoto 
				float calificacion
				String
				String
				*/
				
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Route> getRoutes(int idUsuario){
		List<Route> routes = new ArrayList<Route>();
		//TODO implement function
		
		
		return routes;
	}
	
	// TODO revisar este caso
	public int modifyRoute(Route route) {	
		int result = -1;
				
		try {
			String queryRuta = "UPDATE ruta SET id_usuario=?, nombre=?, num_lugares=?, duracion=?, distancia=?, status=? WHERE id=?";
			PreparedStatement stmntRuta = conn.prepareStatement(queryRuta);
					
			// Obtiene los valores de una ruta y los pone en un stmnt para mandarlos en un query de sql
			stmntRuta.setInt(1, route.getIdUsuario());
			stmntRuta.setString(2, route.getNombre());
			stmntRuta.setInt(3, route.getNumLugares());
			stmntRuta.setInt(4, route.getDuracion());
			stmntRuta.setInt(5, route.getDistancia());
			stmntRuta.setString(6, route.getStatus());
			stmntRuta.setInt(7, route.getId());
					
			stmntRuta.executeUpdate(); // Used to perform INSERT, UPDATE or DELETE
					
			for(Trajectory t: route.getTrayectorias()) {
				String queryTr = "UPDATE trayectoria SET id_origen=?, id_destino=?, duracion=?, distancia=?, direcciones=? WHERE id_ruta=?";
				PreparedStatement stmntTr = conn.prepareStatement(queryTr);
						
				//stmntTr.setInt(1, t.getIdRuta());
				stmntTr.setInt(1, t.getIdOrigen());
				stmntTr.setInt(2, t.getIdDestino());
				stmntTr.setInt(3, t.getTiempo());
				stmntTr.setInt(4, t.getDistancia());
				stmntTr.setString(5, t.getDirecciones());
				stmntTr.setInt(6, route.getId());
						
				stmntTr.executeUpdate(); // Used to perform INSERT, UPDATE or DELETE
			}
					
			for(Place p: route.getLugares()) {
				String queryPl = "INSERT INTO lugar(id_ruta, nombre, direccion, tiempoUsuario, urlfoto, calificacion, costo, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement stmntPl = conn.prepareStatement(queryPl);
						
				stmntPl.setInt(1, p.getIdRuta());
				stmntPl.setString(2, p.getNombre());
				stmntPl.setString(3, p.getDireccion());
				stmntPl.setInt(4, p.getTiempoUsuario());
				stmntPl.setString(5, p.getUrlFoto());
				stmntPl.setFloat(6, p.getCalificacion());
				stmntPl.setString(7, p.getCosto());
				stmntPl.setString(8, p.getTipo());
						
				stmntPl.executeUpdate(); // Used to perform INSERT, UPDATE or DELETE
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return result;
	}
	
}
