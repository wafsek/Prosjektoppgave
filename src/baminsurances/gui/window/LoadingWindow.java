package baminsurances.gui.window;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * A loading window that is to be shown when data is generated. This is to
 * signify that the software has not frozen, as the generation may take some
 * time.
 * 
 * @author Adrian Melsom
 */
public class LoadingWindow {
    private Stage stage;
    private Scene scene;
    private Label informationLabel;
    private ProgressIndicator progressIndicator;
    private VBox container;

    /**
     * Creates a new loading window.
     */
    public LoadingWindow() {
        stage = new Stage();
        stage.setWidth(200);
        stage.setHeight(200);
        stage.setResizable(false);
        progressIndicator = new ProgressIndicator();
        informationLabel = new Label("Genererer...");
        informationLabel.setStyle("-fx-font: 28px Times;");
        container = new VBox(0, informationLabel, progressIndicator);
        container.setAlignment(Pos.CENTER);
        container.backgroundProperty().setValue(
                new Background(new BackgroundFill(Color.web("D7EBE6"),
                        CornerRadii.EMPTY, Insets.EMPTY) ));
        scene = new Scene(container);
        stage.setScene(scene);
    }

    /**
     * Displays the loading window.
     */
    public void display() {
        stage.show();
    }

    /**
     * Closes the loading window.
     */
    public void close() {
        stage.close();
    }
}
