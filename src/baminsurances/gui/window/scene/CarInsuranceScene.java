package baminsurances.gui.window.scene;

import baminsurances.data.CarType;
import baminsurances.data.NameDisplayable;
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

/**
 * Created by Adrian on 14/05/2015.
 */
public class CarInsuranceScene extends InsuranceScene{

    private Label registrationNumberLabel, carTypeLabel, carBrandLabel, carModelLabel,
            productionYearLabel, annualMilageLabel, pricePerKilometerLabel,
            bonusPercentageLabel;
    private TextField registrationNumberField, carModelField, productionYearField, annualMilageField, pricePerKilometerField,
            bonusPercentageField;
    private ComboBox<String> carTypeDropDown, carBrandDropDown;
    private CheckBox ownerBox;
    private Person person;
    private boolean ownerBoxIsSelected;

    public CarInsuranceScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler, String displayName){
        super(guiEventHandler, keyPressHandler, displayName);
        insuranceDropDown.setOnAction(null);
        insuranceDropDown.setValue("Bilforsikring");
        insuranceDropDown.setOnAction(guiEventHandler);
        annualPremiumField.setEditable(true);
        insuranceValueField.setEditable(true);
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

        carTypeDropDown = new ComboBox<>(FXCollections.observableArrayList(getFillingArray(CarType.values())));

        registrationNumberLabel = new Label("Registreringsnummer:");
        carTypeLabel = new Label("Biltype:");
        carBrandLabel = new Label("Bilmerke:");
        carModelLabel = new Label("Bilmodell:");
        productionYearLabel = new Label("Produksjons�r:");
        annualMilageLabel = new Label("�rlig kilometer");
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
                new MessageDialog().showMessageDialog("Slettet", "Eieren er n� " +
                        "satt tilbake p� kunden som blir behandlet.",
                        MessageDialog.INFORMATION_ICON);
                person = null;
                ownerBoxIsSelected = !ownerBoxIsSelected;
            }
        });

        rightSideFieldContainer.addColumn(0, ownerBox, registrationNumberLabel, carTypeLabel,
                carBrandLabel, carModelLabel, productionYearLabel, annualMilageLabel,
                pricePerKilometerLabel, bonusPercentageLabel);
        rightSideFieldContainer.addColumn(1, new Label(""), registrationNumberField, carTypeDropDown,
                carBrandDropDown, carModelField, productionYearField, annualMilageField,
                pricePerKilometerField, bonusPercentageField);

        rightSideBorderPane = new BorderPane(rightSideFieldContainer, rightSideHeader, null, rightSideFooter, null);
        rightSideBorderPane.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);

        borderPane = new BorderPane(leftSideBorderPane, null, rightSideBorderPane, footer, null);
        scene = new Scene(borderPane);
    }

    public String getRegistrationNumberFieldText() {
        return registrationNumberField.getText();
    }

    public String getCarModelFieldText() {
        return carModelField.getText();
    }

    public String getProductionYearFieldText() {
        return productionYearField.getText();
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

    public String getCarTypeDropDownSelectedValue() {
        return carTypeDropDown.getValue();
    }

    public String getCarBrandDropDownSelectedValue() {
        return carBrandDropDown.getValue();
    }

    public ComboBox<String> getCarTypeDropDown() {
        return carTypeDropDown;
    }

    public ComboBox<String> getCarBrandDropDown() {
        return carBrandDropDown;
    }

    public Person getPerson() {
        return person;
    }
}