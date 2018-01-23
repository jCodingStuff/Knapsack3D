import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

public class Window extends Application {

  Button pentoButton;
  Button parcelButton;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Cargo Filling v0.01");

    pentoButton = new Button("Pentominoes");
    pentoButton.setOnAction(e -> primaryStage.setScene(createPentoConfigScene()));
    parcelButton = new Button("Parcels");
    parcelButton.setOnAction(e -> primaryStage.setScene(createParcelConfigScene()));

    HBox initialLayout = new HBox(20);
    initialLayout.setAlignment(Pos.CENTER);
    initialLayout.setPadding(new Insets(20, 20, 20, 20));
    initialLayout.getChildren().addAll(parcelButton, pentoButton);
    Scene initialScene = new Scene(initialLayout, 300, 250);
    primaryStage.setScene(initialScene);
    primaryStage.show();
  }

  private Scene createPentoConfigScene() {
    // Create the start layout
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(20, 20, 20, 20));
    grid.setVgap(8);
    grid.setHgap(10);

    // Create the labels
    Label lLabel = new Label("Pento L");
    GridPane.setConstraints(lLabel, 0, 0);
    Label pLabel = new Label("Pento P");
    GridPane.setConstraints(pLabel, 1, 0);
    Label tLabel = new Label("Pento T");
    GridPane.setConstraints(tLabel, 2, 0);

    // Create parcel input fields
    TextField lField = new TextField();
    lField.setPromptText("L Value");
    GridPane.setConstraints(lField, 0, 1);
    TextField pField = new TextField();
    pField.setPromptText("P Value");
    GridPane.setConstraints(pField, 1, 1);
    TextField tField = new TextField();
    tField.setPromptText("T Value");
    GridPane.setConstraints(tField, 2, 1);

    // Create cargo input fields
    TextField widthField = new TextField();
    widthField.setPromptText("Width");
    GridPane.setConstraints(widthField, 0, 2);
    TextField heightField = new TextField();
    heightField.setPromptText("Height");
    GridPane.setConstraints(heightField, 1, 2);
    TextField depthField = new TextField();
    depthField.setPromptText("Depth");
    GridPane.setConstraints(depthField, 2, 2);

    // Create the buttons to start the algorithms
    Button greedyButton = new Button("Greedy");
    GridPane.setConstraints(greedyButton, 0, 3);
    Button backtrackingButton = new Button("Backtracking");
    GridPane.setConstraints(backtrackingButton, 1, 3);
    Button dynamicButton = new Button("Dynamic");
    GridPane.setConstraints(dynamicButton, 2, 3);

    grid.getChildren().addAll(lLabel, pLabel, tLabel, lField, pField, tField, widthField, heightField, depthField, greedyButton, backtrackingButton, dynamicButton);
    return new Scene(grid);
  }

  private Scene createParcelConfigScene() {
    // Create the start layout
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(20, 20, 20, 20));
    grid.setVgap(8);
    grid.setHgap(10);

    // Create the labels
    Label aLabel = new Label("A Box");
    GridPane.setConstraints(aLabel, 0, 0);
    Label bLabel = new Label("B Box");
    GridPane.setConstraints(bLabel, 1, 0);
    Label cLabel = new Label("C Box");
    GridPane.setConstraints(cLabel, 2, 0);

    // Create parcel input fields
    TextField aField = new TextField();
    aField.setPromptText("A Value");
    GridPane.setConstraints(aField, 0, 1);
    TextField bField = new TextField();
    bField.setPromptText("B Value");
    GridPane.setConstraints(bField, 1, 1);
    TextField cField = new TextField();
    cField.setPromptText("C Value");
    GridPane.setConstraints(cField, 2, 1);

    // Create cargo input fields
    TextField widthField = new TextField();
    widthField.setPromptText("Width");
    GridPane.setConstraints(widthField, 0, 2);
    TextField heightField = new TextField();
    heightField.setPromptText("Height");
    GridPane.setConstraints(heightField, 1, 2);
    TextField depthField = new TextField();
    depthField.setPromptText("Depth");
    GridPane.setConstraints(depthField, 2, 2);

    // Create the buttons to start the algorithms
    Button greedyButton = new Button("Greedy");
    GridPane.setConstraints(greedyButton, 0, 3);
    Button backtrackingButton = new Button("Backtracking");
    GridPane.setConstraints(backtrackingButton, 1, 3);
    Button dynamicButton = new Button("Dynamic");
    GridPane.setConstraints(dynamicButton, 2, 3);

    grid.getChildren().addAll(aLabel, bLabel, cLabel, aField, bField, cField, widthField, heightField, depthField, greedyButton, backtrackingButton, dynamicButton);
    return new Scene(grid);
  }

}
