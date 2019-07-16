package kavinex;

public class Uzsakymas {
	
	private int id;
	private String pav;
	private int trukme_ruosimo;
	private int trukme_kaitinimo;

	public void setId( int id ) {
		
		this.id = id;
	}
	
	public int getId () {
		
		return this.id;
	}
	
	public void setPav( String pav ) {
		this.pav = pav;
	}
	
	public String getPav () {
		
		return this.pav;
	}
	
	public void setTrukme_ruosimo( int trukme_ruosimo ) {
		
		this.trukme_ruosimo = trukme_ruosimo;
	}
	
	public int getTrukme_ruosimo () {
		
		return this.trukme_ruosimo;
	}
	
	public void setTrukme_kaitinimo( int trukme_kaitinimo ) {
		
		this.trukme_kaitinimo = trukme_kaitinimo;
	}
	
	public int getTrukme_kaitinimo () {
		
		return this.trukme_kaitinimo;
	}
}
