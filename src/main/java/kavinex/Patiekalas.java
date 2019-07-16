package kavinex;


public class Patiekalas {
	
	public String pavadinimas;
	public int bus_paruostas_uz; /* minutemis */
	public int bus_patiektas_apytiksliai_uz; /* minutemis */
	public int patiekimo_laikas = 0;
	public PatiekaluPateikimoBusenos bukle = PatiekaluPateikimoBusenos.Paruoštas;
	
	public Patiekalas() {
		
	}
	
	public Patiekalas( String pavadinimas ) {
		
		this.pavadinimas = pavadinimas;
		bus_paruostas_uz = 0;
		bus_patiektas_apytiksliai_uz = 0;
	}
	
	public void busPradetasRuostiUz (int ruosimo_pradzia) {
		
		bus_paruostas_uz += ruosimo_pradzia;
		bus_patiektas_apytiksliai_uz += ruosimo_pradzia; 
	}
	
	public int trukmeRuosimo() {
		
		return bus_paruostas_uz;
	}
	
	public int trukmePateikimo() {
		
		return bus_patiektas_apytiksliai_uz;
	}
	
	public void patiekti(int patiekimo_laikas) {
		
		this.patiekimo_laikas = patiekimo_laikas;
	}
	
	public int kadaPatiekta() {
		
		return this.patiekimo_laikas; 
	}
	
	public void rodyk() {
		
		System.out.println ( "\t" + pavadinimas + " -> " + bus_paruostas_uz + " / " + bus_patiektas_apytiksliai_uz + " min." );
	}
}

