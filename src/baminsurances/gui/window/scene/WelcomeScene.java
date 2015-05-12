package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.GuiEventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.logging.Handler;

/**
 * Created by Adrian on 15/04/2015.
 */
public class WelcomeScene {

    private Scene scene;
    private Image welcomeImage;
    private ImageView welcomeImageView;
    private BorderPane borderPane;

    /**
    * creates a new scene of the type defines as WelcomeScene
    * by this class.
    *
    *@param header horizontal array of components
    *@param footer horizontal array of components
     *@param handler guiEventHandler that listens for actions
     */
    public WelcomeScene(HBox header, HBox footer, GuiEventHandler handler){
        welcomeImage = new Image(this.getClass().getResourceAsStream("../../img/add.png"));
        welcomeImageView = new ImageView(welcomeImage);
        borderPane = new BorderPane(welcomeImageView, header, null, footer, null);

        scene = new Scene(borderPane);
    }

    public Scene getScene()
    {
        return scene;
    }
}