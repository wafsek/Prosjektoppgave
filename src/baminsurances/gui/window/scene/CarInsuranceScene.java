package baminsurances.gui.window.scene;

import baminsurances.data.CarType;
import baminsurances.data.Person;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.DifferentVehicleOwnerWindow;
import baminsurances.gui.window.GuiConfig;
import baminsurances.gui.window.MessageDialog;
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
 * Created by Adrian on 14/05/2015.
 * @author Adrian Melsom
 */
public class CarInsuranceScene extends InsuranceScene{

    private Label registrationNumberLabel, carTypeLabel, carBrandLabel, carModelLabel,
            annualMilageLabel, pricePerKilometerLabel, productionYearLabel,
            bonusPercentageLabel;
    private TextField registrationNumberField, carModelField, productionYearField, annualMilageField, pricePerKilometerField,
            bonusPercentageField;
    private ComboBox<String> carBrandDropDown, productionYearDropDown;
    private ComboBox<CarType> carTypeDropDown;
    private CheckBox ownerBox;
    private Person person;
    private boolean ownerBoxIsSelected;

    /**
     * Creates the components used in this class.
     *
     * @param guiEventHandler
     * @param keyPressHandler
     */
    public CarInsuranceScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler){
        super(guiEventHandler, keyPressHandler);
        annualPremiumField.setEditable(true);
        insuranceValueField.setEditable(true);
        paymentFrequencyDropDown.setDisable(false);
        conditionArea.setEditable(true);
        registerInsuranceButton.setDisable(false);
        leftSideContentContainer.getChildren().remove(discribtionContainer);

        ownerBoxIsSelected = false;
        person = null;

        carBrandDropDown = new ComboBox<>(FXCollections.observableArrayList("Alfa Romeo",
                "Aston Martin", "Audi", "Austin", "Bentley", "BMW", "Buddy",
                "Buick", "Cadillac", "Chevrolet", "Chrysler", "Citroen",
                "Dacia", "Daewoo", "Daihatsu", "Datsun", "DeLorean", "Dodge",
                "Ferrari", "Fiat", "Fisker", "Ford", "GMC", "Honda", "Hummer",
                "Hyundai", "Infiniti", "Isuzu", "Iveco", "Jaguar", "Jeep",
                "Jensen", "Kewet", "Kia", "Lada", "Lamborghini", "Lancia",
                "Land Rover", "Lexus", "Lincoln", "Lotus", "Maserati", "Mazda",
                "McLaren", "Mercedes-Benz", "Mercury", "MG", "mia electric",
                "MINI", "Mitsubishi", "Morgan", "Morris", "Nissan", "Oldsmobile",
                "Opel", "Peugeot", "Plymouth", "Pontiac", "Porsche", "Renault",
                "Reva", "Rolls Royce", "Rover", "Saab", "Seat", "Skoda", "Smart",
                "Ssangyong", "Subaru", "Suzuki", "Tazzari", "Tesla", "Think",
                "Toyota", "Triumph", "Volkswagen", "Volvo", "Andre"));

        carTypeDropDown = new ComboBox<>();
        carTypeDropDown.getItems().setAll(CarType.values());
        productionYearDropDown = new ComboBox<>(FXCollections.observableArrayList(fillArray(LocalDate.now().getYear(), 1925)));

        registrationNumberLabel = new Label("Registreringsnummer:");
        productionYearLabel = new Label("Produksjonsår:");
        carTypeLabel = new Label("Biltype:");
        carBrandLabel = new Label("Bilmerke:");
        carModelLabel = new Label("Bilmodell:");
        annualMilageLabel = new Label("Årlig kilometer");
        pricePerKilometerLabel = new Label("Pris per kilometer:");
        bonusPercentageLabel = new Label("Bonusprosent i heltall:");

        registrationNumberField = new TextField();
        carModelField = new TextField();
        productionYearField = new TextField();
        annualMilageField = new TextField();
        pricePerKilometerField = new TextField();
        bonusPercentageField = new TextField();
        registrationNumberField.setPromptText("Eks: AB12345");

        ownerBox = new CheckBox("Annen eier?");
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
                        MessageDialog.INFORMATION_ICON);
                person = null;
                ownerBoxIsSelected = !ownerBoxIsSelected;
            }
        });

        rightSideFieldContainer.addColumn(0, ownerBox, registrationNumberLabel, carTypeLabel,
                carBrandLabel, carModelLabel, productionYearLabel, annualMilageLabel,
                pricePerKilometerLabel, bonusPercentageLabel);
        rightSideFieldContainer.addColumn(1, new Label(""), registrationNumberField, carTypeDropDown,
                carBrandDropDown, carModelField, productionYearDropDown, annualMilageField,
                pricePerKilometerField, bonusPercentageField);

        rightSideBorderPane = new BorderPane(rightSideFieldContainer, rightSideHeader, null, rightSideFooter, null);
        rightSideBorderPane.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);

        borderPane = new BorderPane(leftSideBorderPane, null, rightSideBorderPane, footer, null);
        borderPane.backgroundProperty().setValue(
                new Background(new BackgroundFill(Color.web("D7EBE6"),
                        CornerRadii.EMPTY, Insets.EMPTY) ));
        scene = new Scene(borderPane);
    }

    /**
     * Returns a String of the value from registrationNumberField.
     *
     * @return a String of the value from registrationNumberField.
     */
    public String getRegistrationNumberFieldText() {
        return registrationNumberField.getText();
    }

    /**
     * Returns a String of the value from carModelField.
     *
     * @return a String of the value from carModelField.
     */
    public String getCarModelFieldText() {
        return carModelField.getText();
    }

    /**
     * Returns a String of the value from annualMilageField.
     *
     * @return a String of the value from annualMilageField.
     */
    public String getAnnualMilageFieldText() {
        return annualMilageField.getText();
    }

    /**
     * Returns a String of the value from pricePerKilometerField.
     *
     * @return a String of the value from pricePerKilometerField.
     */
    public String getPricePerKilometerFieldText() {
        return pricePerKilometerField.getText();
    }

    /**
     * Returns a String of the value from bonusPercentageField.
     *
     * @return a String of the value from bonusPercentageField.
     */
    public String getBonusPercentageFieldText() {
        return bonusPercentageField.getText();
    }

    /**
     * Returns an enum of type CarType.
     *
     * @return an enum of type CarType.
     */
    public CarType getCarTypeDropDownSelectedValue() {
        return carTypeDropDown.getValue();
    }

    /**
     * Returns a String of the value from productionYearField.
     *
     * @return a String of the value from productionYearField.
     */
    public String getProductionYearSelectedValue() {
        return productionYearDropDown.getValue();
    }

    /**
     * Returns a String of the value from carBrandDropDown.
     *
     * @return a String of the value from carBrandDropDown.
     */
    public String getCarBrandDropDownSelectedValue() {
        return carBrandDropDown.getValue();
    }

    /**
     * Clears the information in the given components.
     */
    public void clearInfo() {
        super.clearInfo();
        registrationNumberField.setText("");
        carModelField.setText("");
        productionYearField.setText("");
        annualMilageField.setText("");
        pricePerKilometerField.setText("");
        bonusPercentageField.setText("");
        carBrandDropDown.setValue("");
        productionYearDropDown.setValue("");
        ownerBox.setSelected(false);
        person = null;
    }

    @Override
    public Scene getScene() {
        clearInfo();
        return scene;
    }

    /**
     * Returns a person Object or null.
     *
     * @return a person Object or null.
     */
    public Person getPerson() {
        return person;
    }
} // End of File
