package Safety;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
*
* @author Dominik Jurkowski <jurkowski.domink.andrzej@gmail.com>
*
*/

public class Alert {

	public static void display(String title, String massage) {
		Stage stage = new Stage();

		stage.initModality(Modality.APPLICATION_MODAL);
		
		stage.setTitle(title);
		stage.setMinWidth(250);
		stage.setMinHeight(100);

		Label label = new Label();
		label.setText(massage);
		Button closeButton = new Button("Close the window");
		closeButton.setOnAction(e -> stage.close());

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.showAndWait();
	}

}
