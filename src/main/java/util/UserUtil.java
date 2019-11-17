package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import models.User;

// Logic already finished. We need to test the module.
public class UserUtil {
	public static final String ALPHA_NUMERICO = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789*$_.";
	private static String rndAlphaNumeric = "";
	private static final int PASSWORD_LENGTH = 12;
	
	static {
		try {
			File file = new File("C:\\Users\\Jesus\\Desktop\\PASSWORD_KEY.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			rndAlphaNumeric = br.readLine();
			br.close();
		} catch (IOException e) {
			System.out.println("No se encontró el archivo del alfabeto");
			e.printStackTrace();
		}
	}
	
	public UserUtil() {}
	
	public static String generatePassword() {
		StringBuilder psswd = new StringBuilder();
		
		for (int contador=1; contador <= PASSWORD_LENGTH ; contador++) {
			int posNuevoCaracter = (int)(Math.random()*ALPHA_NUMERICO.length());
			psswd.append(ALPHA_NUMERICO.charAt(posNuevoCaracter));
		}
		
		String p = passwordCipher(psswd.toString());
		
		return p;
	}
	
	public static String passwordCipher(String psswd) {
		StringBuilder password = new StringBuilder();
		
		for(int i = 0; i<psswd.length(); i++) {
			password.append(rndAlphaNumeric.charAt(ALPHA_NUMERICO.indexOf(psswd.charAt(i))));
		}
		
		String new_psswd = password.toString();
		return new_psswd;
	}
	
	public static String passwordDeCipher(String psswd) {
		StringBuilder password = new StringBuilder(psswd);
		
		for(int i = 0; i<psswd.length(); i++) {
			password.append(ALPHA_NUMERICO.charAt(rndAlphaNumeric.indexOf(psswd.charAt(i))));
		}
		String new_psswd = password.toString();
		return new_psswd;
	}
	
	public static boolean checkPsswd(User usuario, String psswd) {
		String psswdToCheck = passwordDeCipher(psswd);
		if(usuario.getContrasenha().equals(psswdToCheck)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String generateToken() {
		String token = generatePassword();
		
		return token;
	}
	
}
