package baminsurances.gui.window;

import baminsurances.api.Config;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @author Adrian Melsom
 */
public class GeneralStage {

    private Stage stage;

    public GeneralStage(double width, double height) {
        stage = new Stage();
        stage.setTitle(Config.getApplicationName());
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("../img/temp_logo.png")));
        stage.setHeight(height);
        stage.setWidth(width);
        stage.setResizable(false);
    }

    public void initiate(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }

    public void close() {
        stage.hide();
    }

    public void reopen() {
        stage.show();
    }
}
