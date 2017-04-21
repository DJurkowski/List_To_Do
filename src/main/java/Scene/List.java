package Scene;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import Database.MainDatabase;
import Database.Task;
import Database.User;
import Main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
*
* @author Dominik Jurkowski <jurkowski.domink.andrzej@gmail.com>
*
*/
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
	private Button deleteButton;

	private LocalDate date;

	public List(Stage stage, Main main, double width, double height, User user, LocalDate date) {

		this.stage = stage;
		this.main = main;
		this.width = width;
		this.height = height;
		this.user = user;
		this.setDate(date);

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
	
	/**
	 * The method set up scene
	 */
	private void initScene() {
		scene = new Scene(borderPane, width, height);
	}

	public GridPane createLayout1() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(0);
		grid.setHgap(10);

		initButtons();

		grid.getChildren().addAll(backButton, logOutButton, addButton, deleteButton, nextButton);
		return grid;
	}

	public VBox createLayout2() {
		VBox grid = new VBox();

		grid.getChildren().addAll(createTable());

		return grid;

	}
	
	/**
	 * The method create the buttons
	 */
	private void initButtons() {

		backButton = new Button("Back");
		GridPane.setConstraints(backButton, 0, 0);
		backButton.setOnAction(e -> {
			this.setDate(date.minusDays(1));

			new List(stage, main, width, height, user, this.getDate());
		});

		logOutButton = new Button("Log Out");
		GridPane.setConstraints(logOutButton, 7, 0);
		logOutButton.setOnAction(e -> {
			stage.setScene(main.getLoginScene().getScene());
		});

		addButton = new Button("Add task");
		GridPane.setConstraints(addButton, 9, 0);
		addButton.setOnAction(e -> {

			this.getTable().getItems().add(AddTask.display(user));

		});

		deleteButton = new Button("Delete task");
		GridPane.setConstraints(deleteButton, 8, 0);
		deleteButton.setOnAction(e -> {
			deleteSelectedTask();
		});

		nextButton = new Button("Next");
		GridPane.setConstraints(nextButton, 15, 0);
		nextButton.setOnAction(e -> {
			this.setDate(date.plus(1, ChronoUnit.DAYS));

			new List(stage, main, width, height, user, this.getDate());
		});

	}
	
	/**
	 * The method create tableView and sets up object in table
	 * @return Retrun created tableView
	 */
	public TableView<Task> createTable() {

		TableColumn<Task, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Task, String> dateColumn = new TableColumn<>("Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

		table = new TableView<>();
		table.setItems(getTasks());

		table.getColumns().addAll(nameColumn, dateColumn);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		return table;

	}

	/**
	 * The method gets obcjets from database and selects them by date
	 * @return Retrun observable list with objects
	 */
	public ObservableList<Task> getTasks() {
		ObservableList<Task> tasks = FXCollections.observableArrayList();

		for (Task t : user.getTasksList()) {
			if (t.getDate().equals(date)) {
				tasks.add(t);
			}
		}

		return tasks;
	}

	/**
	 * The method delete selected rows from TableView 
	 * Also delete selected objects from database
	 */
	private void deleteSelectedTask() {
		ObservableList<Task> taskSelected, allTasks;
		allTasks = table.getItems();
		taskSelected = table.getSelectionModel().getSelectedItems();

		ArrayList<Task> taskList = new ArrayList<>();
		taskList.addAll(taskSelected);
		for (Task t : taskList) {
			MainDatabase.remove(t);
		}
		taskSelected.forEach(allTasks::remove);
	}

	/**
	 * 
	 * GETTERS AND SETTERS
	 * 
	 */

	public TableView<Task> getTable() {
		return table;
	}

	public void setTable(TableView<Task> table) {
		this.table = table;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
