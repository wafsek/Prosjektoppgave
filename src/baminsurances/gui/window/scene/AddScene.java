package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.GuiConfig;
import baminsurances.gui.window.MessageDialog;
import baminsurances.gui.window.OperationWindow;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Adrian Melsom
 */
public class AddScene extends GeneralScene{

    private Scene scene;
    private TextField firstNameField, lastNameField, birthNumberField,
            emailField, telephoneNumberField, adressField, zipCodeField,
            billingZipCodeField, billingAdressField;
    private Label firstNameLabel, lastNameLabel, birthNumberLabel, emailLabel,
            telephoneNumberLabel, adressLabel, zipCodeLabel,
            billingZipCodeLabel, billingAdressLabel, headerLabel;
    private ArrayList<TextField> textFieldArrayList;
    private Iterator<TextField> textFieldIterator;
    private CheckBox differentAdressCheck;
    private GridPane fieldBox;
    private TextArea printArea;
    private ScrollPane scrollPane;
    private Button registerPersonButton;
    private HBox headerContainer;
    private int adressCheckCounter = 0;

    /**
     * Creates a new Scene with the given values.
     *
     * @param guiEventHandler
     * @param keyPressHandler
     */
    public AddScene(GuiEventHandler guiEventHandler,
            KeyPressHandler keyPressHandler, String displayName) {
        super(guiEventHandler, keyPressHandler, displayName);

        firstNameField = new TextField();
        firstNameField.setStyle("-fx-effect: dropshadow"
                + "(three-pass-box, rgba(250, 0, 0, 250), 5, 0, 0, 0);");
        firstNameField.setOnKeyReleased(e -> fieldCheck(firstNameField));
        firstNameLabel = new Label("Fornavn:");
        lastNameField = new TextField();
        lastNameField.setStyle("-fx-effect: dropshadow"
                + "(three-pass-box, rgba(250, 0, 0, 250), 5, 0, 0, 0);");
        lastNameField.setOnKeyReleased(e -> fieldCheck(lastNameField));
        lastNameLabel = new Label("Etternavn:");
        birthNumberField = new TextField();
        birthNumberField.setStyle("-fx-effect: dropshadow"
                + "(three-pass-box, rgba(250, 0, 0, 250), 5, 0, 0, 0);");
        birthNumberField.setOnKeyReleased(e -> fieldCheck(birthNumberField));
        birthNumberLabel = new Label("Fødselsnummer:");
        emailField = new TextField();
        emailField.setStyle("-fx-effect: dropshadow"
                + "(three-pass-box, rgba(250, 0, 0, 250), 5, 0, 0, 0);");
        emailField.setOnKeyReleased(e -> fieldCheck(emailField));
        emailLabel = new Label("Mailadresse:");
        telephoneNumberField = new TextField();
        telephoneNumberField.setStyle("-fx-effect: dropshadow"
                + "(three-pass-box, rgba(250, 0, 0, 250), 5, 0, 0, 0);");
        telephoneNumberField.setOnKeyReleased(e -> fieldCheck(telephoneNumberField));
        telephoneNumberLabel = new Label("Telefonnummer:");
        adressField = new TextField();
        adressField.setStyle("-fx-effect: dropshadow"
                + "(three-pass-box, rgba(250, 0, 0, 250), 5, 0, 0, 0);");
        adressLabel = new Label("Adresse:");
        zipCodeField = new TextField();
        zipCodeField.setStyle("-fx-effect: dropshadow"
                + "(three-pass-box, rgba(250, 0, 0, 250), 5, 0, 0, 0);");
        zipCodeLabel = new Label("Postnummer:");

        headerLabel = new Label("Kunderegistrering");
        headerLabel.setStyle("-fx-padding: 5;" +
                "-fx-font: 28px Times;");
        headerContainer = new HBox(0, headerLabel);
        headerContainer.setStyle("-fx-border-color: gray;");
        headerContainer.setAlignment(Pos.CENTER);

        billingAdressField = new TextField();
        billingAdressField.setOnKeyReleased(e -> fieldCheck(billingAdressField));
        billingAdressField.setEditable(false);
        billingAdressLabel = new Label("Betalingsadresse:");
        billingZipCodeField = new TextField();
        billingZipCodeField.setOnKeyReleased(e -> fieldCheck(billingZipCodeField));
        billingZipCodeField.setEditable(false);
        billingZipCodeLabel = new Label("Postnr. for betaling:");

        textFieldArrayList = new ArrayList<TextField>();
        textFieldArrayList.add(firstNameField);
        textFieldArrayList.add(lastNameField);
        textFieldArrayList.add(birthNumberField);
        textFieldArrayList.add(emailField);
        textFieldArrayList.add(telephoneNumberField);
        textFieldArrayList.add(adressField);
        textFieldArrayList.add(zipCodeField);
        textFieldArrayList.add(billingAdressField);
        textFieldArrayList.add(billingZipCodeField);
        textFieldIterator = textFieldArrayList.iterator();

        differentAdressCheck = new CheckBox("Forskjellig betalingsadresse?");
        differentAdressCheck.setOnAction(ev -> fillAdressAndZipFields());

        registerPersonButton = new Button("Registrer");
        registerPersonButton.setOnAction(guiEventHandler);
        registerPersonButton.setDisable(true);

        fieldBox = new GridPane();
        fieldBox.addColumn(0, firstNameLabel, lastNameLabel, birthNumberLabel,
                emailLabel, telephoneNumberLabel, adressLabel, zipCodeLabel,
                differentAdressCheck, billingAdressLabel, billingZipCodeLabel);
        fieldBox.addColumn(1, firstNameField, lastNameField, birthNumberField,
                emailField, telephoneNumberField, adressField, zipCodeField,
                new Label(""), billingAdressField, billingZipCodeField,
                registerPersonButton);
        fieldBox.setAlignment(Pos.CENTER);
        fieldBox.setVgap(20);
        fieldBox.setHgap(40);
        fieldBox.setStyle("-fx-border-color: gray;");
        footerRightSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        footerLeftSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        footer = new HBox(0, footerLeftSide, footerRightSide);
        footer.setStyle("-fx-border-color: gray;");
        footer.setAlignment(Pos.CENTER_RIGHT);
        borderPane = new BorderPane(fieldBox, headerContainer, null, footer, null);
        scene = new Scene(borderPane);
    }

    /**
     * returns the scene made by this class.
     *
     * @return the scene made by this class.
     */
    public Scene getScene() {
        return scene;
    }

    private void fillAdressAndZipFields(){
        if (adressCheckCounter == 0) {
            adressField.setOnKeyReleased(e -> {
                billingAdressField.setText(adressField.getText());
                fieldCheck(adressField);
            });
            zipCodeField.setOnKeyReleased(e -> {
                billingZipCodeField.setText(zipCodeField.getText());
                fieldCheck(zipCodeField);
            });
            billingAdressField.setText(adressField.getText());
            billingAdressField.setEditable(false);
            billingAdressField.setStyle("");
            billingZipCodeField.setText(zipCodeField.getText());
            billingZipCodeField.setEditable(false);
            billingZipCodeField.setStyle("");
            adressCheckCounter++;

        } else {
            adressField.setOnKeyReleased(null);
            zipCodeField.setOnKeyReleased(null);
            billingAdressField.setText("");
            billingAdressField.setEditable(true);
            billingAdressField.setStyle("-fx-effect: dropshadow"
                    + "(three-pass-box, rgba(250, 0, 0, 250), 5, 0, 0, 0);");
            billingZipCodeField.setText("");
            billingZipCodeField.setEditable(true);
            billingZipCodeField.setStyle("-fx-effect: dropshadow"
                    + "(three-pass-box, rgba(250, 0, 0, 250), 5, 0, 0, 0);");
            adressCheckCounter--;
        }
        emptyFieldsCheck();
    }

    public void requestRegistration() {
        if (firstNameField.getText().trim().isEmpty() ||
                lastNameField.getText().trim().isEmpty() ||
                birthNumberField.getText().trim().isEmpty()) {
            new MessageDialog().showMessageDialog("Ugyldig informasjon!",
                    "Feltene markert med stjerne må være utfylt.",
                    MessageDialog.WARNING_ICON, MessageDialog.OK_OPTION);
        } else {
            /*(MethodForRegistrationOfACustomers(birthNumberField.getText(), name.getText(), lastNameField.getText(), telephoneNumberField.getText(), zipCodeField.getText(), adressField.getText()))? MessageDialog.showMessageDialog("Godkjent",
            name.getText() + "\s" + lastNameField.getText() + " er n� registrert.",
            MessageDialog.INFORMATION_ICON): MessageDialog.showMessageDialog(
            "Error", "Noe gikk galt under registreingen.", MessageDialog.ERROR_ICON);*/
        }
    }

    /**
     * Returns the Button that requests the registration of a person
     * defined by the given values.
     *
     * @return Button that requests the registration of a person
     * defined by the given values.
     */
    public Button getRegisterPersonButton() {
        return registerPersonButton;
    }

    /**
     * Returns a String of what is written in firstNameField.
     *
     * @return String of what is written in firstNameField.
     */
    public String getFirstNameFieldText() {
        return firstNameField.getText();
    }

    /**
     * Returns a String of what is written in lastNameField.
     *
     * @return String of what is written in lastNameField.
     */
    public String getLastNameFieldText() {
        return lastNameField.getText();
    }

    /**
     * Returns a String of what is written in birthNumberField.
     *
     * @return String of what is written in birthNumberField.
     */
    public String getBirthNumberFieldText() {
        return birthNumberField.getText();
    }

    /**
     * Returns a String of what is written in emailField.
     *
     * @return String of what is written in emailField.
     */
    public String getEmailFieldText() {
        return emailField.getText();
    }

    /**
     * Returns a String of what is written in telephoneNumberField.
     *
     * @return String of what is written in telephoneNumberField.
     */
    public String getTelephoneNumberFieldText() {
        return telephoneNumberField.getText();
    }

    /**
     * Returns a String of what is written in adressField.
     *
     * @return String of what is written in adresField.
     */
    public String getAdressFieldText() {
        return adressField.getText();
    }

    /**
     * Returns a String of what is written in zipCodeField.
     *
     * @return String of what is written in zipCodeField.
     */
    public String getZipCodeFieldText() {
        return zipCodeField.getText();
    }

    /**
     * Returns a String of what is written in billingZipCodeField.
     *
     * @return String of what is written in billingZipCodeField.
     */
    public String getBillingZipCodeFieldText() {
        return billingZipCodeField.getText();
    }

    /**
     * Returns a String of what is written in billingAdressField.
     *
     * @return String of what is written in billingAdressField.
     */
    public String getBillingAdressFieldText() {
        return billingAdressField.getText();
    }

    /**
     * Sets the text of the printArea to what the given String
     * contains.
     *
     * @param text
     */
    public void registerPerson(String text) {
        printArea.setText(text);
    }

    /**
     * Returns the Iterator of the TextFields in this class.
     *
     * @return Iterator of the TextFields in this class.
     */
    public Iterator getTextFieldIterator() {
        return textFieldIterator;
    }

    /**
     * Refreshes the Iterator of the TextFields in this class.
     */
    public void resetIterator() {
        textFieldIterator = textFieldArrayList.iterator();
    }

    /**
     * Iterates through the TextFields to check if they are empty or not.
     * If all are empty, the Button to register a person will not be
     * clickable. Else if they are all not empty, it will be clickable.
     */
    public void emptyFieldsCheck() {
        while(textFieldIterator.hasNext()) {
            if(textFieldIterator.next().getText().trim().isEmpty()) {
                registerPersonButton.setDisable(true);
                resetIterator();
                return;
            }
        }
        resetIterator();
        registerPersonButton.setDisable(false);
    }

    private void fieldCheck(TextField textField){
        if (textField.getText().trim().isEmpty()) {
            textField.setStyle("-fx-effect: dropshadow"
                    + "(three-pass-box, rgba(250, 0, 0, 250), 5, 0, 0, 0);");
            this.emptyFieldsCheck();
        } else {
            textField.setStyle("");
            this.emptyFieldsCheck();
        }
    }

    public void insertText(String[] infoArray){
        TextField[] textFieldsArray = new TextField[] {firstNameField, lastNameField,
                birthNumberField, adressField,
                zipCodeField};
        for(int i = 0; i<infoArray.length; i++){
            textFieldsArray[i].setText(infoArray[i]);
            fieldCheck(textFieldsArray[i]);
        }
        fillAdressAndZipFields();
    }
}