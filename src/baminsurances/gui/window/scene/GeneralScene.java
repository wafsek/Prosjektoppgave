package baminsurances.gui.window.scene;

import baminsurances.api.Config;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Created by Adrian on 11/05/2015.
 */
public abstract class GeneralScene {

    protected Scene scene;
    protected HBox buttonContainer, infoContainer, footer;
    protected Button logOutButton, backButton;
    protected GuiEventHandler guiEventHandler;
    protected KeyPressHandler keyPressHandler;
    protected BorderPane borderPane;
    protected Label informationLabel;
    protected String displayName;

    public GeneralScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler, String displayName){
        this.guiEventHandler = guiEventHandler;
        this.keyPressHandler = keyPressHandler;
        this.displayName = displayName;
        logOutButton = new Button("Logg ut");
        logOutButton.setStyle("-fx-padding: 5;");
        logOutButton.setOnAction(guiEventHandler);
        backButton = new Button("Tilbake");
        backButton.setOnAction(guiEventHandler);
        informationLabel = new Label(displayName);
    }

    public Scene getScene() {
        return scene;
    }

    public Button getLogOutButton() {
        return logOutButton;
    }

    public Button getBackButton(){
        return backButton;
    }
}
