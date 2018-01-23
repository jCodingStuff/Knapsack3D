import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.stage.Modality;

public class Window extends Application {

  Stage mainWindow, dynStage;
  int limit, value1, value2, value3, cargoWidth, cargoHeight, cargoDepth;
  boolean dynOptimized;
  Button pentoButton, parcelButton, backButton, clearButton;
  Scene mainScene;
  Label infoLabel1, infoLabel2, infoLabel3, infoLabel4, infoLabel5;
  Button greedyButton, backtrackingButton, dynamicButton;
  TextField valueField1, valueField2, valueField3, widthField, heightField,
            depthField;

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

    // Common components
    this.infoLabel5 = new Label("Cargo");
    this.clearButton = new Button("Clear");
    this.clearButton.setOnAction(e -> clearFields());
    this.backButton = new Button("Back");
    this.widthField = new TextField();
    this.widthField.setPromptText("Width");
    this.heightField = new TextField();
    this.heightField.setPromptText("Height");
    this.depthField = new TextField();
    this.depthField.setPromptText("Depth");
    this.greedyButton = new Button("Greedy");
    this.backtrackingButton = new Button("Backtracking");
    this.dynamicButton = new Button("Dynamic");

    pentoButton = new Button("Pentominoes");
    pentoButton.setOnAction(e -> this.mainWindow.setScene(createPentoConfigScene()));
    parcelButton = new Button("Parcels");
    parcelButton.setOnAction(e -> this.mainWindow.setScene(createParcelConfigScene()));

    HBox initialLayout = new HBox(20);
    initialLayout.setAlignment(Pos.CENTER);
    initialLayout.setPadding(new Insets(20, 20, 20, 20));
    initialLayout.getChildren().addAll(parcelButton, pentoButton);
    this.mainScene = new Scene(initialLayout, 300, 100);
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
    GridPane.setConstraints(this.widthField, 1, 2);
    GridPane.setConstraints(this.heightField, 2, 2);
    GridPane.setConstraints(this.depthField, 3, 2);

    // Create the buttons to start the algorithms
    HBox bottomMenu = new HBox(60);
    this.greedyButton.setOnAction(e -> launchPentoGreedy());
    this.backtrackingButton.setOnAction(e -> launchPentoBack());
    this.dynamicButton.setOnAction(e -> launchPentoDynamic());
    bottomMenu.setAlignment(Pos.CENTER);
    bottomMenu.getChildren().addAll(this.clearButton, this.greedyButton,
                                    this.backtrackingButton, this.dynamicButton);

    grid.getChildren().addAll(this.infoLabel1, this.infoLabel2, this.infoLabel3,
                              this.infoLabel4, this.infoLabel5, this.valueField1,
                              this.valueField2, this.valueField3, this.widthField,
                              this.heightField, this.depthField);

    // Set back Button
    HBox topMenu = new HBox();
    this.backButton.setOnAction(e -> this.mainWindow.setScene(this.mainScene));
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
    GridPane.setConstraints(this.widthField, 1, 2);
    GridPane.setConstraints(this.heightField, 2, 2);
    GridPane.setConstraints(this.depthField, 3, 2);

    // Create the buttons to start the algorithms
    HBox bottomMenu = new HBox(60);
    this.greedyButton.setOnAction(e -> launchParcelGreedy());
    this.backtrackingButton.setOnAction(e -> launchParcelBack());
    this.dynamicButton.setOnAction(e -> launchParcelDynamic());
    bottomMenu.setAlignment(Pos.CENTER);
    bottomMenu.getChildren().addAll(this.clearButton, this.greedyButton,
                                    this.backtrackingButton, this.dynamicButton);

    grid.getChildren().addAll(this.infoLabel1, this.infoLabel2, this.infoLabel3,
                              this.infoLabel4, this.infoLabel5, this.valueField1,
                              this.valueField2, this.valueField3, this.widthField,
                              this.heightField, this.depthField);

    // Set back Button
    HBox topMenu = new HBox();
    this.backButton.setOnAction(e -> this.mainWindow.setScene(this.mainScene));
    topMenu.getChildren().add(this.backButton);

    majorLayout.setTop(topMenu);
    majorLayout.setCenter(grid);
    majorLayout.setBottom(bottomMenu);
    return new Scene(majorLayout);
  }

  private void closeProgram() {
    boolean answer = ConfirmBox.display("Warning", "Do you want to exit?", "Yes", "No");
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

  private void launchParcelGreedy() {
    boolean cont = collectData();
    if (!cont) return;
    boolean random = ConfirmBox.display("Greedy Config", "Choose mode",
                                        "Random", "Discrete");
    clearFields();
    View view = new View();
    view.showParcelGreedy(random, this.value1, this.value2, this.value3,
                          this.cargoWidth, this.cargoHeight, this.cargoDepth);
  }

  private void launchPentoGreedy() {
    boolean cont = collectData();
    if (!cont) return;
    boolean random = ConfirmBox.display("Greedy Config", "Choose mode",
                                        "Random", "Discrete");
    clearFields();
    View view = new View();
    view.showPentoGreedy(random, this.value1, this.value2, this.value3,
                         this.cargoWidth, this.cargoHeight, this.cargoDepth);
  }

  private void launchPentoBack() {
    boolean cont = collectData();
    if (!cont) return;
    boolean optimized = ConfirmBox.display("Backtracking Config", "Is optimization wanted?",
                                           "Yes", "No");
    clearFields();
    View view = new View();
    view.showPentoBT(this.value1, this.value2, this.value3, this.cargoWidth,
                     this.cargoHeight, this.cargoDepth, optimized);
  }

  private void launchParcelBack() {
    boolean cont = collectData();
    if (!cont) return;
    boolean optimized = ConfirmBox.display("Backtracking Config", "Is optimization wanted?",
                                           "Yes", "No");
    clearFields();
    View view = new View();
    view.showParcelBT(this.value1, this.value2, this.value3, this.cargoWidth,
                      this.cargoHeight, this.cargoDepth, optimized);
  }

  private void launchPentoDynamic() {
    boolean cont = collectData();
    if (!cont) return;
    promptDynamic();
    clearFields();
    View view = new View();
    view.showPentoDynamic(this.value1, this.value2, this.value3, this.cargoWidth,
                          this.cargoHeight, this.cargoDepth, this.limit, this.dynOptimized);
  }

  private void launchParcelDynamic() {
    boolean cont = collectData();
    if (!cont) return;
    promptDynamic();
    clearFields();
    View view = new View();
    view.showParcelDynamic(this.value1, this.value2, this.value3, this.cargoWidth,
                           this.cargoHeight, this.cargoDepth, this.limit, this.dynOptimized);
  }

  private void promptDynamic() {
    BorderPane pane = new BorderPane();
    pane.setPadding(new Insets(20, 20, 20, 20));
    dynStage = new Stage();
    dynStage.setResizable(false);
    dynStage.setTitle("Dynamic Config");
    dynStage.initModality(Modality.APPLICATION_MODAL);
    Label label = new Label("Limit:");
    TextField limitField = new TextField();
    limitField.setPromptText("Limit");
    CheckBox optBox = new CheckBox("Optimized");
    optBox.setSelected(true);
    HBox top = new HBox(20);
    top.setAlignment(Pos.CENTER);
    top.getChildren().addAll(label, limitField, optBox);
    Button button = new Button("Go");
    button.setOnAction(e -> {
      try {
        this.limit = Integer.parseInt(limitField.getText());
        this.dynOptimized = optBox.isSelected();
        if (this.limit > 0) dynStage.close();
      }
      catch (NumberFormatException e2) {
        WarningBox.display("Warning", "Invalid data: Integer needed", "I understand");
        clearData();
        return;
      }
    });
    HBox bot = new HBox();
    bot.setPadding(new Insets(10, 0, 0, 0));
    bot.setAlignment(Pos.CENTER);
    bot.getChildren().add(button);

    pane.setTop(top);
    pane.setCenter(bot);

    Scene scene = new Scene(pane);
    dynStage.setScene(scene);
    dynStage.showAndWait();
  }

  private boolean collectData() {
    try {
      this.value1 = Integer.parseInt(this.valueField1.getText());
      if (this.value1 < 0) return false;
      this.value2 = Integer.parseInt(this.valueField2.getText());
      if (this.value2 < 0) return false;
      this.value3 = Integer.parseInt(this.valueField3.getText());
      if (this.value3 < 0) return false;
      this.cargoWidth = Integer.parseInt(this.widthField.getText());
      if (this.cargoWidth < 0) return false;
      this.cargoHeight = Integer.parseInt(this.heightField.getText());
      if (this.cargoHeight < 0) return false;
      this.cargoDepth = Integer.parseInt(this.depthField.getText());
      if (this.cargoDepth < 0) return false;
      return true;
    }
    catch (NumberFormatException e) {
      WarningBox.display("Warning", "Invalid data: Integer needed", "I understand");
      clearData();
      return false;
    }
  }

  private void clearData() {
    this.value1 = 0;
    this.value2 = 0;
    this.value3 = 0;
    this.cargoWidth = 0;
    this.cargoHeight = 0;
    this.cargoDepth = 0;
  }

}
