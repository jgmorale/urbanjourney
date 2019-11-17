package resources;

import java.sql.Connection;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import models.User;
import util.DatabaseUtil;
import util.UserManager;
import util.UserUtil;

@Path("login")
public class LoginResource {
	
	@POST
	@Consumes
	public String userLogIn(@FormParam("username") String username, @FormParam("password") String password) {
		Connection conn = DatabaseUtil.getConnection();
		UserManager usrManager = new UserManager(conn);
		User usuario = usrManager.getUser(username);
		if(UserUtil.checkPsswd(usuario, password) == true) {
			// Genera un nuevo token
			usuario.setToken(UserUtil.generateToken());
			// Añade el nuevo token a la BD
			usrManager.updateUser(usuario);
			return new Gson().toJson(usuario.getId() + " " + usuario.getToken());
		} else {
			return new Gson().toJson("Contraseña incorrecta, vuelva a intentarlo más tarde");
		}
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public String userRegistration(@FormParam("nombre") String nombre, @FormParam("apellido") String apellido, @FormParam("username") String username, @FormParam("password") String password, @FormParam("correo") String correo, @FormParam("ciudad") String ciudad, @FormParam("pais") String pais) {
		User usuario = new User();
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setUsername(username);
		usuario.setContrasenha(password);
		usuario.setCorreo(correo);
		usuario.setCiudad(ciudad);
		usuario.setPais(pais);
		usuario.setToken(UserUtil.generateToken());
		Connection conn = DatabaseUtil.getConnection();
		UserManager usrManager = new UserManager(conn);
		usrManager.insertUser(usuario);
		return new Gson().toJson("El usuario se registró correctamente");
	}

}
