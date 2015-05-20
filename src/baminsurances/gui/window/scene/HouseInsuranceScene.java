package baminsurances.gui.window.scene;

import baminsurances.data.HomeType;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.GuiConfig;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.time.LocalDate;

/**
 * Created by Adrian PC on 13/05/2015.
 */
public class HouseInsuranceScene extends InsuranceScene {

    private Label streetAddressLabel, zipCodeLabel, constructionYearLabel,
            homeTypeLabel, buildingMaterialLabel, standardLabel, squareMetersLabel,
            homeAmountLabel, contentsAmountLabel;
    private TextField streetAddressField, zipCodeField,
            buildingMaterialField, standardField, squareMetersField, homeAmountField,
            contentsAmountField;
    private ComboBox<HomeType> homeTypeDropDown;
    private ComboBox<String> constructionYearDropDown;
    private CheckBox holydayHomeBox, rentableBox;
    private int checkBoxCounter = 0;

    public HouseInsuranceScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler){
        super(guiEventHandler, keyPressHandler);
        annualPremiumField.setEditable(true);
        insuranceValueField.setEditable(true);
        conditionArea.setEditable(true);
        paymentFrequencyDropDown.setDisable(false);
        registerInsuranceButton.setDisable(false);
        leftSideContentContainer.getChildren().remove(discribtionContainer);

        streetAddressLabel = new Label("Gateadresse:");
        zipCodeLabel = new Label("Postnummer:");
        constructionYearLabel = new Label("Konstruksjonsår");
        homeTypeLabel = new Label("Boligtype:");
        buildingMaterialLabel = new Label("Byggmateriale:");
        standardLabel = new Label("Tilstand:");
        squareMetersLabel = new Label("Kvadratmeter:");
        homeAmountLabel = new Label("Husets forsikringsverdi:");
        contentsAmountLabel = new Label("Innbo:");

        streetAddressField = new TextField();
        zipCodeField = new TextField();
        buildingMaterialField = new TextField();
        standardField = new TextField();
        squareMetersField = new TextField();
        homeAmountField = new TextField();
        contentsAmountField = new TextField();

        homeTypeDropDown = new ComboBox<>();
        homeTypeDropDown.getItems().setAll(HomeType.values());

        constructionYearDropDown = new ComboBox<>(FXCollections.observableArrayList(fillArray(LocalDate.now().getYear(), 1925)));

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
                standardLabel, squareMetersLabel, homeAmountLabel,
                contentsAmountLabel, holydayHomeBox);
        rightSideFieldContainer.addColumn(1, streetAddressField, zipCodeField,
                constructionYearDropDown, homeTypeDropDown, buildingMaterialField,
                standardField, squareMetersField, homeAmountField, contentsAmountField,
                rentableBox);

        rightSideBorderPane = new BorderPane(rightSideFieldContainer, rightSideHeader, null, rightSideFooter, null);
        rightSideBorderPane.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);

        borderPane = new BorderPane(leftSideBorderPane, null, rightSideBorderPane, footer, null);
        borderPane.backgroundProperty().setValue(
                new Background(new BackgroundFill(Color.web("D7EBE6"),
                        CornerRadii.EMPTY, Insets.EMPTY)));
        scene = new Scene(borderPane);
    }

    public String getStreetAddressFieldText() {
        return streetAddressField.getText();
    }

    public String getZipCodeFieldText() {
        return zipCodeField.getText();
    }

    public String getConstructionYearFieldText() {
        return constructionYearDropDown.getValue();
    }

    public String getBuildingMaterialFieldText() {
        return buildingMaterialField.getText();
    }

    public String getStandardFieldText() {
        return standardField.getText();
    }

    public String getSquareMetersFieldText() {
        return squareMetersField.getText();
    }

    public String getHomeAmountFieldText() {
        return homeAmountField.getText();
    }

    public String getContentsAmountFieldText() {
        return contentsAmountField.getText();
    }

    public HomeType getHomeTypeDropDownSelectedValue() {
        return homeTypeDropDown.getValue();
    }

    public void clearInfo() {
        super.clearInfo();
        streetAddressField.setText("");
        zipCodeField.setText("");
        constructionYearDropDown.setValue("");
        buildingMaterialField.setText("");
        standardField.setText("");
        squareMetersField.setText("");
        homeAmountField.setText("");
        contentsAmountField.setText("");
        rentableBox.setSelected(false);
        holydayHomeBox.setSelected(false);
    }

    @Override
    public Scene getScene() {
        clearInfo();
        return scene;
    }

    public boolean getRentableBoxIsSelected() {
        return rentableBox.isSelected();
    }
}
