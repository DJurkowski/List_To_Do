package Scene;

import Database.MainDatabase;
import Database.Task;
import Database.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddTask {
		
	public static boolean answer;
	
	public static boolean display(User user){
		Stage stage = new Stage();
		
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setMinHeight(300);
		stage.setMinWidth(300);
		stage.setTitle("Add Task");
		
		Button addButton;
		Button backButton;
		
		TextField textInput = new TextField();
		
		addButton = new Button("Add");
		addButton.setOnAction(e ->{
			
			answer = true;
			
			Task task = new Task();
			task.setName(textInput.getText());
			
			MainDatabase.add(task);
			MainDatabase.begin();
			user.getTasksList().add(task);
			MainDatabase.commit();
			
			stage.close();
		});
		
		backButton = new Button("Back");
		backButton.setOnAction(e ->{
			answer = false;
			stage.close();
		});
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(2,2,2,2));
		grid.setVgap(10);
		grid.setHgap(10);
		
		grid.setConstraints(textInput,1,0);
		grid.setConstraints(addButton, 0, 1);
		grid.setConstraints(backButton, 1, 1);
		grid.getChildren().addAll(textInput,addButton,backButton);
		grid.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(grid);
		stage.setScene(scene);
		stage.showAndWait();
		
		return answer;
		
	}
}
