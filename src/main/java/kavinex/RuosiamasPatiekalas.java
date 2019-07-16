package kavinex;

public class RuosiamasPatiekalas extends Patiekalas {
	
	public int trukme_ruosimo;
	
	public RuosiamasPatiekalas() {
		
	}
	
	public RuosiamasPatiekalas (String pavadinimas, int trukme_ruosimo ) {
		
		super ( pavadinimas );
		this.trukme_ruosimo = trukme_ruosimo;
		bus_paruostas_uz += trukme_ruosimo;
		bus_patiektas_apytiksliai_uz += trukme_ruosimo;
		bukle = PatiekaluPateikimoBusenos.Neparuoštas;
	}

	public int trukmeRuosimo() {
		
		return bus_paruostas_uz;
	}	
}