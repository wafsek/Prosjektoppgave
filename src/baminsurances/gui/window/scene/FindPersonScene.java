package baminsurances.gui.window.scene;

import baminsurances.data.Customer;
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
    private Label infoLabel, headerLabel;
    private BorderPane leftSideContainer;
    private HBox headerContainer;

    public FindPersonScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler, String displayName){
        super(guiEventHandler, keyPressHandler, displayName);
        registerPersonButton = new Button("Registrer ny kunde");
        registerPersonButton.setOnAction(guiEventHandler);
        headerLabel = new Label("Finn kunden du �nsker � behandle");
        headerLabel.setStyle("-fx-font: 28px Times;");
        headerContainer = new HBox(0, headerLabel);
        headerContainer.setStyle("-fx-border-color: gray;");
        headerContainer.setAlignment(Pos.CENTER);

        footerLeftSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        footerRightSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        footer = new HBox(0, footerLeftSide, footerRightSide);
        footer.setAlignment(Pos.BOTTOM_RIGHT);
        footer.setStyle("-fx-border-color: gray;");
        itemContainer.add(registerPersonButton, 0, 5, 1, 1);
        itemContainer.setHgap(50);
        leftSideContainer = new BorderPane(itemContainer, headerContainer, null, null, null);

        borderPane = new BorderPane(leftSideContainer, null, customerTable, footer, null);
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

    public Customer getSelectedCustomer() {
        return customerTable.getSelectionModel().getSelectedItem();
    }
}