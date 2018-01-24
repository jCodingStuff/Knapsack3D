import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.image.Image;

public class WarningBox {

  public static void display(String title, String message, String buttText, Image icon) {

    Stage window = new Stage();
    window.getIcons().add(icon);
    window.setResizable(false);
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle(title);
    window.setResizable(false);
    window.setMinWidth(250);
    Label label = new Label();
    label.setText(message);

    Button button = new Button(buttText);

    button.setOnAction(e -> window.close());

    window.setOnCloseRequest(e -> e.consume());

    VBox layout = new VBox(20);
    layout.setPadding(new Insets(20, 20, 20, 20));
    layout.setAlignment(Pos.CENTER);
    layout.getChildren().addAll(label, button);
    Scene scene = new Scene(layout, 300, 100);
    window.setScene(scene);
    window.showAndWait();
  }

}
