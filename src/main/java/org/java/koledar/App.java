package org.java.koledar;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
	final String[] meseci = {"Januar","Februar","Marec","April","Maj",
					"Junij","Julij","Avgust","September","Oktober",
					"November","December"};
	
	
    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	//konfiguracija elementov
    	inputMesec.getItems().addAll(meseci);
    	
    	labelMesec.setText("Mesec: ");
    	labelLeto.setText("Leto: ");
    	
    	//konfiguracija postavitve elementov
    	HBox inputLayout = new HBox(5);
    	inputLayout.setPadding(new Insets(10));
    	inputLayout.getChildren().addAll(labelMesec,inputMesec,labelLeto,inputLeto);
    	inputLayout.setAlignment(Pos.TOP_CENTER);
    	
    	Koledar calendarLayout = new Koledar();
    	
    	inputMesec.setValue(meseci[calendarLayout.getMesec()-1]);
    	inputLeto.setPrefWidth(60);
    	inputLeto.setText(Integer.toString(calendarLayout.getLeto()));
    	
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
    	
    	BorderPane baseLayout = new BorderPane();
    	baseLayout.setTop(inputLayout);
    	baseLayout.setCenter(calendarLayout);
    	
    	Scene scene = new Scene(baseLayout, 440, 230);
    	//konfiguracija imena okna aplikacije
    	primaryStage.setTitle("Koledar");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    	
    }
}
