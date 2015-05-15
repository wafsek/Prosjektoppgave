package baminsurances.gui.window.scene;

import baminsurances.data.BoatType;
import baminsurances.data.Person;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.DifferentVehicleOwnerWindow;
import baminsurances.gui.window.GuiConfig;
import baminsurances.gui.window.MessageDialog;
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
 * Created by Adrian on 14/05/2015.
 */
public class BoatInsuranceScene extends InsuranceScene {
    private Label registrationNoLabel, typeLabel, brandLabel, modelLabel,
            lengthInFeetLabel, productionYearLabel, motorTypeLabel,
            horsePowerLabel;
    private TextField registrationNoField, modelField, brandField,
            lengthInFeetField, productionYearField, horsePowerField;
    private ComboBox<String> motorTypeDropdown;
    private ComboBox<BoatType> typeDropDown;
    private CheckBox ownerBox;
    private boolean ownerBoxIsSelected;
    private Person person;

    public BoatInsuranceScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler, String displayName){
        super(guiEventHandler, keyPressHandler, displayName);
        annualPremiumField.setEditable(true);
        insuranceValueField.setEditable(true);
        paymentFrequencyDropDown.setDisable(false);
        conditionArea.setEditable(true);
        registerInsuranceButton.setDisable(false);
        leftSideContentContainer.getChildren().remove(discribtionContainer);
        ownerBoxIsSelected = false;
        person = null;

        registrationNoLabel = new Label("Registreringsnummer:");
        typeLabel = new Label("Båttype:");
        brandLabel = new Label("Båtmerke:");
        modelLabel = new Label("Modell:");
        lengthInFeetLabel = new Label("Lengde i fot:");
        productionYearLabel = new Label("Produsjonsår:");
        motorTypeLabel = new Label("Motortype:");
        horsePowerLabel = new Label("Hestekrefter:");

        registrationNoField = new TextField();
        modelField = new TextField();
        brandField = new TextField();
        lengthInFeetField = new TextField();
        productionYearField = new TextField();
        horsePowerField = new TextField();

        ownerBox = new CheckBox("Forskjellig eier?");
        ownerBox.setOnAction(e -> {
            if(!ownerBoxIsSelected){
                person = new DifferentVehicleOwnerWindow().registerOwner();
                if (person == null) {
                    ownerBox.setSelected(false);
                } else {
                    ownerBoxIsSelected = !ownerBoxIsSelected;
                }
            } else {
                new MessageDialog().showMessageDialog("Slettet", "Eieren er nå " +
                                "satt tilbake på kunden som blir behandlet.",
                        MessageDialog.INFORMATION_ICON, MessageDialog.OK_OPTION);
                person = null;
                ownerBoxIsSelected = !ownerBoxIsSelected;
            }
            System.out.println(person == null);
        });

        motorTypeDropdown = new ComboBox<>(FXCollections.observableArrayList("Innbors", "utenbors"));
        typeDropDown = new ComboBox<>();
        typeDropDown.getItems().setAll(BoatType.values());

        rightSideFieldContainer.addColumn(0, ownerBox, registrationNoLabel, typeLabel,
                brandLabel, modelLabel,  lengthInFeetLabel, productionYearLabel,
                motorTypeLabel, horsePowerLabel);
        rightSideFieldContainer.addColumn(1, new Label(""), registrationNoField,
                typeDropDown, brandField, modelField, lengthInFeetField,
                productionYearField, motorTypeDropdown, horsePowerField);

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

    public boolean isOwnerBoxIsSelected() {
        return ownerBoxIsSelected;
    }

    public Person getPerson() {
        return person;
    }

    public String getModelFieldText() {
        return modelField.getText();
    }

    public String getBrandFieldText() {
        return brandField.getText();
    }

    public String getRegistrationNoFieldText() {
        return registrationNoField.getText();
    }

    public BoatType getTypeDropDown() {
        return typeDropDown.getValue();
    }
}
