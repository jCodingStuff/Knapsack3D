import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {

  public static boolean answer;

  public static boolean display(String title, String message) {

    BorderPane major = new BorderPane();
    major.setPadding(new Insets(20, 20, 20, 20));

    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle(title);
    window.setMinWidth(250);
    Label label = new Label();
    label.setText(message);

    // Create two buttons
    Button yesButton = new Button("Yes");
    Button noButton = new Button("No");

    yesButton.setOnAction(e -> {
      answer = true;
      window.close();
    });
    noButton.setOnAction(e -> {
      answer = false;
      window.close();
    });

    HBox buttonLayout = new HBox(10);
    buttonLayout.setPadding(new Insets(20, 0, 0, 0));
    buttonLayout.setAlignment(Pos.CENTER);
    buttonLayout.getChildren().addAll(yesButton, noButton);
    buttonLayout.setAlignment(Pos.CENTER);
    major.setTop(label);
    major.setCenter(buttonLayout);
    Scene scene = new Scene(major);
    window.setScene(scene);
    window.showAndWait();

    return answer;
  }

}
