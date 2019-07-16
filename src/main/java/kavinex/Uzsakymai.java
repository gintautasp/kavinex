package kavinex;

public class Uzsakymai {
	
	public Patiekalas[] patiekalai;
	public int kiek_patiekalu = 0;
	public int[] seka_patiekalu;
	public Skaitymas skait;
	
	
	public Uzsakymai() {
		
		this.patiekalai = new Patiekalas[100];
		this.seka_patiekalu = new int [100];
	}
	
	public Uzsakymai ( SkaitymasIsFailo s ) {
		
		this.patiekalai = new Patiekalas[100];
		this.seka_patiekalu = new int [100];
		this.skait = s;
	}	
	
	/**
	 * setter'is nustato s reikšmę todel vadinas setS
	 * @param s
	 */
	public void setSkait ( Skaitymas s ) {
		
		this.skait = s;
	}

	public void nuskaityti () {
		
		String was_read = null;
		int trukme_ruosimo; 
		int trukme_kaitinimo;

		skait.pradeti();
		System.out.println ( "----------- duomenu failo turinys:\n" );
		
		while ( skait.nuskaitytasFragmentas() ) {
			
			was_read = skait.paimtiFragmenta();
			
			System.out.println( "\t" + was_read );
			String[] langeliai = was_read.split ( "," );
			
			trukme_ruosimo = 0;
			trukme_kaitinimo = 0;
			
			if ( langeliai.length > 1 ) {
			
				trukme_ruosimo = Integer.parseInt( langeliai [ 1 ] );		
			}
			
			if ( langeliai.length > 2 ) {

				trukme_kaitinimo = Integer.parseInt( langeliai [ 2 ] );
			}
			
			if ( trukme_ruosimo == 0 ) {
				
				patiekalai [ kiek_patiekalu ] = new Patiekalas ( langeliai [0] );
				
			} else {
				
				if ( trukme_kaitinimo == 0) {
					
					patiekalai [ kiek_patiekalu ] = new RuosiamasPatiekalas ( langeliai [0], trukme_ruosimo );
					
				} else {
					
					patiekalai [ kiek_patiekalu ] = new KarstasPatiekalas ( langeliai [0], trukme_ruosimo, trukme_kaitinimo );						
				}
			}
			kiek_patiekalu++;
			skait.skaitytiFragmenta();
		}	
	}	
	
	
	/**
	* virėjas ruošia patiekalus
	*
	*/
	public void ruostiPatiekalus() {
		
		int virejas_uztruks = 0;
		
		for (int i = 0; i < kiek_patiekalu; i++) {
			
			if ( patiekalai [ i ].trukmeRuosimo() > 0 ) {
				
				patiekalai [ i ].busPradetasRuostiUz( virejas_uztruks ); // 	      prisumuojam prie ruošimo laiko
				
				virejas_uztruks = patiekalai [ i ].trukmeRuosimo();  //               kada galės ruošti kitą patiekalą
				
				/* ---------------------------------------------------------- tikrinimas
				if (i == 4) {
					
					System.out.println(  
							
						patiekalai [ i ].bus_paruostas_uz + " " + patiekalai [ i ].bus_patiektas_apytiksliai_uz 
					);
					System.out.println( i + " --- " + virejas_uztruks);
				}
				*/
			}
		}
	}	
	
	public void patiekti() {
		
		int padavejos_laikas = 0;
		boolean uzsakymai_ivykdyti = false;
		int k = 0;
	
		while ( ! uzsakymai_ivykdyti ) {							// kol yra neįvykdytų užsakymų
			
			uzsakymai_ivykdyti = true;								// o gal jie įvykdyti? 	
			boolean padaveja_pateike = false;						// kol kas padavėja nieko nepatiekė
			
			for (int i = 0; i < kiek_patiekalu; i++) {				// peržiūrime patiekalų sąrašą:
				
				if ( patiekalai [ i ].bukle != PatiekaluPateikimoBusenos.Patiektas) { // radom nepatiektą patiekalą >>> a1
				
					if ( 
								( patiekalai [ i ].trukmePateikimo() <= padavejos_laikas ) // ar jau paruoštas
							&& 
								! padaveja_pateike 											// ir padavėja nieko naptiekė
					) {
						/*
						 * patiekalo pateikimas
						 */
						patiekalai [ i ].bukle = PatiekaluPateikimoBusenos.Patiektas;
						padavejos_laikas += 2;
						padaveja_pateike = true; 							// šitos peržiūros metu paitekė patiekalą
						patiekalai [ i ].patiekti ( padavejos_laikas );
						seka_patiekalu [ k ] = i;
						k++;
					}
					uzsakymai_ivykdyti = false;									// <<< a1 užsakymai dar buvo neįvykdyti
				}
			}
			if ( ! padaveja_pateike ) {	// jei nieko nepatiekė laikas didėja 1-a minute
				
				padavejos_laikas++;
			}
		}	
	}
	
	public void isnesioti() {
		
		for(int i = 0; i < kiek_patiekalu; i++ ) {
	
			System.out.println ( 		// išvedam pranešimą, apie patiekimo laiką ..
					
					"laikas: " +  patiekalai [ seka_patiekalu [ i ] ].kadaPatiekta() 
					+ " patiekalas: " + patiekalai[ seka_patiekalu [ i ] ].pavadinimas // .. ir pavadinimą.
			);	
		}
	}
	
	/**
	 * pagalbine testine struktura
	 */
	public void parodyti() {
		
		System.out.println ( "\n----------- užsakymų eiga:\n" );		
	
		for (int i = 0; i < kiek_patiekalu; i++) {
			
			patiekalai [ i ].rodyk();
		}
	}	
}
