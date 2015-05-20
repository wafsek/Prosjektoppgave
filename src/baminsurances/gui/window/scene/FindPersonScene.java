package baminsurances.gui.window.scene;

import baminsurances.data.Customer;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.GuiConfig;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Created by Adrian on 11/05/2015.
 * @author Adrian Melsom
 */
public class FindPersonScene extends PersonSearchScene {

    private Button registerPersonButton;
    private Label headerLabel;
    private BorderPane leftSideContainer;
    private HBox headerContainer;

    /**
     * Creates the components used in this class.
     *
     * @param guiEventHandler
     * @param keyPressHandler
     */
    public FindPersonScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler){
        super(guiEventHandler, keyPressHandler);
        registerPersonButton = new Button("Registrer ny kunde");
        registerPersonButton.setOnAction(guiEventHandler);
        headerLabel = new Label("Finn kunden du ønsker å behandle");
        headerLabel.setStyle("-fx-font: 28px Times;");
        headerContainer = new HBox(0, headerLabel);
        headerContainer.setStyle("-fx-border-color: gray;");
        headerContainer.setAlignment(Pos.CENTER);

        footerLeftSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        footerRightSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        footer = new HBox(0, footerLeftSide, footerRightSide);
        footer.setAlignment(Pos.BOTTOM_RIGHT);
        footer.setStyle("-fx-border-color: gray;");
        itemContainer.add(registerPersonButton, 0, 6, 1, 1);
        itemContainer.setHgap(50);
        leftSideContainer = new BorderPane(itemContainer, headerContainer, null, null, null);

        borderPane = new BorderPane(leftSideContainer, null, customerTable, footer, null);
        borderPane.backgroundProperty().setValue(
                new Background(new BackgroundFill(Color.web("D7EBE6"),
                        CornerRadii.EMPTY, Insets.EMPTY) ));
        scene = new Scene(borderPane);
    }

    /**
     * Returns an array of String Objects which which are defined
     * by what the textfields in this class contains.
     *
     * @returnan array of String Objects which which are defined
     * by what the textfields in this class contains.
     */
    public String[] getWrittenInfo(){
        return new String[] {firstNameField.getText(), lastNameField.getText(),
                birthNumberField.getText(), adressField.getText(),
                zipCodeField.getText()};
    }

    /**
     * Returns the button which is used to start the registration process.
     *
     * @return the button which is used to start the registration process.
     */
    public Button getRegisterPersonButton() {
        return registerPersonButton;
    }

    /**
     * Returns the Customer which is selected in the customerTable.
     *
     * @return the Customer which is selected in the customerTable.
     */
    public Customer getSelectedCustomer() {
        return customerTable.getSelectionModel().getSelectedItem();
    }
} // End of File
