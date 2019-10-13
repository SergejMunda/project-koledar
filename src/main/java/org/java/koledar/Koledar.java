package org.java.koledar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Koledar extends GridPane{
	private final String[] meseci = {"Januar","Februar","Marec","April","Maj",
			"Junij","Julij","Avgust","September","Oktober",
			"November","December"};
	
	private final String[] dnevi = {"Poedeljek","Torek","Sreda","Četrtek","Petek","Sobota","Nedelja"};
	
	public Koledar() {
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setVgap(5);
		this.setHgap(5);
		this.setAlignment(Pos.CENTER);
		
		Label datum1 = new Label("0,0");
    	setConstraints(datum1, 0, 1);
    	Label datum2 = new Label("0,1");
    	setConstraints(datum2, 0, 1);
    	Label datum3 = new Label("1,0");
    	setConstraints(datum3, 1, 0);
    	Label datum4 = new Label("1,1");
    	setConstraints(datum4, 1, 1);
		
    	this.getChildren().add(datum1);
    	
		for(int i=0; i<7; i++) {
			Label danLabel = new Label();
			danLabel.setText(dnevi[i]);
			this.add(danLabel, i, 0);
		}
	}
	
}
