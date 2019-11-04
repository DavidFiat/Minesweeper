package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.*;

public class WindowController implements Initializable {

	@FXML
	private AnchorPane a;
	@FXML
	private Button pista;
	@FXML
	private Button resolver;

	private Minesweeper minesweeper;

	int regulador = 0;

	public WindowController() {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO
	}


	public void beginner(ActionEvent event) {
		a.getChildren().clear();
		GridPane root1 = new GridPane();
		for (int i3 = 0; i3 < Minesweeper.FILAS_PRINCIPIANTE; i3++) {
			final int i3Copy = i3;
			for (int i4 = 0; i4 < Minesweeper.COLUMNAS_PRINCIPIANTE; i4++) {
				final int i4Copy = i4;
				Button t = new Button();
				t.setText("" + (i3 + 1) + "-" + (i4 + 1));
				root1.add(t, i3, i4);
				resolver.setOnAction(resol -> {
					for (int i = 0; i < Minesweeper.FILAS_PRINCIPIANTE; i++) {
						for (int i2 = 0; i2 < Minesweeper.COLUMNAS_PRINCIPIANTE; i2++) {
							t.setText("" + minesweeper.getSquares()[i][i2].darValor());
						}
					}

				});
				t.setOnAction(e -> {
					minesweeper.abrirCasilla(i3Copy, i4Copy);
					if (minesweeper.darPerdio()) {
						t.setText("*");
						root1.getChildren().clear();
						Label p = new Label();
						p.setText("Ha perdido el juego");
						root1.add(p, 1, 2);

					} else {
						t.setText("" + minesweeper.cantidadMinasAlrededor(i3Copy, i4Copy));
						if (minesweeper.gano() && regulador == 0) {
							root1.getChildren().clear();
							Label p = new Label();
							p.setText("Ha ganado el juego");
							root1.add(p, 1, 2);
						}

					}

				});

			}
		}
		a.getChildren().add(root1);
		pista.setVisible(true);
		resolver.setVisible(true);
		minesweeper = new Minesweeper(1);

	}

	public void intermediate(ActionEvent event) {
		GridPane root1 = new GridPane();
		for (int i3 = 0; i3 < Minesweeper.FILAS_INTERMEDIO; i3++) {
			for (int i4 = 0; i4 < Minesweeper.COLUMNAS_INTERMEDIO; i4++) {
				Button t = new Button();
				t.setText("" + (i3 + 1) + "-" + (i4 + 1));
				root1.add(t, i3, i4);
			}
		}
		a.getChildren().add(root1);
		minesweeper = new Minesweeper(2);

	}

	public void expert(ActionEvent event) {
		GridPane root1 = new GridPane();
		for (int i3 = 0; i3 < Minesweeper.COLUMNAS_EXPERTO; i3++) {
			for (int i4 = 0; i4 < Minesweeper.FILAS_EXPERTO; i4++) {
				Button t = new Button();
				t.setText("" + (i3 + 1) + "-" + (i4 + 1));
				root1.add(t, i3, i4);

			}
		}
		a.getChildren().add(root1);
		minesweeper = new Minesweeper(3);

	}

}
