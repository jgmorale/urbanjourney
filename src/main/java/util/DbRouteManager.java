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
				String queryPl = "INSERT INTO lugar(id_ruta, nombre, direccion, latitud, longitud, tiempo_usuario, urlfoto, calificacion, costo, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement stmntPl = conn.prepareStatement(queryPl);
				
				stmntPl.setInt(1, p.getIdRuta());
				stmntPl.setString(2, p.getNombre());
				stmntPl.setString(3, p.getDireccion());
				stmntPl.setFloat(4, p.getLatitud());
				stmntPl.setFloat(5, p.getLongitud());
				stmntPl.setInt(6, p.getTiempoUsuario());
				stmntPl.setString(7, p.getUrlFoto());
				stmntPl.setFloat(8, p.getCalificacion());
				stmntPl.setString(9, p.getCosto());
				stmntPl.setString(10, p.getTipo());
				
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
				
				place.setId(rsPl.getInt(1));
				place.setIdRuta(rsPl.getInt(2));
				place.setNombre(rsPl.getString(3));
				place.setDireccion(rsPl.getString(4));
				place.setLatitud(rsPl.getFloat(5));
				place.setLongitud(rsPl.getFloat(6));
				place.setTiempoUsuario(rsPl.getInt(7));
				place.setUrlFoto(rsPl.getString(8));
				place.setCalificacion(rsPl.getFloat(9));
				place.setCosto(rsPl.getString(10));
				place.setTipo(rsPl.getString(11));
				
				route.addPlace(place);
			}
			
			String queryTr = "SELECT * FROM trayectoria WHERE id_ruta=?";
			PreparedStatement stmntTr = null;
			ResultSet rsTr = null;
			
			stmntTr = conn.prepareStatement(queryTr);
			stmntTr.setInt(1, idRuta);
			rsTr = stmntTr.executeQuery();
			
			while(rsTr.next()) {
				Trajectory trayectoria = new Trajectory();
				
				trayectoria.setId(rsTr.getInt(1));
				trayectoria.setIdRuta(rsTr.getInt(2));
				trayectoria.setIdOrigen(rsTr.getInt(3));
				trayectoria.setIdDestino(rsTr.getInt(4));
				trayectoria.setTiempo(rsTr.getInt(5));
				trayectoria.setDistancia(rsTr.getInt(6));
				trayectoria.setDirecciones(rsTr.getString(7));
				trayectoria.setHora(rsTr.getDate(8));
				
				route.addTrajectory(trayectoria);
			}
			
			String queryRt = "SELECT * FROM ruta WHERE id=?";
			PreparedStatement stmntRt = null;
			ResultSet rsRt = null;
			
			stmntRt = conn.prepareStatement(queryRt);
			stmntRt.setInt(1, idRuta);
			rsRt = stmntRt.executeQuery();
			
			route.setId(rsRt.getInt(1));
			route.setIdUsuario(rsRt.getInt(2));
			route.setNombre(rsRt.getString(3));
			route.setNumLugares(rsRt.getInt(4));
			route.setDuracion(rsRt.getInt(5));
			route.setDistancia(rsRt.getInt(6));
			route.setFechaCreacion(rsRt.getDate(7));
			route.setStatus(rsRt.getString(8));
			
			return route;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Route> getRoutes(int idUsuario){
		List<Route> routes = new ArrayList<Route>();
		
		try {
			String query = "SELECT * FROM ruta WHERE id_usuario=?";
			PreparedStatement stmnt = null;
			ResultSet rs = null;
			
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, idUsuario);
			rs = stmnt.executeQuery();
			
			while(rs.next()) {
				int idRuta = rs.getInt(1);
				Route route = new Route();
				route = getRoute(idRuta);
				routes.add(route);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
				String queryPl = "UPDATE lugar SET id_ruta=?, nombre=?, direccion=?, latitud=?, longitud=?, tiempo_usuario=?, urlfoto=?, calificacion=?, costo=?, tipo=? WHERE id_ruta=? ";
				PreparedStatement stmntPl = conn.prepareStatement(queryPl);
						
				stmntPl.setInt(1, p.getIdRuta());
				stmntPl.setString(2, p.getNombre());
				stmntPl.setString(3, p.getDireccion());
				stmntPl.setFloat(4, p.getLatitud());
				stmntPl.setFloat(5, p.getLongitud());
				stmntPl.setInt(6, p.getTiempoUsuario());
				stmntPl.setString(7, p.getUrlFoto());
				stmntPl.setFloat(8, p.getCalificacion());
				stmntPl.setString(9, p.getCosto());
				stmntPl.setString(10, p.getTipo());
						
				stmntPl.executeUpdate(); // Used to perform INSERT, UPDATE or DELETE
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
