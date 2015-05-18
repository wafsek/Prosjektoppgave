package baminsurances.gui.window;

import baminsurances.api.Config;
import baminsurances.api.Validation;
import baminsurances.data.*;
import baminsurances.gui.window.scene.InsuranceScene;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * Created by Adrian on 15/05/2015.
 */
public class UpdateInfoWindow {

    private Stage stage = new Stage();
    private Scene scene;
    private Button updateInfoButton = new Button("Oppdater informasjon");
    private Image image;

    private GridPane gridPane;

    public UpdateInfoWindow() {
        image = new Image(
                this.getClass().getResourceAsStream("../img/temp_logo.png"));
    }

    public Customer updateCustomerInfo(Customer customer) {
        stage.getIcons().add(image);
        stage.setTitle("Oppdater informasjon");

        Label telephoneNoLabel, emailLabel, addressLabel, paymentAddressLabel,
                zipCodeLabel, paymentZipCodeLabel;
        TextField telephoneNoField, emailField, addressField, billingAddressField,
                zipCodeField, billingZipCodeField;

        telephoneNoLabel = new Label("Nytt telefonnummer:");
        emailLabel = new Label("Ny email:");
        addressLabel = new Label("Ny adresse:");
        paymentAddressLabel = new Label("Ny betelingsadresse:");
        zipCodeLabel = new Label("Postnummer:");
        paymentZipCodeLabel = new Label("postNr. for betalingsadresse:");

        telephoneNoField = new TextField(customer.getTelephoneNo());
        emailField = new TextField(customer.getEmail());
        addressField = new TextField(customer.getStreetAddress());
        billingAddressField = new TextField(customer.getBillingStreetAddress());
        zipCodeField = new TextField(customer.getZipCode());
        billingZipCodeField = new TextField(customer.getBillingZipCode());

        gridPane = new GridPane();
        gridPane.addColumn(0, telephoneNoLabel, emailLabel, addressLabel, zipCodeLabel,
                paymentAddressLabel, paymentZipCodeLabel);
        gridPane.addColumn(1, telephoneNoField, emailField, addressField, zipCodeField,
                billingAddressField, billingZipCodeField, updateInfoButton);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(20);
        gridPane.setStyle("-fx-border-color: gray;" +
                "-fx-padding: 10;");

        updateInfoButton.setOnAction(e -> {
            if (!telephoneNoField.getText().trim().isEmpty() &&
                    Validation.isValidTelephoneNo(telephoneNoField.getText())) {
                customer.setTelephoneNo(telephoneNoField.getText());
            }
            if (!emailField.getText().trim().isEmpty() &&
                    Validation.isValidEmail(emailField.getText())) {
                customer.setEmail(emailField.getText());
            }
            if (!addressField.getText().trim().isEmpty() &&
                    Validation.isValidStreetAddress(addressField.getText())) {
                customer.setStreetAddress(addressField.getText());
            }
            if (!billingAddressField.getText().trim().isEmpty() &&
                    Validation.isValidStreetAddress(billingAddressField.getText())) {
                customer.setBillingStreetAddress(billingAddressField.getText());
            } if (!zipCodeField.getText().trim().isEmpty() &&
                    Validation.isValidZipCode(zipCodeField.getText())) {
                customer.setZipCode(zipCodeField.getText());
            } if (!billingZipCodeField.getText().trim().isEmpty() &&
                    Validation.isValidZipCode(billingZipCodeField.getText())) {
                customer.setBillingZipCode(billingZipCodeField.getText());
            }
            stage.close();
        });

        scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.showAndWait();
        return customer;
    }

    public Insurance updateInsurance(Insurance insurance) {
        stage.getIcons().add(image);
        stage.setTitle("Oppdater informasjon");

        Label annualPremuimLabel, amountLabel, termsLabel;
        TextField annualPremuimField, amountField;
        TextArea termsArea;
        CheckBox cancelBox;

        annualPremuimLabel = new Label("Årlig premie:");
        amountLabel = new Label("Forsikringsbeløp:");
        termsLabel = new Label("Vilkår:");

        annualPremuimField = new TextField(""+insurance.getAnnualPremium());
        amountField = new TextField(""+insurance.getAmount());
        termsArea = new TextArea(insurance.getTerms());
        termsArea.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 4);
        termsArea.setStyle("fx-border-color: gray");

        cancelBox = new CheckBox("Gjør innaktiv?");

        updateInfoButton.setOnAction(e -> {
            if (!annualPremuimField.getText().trim().isEmpty()) {
                insurance.setAnnualPremium(Integer.parseInt(annualPremuimField.getText()));
            }
            if (!amountField.getText().trim().isEmpty()) {
                insurance.setAmount(Integer.parseInt(amountField.getText()));
            }
            if (!termsArea.getText().trim().isEmpty()) {
                insurance.setTerms(termsArea.getText());
            }
            if (cancelBox.isSelected()) {
                insurance.setCancellationDate(LocalDate.now());
            }

            stage.close();
        });

        gridPane = new GridPane();
        gridPane.addColumn(0, annualPremuimLabel, amountLabel, termsLabel);
        gridPane.addColumn(1, annualPremuimField, amountField);
        gridPane.add(termsArea, 0, 4, 2, 2);
        if (insurance.getCancellationDate() == null) {
            gridPane.add(cancelBox, 0, 6, 1, 1);
        }
        gridPane.add(updateInfoButton, 1, 6, 1, 1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(20);
        gridPane.setStyle("-fx-border-color: gray;" +
                "-fx-padding: 10;");

        scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.showAndWait();
        return insurance;
    }
}
