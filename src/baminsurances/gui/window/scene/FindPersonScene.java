package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.GuiConfig;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Created by Adrian on 11/05/2015.
 */
public class FindPersonScene extends PersonSearchScene {

    public FindPersonScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler, String displayName){
        super(guiEventHandler, keyPressHandler, displayName);
        buttonContainer = new HBox(0, backButton, logOutButton);
        buttonContainer.setAlignment(Pos.CENTER_RIGHT);
        infoContainer = new HBox(0, informationLabel);
        footer = new HBox(GuiConfig.PRIMARY_WIDTH*3/4, infoContainer, buttonContainer);
        footer.setAlignment(Pos.BOTTOM_RIGHT);
        footer.setStyle("-fx-border-color: gray;");
        borderPane = new BorderPane(itemContainer, null, personTable, footer, null);
        scene = new Scene(borderPane);
    }

    public String[] getWrittenInfo(){
        return new String[] {firstNameField.getText(), lastNameField.getText(),
                birthNumberField.getText(), adressField.getText(),
                zipCodeField.getText()};
    }

}
