package baminsurances.gui.window;

import baminsurances.api.Validation;
import baminsurances.data.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * A window used for updating the information of a single customer or
 * insurance.
 * 
 * @author Adrian Melsom
 */
public class UpdateInfoWindow {

    private Stage stage = new Stage();
    private Scene scene;
    private Button updateInfoButton = new Button("Oppdater informasjon");
    private Image image;

    private GridPane gridPane;

    /**
     * Creates a new update info window.
     */
    public UpdateInfoWindow() {
        image = new Image(
                this.getClass().getClassLoader().getResourceAsStream("temp_logo.png"));
    }

    /**
     * Validates the input fields, and returns the attemptedly altered
     * customer.
     * 
     * @param customer the customer to alter
     * @return the attemptedly altered customer
     */
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
        gridPane.addColumn(0, telephoneNoLabel, emailLabel, addressLabel,
                zipCodeLabel, paymentAddressLabel, paymentZipCodeLabel);
        gridPane.addColumn(1, telephoneNoField, emailField, addressField,
                zipCodeField, billingAddressField, billingZipCodeField,
                updateInfoButton);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(20);
        gridPane.setStyle("-fx-border-color: gray;" +
                "-fx-padding: 10;");

        updateInfoButton.setOnAction(e -> {
            String message = "";
            if (!telephoneNoField.getText().trim().isEmpty() &&
                    Validation.isValidTelephoneNo(telephoneNoField.getText())) {
                customer.setTelephoneNo(telephoneNoField.getText());
            } else {
                message += "Ugyldig info i telefonfeltet.\n";
            }
            if (!emailField.getText().trim().isEmpty() &&
                    Validation.isValidEmail(emailField.getText())) {
                customer.setEmail(emailField.getText());
            } else {
                message += "Ugyldig info i emailfeltet.\n";
            }
            if (!addressField.getText().trim().isEmpty() &&
                    Validation.isValidStreetAddress(addressField.getText())) {
                customer.setStreetAddress(addressField.getText());
            } else {
                message += "Ugyldig info i adressefeltet.\n";
            }
            if (!billingAddressField.getText().trim().isEmpty() &&
                    Validation.isValidStreetAddress(billingAddressField.getText())) {
                customer.setBillingStreetAddress(billingAddressField.getText());
            } else {
                message += "Ugyldig info i betalingsadressefeltet.\n";
            }
            if (!zipCodeField.getText().trim().isEmpty() &&
                    Validation.isValidZipCode(zipCodeField.getText())) {
                customer.setZipCode(zipCodeField.getText());
            } else {
                message += "Ugyldig info i postnummerfeltet.\n";
            }
            if (!billingZipCodeField.getText().trim().isEmpty() &&
                    Validation.isValidZipCode(billingZipCodeField.getText())) {
                customer.setBillingZipCode(billingZipCodeField.getText());
            } else {
                message += "Ugyldig info i betalingspostnummer-feltet.";
            } if (message.trim().isEmpty()) {
                MessageDialog.showMessageDialog("Informasjon", "Kundeinformasjon er oppdatert!",
                        MessageDialog.INFORMATION_ICON, MessageDialog.OK_OPTION);
                stage.close();
            } else {
                MessageDialog.showMessageDialog("Informasjon", message,
                        MessageDialog.INFORMATION_ICON, MessageDialog.OK_OPTION);
            }
        });

        scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.showAndWait();
        return customer;
    }

    /**
     * Validates the input fields, and returns the attemptedly
     * altered insurance.
     * 
     * @param insurance the insurance to alter
     * @return the attemptedly altered insurance
     */
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
            String message = "";
            if (!annualPremuimField.getText().trim().isEmpty() && Validation.consistsOnlyOfNumbers(annualPremuimField.getText())) {
                insurance.setAnnualPremium(Integer.parseInt(
                        annualPremuimField.getText()));
            } else {
                message += "Ugylig info i premiumfeltet.\n";
            }
            if (!amountField.getText().trim().isEmpty() && Validation.consistsOnlyOfNumbers(amountField.getText())) {
                insurance.setAmount(Integer.parseInt(amountField.getText()));
            } else {
                message += "Ugyldig info i Forsikringsbeløpfeltet.\n";
            }
            if (!termsArea.getText().trim().isEmpty()) {
                insurance.setTerms(termsArea.getText());
            } else {
                message += "Ugyldig info i vilkårfeltet.";
            }
            if (cancelBox.isSelected()) {
                insurance.setCancellationDate(LocalDate.now());
            }

            if(message.equals("")) {
                MessageDialog.showMessageDialog("Informasjon", "Forsikringsinformasjonen er oppdatert!", MessageDialog.INFORMATION_ICON, MessageDialog.OK_OPTION);
                stage.close();
            } else {
                MessageDialog.showMessageDialog("Informasjon", message, MessageDialog.INFORMATION_ICON, MessageDialog.OK_OPTION);
            }

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
        gridPane.backgroundProperty().setValue(
                new Background(new BackgroundFill(Color.web("D7EBE6"),
                        CornerRadii.EMPTY, Insets.EMPTY)));

        scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.showAndWait();
        return insurance;
    }
}
