package baminsurances.gui.window;

import baminsurances.api.Config;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Adrian Melsom
 */
public class GeneralStage {

    private Stage stage;
    private Scene scene;

    /**
     * Creates a new general stage with the given values.
     * 
     * @param width the width of the stage
     * @param height the height of the stage
     */
    public GeneralStage(double width, double height) {
        stage = new Stage();
        stage.setTitle(Config.getApplicationName());
        stage.getIcons().add(new Image(
                this.getClass().getClassLoader().getResourceAsStream(
                "temp_logo.png")));
        stage.setHeight(height);
        stage.setWidth(width);
        stage.setResizable(false);
    }

    /**
     * Initiates the stage with the given scene.
     * 
     * @param scene the scene
     */
    public void initiate(Scene scene) {
        stage.setScene(scene);
        this.scene = scene;
        stage.show();
    }

    /**
     * Returns the scene.
     * 
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Closes the stage.
     */
    public void close() {
        stage.hide();
    }

    /**
     * Reopens the stage.
     */
    public void reopen() {
        stage.show();
    }

    /**
     * Returns the stage.
     * 
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }
}
