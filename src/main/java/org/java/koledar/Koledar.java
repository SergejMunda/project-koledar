package org.java.koledar;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;

public class Koledar extends GridPane{
	private final String[] meseci = {"Januar","Februar","Marec","April","Maj",
			"Junij","Julij","Avgust","September","Oktober",
			"November","December"};
	private final String[] dnevi = {"Ponedeljek","Torek","Sreda","Četrtek","Petek","Sobota","Nedelja"};
	private LocalDate datum;
	private int mesec;
	private int leto;
	private Label[] dneviLabels = new Label[42];
	
	public Koledar() {
		this.setPadding(new Insets(5, 5, 10, 10));
		this.setVgap(10);
		this.setHgap(15);
		this.setAlignment(Pos.CENTER);
		
    	
		for(int i=0; i<7; i++) {
			Label danImeLabel = new Label();
			danImeLabel.setText(dnevi[i]);
			this.add(danImeLabel, i, 0);
		}
		
		int vrstica = 1;
		int stolpec = 0;
		for (int i = 0; i < dneviLabels.length; i++) {
			dneviLabels[i] = new Label();
			this.add(dneviLabels[i], stolpec, vrstica);
			stolpec++;
			if(stolpec == 7) {
				stolpec = 0;
				vrstica++;
			}
		}
		 
		datum = LocalDate.now();
		System.out.println(datum.getDayOfMonth()+" "+datum.getMonth().getValue()+" "+datum.getYear()+" "+datum.getDayOfWeek().getValue());
		datum = datum.minusDays(datum.getDayOfMonth()-1);
		System.out.println(datum.getDayOfMonth()+" "+datum.getMonth().getValue()+" "+datum.getYear()+" "+datum.getDayOfWeek().getValue());
		//datum = datum.plusMonths(2);
		
		mesec = datum.getMonthValue();
		leto = datum.getYear();
		System.out.println(getMesec()+" "+getLeto());
		
		prikaziDneve();
		
		
	}
	
	public void posodobiDatum(int mesec, int leto) {
		datum = datum.withYear(leto);
		datum = datum.withMonth(mesec);
		datum = datum.withDayOfMonth(1);
	}
	
	public void prikaziDneve() {
		for (int i = 0; i < dneviLabels.length; i++) {
			dneviLabels[i].setText("");
		}
		
		for(LocalDate tempDatum=datum; tempDatum.isBefore(datum.plusMonths(1)); tempDatum = tempDatum.plusDays(1)) {
			//System.out.println(tempDatum.toString());
			int prviDan = datum.getDayOfWeek().getValue()-1;
			int danVTednu = tempDatum.getDayOfWeek().getValue();
			dneviLabels[prviDan+tempDatum.getDayOfMonth()-1].setText(Integer.toString(tempDatum.getDayOfMonth()));
			if(danVTednu >= 7) {
				dneviLabels[prviDan+tempDatum.getDayOfMonth()-1].setTextFill(Color.RED);
			}
		}
		
	}

	public int getMesec() {
		return mesec;
	}

	public void setMesec(int mesec) {
		this.mesec = mesec;
		posodobiDatum(mesec, getLeto());
		prikaziDneve();
	}

	public int getLeto() {
		return leto;
	}

	public void setLeto(int leto) {
		this.leto = leto;
	}
	
}
