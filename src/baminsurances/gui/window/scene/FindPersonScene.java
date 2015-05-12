package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.GuiConfig;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Created by Adrian on 11/05/2015.
 */
public class FindPersonScene extends PersonSearchScene {

    private Button registerPersonButton;
    private Label infoLabel;

    public FindPersonScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler, String displayName){
        super(guiEventHandler, keyPressHandler, displayName);
        registerPersonButton = new Button("Registrer ny kunde");
        registerPersonButton.setOnAction(guiEventHandler);
        infoLabel = new Label("Hvis tabellen er tom etter et søk, betyr det\n at det ikke finnes en tilsvarende person" +
                ". For\n å registrere en ny person, trykk på knappen\n" +
                "under!");
        buttonContainer = new HBox(0, backButton, logOutButton);
        buttonContainer.setAlignment(Pos.CENTER_RIGHT);
        infoContainer = new HBox(0, informationLabel);
        footer = new HBox(GuiConfig.PRIMARY_WIDTH*3/4, infoContainer, buttonContainer);
        footer.setAlignment(Pos.BOTTOM_RIGHT);
        itemContainer.add(infoLabel, 0, 6, 2, 1);
        itemContainer.add(registerPersonButton, 1, 7, 1, 1);
        footer.setStyle("-fx-border-color: gray;");

        borderPane = new BorderPane(itemContainer, null, personTable, footer, null);
        scene = new Scene(borderPane);
    }

    public String[] getWrittenInfo(){
        return new String[] {firstNameField.getText(), lastNameField.getText(),
                birthNumberField.getText(), adressField.getText(),
                zipCodeField.getText()};
    }

    public Button getRegisterPersonButton() {
        return registerPersonButton;
    }

}
