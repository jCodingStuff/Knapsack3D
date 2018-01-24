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
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.stage.Modality;

/**
* A class for the main structure of the GUI
*
* @author Julian Marrades
* @version 0.1, 23-01-2018
*
* @author Julian Marrades
* @version 0.2, 24-01-2018
*/
public class Window extends Application {

  // Designed by Freepik from www.flaticon.com
  public static Image icon = new Image("trucking.png");
  // Designed by Freepik from www.flaticon.com
  public static Image question = new Image("question.png");
  // Designed by Roundicons from www.flaticon.com
  public static Image warning = new Image("warning.png");
  // Designed by Maxim Basinski from www.flaticon.com
  public static Image information = new Image("information.png");

  Stage mainWindow, dynStage;
  int limit, value1, value2, value3, cargoWidth, cargoHeight, cargoDepth;
  boolean dynOptimized;
  Button pentoButton, parcelButton, backButton, clearButton;
  Scene mainScene, choiceScene;
  Label infoLabel1, infoLabel2, infoLabel3, infoLabel4, infoLabel5;
  Button greedyButton, backtrackingButton, dynamicButton;
  TextField valueField1, valueField2, valueField3, widthField, heightField,
            depthField;
  BorderPane majorLayout;
  GridPane grid;
  HBox bottomMenu;

  public static void main(String[] args) {
    launch(args);
  }

  /**
  * Setup the main scene and the common attributes of the choice scene
  */
  @Override
  public void start(Stage primaryStage) throws Exception {
    setupCommon();
    this.mainWindow = primaryStage;
    this.mainWindow.getIcons().add(icon);
    this.mainWindow.setOnCloseRequest(e -> {
      e.consume();
      closeProgram();
    });
    mainWindow.setResizable(false);
    this.mainWindow.setTitle("Cargo Filling v0.01");

    pentoButton = new Button("Pentominoes");
    pentoButton.setOnAction(e -> {
        setupPentoConfigScene();
        this.mainWindow.setTitle("Pentominoes");
        this.mainWindow.setScene(this.choiceScene);
      });
    parcelButton = new Button("Parcels");
    parcelButton.setOnAction(e -> {
        setupParcelConfigScene();
        this.mainWindow.setTitle("Parcels");
        this.mainWindow.setScene(this.choiceScene);
      });

    HBox initialLayout = new HBox(20);
    initialLayout.setAlignment(Pos.CENTER);
    initialLayout.setPadding(new Insets(20, 20, 20, 20));
    initialLayout.getChildren().addAll(parcelButton, pentoButton);
    this.mainScene = new Scene(initialLayout, 300, 100);
    this.mainWindow.setScene(this.mainScene);
    this.mainWindow.show();
  }

  /**
  * Setup common parts of the choice scene
  */
  private void setupCommon() {
    // Common labels and Buttons
    this.infoLabel5 = new Label("Cargo");
    this.infoLabel4 = new Label();
    this.infoLabel1 = new Label();
    this.infoLabel2 = new Label();
    this.infoLabel3 = new Label();
    this.valueField1 = new TextField();
    this.valueField2 = new TextField();
    this.valueField3 = new TextField();
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

    // Major layout
    this.majorLayout = new BorderPane();
    majorLayout.setPadding(new Insets(20, 20, 20, 20));

    // Grid
    this.grid = new GridPane();
    this.grid.setPadding(new Insets(0, 0, 20, 0));
    this.grid.setVgap(8);
    this.grid.setHgap(10);
    this.grid.setAlignment(Pos.CENTER);

    // Positions on grid
    GridPane.setConstraints(this.infoLabel4, 0, 1);
    GridPane.setConstraints(this.infoLabel5, 0, 2);
    GridPane.setConstraints(this.infoLabel1, 1, 0);
    GridPane.setConstraints(this.infoLabel2, 2, 0);
    GridPane.setConstraints(this.infoLabel3, 3, 0);
    GridPane.setConstraints(this.valueField1, 1, 1);
    GridPane.setConstraints(this.valueField2, 2, 1);
    GridPane.setConstraints(this.valueField3, 3, 1);
    GridPane.setConstraints(this.widthField, 1, 2);
    GridPane.setConstraints(this.heightField, 2, 2);
    GridPane.setConstraints(this.depthField, 3, 2);

    // Back Button
    this.backButton.setOnAction(e -> {
        clearFields();
        this.mainWindow.setTitle("Cargo Filling v0.01");
        this.mainWindow.setScene(this.mainScene);
      });

    // Bottom Menu
    bottomMenu = new HBox(60);
    bottomMenu.setAlignment(Pos.CENTER);
    bottomMenu.getChildren().addAll(this.backButton, this.clearButton, this.greedyButton,
                                    this.backtrackingButton, this.dynamicButton);

    grid.getChildren().addAll(this.infoLabel1, this.infoLabel2, this.infoLabel3,
                              this.infoLabel4, this.infoLabel5, this.valueField1,
                              this.valueField2, this.valueField3, this.widthField,
                              this.heightField, this.depthField);

    majorLayout.setCenter(grid);
    majorLayout.setBottom(bottomMenu);
    this.choiceScene = new Scene(majorLayout);
  }

  /**
  * Setup for Pento config
  */
  private void setupPentoConfigScene() {
    // Info column
    this.infoLabel4.setText("Pento");

    // Create the labels
    this.infoLabel1.setText("L");
    this.infoLabel2.setText("P");
    this.infoLabel3.setText("T");

    // Create parcel input fields
    this.valueField1.setPromptText("L Value");
    this.valueField2.setPromptText("P Value");
    this.valueField3.setPromptText("T Value");


    // Create the buttons to start the algorithms
    this.greedyButton.setOnAction(e -> launchPentoGreedy());
    this.backtrackingButton.setOnAction(e -> launchPentoBack());
    this.dynamicButton.setOnAction(e -> launchPentoDynamic());
  }

  /**
  * Setup for Parcel config
  */
  private void setupParcelConfigScene() {
    // Info column
    this.infoLabel4.setText("Parcel");

    // Create the labels
    this.infoLabel1.setText("A");
    this.infoLabel2.setText("B");
    this.infoLabel3.setText("C");

    // Create parcel input fields
    this.valueField1.setPromptText("A Value");
    this.valueField2.setPromptText("B Value");
    this.valueField3.setPromptText("C Value");

    // Create the buttons to start the algorithms
    this.greedyButton.setOnAction(e -> launchParcelGreedy());
    this.backtrackingButton.setOnAction(e -> launchParcelBack());
    this.dynamicButton.setOnAction(e -> launchParcelDynamic());
  }

  /**
  * Close in a safe way
  */
  private void closeProgram() {
    boolean answer = ConfirmBox.display("Exit", "Do you want to exit?", "Yes", "No", warning);
    if (answer) this.mainWindow.close();
  }

  /**
  * Clear all input fields
  */
  private void clearFields() {
    this.valueField1.clear();
    this.valueField2.clear();
    this.valueField3.clear();
    this.widthField.clear();
    this.heightField.clear();
    this.depthField.clear();
  }

  /**
  * Collect data and run greedy for parcels
  */
  private void launchParcelGreedy() {
    boolean cont = collectData();
    if (!cont) return;
    boolean random = ConfirmBox.display("Greedy Settings", "Choose mode",
                                        "Random", "Discrete", question);
    clearFields();
    View view = new View();
    view.showParcelGreedy(random, this.value1, this.value2, this.value3,
                          this.cargoWidth, this.cargoHeight, this.cargoDepth);
  }

  /**
  * Collect data and run greedy for pentominoes
  */
  private void launchPentoGreedy() {
    boolean cont = collectData();
    if (!cont) return;
    boolean random = ConfirmBox.display("Greedy Settings", "Choose mode",
                                        "Random", "Discrete", question);
    clearFields();
    View view = new View();
    view.showPentoGreedy(random, this.value1, this.value2, this.value3,
                         this.cargoWidth, this.cargoHeight, this.cargoDepth);
  }

  /**
  * Collect data and run backtracking for pentominoes
  */
  private void launchPentoBack() {
    boolean cont = collectData();
    if (!cont) return;
    boolean optimized = ConfirmBox.display("Backtracking Settings", "Is optimization wanted?",
                                           "Yes", "No", question);
    clearFields();
    View view = new View();
    view.showPentoBT(this.value1, this.value2, this.value3, this.cargoWidth,
                     this.cargoHeight, this.cargoDepth, optimized);
  }

  /**
  * Collect data and run backtracking for parcels
  */
  private void launchParcelBack() {
    boolean cont = collectData();
    if (!cont) return;
    boolean optimized = ConfirmBox.display("Backtracking Settings", "Is optimization wanted?",
                                           "Yes", "No", question);
    clearFields();
    View view = new View();
    view.showParcelBT(this.value1, this.value2, this.value3, this.cargoWidth,
                      this.cargoHeight, this.cargoDepth, optimized);
  }

  /**
  * Collect data and run dynamic for pentominoes
  */
  private void launchPentoDynamic() {
    boolean cont = collectData();
    if (!cont) return;
    promptDynamic();
    clearFields();
    View view = new View();
    view.showPentoDynamic(this.value1, this.value2, this.value3, this.cargoWidth,
                          this.cargoHeight, this.cargoDepth, this.limit, this.dynOptimized);
  }

  /**
  * Collect data and run dynamic for parcels
  */
  private void launchParcelDynamic() {
    boolean cont = collectData();
    if (!cont) return;
    promptDynamic();
    clearFields();
    View view = new View();
    view.showParcelDynamic(this.value1, this.value2, this.value3, this.cargoWidth,
                           this.cargoHeight, this.cargoDepth, this.limit, this.dynOptimized);
  }

  /**
  * Open the config Stage for Dynamic algorithm
  */
  private void promptDynamic() {
    BorderPane pane = new BorderPane();
    pane.setPadding(new Insets(20, 20, 20, 20));
    dynStage = new Stage();
    dynStage.getIcons().add(question);
    dynStage.setOnCloseRequest(e -> e.consume());
    dynStage.setResizable(false);
    dynStage.setTitle("Dynamic Settings");
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
        WarningBox.display("Warning", "Invalid data: Integer needed", "I understand", warning);
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

  /**
  * Collect data from input fields
  * @return true if all correct, false if errors
  */
  private boolean collectData() {
    try {
      this.value1 = Integer.parseInt(this.valueField1.getText());
      if (this.value1 < 0) return false;
      this.value2 = Integer.parseInt(this.valueField2.getText());
      if (this.value2 < 0) return false;
      this.value3 = Integer.parseInt(this.valueField3.getText());
      if (this.value3 < 0) return false;
      this.cargoWidth = Integer.parseInt(this.widthField.getText());
      if (this.cargoWidth < 1) return false;
      this.cargoHeight = Integer.parseInt(this.heightField.getText());
      if (this.cargoHeight < 1) return false;
      this.cargoDepth = Integer.parseInt(this.depthField.getText());
      if (this.cargoDepth < 1) return false;
      return true;
    }
    catch (NumberFormatException e) {
      WarningBox.display("Warning", "Invalid data: Integer needed", "I understand", warning);
      clearData();
      return false;
    }
  }

  /**
  * Clear instance fields
  */
  private void clearData() {
    this.value1 = 0;
    this.value2 = 0;
    this.value3 = 0;
    this.cargoWidth = 0;
    this.cargoHeight = 0;
    this.cargoDepth = 0;
  }

}
