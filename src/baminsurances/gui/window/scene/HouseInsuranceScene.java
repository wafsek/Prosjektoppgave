package baminsurances.gui.window.scene;

import baminsurances.data.HomeType;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.GuiConfig;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * Created by Adrian PC on 13/05/2015.
 */
public class HouseInsuranceScene extends InsuranceScene {

    private Label streetAddressLabel, zipCodeLabel, constructionYearLabel,
            homeTypeLabel, buildingMaterialLabel, standardLabel, squareMetersLabel,
            contentsAmountLabel;
    private TextField streetAddressField, zipCodeField, constructionYearField,
            buildingMaterialField, standardField, squareMetersField, contentsAmountField;
    private ComboBox<String> homeTypeDropDown;
    private CheckBox holydayHomeBox, rentableBox;
    private int checkBoxCounter = 0;

    public HouseInsuranceScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler, String displayName){
        super(guiEventHandler, keyPressHandler, displayName);
        insuranceDropDown.setOnAction(null);
        insuranceDropDown.setValue("Boligforsikring");
        insuranceDropDown.setOnAction(guiEventHandler);
        annualPremiumField.setEditable(true);
        insuranceValueField.setEditable(true);
        conditionArea.setEditable(true);
        registerInsuranceButton.setDisable(false);

        streetAddressLabel = new Label("Gateadresse:");
        zipCodeLabel = new Label("Postnummer:");
        constructionYearLabel = new Label("Konstruksjonsår");
        homeTypeLabel = new Label("Boligtype:");
        buildingMaterialLabel = new Label("Byggmateriale:");
        standardLabel = new Label("Tilstand:");
        squareMetersLabel = new Label("Kvadratmeter:");
        contentsAmountLabel = new Label("Innbo:");

        streetAddressField = new TextField();
        zipCodeField = new TextField();
        constructionYearField = new TextField();
        buildingMaterialField = new TextField();
        standardField = new TextField();
        squareMetersField = new TextField();
        contentsAmountField = new TextField();

        homeTypeDropDown = new ComboBox<>(FXCollections.observableArrayList(getFillingArray()));

        holydayHomeBox = new CheckBox("Feriebolig?");
        holydayHomeBox.setOnAction(e -> {
            if(checkBoxCounter == 0){
                rentableBox.setDisable(false);
                checkBoxCounter++;
            } else {
                rentableBox.setDisable(true);
                rentableBox.setSelected(false);
                checkBoxCounter--;
            }
        });
        rentableBox = new CheckBox("Mulig å leie?");
        rentableBox.setDisable(true);

        leftSideFieldContainer.getChildren().remove(discribtionLabel);

        rightSideFieldContainer.addColumn(0, streetAddressLabel, zipCodeLabel,
                constructionYearLabel, homeTypeLabel, buildingMaterialLabel,
                standardLabel, squareMetersLabel, contentsAmountLabel,
                holydayHomeBox);
        rightSideFieldContainer.addColumn(1, streetAddressField, zipCodeField,
                constructionYearField, homeTypeDropDown, buildingMaterialField,
                standardField, squareMetersField, contentsAmountField, rentableBox);

        rightSideBorderPane = new BorderPane(rightSideFieldContainer, rightSideHeader, null, rightSideFooter, null);
        rightSideBorderPane.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);

        borderPane = new BorderPane(leftSideBorderPane, null, rightSideBorderPane, footer, null);
        scene = new Scene(borderPane);
    }

    private String[] getFillingArray() {
        HomeType[] type = HomeType.values();
        String[] returnValues = new String[type.length];
        for (int i = 0; i < type.length; i++) {
            returnValues[i] = type[i].getDisplayName();
        }
        return returnValues;
    }
}
