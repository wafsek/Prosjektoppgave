package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.Controller;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.window.MessageDialog;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Created by Adrian on 15/04/2015.
 */
public class AddScene {

    private Scene scene;
    private TextField name, lastName, birthNmbr, tlfNmbr, adress, zipCode;
    private Label nameLabel, lastNameLabel, birthNmbrLabel, tlfNmbrLabel,
            adressLabel, zipCodeLabel;
    private BorderPane borderPane;
    private GridPane fieldBox;
    private TextArea printArea;
    private ScrollPane scrollPane;
    private Button register;

    private Controller controller;

    /**
     * Creates a new Scene with the given values.
     * @param header
     * @param footer
     * @param handler
     */
    public AddScene(HBox header, HBox footer, GuiEventHandler handler, Controller controller) {
        this.controller = controller;
        name = new TextField();
        nameLabel = new Label("Fornavn:");
        lastName = new TextField();
        lastNameLabel = new Label("Etternavn:");
        birthNmbr = new TextField();
        birthNmbrLabel = new Label("Fødselsnummer:");
        tlfNmbr = new TextField();
        tlfNmbrLabel = new Label("Telefonnummer:");
        adress = new TextField();
        adressLabel = new Label("Adresse:");
        zipCode = new TextField();
        zipCodeLabel = new Label("Postnummer:");

        register = new Button("Registrer");
        register.setOnAction(controller);

        fieldBox = new GridPane();
        fieldBox.addColumn(0, nameLabel, lastNameLabel, birthNmbrLabel, tlfNmbrLabel, adressLabel, zipCodeLabel);
        fieldBox.addColumn(1, name, lastName, birthNmbr, tlfNmbr, adress, zipCode, register);
        fieldBox.setAlignment(Pos.CENTER);
        fieldBox.setVgap(30);
        fieldBox.setHgap(20);
        printArea = new TextArea();
        printArea.setEditable(false);
        scrollPane = new ScrollPane(printArea);
        scrollPane.setPrefWidth(600);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

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
                    "Feltene markert med * må være utfylt.", MessageDialog.WARNING_ICON);
        }else {
            /*(MethodForRegistrationOfACustomers())? MessageDialog.showMessageDialog("Godkjent",
            name.getText() + "\s" + lastName.getText() + " er nå registrert.",
             MessageDialog.INFORMATION_ICON): MessageDialog.showMessageDialog(
             "Error", "Noe gikk galt under registreingen.", MessageDialog.ERROR_ICON);*/
        }
    }

    public Button getRegister(){
        return register;
    }
}