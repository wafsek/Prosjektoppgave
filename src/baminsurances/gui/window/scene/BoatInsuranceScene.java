package baminsurances.gui.window.scene;

import baminsurances.data.BoatType;
import baminsurances.data.Person;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.DifferentVehicleOwnerWindow;
import baminsurances.gui.window.GuiConfig;
import baminsurances.gui.window.MessageDialog;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Created by Adrian on 14/05/2015.
 * @author Adrian Melsom
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

    /**
     * Creates the components used in this class.
     *
     * @param guiEventHandler
     * @param keyPressHandler
     */
    public BoatInsuranceScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler){
        super(guiEventHandler, keyPressHandler);
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
        registrationNoField.setPromptText("Eks: ABC123");

        ownerBox = new CheckBox("Forskjellig eier?");
        ownerBox.setOnAction(e -> {
            if (!ownerBoxIsSelected) {
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
        borderPane.backgroundProperty().setValue(
                new Background(new BackgroundFill(Color.web("D7EBE6"),
                        CornerRadii.EMPTY, Insets.EMPTY)));
        scene = new Scene(borderPane);
    }

    public void clearScene() {
        super.clearInfo();
        /*registrationNoField, modelField, brandField,
                lengthInFeetField, productionYearField, horsePowerField, motorTypeDropdown*/
    }

    /**
     * Returns a String of what's contained in the TextField lengthInFeetField.
     *
     * @return a String of what's contained in the TextField lengthInFeetField.
     */
    public String getLengthInFeetFieldText() {
        return lengthInFeetField.getText();
    }

    /**
     * Returns a String of what's contained in the TextField productionYearField.
     *
     * @return a String of what's contained in the TextField productionYearField.
     */
    public String getProductionYearFieldText() {
        return productionYearField.getText();
    }

    /**
     * Returns a String of what's contained in the TextField horsePowerField.
     *
     * @return a String of what's contained in the TextField horsePowerField.
     */
    public String getHorsePowerFieldText() {
        return horsePowerField.getText();
    }

    /**
     * returns a String of what's selected in the ComboBox motorTypeDropDown.
     *
     * @return a String of what's selected in the ComboBox motorTypeDropDown.
     */
    public String getMotorTypeDropdownSelectedValue() {
        return motorTypeDropdown.getValue();
    }

    /**
     * Returns an Object Person or null if it's not defined by the user.
     *
     * @return an Object Person or null if it's not defined by the user.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Returns a String of what's contained in the Textfield modelField.
     *
     * @return a String of what's contained in the Textfield modelField.
     */
    public String getModelFieldText() {
        return modelField.getText();
    }

    /**
     * Returns a String of what's contained in the TextField brandField.
     *
     * @return a String of what's contained in the TextField brandField.
     */
    public String getBrandFieldText() {
        return brandField.getText();
    }

    /**
     * returns a String of what's contained in the TextField registrationNoField.
     *
     * @return a String of what's contained in the TextField registrationNoField.
     */
    public String getRegistrationNoFieldText() {
        return registrationNoField.getText();
    }

    /**
     * Returns an Enum BoatType, based on what's selected in the Combobox typeDropDown.
     *
     * @return an Enum BoatType, based on what's selected in the Combobox typeDropDown.
     */
    public BoatType getTypeDropDown() {
        return typeDropDown.getValue();
    }

    @Override
    public Scene getScene() {
        clearInfo();
        return scene;
    }
} // End of File
