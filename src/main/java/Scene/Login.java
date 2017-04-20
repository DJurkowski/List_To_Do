package Scene;

import Database.MainDatabase;
import Database.User;
import Main.Main;
import Safety.Alert;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Login {

	private Stage stage;
	private Main main;
	private double width;
	private double height;

	private GridPane grid;
	private Scene scene;

	private Label nameLabel;
	private Label passLabel;

	private TextField nameInput;
	private PasswordField passInput;
	
	private Button loginButton;
	private Button signButton;
	
	private User user;

	public Login(Stage stage, Main main, double width, double height) {

		this.stage = stage;
		this.main = main;
		this.width = width;
		this.height = height;
		
		initLabels();
		initTextField();
		initButtons();
		initLayouts();
		initScene();
		
		stage.setScene(scene);
		stage.show();
	}

	private void initLayouts() {
		grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton, signButton);
		
	}

	private void initLabels() {
		
		nameLabel = new Label("Username");
		GridPane.setConstraints(nameLabel, 0, 0);
		
		passLabel = new Label("Password");
		GridPane.setConstraints(passLabel, 0, 1);
	}

	private void initTextField() {
		
		nameInput = new TextField();
		nameInput.setPromptText("Username");
		GridPane.setConstraints(nameInput, 1, 0);
		
		passInput = new PasswordField();
		passInput.setPromptText("Password");
		GridPane.setConstraints(passInput, 1, 1);

	}

	private void initButtons() {
		
		loginButton = new Button("Log In");
		GridPane.setConstraints(loginButton, 1, 2);
		loginButton.setOnAction(e ->{
			user = MainDatabase.findUser(nameInput.getText(), passInput.getText());
			if(checkUser() == false){
				
				Alert.display("Error", "Not found user");
			}else {
			new List(stage, main, width, height, user);
			}
		});
		
		signButton = new Button("Sign Up");
		GridPane.setConstraints(signButton, 1, 3);
		signButton.setOnAction(e ->{
			new Registration(stage, main, width, height);
			
		});
	}
	
	private boolean checkUser(){
		return MainDatabase.find(nameInput.getText(), passInput.getText());
	}

	private void initScene() {
		
		scene = new Scene(grid, width, height);
	}

	public Scene getScene() {
		return scene;
	}
}
