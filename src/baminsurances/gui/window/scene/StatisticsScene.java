package baminsurances.gui.window.scene;

import baminsurances.gui.button.IconButton;
import baminsurances.gui.eventhandler.Controller;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Adrian PC on 19/04/2015.
 */
public class StatisticsScene {
    private Scene scene;

    private Button backToRegistration;

    private TextField employeeNmbr, birthNmbr, firstName, lastName,
            carBrand, carType, adress;
    private ArrayList<TextField> textFields;
    private Iterator textFieldsIterator;
    private Text from, to;
    private DatePicker fromDate, toDate;

    private CheckBox dummy1, dummy2, dummy3, dummy4, dummy5, dummy6, dummy7,
            dummy8, dummy9, dummy10;

    private BorderPane borderPane;
    private GridPane checkBoxContainer, fieldContainer;
    private VBox menu;

    private KeyPressHandler keyHandler;
    private Controller controller;
    private GuiEventHandler handler;

    /**
     * creates a new Scene based on the given values.
     *
     * @param footer
     * @param keyHandler
     * @param controller
     * @param handler
     */
    public StatisticsScene(HBox footer, KeyPressHandler keyHandler, Controller controller, GuiEventHandler handler){
        this.handler = handler;
        this.keyHandler = keyHandler;
        this.controller = controller;

        employeeNmbr = new TextField();
        employeeNmbr.setPromptText("Ansattnummer");
        employeeNmbr.setOnKeyReleased(keyHandler);
        birthNmbr = new TextField();
        birthNmbr.setPromptText("Personnummer");
        birthNmbr.setOnKeyReleased(keyHandler);
        firstName = new TextField();
        firstName.setPromptText("Fornavn");
        firstName.setOnKeyReleased(keyHandler);
        lastName = new TextField();
        lastName.setPromptText("Etternavn");
        lastName.setOnKeyReleased(keyHandler);
        carBrand = new TextField();
        carBrand.setPromptText("Bilmerke");
        carBrand.setOnKeyReleased(keyHandler);
        carType = new TextField();
        carType.setPromptText("Type bil (SUV, personbil, etc)");
        carType.setOnKeyReleased(keyHandler);
        adress = new TextField();
        adress.setPromptText("Adresse");
        adress.setOnKeyReleased(keyHandler);
        textFields = new ArrayList<TextField>();
        textFields.add(employeeNmbr);
        textFields.add(birthNmbr);
        textFields.add(firstName);
        textFields.add(lastName);
        textFields.add(carBrand);
        textFields.add(carType);
        textFields.add(adress);
        textFieldsIterator = textFields.iterator();

        fieldContainer = new GridPane();
        fieldContainer.addColumn(0, employeeNmbr, birthNmbr, firstName,
                lastName, carBrand, carType, adress);
        fieldContainer.setVgap(20);
        fieldContainer.setAlignment(Pos.CENTER);

        fromDate = new DatePicker();
        from = new Text("Fra:");
        toDate = new DatePicker(LocalDate.now());
        to = new Text("Til:");

        dummy1 = new CheckBox("Dummy1");
        dummy2 = new CheckBox("Dummy2");
        dummy3 = new CheckBox("Dummy3");
        dummy4 = new CheckBox("Dummy4");
        dummy5 = new CheckBox("Dummy5");
        dummy6 = new CheckBox("Dummy6");
        dummy7 = new CheckBox("Dummy7");
        dummy8 = new CheckBox("Dummy8");
        dummy9 = new CheckBox("Dummy9");
        dummy10 = new CheckBox("Dummy10");

        checkBoxContainer = new GridPane();
        checkBoxContainer.addColumn(0, from, fromDate, dummy1, dummy3, dummy5, dummy7, dummy9);
        checkBoxContainer.addColumn(1, to, toDate, dummy2, dummy4, dummy6, dummy8, dummy10);
        checkBoxContainer.setVgap(20);
        checkBoxContainer.setHgap(20);
        checkBoxContainer.setPrefWidth(800);
        checkBoxContainer.setAlignment(Pos.CENTER);

        backToRegistration = new IconButton().iconButton(100, 100, IconButton.ADD_BUTTON);
        backToRegistration.setOnAction(handler);

        menu = new VBox(0, backToRegistration);
        menu.setAlignment(Pos.CENTER);

        borderPane = new BorderPane(fieldContainer, menu, checkBoxContainer, footer, null);

        scene = new Scene(borderPane);
    }

    /**
     * returns the Scene defined in this class.
     *
     * @return the Scene defined in this class.
     */
    public Scene getScene(){
        return scene;
    }

    /**
     * returns the Button that requests the Scene change between the
     * two stages defined in this class
     *
     * @return the Button that requests the Scene change between the
     * two stages defined in this class
     */
    public Button getBackToRegistration(){
        return backToRegistration;
    }

    public ArrayList getTextFields(){
        return textFields;
    }

    public void employeeStatistics(){
        carType.setEditable(false);
        carBrand.setEditable(false);
    }

    public void setEditableEmployeeFields(){
        if (employeeNmbr.getText().trim().isEmpty()){
            birthNmbr.setEditable(true);
            carBrand.setEditable(true);
            adress.setEditable(true);
            birthNmbr.setPromptText("Personnummer");
            carBrand.setPromptText("Bilmerke");
            adress.setPromptText("Adresse");
        }else {
            birthNmbr.setEditable(false);
            carBrand.setEditable(false);
            adress.setEditable(false);
            birthNmbr.setPromptText("irrelevant felt.");
            carBrand.setPromptText("irrelevant felt.");
            adress.setPromptText("irrelevant felt.");
        }
    }

    public void setEditableCustomerFields(){
        if (birthNmbr.getText().trim().isEmpty() &&
                carBrand.getText().trim().isEmpty() &&
                adress.getText().trim().isEmpty()){
            employeeNmbr.setEditable(true);
            employeeNmbr.setPromptText("Ansattnummer");
        }else {
            employeeNmbr.setEditable(false);
            employeeNmbr.setPromptText("Irrelevant felt");
        }
    }

    public Iterator getTextFieldsIterator(){
        return textFieldsIterator;
    }

    public void resetIterator(){
        textFieldsIterator = textFields.iterator();
    }
}
