package me.hub.API.Util;

import java.util.Random;

public class UtilTexto {

	private static Random rand = new Random();  
	private static char[] letras = ("abcdefghijklmnopqrstuvwxyz" +
		      "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
		      "01234567890" +
		      "@#$%&*()_-").toCharArray(); 
	public static String TextoAleatorio(int nCaracteres) {  
	    StringBuffer sb = new StringBuffer();  
	    for (int i = 0; i < nCaracteres; i++) {  
	        int ch = rand.nextInt (letras.length);  
	        sb.append (letras [ch]);  
	    }      
	    return sb.toString();      
	}  
	
}
