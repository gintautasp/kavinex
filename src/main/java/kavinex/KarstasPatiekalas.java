package kavinex;

public class KarstasPatiekalas extends RuosiamasPatiekalas {

	public int trukme_kaitinimo;
	
	public KarstasPatiekalas() {
		
	}
	
	public KarstasPatiekalas (String pavadinimas, int trukme_ruosimo, int trukme_kaitinimo ) {
		
		super ( pavadinimas, trukme_ruosimo );
		this.trukme_kaitinimo = trukme_kaitinimo;
		bus_patiektas_apytiksliai_uz += trukme_kaitinimo;
	}
}
