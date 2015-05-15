package baminsurances.gui.window;

import baminsurances.api.Validation;
import baminsurances.controller.DataControl;
import baminsurances.data.Person;
import baminsurances.gui.eventhandler.GuiEventHandler;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by Adrian on 14/05/2015.
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

    private static IntegerProperty returnCode;

    public DifferentVehicleOwnerWindow() {
        stage = new Stage();
        person = null;
        returnCode = new SimpleIntegerProperty(-1);

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
        container.addColumn(0, birthNoLabel, firstNameLabel, lastNameLabel, telephoneNoLabel,
                emailLabel, zipCodeLabel, streetAddressLabel);
        container.addColumn(1, birthNoField, firstNameField, lastNameField, telephoneNoField,
                emailField, zipCodeField, streetAddressField, registerOwnerButton);
        scene = new Scene(container);

        stage.setScene(scene);
    }

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
                person = new Person(birthNo, firstName, lastName, telephoneNo, email, zipCode, streetAddress);
                new MessageDialog().showMessageDialog("Registrert", "Du har nå registrert en person ny eier av bilen.",
                    MessageDialog.INFORMATION_ICON, MessageDialog.OK_OPTION);
                stage.close();
            } else {
                if (new MessageDialog().showMessageDialog("Ugyldig", "Ugyldig informasjon. Ønsker du å gjøre om?",
                        MessageDialog.INFORMATION_ICON, MessageDialog.YES__NO_OPTION) == MessageDialog.YES_OPTION) {

                } else {
                    stage.close();
                }
            }
        });
        stage.showAndWait();
        return person;
    }

    private DataControl validatePersonData(){

        if(!Validation.isValidFirstName(firstNameField.getText())){
            System.out.println("Fn");
            return DataControl.INVALID_FIRST_NAME;
        }else if(!Validation.isValidLastName(lastNameField.getText())){
            System.out.println("Ln");
            return DataControl.INVALID_LAST_NAME;
        }else if(!Validation.isValidBirthNo(birthNoField.getText())){
            System.out.println("Bnr");
            return DataControl.INVALID_BIRTHNO;
        }else if(!Validation.isValidEmail(emailField.getText())){
            System.out.println("mail");
            return DataControl.INVALID_EMAIL;
        }else if(!Validation.isValidTelephoneNo(telephoneNoField.getText())){
            System.out.println("rlf");
            return DataControl.INVALID_TLF;
        }else if(!Validation.isValidStreetAddress(streetAddressField.getText())){
            System.out.println("strt");
            return DataControl.INVALID_ADDRESS;
        }else if(!Validation.isValidZipCode(zipCodeField.getText())){
            System.out.println("zip");
            return DataControl.INVALID_ZIPCODE;
        } else {
            return DataControl.SUCCESS;
        }
    }
}
