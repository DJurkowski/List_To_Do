package Scene;

import Database.User;
import Main.Main;
import javafx.stage.Stage;

public class List {

	private Stage stage;
	private Main main;
	private double width;
	private double height;
	private User user;

	List(Stage stage, Main main, double width, double height, User user) {
		
		this.stage = stage;
		this.main = main;
		this.width = width;
		this.height = height;
		this.user = user;
	}

}
