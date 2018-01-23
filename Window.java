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
  Button pentoButton;
  Button parcelButton;
  Scene mainScene;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.mainWindow = primaryStage;
    mainWindow.setResizable(false);
    this.mainWindow.setTitle("Cargo Filling v0.01");

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
    Label pentoLabel = new Label("Pento");
    GridPane.setConstraints(pentoLabel, 0, 1);
    Label cargoLabel = new Label("Cargo");
    GridPane.setConstraints(cargoLabel, 0, 2);

    // Create the labels
    Label lLabel = new Label("L");
    GridPane.setConstraints(lLabel, 1, 0);
    Label pLabel = new Label("P");
    GridPane.setConstraints(pLabel, 2, 0);
    Label tLabel = new Label("T");
    GridPane.setConstraints(tLabel, 3, 0);

    // Create parcel input fields
    TextField lField = new TextField();
    lField.setPromptText("L Value");
    GridPane.setConstraints(lField, 1, 1);
    TextField pField = new TextField();
    pField.setPromptText("P Value");
    GridPane.setConstraints(pField, 2, 1);
    TextField tField = new TextField();
    tField.setPromptText("T Value");
    GridPane.setConstraints(tField, 3, 1);

    // Create cargo input fields
    TextField widthField = new TextField();
    widthField.setPromptText("Width");
    GridPane.setConstraints(widthField, 1, 2);
    TextField heightField = new TextField();
    heightField.setPromptText("Height");
    GridPane.setConstraints(heightField, 2, 2);
    TextField depthField = new TextField();
    depthField.setPromptText("Depth");
    GridPane.setConstraints(depthField, 3, 2);

    // Create the buttons to start the algorithms
    Button greedyButton = new Button("Greedy");
    Button backtrackingButton = new Button("Backtracking");
    Button dynamicButton = new Button("Dynamic");
    HBox bottomMenu = new HBox(60);
    bottomMenu.setAlignment(Pos.CENTER);
    bottomMenu.getChildren().addAll(greedyButton, backtrackingButton, dynamicButton);

    grid.getChildren().addAll(pentoLabel, cargoLabel, pLabel, tLabel, lField, pField, tField, widthField, heightField, depthField);

    // Set back Button
    HBox topMenu = new HBox();
    Button goBack = new Button("Back");
    goBack.setOnAction(e -> this.mainWindow.setScene(this.mainScene));
    topMenu.getChildren().add(goBack);

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
    Label parcelLabel = new Label("Parcel");
    GridPane.setConstraints(parcelLabel, 0, 1);
    Label cargoLabel = new Label("Cargo");
    GridPane.setConstraints(cargoLabel, 0, 2);

    // Create the labels
    Label aLabel = new Label("A");
    GridPane.setConstraints(aLabel, 1, 0);
    Label bLabel = new Label("B");
    GridPane.setConstraints(bLabel, 2, 0);
    Label cLabel = new Label("C");
    GridPane.setConstraints(cLabel, 3, 0);

    // Create parcel input fields
    TextField aField = new TextField();
    aField.setPromptText("A Value");
    GridPane.setConstraints(aField, 1, 1);
    TextField bField = new TextField();
    bField.setPromptText("B Value");
    GridPane.setConstraints(bField, 2, 1);
    TextField cField = new TextField();
    cField.setPromptText("C Value");
    GridPane.setConstraints(cField, 3, 1);

    // Create cargo input fields
    TextField widthField = new TextField();
    widthField.setPromptText("Width");
    GridPane.setConstraints(widthField, 1, 2);
    TextField heightField = new TextField();
    heightField.setPromptText("Height");
    GridPane.setConstraints(heightField, 2, 2);
    TextField depthField = new TextField();
    depthField.setPromptText("Depth");
    GridPane.setConstraints(depthField, 3, 2);

    // Create the buttons to start the algorithms
    Button greedyButton = new Button("Greedy");
    Button backtrackingButton = new Button("Backtracking");
    Button dynamicButton = new Button("Dynamic");
    HBox bottomMenu = new HBox(60);
    bottomMenu.setAlignment(Pos.CENTER);
    bottomMenu.getChildren().addAll(greedyButton, backtrackingButton, dynamicButton);

    grid.getChildren().addAll(parcelLabel, cargoLabel, aLabel, bLabel, cLabel, aField, bField, cField, widthField, heightField, depthField);

    // Set back Button
    HBox topMenu = new HBox();
    Button goBack = new Button("Back");
    goBack.setOnAction(e -> this.mainWindow.setScene(this.mainScene));
    topMenu.getChildren().add(goBack);

    majorLayout.setTop(topMenu);
    majorLayout.setCenter(grid);
    majorLayout.setBottom(bottomMenu);
    return new Scene(majorLayout);
  }

}
