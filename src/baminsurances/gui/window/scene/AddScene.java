package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.GuiEventHandler;
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

/**
 * Created by Adrian on 15/04/2015.
 */
public class AddScene {

    private Scene scene;
    private TextField name, lastName, birthNmbr, email, tlfNmbr, adress, zipCode, billingZipCode, billingAdress;
    private Label nameLabel, lastNameLabel, birthNmbrLabel, emailLabel, tlfNmbrLabel,
            adressLabel, zipCodeLabel, billingZipCodeLabel, billingAdressLabel;
    private CheckBox differentAdressCheck;
    private BorderPane borderPane;
    private GridPane fieldBox;
    private TextArea printArea;
    private ScrollPane scrollPane;
    private Button register;

    private GuiEventHandler handler;
    private int checkCounter = 0;

    /**
     * Creates a new Scene with the given values.
     * @param header
     * @param footer
     * @param handler
     */
    public AddScene(HBox header, HBox footer, GuiEventHandler handler) {
        this.handler = handler;

        name = new TextField();
        nameLabel = new Label("Fornavn:");
        lastName = new TextField();
        lastNameLabel = new Label("Etternavn:");
        birthNmbr = new TextField();
        birthNmbrLabel = new Label("Fødselsnummer:");
        email = new TextField();
        emailLabel = new Label("Mailadresse:");
        tlfNmbr = new TextField();
        tlfNmbrLabel = new Label("Telefonnummer:");
        adress = new TextField();
        adressLabel = new Label("Adresse:");
        zipCode = new TextField();
        zipCodeLabel = new Label("Postnummer:");
        billingAdress = new TextField();
        billingAdress.setEditable(false);
        billingAdressLabel = new Label("Betalingsadresse:");
        billingZipCode = new TextField();
        billingZipCode.setEditable(false);
        billingZipCodeLabel = new Label("Postnr. for betaling:");
        adress.setOnKeyReleased(e -> billingAdress.setText(adress.getText()));
        zipCode.setOnKeyReleased(e -> billingZipCode.setText(zipCode.getText()));

        differentAdressCheck = new CheckBox("Forskjellig betalingsadresse?");
        differentAdressCheck.setOnAction(ev -> {
            if(checkCounter == 0){
                adress.setOnKeyReleased(null);
                zipCode.setOnKeyReleased(null);
                billingAdress.setText("");
                billingAdress.setEditable(true);
                billingZipCode.setText("");
                billingZipCode.setEditable(true);
                checkCounter++;
            }
            else{
                adress.setOnKeyReleased(e -> billingAdress.setText(adress.getText()));
                zipCode.setOnKeyReleased(e -> billingZipCode.setText(zipCode.getText()));
                billingAdress.setText(adress.getText());
                billingAdress.setEditable(false);
                billingZipCode.setText(zipCode.getText());
                billingZipCode.setEditable(false);
                checkCounter--;
            }
        });

        register = new Button("Registrer");
        register.setOnAction(handler);

        fieldBox = new GridPane();
        fieldBox.addColumn(0, nameLabel, lastNameLabel, birthNmbrLabel, emailLabel, tlfNmbrLabel,
                adressLabel, zipCodeLabel, differentAdressCheck, billingAdressLabel, billingZipCodeLabel);
        fieldBox.addColumn(1, name, lastName, birthNmbr, email, tlfNmbr, adress, zipCode, new Label(""), billingAdress, billingZipCode, register);
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
        if (name.getText().trim().isEmpty() || lastName.getText().trim().isEmpty() ||
                birthNmbr.getText().trim().isEmpty()){
            new MessageDialog().showMessageDialog("Ugyldig informasjon!",
                    "Feltene markert med stjerne må være utfylt.", MessageDialog.WARNING_ICON, MessageDialog.OK_OPTION);
        }else {
            /*(MethodForRegistrationOfACustomers(birthNmbr.getText(), name.getText(), lastName.getText(), tlfNmbr.getText(), zipCode.getText(), adress.getText()))? MessageDialog.showMessageDialog("Godkjent",
            name.getText() + "\s" + lastName.getText() + " er nå registrert.",
            MessageDialog.INFORMATION_ICON): MessageDialog.showMessageDialog(
            "Error", "Noe gikk galt under registreingen.", MessageDialog.ERROR_ICON);*/
        }
    }

    public Button getRegister(){
        return register;
    }
}