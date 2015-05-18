package baminsurances.gui.window;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.security.cert.Extension;

/**
 * Created by Adrian on 18/05/2015.
 */
public class LoadingWindow {
    private Stage stage;
    private Scene scene;
    private Label informationLabel;
    private ProgressIndicator progressIndicator;
    private VBox container;


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
        scene = new Scene(container);
        stage.setScene(scene);
    }

    public void display() {
        stage.show();
    }

    public void close() {
        stage.close();
    }
}
