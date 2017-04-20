package Scene;

import Database.MainDatabase;
import Database.User;
import Main.Main;
import Safety.Alert;
import Safety.Validation;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Registration {

	private Stage stage;
	private Main main;
	private double width;
	private double height;

	private GridPane grid;
	private Scene scene;

	private Label nameLabel;
	private Label nickLabel;
	private Label emailLabel;
	private Label passLabel;
	private Label confimPassLabel;

	private TextField nameInput;
	private TextField nickInput;
	private TextField emailInput;
	private PasswordField passInput;
	private PasswordField confirmPassInput;

	private Button createButton;
	private Button backButton;
	
	User user;

	public Registration(Stage stage, Main main, double width, double height) {
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
		grid.getChildren().addAll(nameLabel, nameInput, nickLabel, nickInput, emailLabel, emailInput, passLabel,
				passInput, confimPassLabel, confirmPassInput, createButton, backButton);

	}

	private void initLabels() {

		nameLabel = new Label("First Name");
		GridPane.setConstraints(nameLabel, 0, 0);

		nickLabel = new Label("Username");
		GridPane.setConstraints(nickLabel, 0, 1);

		emailLabel = new Label("Emali");
		GridPane.setConstraints(emailLabel, 0, 2);

		passLabel = new Label("Password");
		GridPane.setConstraints(passLabel, 0, 3);

		confimPassLabel = new Label("Confim Password");
		GridPane.setConstraints(confimPassLabel, 0, 4);
	}

	private void initTextField() {

		nameInput = new TextField();
		nameInput.setPromptText("First Name");
		GridPane.setConstraints(nameInput, 1, 0);

		nickInput = new TextField();
		nickInput.setPromptText("Nickname");
		GridPane.setConstraints(nickInput, 1, 1);

		emailInput = new TextField();
		emailInput.setPromptText("Email");
		GridPane.setConstraints(emailInput, 1, 2);

		passInput = new PasswordField();
		passInput.setPromptText("Password");
		GridPane.setConstraints(passInput, 1, 3);

		confirmPassInput = new PasswordField();
		confirmPassInput.setPromptText("Confrim Password");
		GridPane.setConstraints(confirmPassInput, 1, 4);

	}

	private void initButtons() {

		createButton = new Button("Create");
		GridPane.setConstraints(createButton, 1, 5);
		createButton.setOnAction(e -> {
			if(validateDate(nameInput, emailInput, passInput, confirmPassInput) == true){
				
				//createUser();
				Alert.display("Account", "Your account was created successfully");
				stage.setScene(main.getLoginScene().getScene());
			}else{
				Alert.display("Error", "User registration failed");
			}
		});

		backButton = new Button("Back");
		GridPane.setConstraints(backButton, 1, 6);
		backButton.setOnAction(e -> {
			stage.setScene(main.getLoginScene().getScene());
		});

	}

	private void initScene() {

		scene = new Scene(grid, width, height);
	}

	private static boolean validateDate(TextField nameInput, TextField emailInput, PasswordField passInput, PasswordField confirmPassInput) {
		boolean error = false;

		if (Validation.nameValidation(nameInput.getText()) && Validation.emailValidation(emailInput.getText())
				&& Validation.passwordValidation(passInput.getText())) {
			error = true;
		}
		colorTextField(nameInput, emailInput, passInput, confirmPassInput);
		return error;
	}

	private static void colorTextField(TextField nameInput, TextField emailInput, PasswordField passInput, PasswordField confirmPassInput) {
		
		if (Validation.nameValidation(nameInput.getText()) == false) {
			nameInput.setStyle("-fx-background-color: #ed7765;");
		} else {
			nameInput.setStyle("-fx-background-color: #83ed65;");
		}
		
		if (Validation.emailValidation(emailInput.getText()) == false){
			emailInput.setStyle("-fx-background-color: #ed7765;");
		} else {
			emailInput.setStyle("-fx-background-color: #83ed65;");
		}
		
		if (Validation.passwordValidation(passInput.getText()) == true && passInput.getText().equals(confirmPassInput.getText()) == true){
			passInput.setStyle("-fx-background-color: #83ed65;");
		} else {
			passInput.setStyle("-fx-background-color: #ed7765;");
		}
	}
	
	private void createUser(){
		
		user = new User();
		user.setName(nameInput.getText());
		user.setNick(nickInput.getText());
		user.setEmail(emailInput.getText());
		user.setPassword(passInput.getText());
		
		MainDatabase.add(user);
	}

}
