package baminsurances.gui.window.scene;

import baminsurances.data.CarType;
import baminsurances.data.Person;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.DifferentVehicleOwnerWindow;
import baminsurances.gui.window.GuiConfig;
import baminsurances.gui.window.MessageDialog;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.time.LocalDate;

/**
 * Created by Adrian on 14/05/2015.
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

    public CarInsuranceScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler, String displayName){
        super(guiEventHandler, keyPressHandler, displayName);
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
        productionYearDropDown = new ComboBox<>(FXCollections.observableArrayList(years()));

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
        scene = new Scene(borderPane);
    }

    private String[] years() {
        int i = LocalDate.now().getYear();
        String[] returnValue = new String[LocalDate.now().getYear() - 1924];
        for (int j = 0; j < returnValue.length; j++) {
            returnValue[j] = ""+i--;
        }
        return returnValue;
    }

    public String getRegistrationNumberFieldText() {
        return registrationNumberField.getText();
    }

    public String getCarModelFieldText() {
        return carModelField.getText();
    }

    public String getAnnualMilageFieldText() {
        return annualMilageField.getText();
    }

    public String getPricePerKilometerFieldText() {
        return pricePerKilometerField.getText();
    }

    public String getBonusPercentageFieldText() {
        return bonusPercentageField.getText();
    }

    public CarType getCarTypeDropDownSelectedValue() {
        return carTypeDropDown.getValue();
    }

    public String getProductionYearSelectedValue() {
        return productionYearDropDown.getValue();
    }
    public String getCarBrandDropDownSelectedValue() {
        return carBrandDropDown.getValue();
    }

    public ComboBox<CarType> getCarTypeDropDown() {
        return carTypeDropDown;
    }

    public ComboBox<String> getCarBrandDropDown() {
        return carBrandDropDown;
    }

    public Person getPerson() {
        return person;
    }
}
