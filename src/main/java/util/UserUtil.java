package util;

import models.User;

public class UserUtil {
	public static final String ALPHA_NUMERICO = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789*$_.";
	private static final String RANDOM_ALPHA_NUMERICO = "gm0yB.Dvwd7ntaHLs$eGrqKjuUMSPNOJWT_fhx6482Y35VlpzIQc1XACbkiZE*9RFo";
	private static final int PASSWORD_LENGTH = 12;
	
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
			password.append(RANDOM_ALPHA_NUMERICO.charAt(ALPHA_NUMERICO.indexOf(psswd.charAt(i))));
		}
		
		String new_psswd = password.toString();
		return new_psswd;
	}
	
	public static String passwordDeCipher(String psswd) {
		StringBuilder password = new StringBuilder(psswd);
		
		for(int i = 0; i<psswd.length(); i++) {
			password.append(ALPHA_NUMERICO.charAt(RANDOM_ALPHA_NUMERICO.indexOf(psswd.charAt(i))));
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
