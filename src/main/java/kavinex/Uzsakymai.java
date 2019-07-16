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
		
		Uzsakymas was_read = null;

		skait.pradeti();
		System.out.println ( "----------- duomenu failo turinys:\n" );
		
		while ( skait.nuskaitytasFragmentas() ) {
			
			was_read = skait.paimtiFragmenta();
			
			if ( was_read.getTrukme_ruosimo() == 0 ) {
				
				patiekalai [ kiek_patiekalu ] = new Patiekalas ( was_read.getPav() );
				
			} else {
				
				if ( was_read.getTrukme_kaitinimo() == 0) {
					
					patiekalai [ kiek_patiekalu ] = new RuosiamasPatiekalas (
							
							was_read.getPav()
							, was_read.getTrukme_ruosimo() 
					);
					
				} else {
					
					patiekalai [ kiek_patiekalu ] = new KarstasPatiekalas (
							
						was_read.getPav()
						, was_read.getTrukme_ruosimo()
						, was_read.getTrukme_kaitinimo() 
					);						
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
