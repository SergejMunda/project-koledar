package org.java.koledar;

import java.time.LocalDate;

public class Praznik {
	private int dan;
	private int mesec;
	private int leto;
	private Boolean ponovljiv;
	
	//konstruktor za ponovljive praznike
	public Praznik(int dan, int mesec, boolean ponovljiv) {
		this.dan = dan;
		this.mesec = mesec;
		this.ponovljiv = ponovljiv;
	}
	
	//konstruktor za neponovljive praznike
	public Praznik(int dan, int mesec,int leto, boolean ponovljiv) {
		this.dan = dan;
		this.mesec = mesec;
		this.leto = leto;
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

	public int getLeto() {
		return leto;
	}

	public void setLeto(int leto) {
		this.leto = leto;
	}
}
