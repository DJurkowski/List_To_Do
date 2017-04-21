package Scene;

import Database.MainDatabase;
import Database.Task;
import Database.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
*
* @author Dominik Jurkowski <jurkowski.domink.andrzej@gmail.com>
*
*/
public class AddTask {
		
	
	/**
	 * The method add new task to database
	 * @param user The user who are sign in
	 * @return Retrun new task which can be use in table view
	 */
	public static Task display(User user){
		Stage stage = new Stage();
		Task task = new Task(); ;
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setMinHeight(300);
		stage.setMinWidth(300);
		stage.setTitle("Add Task");
		
		Button addButton;
		Button backButton;
		
		TextField textInput = new TextField();
		DatePicker datePicker = new DatePicker(); 
		
		
		addButton = new Button("Add");
		addButton.setOnAction(e ->{
			
			
			task.setName(textInput.getText());
			task.setDate(datePicker.getValue());
			
			MainDatabase.add(task);
			MainDatabase.begin();
			user.getTasksList().add(task);
			MainDatabase.commit();
			
			
			stage.close();
		});
		
		backButton = new Button("Back");
		backButton.setOnAction(e ->{
			
			stage.close();
		});
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(2,2,2,2));
		grid.setVgap(10);
		grid.setHgap(10);
		
		grid.setConstraints(textInput,1,0);
		grid.setConstraints(datePicker, 1, 1);
		grid.setConstraints(addButton, 0, 2);
		grid.setConstraints(backButton, 1, 2);
		grid.getChildren().addAll(textInput,datePicker,addButton,backButton);
		grid.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(grid);
		stage.setScene(scene);
		stage.showAndWait();
		
		return task;
		
	}
}
