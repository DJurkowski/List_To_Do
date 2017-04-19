package Safety;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * @author Dominik Jurkowski <jurkowski.domink.andrzej@gmail.com>
 *
 */
public class Asking {

	public static boolean answer;

	public static boolean display(String title, String message) {

		Stage stage = new Stage();

		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setMinWidth(250);
		stage.setMinHeight(250);

		Label label = new Label();
		label.setText(message);

		Button yesButton = new Button("YES");
		Button noButton = new Button("NO");

		yesButton.setOnAction(e -> {
			answer = true;

			stage.close();
		});

		noButton.setOnAction(e -> {
			answer = false;

			stage.close();
		});

		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(2, 2, 2, 2));
		gridPane.setVgap(5);
		gridPane.setHgap(5);
		gridPane.setConstraints(label, 0, 0);
		gridPane.setConstraints(yesButton, 0, 1);
		gridPane.setConstraints(noButton, 1, 1);
		gridPane.getChildren().addAll(label, yesButton, noButton);
		gridPane.setAlignment(Pos.CENTER);

		Scene scene = new Scene(gridPane);
		stage.setScene(scene);
		stage.showAndWait();

		return answer;

	}
}
