package org.java.koledar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Bralec {
	//bralec txt datoteke
	BufferedReader reader;
	
	//seznam praznikov
	ArrayList<Praznik> prazniki;
	
	public Bralec() {}
	
	public ArrayList<Praznik> pridobiPraznike(){
		//poskus dostopa do datoteke
		try {
			reader = new BufferedReader(new FileReader("src/main/resources/prazniki.txt"));
			prazniki = new ArrayList<Praznik>();
			for (String vrstica = reader.readLine(); vrstica != null; vrstica = reader.readLine()) {
				//locevanje vrstice s piko
				String[] poDelih = vrstica.split("\\.");
				Praznik praznik;
				boolean ponovljiv = false;
				if (poDelih[poDelih.length-1].equals("pon")) {
					ponovljiv = true;
					praznik = new Praznik(Integer.parseInt(poDelih[0]),Integer.parseInt(poDelih[1]),ponovljiv);
				}
				else {
					praznik = new Praznik(Integer.parseInt(poDelih[0]),Integer.parseInt(poDelih[1]),Integer.parseInt(poDelih[2]),ponovljiv);
				}
				prazniki.add(praznik);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prazniki;
	}
}
