package baminsurances.gui.window.scene;

import baminsurances.data.TravelInsurance;
import baminsurances.data.TravelRegion;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.GuiConfig;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * Created by Adrian on 15/05/2015.
 */
public class TravelInsuranceScene extends InsuranceScene{

    private Label regionLabel;
    private ComboBox<TravelRegion> regionDropDown;

    public TravelInsuranceScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler, String displayName) {
        super(guiEventHandler, keyPressHandler, displayName);

        insuranceDropDown.setOnAction(null);
        insuranceDropDown.setValue("Baatforsikring");
        insuranceDropDown.setOnAction(guiEventHandler);
        annualPremiumField.setEditable(true);
        insuranceValueField.setEditable(true);
        conditionArea.setEditable(true);
        registerInsuranceButton.setDisable(false);
        leftSideContentContainer.getChildren().remove(discribtionContainer);

        regionLabel = new Label("Område:");
        regionDropDown = new ComboBox<>();
        regionDropDown.getItems().setAll(TravelRegion.values());

        rightSideFieldContainer.addRow(0, regionLabel, regionDropDown);

        rightSideBorderPane = new BorderPane(rightSideFieldContainer, rightSideHeader, null, rightSideFooter, null);
        rightSideBorderPane.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);

        borderPane = new BorderPane(leftSideBorderPane, null, rightSideBorderPane, footer, null);
        scene = new Scene(borderPane);
    }

    public Scene getScene() {
        return scene;
    }

    public TravelRegion getRegionDropDown() {
        return regionDropDown.getValue();
    }
}
