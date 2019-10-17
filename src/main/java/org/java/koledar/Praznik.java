package org.java.koledar;

import java.time.LocalDate;

public class Praznik {
	private int dan;
	private int mesec;
	private Boolean ponovljiv;
	
	public Praznik(int dan, int mesec, boolean ponovljiv) {
		this.dan = dan;
		this.mesec = mesec;
		this.ponovljiv = ponovljiv;
	}
	
	public Boolean getPonovljiv() {
		return ponovljiv;
	}
	public void setPonovljiv(Boolean ponovljiv) {
		this.ponovljiv = ponovljiv;
	}
	public int getDan() {
		return dan;
	}
	public void setDan(int dan) {
		this.dan = dan;
	}
	public int getMesec() {
		return mesec;
	}
	public void setMesec(int mesec) {
		this.mesec = mesec;
	}
}
