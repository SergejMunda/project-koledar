package org.java.koledar;

import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application
{
    
	
	public static void main( String[] args )
    {
    	launch(args);
    }
    
	//deklaracija vnosnih polj
	final Label labelMesec = new Label();
	final ComboBox<String> inputMesec = new ComboBox<String>();
	final Label labelLeto = new Label();
	final TextField inputLeto = new TextField();
	final Label labelLetoInfo = new Label();
	final Label labelDatum = new Label();
	final TextField inputDatum = new TextField();
	final Label labelDatumInfo = new Label();
	final Button potrdiLeto = new Button();
	final Button potrdiDatum = new Button();
	final String[] meseci = {"Januar","Februar","Marec","April","Maj",
					"Junij","Julij","Avgust","September","Oktober",
					"November","December"};
	
	
    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	//konfiguracija elementov
    	inputMesec.getItems().addAll(meseci);
    	
    	labelMesec.setText("Mesec:");
    	labelLeto.setText("Leto:");
    	labelDatum.setText("Datum:");
    	
    	potrdiLeto.setText("Potrdi Leto");
    	potrdiDatum.setText("Potrdi Datum");
    	
    	//konfiguracija postavitve elementov
    	VBox inputLayoutLeft = new VBox(5);
    	inputLayoutLeft.setPadding(new Insets(10));
    	inputLayoutLeft.getChildren().addAll(labelMesec,inputMesec,labelLeto,inputLeto, potrdiLeto,labelLetoInfo);
    	inputLayoutLeft.setAlignment(Pos.CENTER);
    	
    	VBox inputLayoutRight = new VBox(5);
    	inputLayoutRight.setPadding(new Insets(10));
    	inputLayoutRight.getChildren().addAll(labelDatum,inputDatum,potrdiDatum,labelDatumInfo);
    	inputLayoutRight.setAlignment(Pos.CENTER);
    	
    	Koledar calendarLayout = new Koledar();
    	
    	inputMesec.setValue(meseci[calendarLayout.getMesec()-1]);
    	inputLeto.setPrefWidth(60);
    	inputLeto.setText(Integer.toString(calendarLayout.getLeto()));
    	inputDatum.setPrefWidth(100);
    	
    	inputMesec.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			String imeMeseca = inputMesec.getValue();
    			System.out.println(inputMesec.getValue());
    			int mesec = 0;
    			for (int i = 0; i < meseci.length; i++) {
					if (imeMeseca.equals(meseci[i])) {
						mesec = i;
						break;
					}
					
				}
    			System.out.println(mesec);
    			calendarLayout.setMesec(mesec+1);
    			
    		}
		});
    	
    	potrdiLeto.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			
    			Boolean jeLeto = Pattern.matches("^(19[0-9][0-9]|2[0-1][0-9][0-9]|2200)$", inputLeto.getText());
    			System.out.println(jeLeto);
    			
    			if(jeLeto) {
    				int leto=Integer.parseInt(inputLeto.getText());
    				System.out.println(leto);
    				calendarLayout.setLeto(leto);
    				labelLetoInfo.setTextFill(Color.SEAGREEN);
    				labelLetoInfo.setText("Datum posodobljen");
    				
    			}else {
    				labelLetoInfo.setTextFill(Color.RED);
    				labelLetoInfo.setText("Vnesite leto v\nrazponu 1900-2200!");
				}
    			
    			
    			
    		}
		});
    	
    	potrdiDatum.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			String datum = inputDatum.getText();
    			System.out.println(datum);
    			if(calendarLayout.setDatum(datum)) {
    				calendarLayout.setDatum(datum);
    				labelDatumInfo.setTextFill(Color.SEAGREEN);
    				labelDatumInfo.setText("Datum posodobljen");
    			}else {
    				labelDatumInfo.setTextFill(Color.RED);
    				labelDatumInfo.setText("Uporabite format\ndd.mm.yyyy");
    			}
    			
    		}
		});
    	
    	BorderPane baseLayout = new BorderPane();
    	baseLayout.setLeft(inputLayoutLeft);
    	baseLayout.setRight(inputLayoutRight);
    	baseLayout.setCenter(calendarLayout);
    	
    	Scene scene = new Scene(baseLayout, 800, 300);
    	//konfiguracija imena okna aplikacije
    	primaryStage.setTitle("Koledar");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    	
    }
}
