import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {

  public static boolean answer;

  public static boolean display(String title, String message, String yes, String no) {

    BorderPane major = new BorderPane();
    major.setPadding(new Insets(20, 20, 20, 20));

    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle(title);
    window.setResizable(false);
    window.setMinWidth(250);
    Label label = new Label();
    label.setText(message);

    // Create two buttons
    Button yesButton = new Button(yes);
    Button noButton = new Button(no);

    yesButton.setOnAction(e -> {
      answer = true;
      window.close();
    });
    noButton.setOnAction(e -> {
      answer = false;
      window.close();
    });

    HBox labelLayout = new HBox();
    labelLayout.setAlignment(Pos.CENTER);
    labelLayout.getChildren().add(label);
    HBox buttonLayout = new HBox(10);
    buttonLayout.setPadding(new Insets(10, 0, 0, 0));
    buttonLayout.setAlignment(Pos.CENTER);
    buttonLayout.getChildren().addAll(yesButton, noButton);
    major.setTop(labelLayout);
    major.setCenter(buttonLayout);
    Scene scene = new Scene(major, 300, 100);
    window.setScene(scene);
    window.showAndWait();

    return answer;
  }

}
