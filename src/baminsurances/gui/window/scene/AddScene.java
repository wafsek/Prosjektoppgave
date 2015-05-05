package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
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
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Adrian on 15/04/2015.
 * @author Adrian
 */
public class AddScene {

    private Scene scene;
    private TextField firstNameField, lastNameField, birthNumberField, emailField,
            telephoneNumberField, adressField, zipCodeField, billingZipCodeField, billingAdressField;
    private Label firstNameLabel, lastNameLabel, birthNumberLabel, emailLabel, telephoneNumberLabel,
            adressLabel, zipCodeLabel, billingZipCodeLabel, billingAdressLabel;
    private ArrayList<TextField> textFieldArrayList;
    private Iterator<TextField> textFieldIterator;
    private CheckBox differentAdressCheck;
    private BorderPane borderPane;
    private GridPane fieldBox;
    private TextArea printArea;
    private ScrollPane scrollPane;
    private Button registerPersonButton;
    private GuiEventHandler handler;
    private int adressCheckCounter = 0;
    private int test = 0;

    /**
     * Creates a new Scene with the given values.
     * @param header
     * @param footer
     * @param handler
     */
    public AddScene(HBox header, HBox footer, GuiEventHandler handler, KeyPressHandler keyPressHandler) {
        this.handler = handler;

        firstNameField = new TextField();
        firstNameField.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(250, 0, 0, 250), 5, 0, 0, 0);");
        firstNameField.setOnKeyReleased(e -> {
            if (firstNameField.getText().trim().isEmpty()) {
                firstNameField.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(250, 0, 0, 250), 5, 0, 0, 0);");
                this.emptyFieldsCheck();
            } else {
                firstNameField.setStyle("");
                this.emptyFieldsCheck();
            }
        });
        firstNameLabel = new Label("Fornavn:");
        lastNameField = new TextField();
        lastNameField.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(250, 0, 0, 250), 5, 0, 0, 0);");
        lastNameLabel = new Label("Etternavn:");
        birthNumberField = new TextField();
        birthNumberField.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(250, 0, 0, 250), 5, 0, 0, 0);");
        birthNumberLabel = new Label("Fødselsnummer:");
        emailField = new TextField();
        emailField.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(250, 0, 0, 250), 5, 0, 0, 0);");
        emailLabel = new Label("Mailadresse:");
        telephoneNumberField = new TextField();
        telephoneNumberField.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(250, 0, 0, 250), 5, 0, 0, 0);");
        telephoneNumberLabel = new Label("Telefonnummer:");
        adressField = new TextField();
        adressField.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(250, 0, 0, 250), 5, 0, 0, 0);");
        adressLabel = new Label("Adresse:");
        zipCodeField = new TextField();
        zipCodeField.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(250, 0, 0, 250), 5, 0, 0, 0);");
        zipCodeLabel = new Label("Postnummer:");
        billingAdressField = new TextField();
        billingAdressField.setEditable(false);
        billingAdressLabel = new Label("Betalingsadresse:");
        billingZipCodeField = new TextField();
        billingZipCodeField.setEditable(false);
        billingZipCodeLabel = new Label("Postnr. for betaling:");
        adressField.setOnKeyReleased(e -> billingAdressField.setText(adressField.getText()));
        zipCodeField.setOnKeyReleased(e -> billingZipCodeField.setText(zipCodeField.getText()));

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
        differentAdressCheck.setOnAction(ev -> {
            if (adressCheckCounter == 0) {
                adressField.setOnKeyReleased(null);
                zipCodeField.setOnKeyReleased(null);
                billingAdressField.setText("");
                billingAdressField.setEditable(true);
                billingAdressField.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(250, 0, 0, 250), 5, 0, 0, 0);");
                billingZipCodeField.setText("");
                billingZipCodeField.setEditable(true);
                billingZipCodeField.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(250, 0, 0, 250), 5, 0, 0, 0);");
                adressCheckCounter++;
            } else {
                adressField.setOnKeyReleased(e -> billingAdressField.setText(adressField.getText()));
                zipCodeField.setOnKeyReleased(e -> billingZipCodeField.setText(zipCodeField.getText()));
                billingAdressField.setText(adressField.getText());
                billingAdressField.setEditable(false);
                billingAdressField.setStyle("");
                billingZipCodeField.setText(zipCodeField.getText());
                billingZipCodeField.setEditable(false);
                billingZipCodeField.setStyle("");
                adressCheckCounter--;
            }
        });

        registerPersonButton = new Button("Registrer");
        registerPersonButton.setOnAction(handler);
        registerPersonButton.setDisable(true);

        fieldBox = new GridPane();
        fieldBox.addColumn(0, firstNameLabel, lastNameLabel, birthNumberLabel, emailLabel, telephoneNumberLabel,
                adressLabel, zipCodeLabel, differentAdressCheck, billingAdressLabel, billingZipCodeLabel);
        fieldBox.addColumn(1, firstNameField, lastNameField, birthNumberField, emailField, telephoneNumberField, adressField, zipCodeField, new Label(""), billingAdressField, billingZipCodeField, registerPersonButton);
        fieldBox.setAlignment(Pos.CENTER);
        fieldBox.setVgap(10);
        fieldBox.setHgap(20);
        fieldBox.setStyle("-fx-border-color: gray;");
        printArea = new TextArea();
        printArea.setEditable(false);
        scrollPane = new ScrollPane(printArea);
        scrollPane.setPrefWidth(OperationWindow.STAGE_WIDTH * 3/5);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-border-color: gray;");

        borderPane = new BorderPane(fieldBox, header, scrollPane, footer, null);
        scene = new Scene(borderPane);
    }

    /**
     * returns the scene made by this class.
     *
     * @return the scene made by this class.
     */
    public Scene getScene(){
        return scene;
    }

    public void requestRegistration(){
        if (firstNameField.getText().trim().isEmpty() || lastNameField.getText().trim().isEmpty() ||
                birthNumberField.getText().trim().isEmpty()){
            new MessageDialog().showMessageDialog("Ugyldig informasjon!",
                    "Feltene markert med stjerne må være utfylt.", MessageDialog.WARNING_ICON, MessageDialog.OK_OPTION);
        }else {
            /*(MethodForRegistrationOfACustomers(birthNumberField.getText(), name.getText(), lastNameField.getText(), telephoneNumberField.getText(), zipCodeField.getText(), adressField.getText()))? MessageDialog.showMessageDialog("Godkjent",
            name.getText() + "\s" + lastNameField.getText() + " er nå registrert.",
            MessageDialog.INFORMATION_ICON): MessageDialog.showMessageDialog(
            "Error", "Noe gikk galt under registreingen.", MessageDialog.ERROR_ICON);*/
        }
    }

    public Button getRegisterPersonButton(){
        return registerPersonButton;
    }

    public String getFirstNameField() {
        return firstNameField.getText();
    }
    
    public String getLastNameField(){
        return lastNameField.getText();
    }

    public String getBirthNumberField() {
        return birthNumberField.getText();
    }

    public String getEmailField() {
        return emailField.getText();
    }

    public String getTelephoneNumberField() {
        return telephoneNumberField.getText();
    }

    public String getAdressField() {
        return adressField.getText();
    }

    public String getZipCodeField() {
        return zipCodeField.getText();
    }

    public String getBillingZipCodeField() {
        return billingZipCodeField.getText();
    }

    public String getBillingAdressField() {
        return billingAdressField.getText();
    }

    public void registerPerson(String text){
        printArea.setText(text);
    }

    public Iterator getTextFieldIterator(){
        return textFieldIterator;
    }

    public void resetIterator(){
        textFieldIterator = textFieldArrayList.iterator();
    }

    public void emptyFieldsCheck(){
        int i = 0;
        while(textFieldIterator.hasNext()){
            if(textFieldIterator.next().getText().trim().isEmpty()){
                registerPersonButton.setDisable(true);
                resetIterator();
                return;
            }
            System.out.println(i++);
        }
        registerPersonButton.setDisable(false);
    }

}