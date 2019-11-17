package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.User;

// TODO logic already completed.
public class UserManager {
	public Connection conn;
	
	public UserManager() {}
	
	public UserManager(Connection conn) {
		this.conn = conn;
	}
	
	public User getUser(int id_usuario) {
		String query = "SELECT * FROM usuario WHERE id=?";
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, id_usuario);
			rs = stmnt.executeQuery();
			if (rs.first()) {
				int id = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				String username = rs.getString(4);
				String correo = rs.getString(5);
				String password = rs.getString(6);
				String ciudad = rs.getString(7);
				String pais = rs.getString(8);
				String token = rs.getString(9);
				
				User usuario = new User();
				usuario.setId(id);
				usuario.setNombre(nombre);
				usuario.setApellido(apellido);
				usuario.setUsername(username);
				usuario.setCorreo(correo);
				usuario.setContrasenha(password);
				usuario.setCiudad(ciudad);
				usuario.setPais(pais);
				usuario.setToken(token);
				
				return usuario;
			}
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	public User getUser(String username) {
		String query = "SELECT * FROM usuario WHERE username=?";
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setString(1, username);
			rs = stmnt.executeQuery();
			if (rs.first()) {
				int id = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				String correo = rs.getString(5);
				String password = rs.getString(6);
				String ciudad = rs.getString(7);
				String pais = rs.getString(8);
				String token = rs.getString(9);
				
				User usuario = new User();
				usuario.setId(id);
				usuario.setNombre(nombre);
				usuario.setApellido(apellido);
				usuario.setUsername(username);
				usuario.setCorreo(correo);
				usuario.setContrasenha(password);
				usuario.setCiudad(ciudad);
				usuario.setPais(pais);
				usuario.setToken(token);
				
				return usuario;
			}
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	// Inserta un usuario en la base de datos
	public int insertUser(User usuario) {
		int result = -1;
		// Crea el query para poder insertar el usuario en la BD
		String query = "INSERT INTO usuario(nombre, apellido, username, password, correo, ciudad, pais, token) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		// Crea un prepared statement para poder hacer la query parametrizada
		PreparedStatement stmnt = null;
		
		try {
			stmnt = conn.prepareStatement(query);
			// Inserta los valores del usuario en la query
			stmnt.setString(1, usuario.getNombre());
			stmnt.setString(2, usuario.getApellido());
			stmnt.setString(3, usuario.getUsername());
			stmnt.setString(4, usuario.getContrasenha());
			stmnt.setString(5, usuario.getCorreo());
			stmnt.setString(6, usuario.getCiudad());
			stmnt.setString(7, usuario.getPais());
			stmnt.setString(8, usuario.getToken());
			
			// Se ejecuta la query para persistir en la base de datos
			stmnt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	public int updateUser(User usuario) {
		String query = "UPDATE usuario SET nombre=?, apellido=?, username=?, password=?, correo=?, ciudad=?, pais=?, token=? WHERE id=?";
		PreparedStatement stmnt = null;
		int result = -1;
		
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setString(1, usuario.getNombre());
			stmnt.setString(2, usuario.getApellido());
			stmnt.setString(3, usuario.getUsername());
			stmnt.setString(4, usuario.getContrasenha());
			stmnt.setString(5, usuario.getCorreo());
			stmnt.setString(6, usuario.getCiudad());
			stmnt.setString(7, usuario.getPais());
			stmnt.setString(8, usuario.getToken());
			stmnt.setInt(9, usuario.getId());
			
			result = stmnt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
}
