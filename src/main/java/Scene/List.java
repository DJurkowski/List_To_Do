package Scene;

import Database.MainDatabase;
import Database.Task;
import Database.User;
import Main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class List {

	private Stage stage;
	private Main main;
	private double width;
	private double height;
	private User user;
	
	private Scene scene;

	BorderPane borderPane; 
	
	private TableView<Task> table;
	
	private Button backButton;
	private Button logOutButton;
	private Button addButton;
	private Button nextButton;
	
	public List(Stage stage, Main main, double width, double height, User user) {

		this.stage = stage;
		this.main = main;
		this.width = width;
		this.height = height;
		this.user = user;
		
		initLayouts();
		initScene();
		
		stage.setScene(scene);
		stage.show();
	}

	private void initLayouts() {
		GridPane gridBottom = createLayout1();
		
		VBox gridTable = createLayout2();
		
		
		borderPane = new BorderPane();
		borderPane.setBottom(gridBottom);
		borderPane.setCenter(gridTable);

	}

	private void initLabels() {

	}

	private void initTextField() {

	}


	private void initScene() {
		scene = new Scene(borderPane, width, height);
	}
	
	public GridPane createLayout1(){
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(0);
		grid.setHgap(10);
		
		initButtons();
		
		grid.getChildren().addAll(backButton, logOutButton, addButton, nextButton);
		return grid;
	}
	
	public VBox createLayout2(){
		VBox grid = new VBox();
		
		grid.getChildren().addAll(createTable());
		
		return grid;
		
	}
	
	private void initButtons(){
		backButton = new Button("Back");
		GridPane.setConstraints(backButton, 0, 0);
		
		
		logOutButton = new Button("Log Out");
		GridPane.setConstraints(logOutButton, 7, 0);
		
		addButton = new Button("Add task");
		GridPane.setConstraints(addButton, 9, 0);
		addButton.setOnAction(e ->{
			AddTask.display(user);
		});

		nextButton = new Button("Next");
		GridPane.setConstraints(nextButton, 15, 0);
		
	}

	public TableView<Task> createTable() {

		TableColumn<Task, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Task, String> dateColumn = new TableColumn<>("Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

		table = new TableView<>();
		table.setItems(getTasks());

		table.getColumns().addAll(nameColumn, dateColumn);
		table.setColumnResizePolicy(table.CONSTRAINED_RESIZE_POLICY);

		return table;

	}
	

	public ObservableList<Task> getTasks() {
		ObservableList<Task> tasks = FXCollections.observableArrayList();

		tasks.addAll(MainDatabase.loadTask(user));

		return tasks;
	}

}
