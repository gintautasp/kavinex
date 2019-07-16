package kavinex;

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList; 

public class SkaitymasIsFailo implements Skaitymas {
	
	private String vardas_failo;
	private BufferedReader br;
	private String file_line;
	
	public SkaitymasIsFailo () {

	}
	
	public void setVardas_failo ( String failo_vardas ) {
		
		this.vardas_failo = failo_vardas;
	}
	
	public SkaitymasIsFailo ( String vardas_failo) {
		
		this.vardas_failo = vardas_failo;
	}
	
	public void pradeti() {
		
		try {
		
			this.br = new BufferedReader( new FileReader( vardas_failo ) );
			skaitytiFragmenta();
			
		} catch ( Exception e ) {
			
			System.err.format ( "IOException: %s%n", e );
		}		
	}
	
	public ArrayList<String> iMasyva() {
		
		ArrayList<String> zodziai = new ArrayList<String>();
		
		pradeti(); 
		
		while ( nuskaitytasFragmentas() ) {
			
			paimtiFragmenta();
			
			zodziai.add ( file_line );
	
			skaitytiFragmenta();
		}		
		
		return zodziai;
	}
	
	public void skaitytiFragmenta() {
		
		try {
			
			this.file_line = this.br.readLine();
			
			if ( ! nuskaitytasFragmentas() ) {
				this.br.close();
			}			
		
		} catch ( Exception e ) {
			
			System.err.format ( "IOException: %s%n", e );
		}

	}
	 
	public boolean nuskaitytasFragmentas() {
		
		return this.file_line != null;
	}
	
	public String paimtiFragmenta() {
		
		return this.file_line;
	}
}