package baminsurances.gui.window;

import baminsurances.api.Validation;
import baminsurances.controller.DataControl;
import baminsurances.data.Person;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * A window for use when a vehicle insurance has a different owner than the
 * buyer of the insurance.
 * 
 * @author Adrian Melsom
 */
public class DifferentVehicleOwnerWindow {

    private Stage stage;
    private Scene scene;

    private Label birthNoLabel, firstNameLabel, lastNameLabel, telephoneNoLabel,
            emailLabel, zipCodeLabel, streetAddressLabel;
    private TextField birthNoField, firstNameField, lastNameField, telephoneNoField,
            emailField, zipCodeField, streetAddressField;
    private Button registerOwnerButton;

    private GridPane container;

    private Person person;

    /**
     * Creates a new window for
     */
    public DifferentVehicleOwnerWindow() {
        stage = new Stage();
        person = null;

        birthNoLabel = new Label("Fødselsnummer:");
        firstNameLabel = new Label("Fornavn:");
        lastNameLabel = new Label("Etternavn:");
        telephoneNoLabel = new Label("Telefonnummer:");
        emailLabel = new Label("Email:");
        zipCodeLabel = new Label("Postnummer:");
        streetAddressLabel = new Label("Adresse:");

        birthNoField = new TextField();
        firstNameField = new TextField();
        lastNameField = new TextField();
        telephoneNoField = new TextField();
        emailField = new TextField();
        zipCodeField = new TextField();
        streetAddressField = new TextField();

        registerOwnerButton = new Button("Registrer eier");

        container = new GridPane();
        container.addColumn(0, birthNoLabel, firstNameLabel, lastNameLabel,
                telephoneNoLabel, emailLabel, zipCodeLabel,
                streetAddressLabel);
        container.addColumn(1, birthNoField, firstNameField, lastNameField,
                telephoneNoField, emailField, zipCodeField, streetAddressField,
                registerOwnerButton);
        scene = new Scene(container);

        stage.setScene(scene);
    }

    /**
     * Attempts to create a new person with the current values in the input
     * fields, and returns this person if it was a success.
     * 
     * @return a person if the none of the inputs contained illegal values,
     * <code>null</code> otherwise
     */
    public Person registerOwner() {
        registerOwnerButton.setOnAction(e -> {
            if (validatePersonData() == DataControl.SUCCESS) {
                String birthNo = birthNoField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String telephoneNo = telephoneNoField.getText();
                String email = emailField.getText();
                String zipCode = zipCodeField.getText();
                String streetAddress = streetAddressField.getText();
                person = new Person(birthNo, firstName, lastName, telephoneNo,
                        email, zipCode, streetAddress);
                MessageDialog.showMessageDialog("Registrert",
                        "Du har nå registrert en person ny eier av bilen.",
                        MessageDialog.INFORMATION_ICON,
                        MessageDialog.OK_OPTION);
                stage.close();
            } else {
                if (MessageDialog.showMessageDialog("Ugyldig",
                        "Ugyldig informasjon. Ønsker du å gjøre om?",
                        MessageDialog.INFORMATION_ICON,
                        MessageDialog.YES__NO_OPTION) ==
                            MessageDialog.YES_OPTION) {
                    // do nothing after closing dialog
                } else {
                    stage.close();
                }
            }
        });
        stage.showAndWait();
        return person;
    }

    /**
     * Validates the input, and returns a {@link
     * baminsurances.controller.DataControl} enum representing the result.
     *  
     * @return a {@link baminsurancs.controller.DataControl} enum representing
     * the result
     */
    private DataControl validatePersonData(){
        if (!Validation.isValidFirstName(firstNameField.getText())) {
            System.out.println("Fn");
            return DataControl.INVALID_FIRST_NAME;
        } else if (!Validation.isValidLastName(lastNameField.getText())) {
            System.out.println("Ln");
            return DataControl.INVALID_LAST_NAME;
        } else if (!Validation.isValidBirthNo(birthNoField.getText())) {
            System.out.println("Bnr");
            return DataControl.INVALID_BIRTHNO;
        } else if (!Validation.isValidEmail(emailField.getText())) {
            System.out.println("mail");
            return DataControl.INVALID_EMAIL;
        } else if (!Validation.isValidTelephoneNo(telephoneNoField.getText())) {
            System.out.println("rlf");
            return DataControl.INVALID_TLF;
        } else if (!Validation.isValidStreetAddress(streetAddressField.getText())) {
            System.out.println("strt");
            return DataControl.INVALID_ADDRESS;
        } else if (!Validation.isValidZipCode(zipCodeField.getText())) {
            System.out.println("zip");
            return DataControl.INVALID_ZIPCODE;
        } else {
            return DataControl.SUCCESS;
        }
    }
}
