package org.java.koledar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Bralec {
	BufferedReader reader;
	ArrayList<Praznik> prazniki;
	
	public Bralec() {
		
	}
	
	public ArrayList<Praznik> pridobiPraznike(){
		try {
			reader = new BufferedReader(new FileReader("src/main/resources/prazniki.txt"));
			prazniki = new ArrayList<Praznik>();
			for (String vrstica = reader.readLine(); vrstica != null; vrstica = reader.readLine()) {
				String[] poDelih = vrstica.split("\\.");
				//System.out.println(poDelih[0]+" "+poDelih[1]+" "+poDelih[2]);
				boolean ponovljiv = false;
				if (poDelih[2].equals("pon")) {
					ponovljiv = true;
				}
				Praznik praznik = new Praznik(Integer.parseInt(poDelih[0]),Integer.parseInt(poDelih[1]),ponovljiv);
				prazniki.add(praznik);
				//System.out.println(prazniki.get(0).getDan()+" "+prazniki.get(0).getMesec());
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prazniki;
	}
}
