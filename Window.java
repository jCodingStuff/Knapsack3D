import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

public class Window extends Application {

  Stage mainWindow;
  Button pentoButton, parcelButton, backButton, clearButton;
  Scene mainScene;
  Label infoLabel1, infoLabel2, infoLabel3, infoLabel4, infoLabel5;
  Button greedyButton, backtrackingButton, dynamicButton;
  TextField valueField1, valueField2, valueField3, widthField, heightField, depthField;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.mainWindow = primaryStage;
    this.mainWindow.setOnCloseRequest(e -> {
      e.consume();
      closeProgram();
    });
    mainWindow.setResizable(false);
    this.mainWindow.setTitle("Cargo Filling v0.01");

    this.clearButton = new Button("Clear");
    this.clearButton.setOnAction(e -> clearFields());

    this.backButton = new Button("Back");
    this.backButton.setOnAction(e -> this.mainWindow.setScene(this.mainScene));

    pentoButton = new Button("Pentominoes");
    pentoButton.setOnAction(e -> this.mainWindow.setScene(createPentoConfigScene()));
    parcelButton = new Button("Parcels");
    parcelButton.setOnAction(e -> this.mainWindow.setScene(createParcelConfigScene()));

    HBox initialLayout = new HBox(20);
    initialLayout.setAlignment(Pos.CENTER);
    initialLayout.setPadding(new Insets(20, 20, 20, 20));
    initialLayout.getChildren().addAll(parcelButton, pentoButton);
    this.mainScene = new Scene(initialLayout, 300, 250);
    this.mainWindow.setScene(this.mainScene);
    this.mainWindow.show();
  }

  private Scene createPentoConfigScene() {
    // Major layout
    BorderPane majorLayout = new BorderPane();
    majorLayout.setPadding(new Insets(20, 20, 20, 20));

    // Create the start layout
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(20, 20, 20, 20));
    grid.setVgap(8);
    grid.setHgap(10);

    // Info column
    this.infoLabel4 = new Label("Pento");
    GridPane.setConstraints(this.infoLabel4, 0, 1);
    this.infoLabel5 = new Label("Cargo");
    GridPane.setConstraints(this.infoLabel5, 0, 2);

    // Create the labels
    this.infoLabel1 = new Label("L");
    GridPane.setConstraints(this.infoLabel1, 1, 0);
    this.infoLabel2 = new Label("P");
    GridPane.setConstraints(this.infoLabel2, 2, 0);
    this.infoLabel3 = new Label("T");
    GridPane.setConstraints(this.infoLabel3, 3, 0);

    // Create parcel input fields
    this.valueField1 = new TextField();
    this.valueField1.setPromptText("L Value");
    GridPane.setConstraints(this.valueField1, 1, 1);
    this.valueField2 = new TextField();
    this.valueField2.setPromptText("P Value");
    GridPane.setConstraints(this.valueField2, 2, 1);
    this.valueField3 = new TextField();
    this.valueField3.setPromptText("T Value");
    GridPane.setConstraints(this.valueField3, 3, 1);

    // Create cargo input fields
    this.widthField = new TextField();
    this.widthField.setPromptText("Width");
    GridPane.setConstraints(this.widthField, 1, 2);
    this.heightField = new TextField();
    this.heightField.setPromptText("Height");
    GridPane.setConstraints(this.heightField, 2, 2);
    this.depthField = new TextField();
    this.depthField.setPromptText("Depth");
    GridPane.setConstraints(this.depthField, 3, 2);

    // Create the buttons to start the algorithms
    this.greedyButton = new Button("Greedy");
    this.backtrackingButton = new Button("Backtracking");
    this.dynamicButton = new Button("Dynamic");
    HBox bottomMenu = new HBox(60);
    bottomMenu.setAlignment(Pos.CENTER);
    bottomMenu.getChildren().addAll(this.clearButton, this.greedyButton,
                                    this.backtrackingButton, this.dynamicButton);

    grid.getChildren().addAll(this.infoLabel1, this.infoLabel2, this.infoLabel3,
                              this.infoLabel4, this.infoLabel5, this.valueField1,
                              this.valueField2, this.valueField3, this.widthField,
                              this.heightField, this.depthField);

    // Set back Button
    HBox topMenu = new HBox();
    topMenu.getChildren().add(this.backButton);

    majorLayout.setTop(topMenu);
    majorLayout.setCenter(grid);
    majorLayout.setBottom(bottomMenu);
    return new Scene(majorLayout);
  }

  private Scene createParcelConfigScene() {
    // Major layout
    BorderPane majorLayout = new BorderPane();
    majorLayout.setPadding(new Insets(20, 20, 20, 20));

    // Create the start layout
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(20, 20, 20, 20));
    grid.setVgap(8);
    grid.setHgap(10);

    // Info column
    this.infoLabel4 = new Label("Parcel");
    GridPane.setConstraints(this.infoLabel4, 0, 1);
    this.infoLabel5 = new Label("Cargo");
    GridPane.setConstraints(this.infoLabel5, 0, 2);

    // Create the labels
    this.infoLabel1 = new Label("A");
    GridPane.setConstraints(this.infoLabel1, 1, 0);
    this.infoLabel2 = new Label("B");
    GridPane.setConstraints(this.infoLabel2, 2, 0);
    this.infoLabel3 = new Label("C");
    GridPane.setConstraints(this.infoLabel3, 3, 0);

    // Create parcel input fields
    this.valueField1 = new TextField();
    this.valueField1.setPromptText("A Value");
    GridPane.setConstraints(this.valueField1, 1, 1);
    this.valueField2 = new TextField();
    this.valueField2.setPromptText("B Value");
    GridPane.setConstraints(this.valueField2, 2, 1);
    this.valueField3 = new TextField();
    this.valueField3.setPromptText("C Value");
    GridPane.setConstraints(this.valueField3, 3, 1);

    // Create cargo input fields
    this.widthField = new TextField();
    this.widthField.setPromptText("Width");
    GridPane.setConstraints(this.widthField, 1, 2);
    this.heightField = new TextField();
    this.heightField.setPromptText("Height");
    GridPane.setConstraints(this.heightField, 2, 2);
    this.depthField = new TextField();
    this.depthField.setPromptText("Depth");
    GridPane.setConstraints(this.depthField, 3, 2);

    // Create the buttons to start the algorithms
    this.greedyButton = new Button("Greedy");
    this.backtrackingButton = new Button("Backtracking");
    this.dynamicButton = new Button("Dynamic");
    HBox bottomMenu = new HBox(60);
    bottomMenu.setAlignment(Pos.CENTER);
    bottomMenu.getChildren().addAll(this.clearButton, this.greedyButton,
                                    this.backtrackingButton, this.dynamicButton);

    grid.getChildren().addAll(this.infoLabel1, this.infoLabel2, this.infoLabel3,
                              this.infoLabel4, this.infoLabel5, this.valueField1,
                              this.valueField2, this.valueField3, this.widthField,
                              this.heightField, this.depthField);

    // Set back Button
    HBox topMenu = new HBox();
    topMenu.getChildren().add(this.backButton);

    majorLayout.setTop(topMenu);
    majorLayout.setCenter(grid);
    majorLayout.setBottom(bottomMenu);
    return new Scene(majorLayout);
  }

  private void closeProgram() {
    boolean answer = ConfirmBox.display("Warning", "Do you want to exit?");
    if (answer) this.mainWindow.close();
  }

  private void clearFields() {
    this.valueField1.clear();
    this.valueField2.clear();
    this.valueField3.clear();
    this.widthField.clear();
    this.heightField.clear();
    this.depthField.clear();
  }

}
