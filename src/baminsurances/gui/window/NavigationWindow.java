package baminsurances.gui.window;

import baminsurances.api.Config;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Adrian Melsom
 */
public class NavigationWindow {

    public static final int STAGE_WIDTH = OperationWindow.STAGE_HEIGHT*1/2,
            STAGE_HEIGHT = OperationWindow.STAGE_WIDTH*1/4;
    private Stage stage;

    public NavigationWindow() {
        stage = new Stage();
        stage.setTitle(Config.getApplicationName());
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("../img/temp_logo.png")));
        stage.setHeight(STAGE_HEIGHT);
        stage.setWidth(STAGE_WIDTH);
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
