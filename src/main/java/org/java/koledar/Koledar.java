package org.java.koledar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Koledar extends GridPane{
	private final String[] meseci = {"Januar","Februar","Marec","April","Maj",
			"Junij","Julij","Avgust","September","Oktober",
			"November","December"};
	private final String[] dnevi = {"Ponedeljek","Torek","Sreda","Četrtek","Petek","Sobota","Nedelja"};
	private LocalDate datum;
	private int mesec;
	private int leto;
	
	private Label[] dneviLabels;
	
	private Bralec bralec;
	private ArrayList<Praznik> prazniki;
	
	public Koledar() {
		
		//konfiguracija razreda Koledar
		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(10);
		this.setHgap(15);
		this.setAlignment(Pos.CENTER);
    	
		//Izpis imen dnevov
		for(int i=0; i<7; i++) {
			Label danImeLabel = new Label();
			danImeLabel.setText(dnevi[i]);
			danImeLabel.setFont(Font.font(20));
			if (i == 6) {
				danImeLabel.setTextFill(Color.RED);
			}
			this.add(danImeLabel, i, 0);
		}
		
		//definicija polj za dneve
		dneviLabels = new Label[42];
		
		//postavitev polj za dneve v vrstice in stolpce
		int vrstica = 1;
		int stolpec = 0;
		for (int i = 0; i < dneviLabels.length; i++) {
			dneviLabels[i] = new Label();
			dneviLabels[i].setFont(Font.font(20));
			this.add(dneviLabels[i], stolpec, vrstica);
			stolpec++;
			if(stolpec == 7) {
				stolpec = 0;
				vrstica++;
			}
		}
		
		//pridobi danasnji datum
		datum = LocalDate.now();
		//premik na prvi dan v mescu
		datum = datum.minusDays(datum.getDayOfMonth()-1);
		
		//dodeljevanje vrednosti atributov mesec in leto
		mesec = datum.getMonthValue();
		leto = datum.getYear();
		
		//branje podatkov o praznikih iz datoteke
		bralec = new Bralec();
		prazniki = bralec.pridobiPraznike();
		
		prikaziDneve();
		
		
	}
	
	public void posodobiDatum(int mesec, int leto) {
		datum = datum.withYear(leto);
		datum = datum.withMonth(mesec);
		datum = datum.withDayOfMonth(1);
	}
	
	public void prikaziDneve() {
		
		//brisanje prejsnjih vrednosti
		for (int i = 0; i < dneviLabels.length; i++) {
			dneviLabels[i].setTextFill(Color.BLACK);
			dneviLabels[i].setText("");
		}
		
		//izpis novih vrednosti
		for(LocalDate tempDatum=datum; tempDatum.isBefore(datum.plusMonths(1)); tempDatum = tempDatum.plusDays(1)) {
			int prviDan = datum.getDayOfWeek().getValue()-1;
			int danVTednu = tempDatum.getDayOfWeek().getValue();
			dneviLabels[prviDan+tempDatum.getDayOfMonth()-1].setText(Integer.toString(tempDatum.getDayOfMonth()));
			//barvanje nedelj
			if(danVTednu >= 7) {
				dneviLabels[prviDan+tempDatum.getDayOfMonth()-1].setTextFill(Color.RED);
			}
		}
		
		//barvanje praznikov
		prazniki.forEach( (praznik) -> {
			int prviDan = datum.getDayOfWeek().getValue()-1;
			if (mesec == praznik.getMesec()) {
				if (praznik.getPonovljiv()) {
					dneviLabels[prviDan+praznik.getDan()-1].setTextFill(Color.LIMEGREEN);
				}else if(!praznik.getPonovljiv() && leto==praznik.getLeto()) {
					dneviLabels[prviDan+praznik.getDan()-1].setTextFill(Color.LIMEGREEN);
				}
			}
		});
		
	}

	public int getMesec() {
		return mesec;
	}

	public void setMesec(int mesec) {
		this.mesec = mesec;
		posodobiDatum(mesec, leto);
		prikaziDneve();
	}

	public int getLeto() {
		return leto;
	}

	public void setLeto(int leto) {
		this.leto = leto;
		posodobiDatum(mesec, leto);
		prikaziDneve();
	}

	public String[] getMeseci() {
		return meseci;
	}
	
	
	public boolean setDatum(String datumString) {
		//preverjanje ustreznosti vnosa v polje
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.M.uuuu");
		try {
			LocalDate datum = LocalDate.parse(datumString, dateFormatter);
			this.mesec = datum.getMonthValue();
			if (datum.getYear() >= 1900 && datum.getYear()<=2200) {
				this.leto = datum.getYear();
				posodobiDatum(datum.getMonthValue(), datum.getYear());
				prikaziDneve();
				return true;
			} else {
				throw new DateTimeParseException("Nepravilen datum!",datumString, 0);
			}
		} catch (DateTimeParseException e) {
			return false;
		}
		
	}
	
}
