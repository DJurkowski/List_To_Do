package Main;

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
		
		stage.setTitle("List To Do");
		stage.show();
		
		stage.setOnCloseRequest(e ->{
			e.consume();
			closeProgram();
		});

	}

	private void closeProgram() {
		Boolean answer = Asking.display("Exit","Do you want to leave");
		if(answer == true){
			stage.close();
		}
	}

}
