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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {
	
	public static void main(String[] args) {
		filePath = args[0];
		launch(args);
	}
	
	//opis poti do datoteke
	public static String filePath;

	// deklaracija vnosnih polj
	Label labelMesec;
	ComboBox<String> inputMesec;

	Label labelLeto;
	TextField inputLeto;
	Label labelLetoInfo;

	Label labelDatum;
	TextField inputDatum;
	Label labelDatumInfo;

	Button potrdiLeto;
	Button potrdiDatum;

	Label labelTrenutnoPrikazano;

	@Override
	public void start(Stage okno) throws Exception {

		// konstrukcija razreda Koledar
		Koledar calendarLayout = new Koledar();

		// definicija in konfiguracija elementa labelMesec
		labelMesec = new Label();
		labelMesec.setText("Mesec:");

		// definicija in konfiguracija elementa inputMesec
		inputMesec = new ComboBox<String>();
		inputMesec.getItems().addAll(calendarLayout.getMeseci());
		inputMesec.setValue(calendarLayout.getMeseci()[calendarLayout.getMesec() - 1]);
		inputMesec.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String imeMeseca = inputMesec.getValue();
				int mesec = 0;
				for (int i = 0; i < calendarLayout.getMeseci().length; i++) {
					if (imeMeseca.equals(calendarLayout.getMeseci()[i])) {
						mesec = i;
						break;
					}

				}
				calendarLayout.setMesec(mesec + 1);
				prikaziTrenutniDatum(calendarLayout);

			}
		});

		// definicija in konfiguracija elementa labelLeto
		labelLeto = new Label();
		labelLeto.setText("Leto:");

		// definicija in konfiguracija elementa inputLeto
		inputLeto = new TextField();
		inputLeto.setPrefWidth(60);
		inputLeto.setText(Integer.toString(calendarLayout.getLeto()));

		// definicija in konfiguracija elementa labelLetoInfo
		labelLetoInfo = new Label();

		// definicija in konfiguracija elementa labelDatum
		labelDatum = new Label();
		labelDatum.setText("Datum:");

		// definicija in konfiguracija elementa inputDatum
		inputDatum = new TextField();
		inputDatum.setPrefWidth(100);

		// definicija in konfiguracija elementa labelDatumInfo
		labelDatumInfo = new Label();

		// definicija in konfiguracija elementa potrdiLeto
		potrdiLeto = new Button();
		potrdiLeto.setText("Potrdi Leto");
		potrdiLeto.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Boolean jeLeto = Pattern.matches("^(19[0-9][0-9]|2[0-1][0-9][0-9]|2200)$", inputLeto.getText());
				if (jeLeto) {
					int leto = Integer.parseInt(inputLeto.getText());
					calendarLayout.setLeto(leto);
					labelLetoInfo.setTextFill(Color.SEAGREEN);
					labelLetoInfo.setText("Datum posodobljen");
					prikaziTrenutniDatum(calendarLayout);

				} else {
					labelLetoInfo.setTextFill(Color.RED);
					labelLetoInfo.setText("Vnesite leto v\nrazponu 1900-2200!");
				}

			}
		});

		// definicija in konfiguracija elementa potrdiDatum
		potrdiDatum = new Button();
		potrdiDatum.setText("Potrdi Datum");
		potrdiDatum.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String datum = inputDatum.getText();
				if (calendarLayout.setDatum(datum)) {
					labelDatumInfo.setTextFill(Color.SEAGREEN);
					labelDatumInfo.setText("Datum posodobljen");
					prikaziTrenutniDatum(calendarLayout);
				} else {
					labelDatumInfo.setTextFill(Color.RED);
					labelDatumInfo.setText("Uporabite format\ndd.mm.yyyy!\n\nVnesite leto v\nrazponu 1900-2200!");
				}

			}
		});

		// definicija in konfiguracija elementa labelTrenutnoPrikazano
		labelTrenutnoPrikazano = new Label();
		labelTrenutnoPrikazano.setFont(Font.font(20));
		prikaziTrenutniDatum(calendarLayout);

		// konfiguracija postavitve elementov na levi strani okna
		VBox inputLayoutLeft = new VBox(5);
		inputLayoutLeft.setPadding(new Insets(10));
		inputLayoutLeft.getChildren().addAll(labelMesec, inputMesec, labelLeto, inputLeto, potrdiLeto, labelLetoInfo);
		inputLayoutLeft.setAlignment(Pos.CENTER);

		// konfiguracija postavitve elementov na desni strani okna
		VBox inputLayoutRight = new VBox(5);
		inputLayoutRight.setPadding(new Insets(10));
		inputLayoutRight.getChildren().addAll(labelDatum, inputDatum, potrdiDatum, labelDatumInfo);
		inputLayoutRight.setAlignment(Pos.CENTER);

		// konfiguracija postavitve elementov na spodnji strani okna
		HBox infoLayoutBottom = new HBox(2);
		infoLayoutBottom.setPadding(new Insets(0, 0, 10, 0));
		infoLayoutBottom.getChildren().add(labelTrenutnoPrikazano);
		infoLayoutBottom.setAlignment(Pos.TOP_CENTER);

		// konfiguracija zdruzitve locenih pogledov v celoto
		BorderPane baseLayout = new BorderPane();
		baseLayout.setLeft(inputLayoutLeft);
		baseLayout.setRight(inputLayoutRight);
		baseLayout.setCenter(calendarLayout);
		baseLayout.setBottom(infoLayoutBottom);

		// dodajanje celotne postavitve v okno
		Scene scene = new Scene(baseLayout, 800, 330);

		// konfiguracija okna aplikacije
		okno.setTitle("Koledar");
		okno.setResizable(false);
		okno.setScene(scene);
		okno.show();
	}

	// posodabljanje napisa meseca in leta
	public void prikaziTrenutniDatum(Koledar calendarLayout) {
		labelTrenutnoPrikazano
				.setText(calendarLayout.getMeseci()[calendarLayout.getMesec() - 1] + ", " + calendarLayout.getLeto());
	}
}
