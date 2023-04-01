package package1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Lector {
	 private final String caminodeArchivo;
	 
	 public Lector() {
	        this.caminodeArchivo = "emails.csv";
	    }
	 
	 public void ejecutar(int num1) throws IOException {
		  
		 	String num = Integer.toString(num1);
	        String linea;
	        int filaAIniciar = Integer.parseInt(num.substring(num.length() - 3));
	        int filaActual = 0;
	        Palabra[] recuentoDePalabras = new Palabra[3000];
	        String nombreArchivo = String.format("%03d.txt", Integer.parseInt(num));
	        Arrays.fill(recuentoDePalabras, null);

	        try (BufferedReader br = new BufferedReader(new FileReader(caminodeArchivo));
	             FileWriter fw = new FileWriter(nombreArchivo)) {

	            while ((linea = br.readLine()) != null) {
	               
	            	String[] values = linea.split(",");
	            	
	            	if (filaActual == 0) { 
	                    for (int i = 1; i <= 3000; i++) {
	                        String palabra = values[i];
	                        if (!palabra.equals("")) {
	                            recuentoDePalabras[i - 1] = new Palabra(palabra, 0);
	                        }
	                    }
	            	}
	                    
	            	filaActual++;

	                if (filaActual < filaAIniciar || filaActual > filaAIniciar + 49) {
	                    continue;
	                }else { 
	                    for (int i = 1; i <= 3000; i++) {
	                        int cantidad = Integer.parseInt(values[i]);
	                        if (cantidad > 0) {
	                            Palabra palabra = recuentoDePalabras[i - 1];
	                            if (palabra != null) {
	                                palabra.contador += cantidad;
	                            }
	                        }
	                    }
	                }
	            }

	            for (Palabra palabra : recuentoDePalabras) {
	                if (palabra != null) {
	                    fw.write(palabra.nombre + "," + palabra.contador + "\n");
	                }
	            }
	        }
	    }
}