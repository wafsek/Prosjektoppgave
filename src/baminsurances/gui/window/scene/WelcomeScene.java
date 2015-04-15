package baminsurances.gui.window.scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * Created by Adrian on 15/04/2015.
 */
public class WelcomeScene {

    private Scene scene;
    private Image welcomeImage;
    private ImageView welcomeImageView;
    private BorderPane borderPane;

    public WelcomeScene(GridPane gridPane){
        welcomeImage = new Image(this.getClass().getResourceAsStream("../../img/add.png"));
        welcomeImageView = new ImageView(welcomeImage);

        borderPane = new BorderPane(welcomeImageView, gridPane, null, null, null);

        scene = new Scene(borderPane);
    }

    public Scene getScene()
    {
        return scene;
    }
}