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
 * @author Adrian Melsom
 */
public abstract class GeneralScene {

    protected Scene scene;
    protected HBox footerLeftSide, footerRightSide, footer;
    protected Button logOutButton, backButton;
    protected GuiEventHandler guiEventHandler;
    protected KeyPressHandler keyPressHandler;
    protected BorderPane borderPane;
    protected Label informationLabel;

    /**
     * Created the components used in this class.
     *
     * @param guiEventHandler
     * @param keyPressHandler
     */
    public GeneralScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler){
        this.guiEventHandler = guiEventHandler;
        this.keyPressHandler = keyPressHandler;
        logOutButton = new Button("Logg ut");
        logOutButton.setOnAction(guiEventHandler);
        backButton = new Button("Tilbake");
        backButton.setOnAction(guiEventHandler);
        informationLabel = new Label();
        footerLeftSide = new HBox(0, informationLabel);
        footerLeftSide.setStyle("-fx-padding: 5;");
        footerLeftSide.setAlignment(Pos.CENTER_LEFT);
        footerRightSide = new HBox(10, backButton, logOutButton);
        footerRightSide.setStyle("-fx-padding: 5;");
        footerRightSide.setAlignment(Pos.CENTER_RIGHT);
    }

    /**
     * Returns the scene object of this class.
     *
     * @return the scene object of this class.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Returns the Button which controls the the switching to the log out scene.
     *
     * @return the Button which controls the the switching to the log out scene.
     */
    public Button getLogOutButton() {
        return logOutButton;
    }

    /**
     * Returns the Button which controls the the switching to the previous scene.
     *
     * @return the Button which controls the the switching to the previous scene.
     */
    public Button getBackButton(){
        return backButton;
    }

    /**
     * Sets the value of informationLabel to display the String displayName.
     *
     * @param displayName
     */
    public void setDisplayName (String displayName) {
        informationLabel.setText(displayName);
    }
} // End of File
