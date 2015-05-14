package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.GuiConfig;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * Created by Adrian on 14/05/2015.
 */
public class BoatInsuranceScene extends InsuranceScene {
    private Label lengthInFeetLabel, productionYearLabel, motorTypeLabel,
            horsePowerLabel;
    private TextField lengthInFeetField, productionYearField, horsePowerField;
    private ComboBox<String> motorTypeDropdown;

    public BoatInsuranceScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler, String displayName){
        super(guiEventHandler, keyPressHandler, displayName);
        insuranceDropDown.setOnAction(null);
        insuranceDropDown.setValue("Baatforsikring");
        insuranceDropDown.setOnAction(guiEventHandler);
        annualPremiumField.setEditable(true);
        insuranceValueField.setEditable(true);
        conditionArea.setEditable(true);
        registerInsuranceButton.setDisable(false);
        leftSideContentContainer.getChildren().remove(discribtionContainer);

        lengthInFeetLabel = new Label("Lengde i fot:");
        productionYearLabel = new Label("Produsjonsår:");
        motorTypeLabel = new Label("Motortype:");
        horsePowerLabel = new Label("Hestekrefter:");

        lengthInFeetField = new TextField();
        productionYearField = new TextField();
        horsePowerField = new TextField();

        motorTypeDropdown = new ComboBox<>(FXCollections.observableArrayList("Innbors", "utenbors"));

        rightSideFieldContainer.addColumn(0, lengthInFeetLabel, productionYearLabel,
                motorTypeLabel, horsePowerLabel);
        rightSideFieldContainer.addColumn(1, lengthInFeetField, productionYearField,
                motorTypeDropdown, horsePowerField);

        rightSideBorderPane = new BorderPane(rightSideFieldContainer, rightSideHeader, null, rightSideFooter, null);
        rightSideBorderPane.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);

        borderPane = new BorderPane(leftSideBorderPane, null, rightSideBorderPane, footer, null);
        scene = new Scene(borderPane);
    }

    public String getLengthInFeetFieldText() {
        return lengthInFeetField.getText();
    }

    public String getProductionYearFieldText() {
        return productionYearField.getText();
    }

    public String getHorsePowerFieldText() {
        return horsePowerField.getText();
    }

    public String getMotorTypeDropdownSelectedValue() {
        return motorTypeDropdown.getValue();
    }
}
