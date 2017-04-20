package Main;

import Database.MainDatabase;
import Safety.Asking;
import Scene.Login;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 
 * @author Dominik Jurkowski <jurkowski.domink.andrzej@gmail.com>
 *
 */
public class Main extends Application {

	private Stage stage;
	private Login loginScene;
	private MainDatabase mainDatabase;

	private final double WIGHT = 400;
	private final double HEIGHT = 400;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		stage = primaryStage;
		stage.setMinWidth(WIGHT);
		stage.setMinHeight(HEIGHT);

		loginScene = new Login(stage, this, WIGHT, HEIGHT);
		mainDatabase = new MainDatabase();
		
		stage.setTitle("List To Do");
		stage.show();

		stage.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
		});

	}

	private void closeProgram() {
		Boolean answer = Asking.display("Exit", "Do you want to leave");
		if (answer == true) {
			stage.close();
		}
	}

	public Login getLoginScene() {
		return loginScene;
	}

	public void setLoginScene(Login loginScene) {
		this.loginScene = loginScene;
	}

	public MainDatabase getMainDatabase() {
		return mainDatabase;
	}

	public void setMainDatabase(MainDatabase mainDatabase) {
		this.mainDatabase = mainDatabase;
	}

}
