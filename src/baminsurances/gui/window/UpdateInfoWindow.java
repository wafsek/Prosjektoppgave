package baminsurances.gui.window;

import baminsurances.api.Validation;
import baminsurances.data.Customer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by Adrian on 15/05/2015.
 */
public class UpdateInfoWindow {

    private Stage stage;
    private Scene scene;

    private Label telephoneNoLabel, emailLabel, addressLabel, paymentAddressLabel;
    private TextField telephoneNoField, emailField, addressField, billingAddressField;

    private Button updateInfoButton;

    private GridPane gridPane;

    public UpdateInfoWindow() {
        stage = new Stage();
        telephoneNoLabel = new Label("Nytt telefonnummer:");
        emailLabel = new Label("Ny email:");
        addressLabel = new Label("Ny adresse:");
        paymentAddressLabel = new Label("Ny betelingsadresse:");

        telephoneNoField = new TextField();
        emailField = new TextField();
        addressField = new TextField();
        billingAddressField = new TextField();

        updateInfoButton = new Button("Oppdater informasjon");

        gridPane = new GridPane();
        gridPane.addColumn(0, telephoneNoLabel, emailLabel, addressLabel, paymentAddressLabel);
        gridPane.addColumn(1, telephoneNoField, emailField, addressField, billingAddressField, updateInfoButton);

        scene = new Scene(gridPane);
        stage.setScene(scene);
    }

    public Customer updateCustomerInfo(Customer customer) {
        telephoneNoField.setText(customer.getTelephoneNo());
        emailField.setText(customer.getEmail());
        addressField.setText(customer.getStreetAddress());
        billingAddressField.setText(customer.getBillingStreetAddress());
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
            }
            stage.close();
        });
        stage.showAndWait();
        return customer;
    }
}
