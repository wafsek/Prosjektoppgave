package baminsurances.gui.window.scene;

import baminsurances.data.Person;
import baminsurances.gui.button.IconButton;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.OperationWindow;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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
    private TextField employeeNumberField, birthNumberField, firstNameField, lastNameField,
            carBrandField, carTypeField, adressField;
    private ArrayList<TextField> textFieldList;
    private Iterator<TextField> textFieldsIterator;
    private Label fromLabel, toLabel;
    private DatePicker fromDate, toDate;
    private CheckBox dummy1, dummy2, dummy3, dummy4, dummy5, dummy6, dummy7,
            dummy8, dummy9, dummy10;
    private TableView possiblePeople;
    private TableColumn<String, Person> fname, lname, bnmbr;
    private BorderPane borderPane;
    private GridPane checkBoxContainer, fieldContainer;
    private VBox menu, tableContainer;
    private HBox container;
    private KeyPressHandler keyHandler;
    private GuiEventHandler handler;

    /**
     * creates a new Scene based on the given values.
     *
     * @param footer
     * @param keyHandler
     * @param handler
     */
    public StatisticsScene(HBox footer, KeyPressHandler keyHandler, GuiEventHandler handler){
        this.handler = handler;
        this.keyHandler = keyHandler;

        employeeNumberField = new TextField();
        employeeNumberField.setPromptText("Ansattnummer");
        employeeNumberField.setOnKeyReleased(keyHandler);
        employeeNumberField.setPrefWidth(OperationWindow.STAGE_WIDTH * 1 / 6);
        birthNumberField = new TextField();
        birthNumberField.setPromptText("Personnummer");
        birthNumberField.setOnKeyReleased(keyHandler);
        firstNameField = new TextField();
        firstNameField.setPromptText("Fornavn");
        firstNameField.setOnKeyReleased(keyHandler);
        lastNameField = new TextField();
        lastNameField.setPromptText("Etternavn");
        lastNameField.setOnKeyReleased(keyHandler);
        carBrandField = new TextField();
        carBrandField.setPromptText("Bilmerke");
        carBrandField.setOnKeyReleased(keyHandler);
        carTypeField = new TextField();
        carTypeField.setPromptText("Type bil (SUV, personbil, etc)");
        carTypeField.setOnKeyReleased(keyHandler);
        adressField = new TextField();
        adressField.setPromptText("Adresse");
        adressField.setOnKeyReleased(keyHandler);
        textFieldList = new ArrayList<TextField>();
        textFieldList.add(employeeNumberField);
        textFieldList.add(birthNumberField);
        textFieldList.add(firstNameField);
        textFieldList.add(lastNameField);
        textFieldList.add(carBrandField);
        textFieldList.add(carTypeField);
        textFieldList.add(adressField);
        textFieldsIterator = textFieldList.iterator();


        fieldContainer = new GridPane();
        fieldContainer.addColumn(0, employeeNumberField, birthNumberField, firstNameField,
                lastNameField, carBrandField, carTypeField, adressField);
        fieldContainer.setVgap(20);
        fieldContainer.setAlignment(Pos.CENTER);
        fieldContainer.setPrefWidth(OperationWindow.STAGE_WIDTH * 1 / 4);
        fieldContainer.setStyle("-fx-border-color: gray;");

        possiblePeople = new TableView();
        fname = new TableColumn("Fornavn");
        lname = new TableColumn("Etternavn");
        bnmbr = new TableColumn("Fødselsnummer");
        possiblePeople.getColumns().addAll(fname, lname, bnmbr);
        possiblePeople.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableContainer = new VBox(20, possiblePeople);
        tableContainer.setPrefWidth(OperationWindow.STAGE_WIDTH * 2 / 4);
        tableContainer.setStyle("-fx-border-color: gray;");

        fromDate = new DatePicker();
        fromLabel = new Label("Fra:");
        toDate = new DatePicker(LocalDate.now());
        toLabel = new Label("Til:");

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
        checkBoxContainer.addColumn(0, fromLabel, fromDate, dummy1, dummy3, dummy5, dummy7, dummy9);
        checkBoxContainer.addColumn(1, toLabel, toDate, dummy2, dummy4, dummy6, dummy8, dummy10);
        checkBoxContainer.setVgap(20);
        checkBoxContainer.setHgap(20);
        checkBoxContainer.setPrefWidth(OperationWindow.STAGE_WIDTH * 1 / 4);
        checkBoxContainer.setAlignment(Pos.CENTER);
        checkBoxContainer.setStyle("-fx-border-color: gray;");

        backToRegistration = new IconButton().iconButton(100, 100, IconButton.ADD_BUTTON);
        backToRegistration.setOnAction(handler);

        container = new HBox(0, fieldContainer, tableContainer, checkBoxContainer);
        menu = new VBox(0, backToRegistration);
        menu.setAlignment(Pos.CENTER);
        menu.setPrefHeight(120);
        menu.setStyle("-fx-border-color: gray;");

        borderPane = new BorderPane(container, menu, null, footer, null);

        scene = new Scene(borderPane);

        keyHandler.setStatisticsScene(this, textFieldList);
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

    public ArrayList getTextFieldList(){
        return textFieldList;
    }

    public void employeeStatistics(){
        carTypeField.setEditable(false);
        carBrandField.setEditable(false);
    }

    public void setEditableEmployeeFields(){
        if (employeeNumberField.getText().trim().isEmpty()){
            birthNumberField.setEditable(true);
            carBrandField.setEditable(true);
            adressField.setEditable(true);
            birthNumberField.setPromptText("Personnummer");
            carBrandField.setPromptText("Bilmerke");
            adressField.setPromptText("Adresse");
        }else {
            birthNumberField.setEditable(false);
            carBrandField.setEditable(false);
            adressField.setEditable(false);
            birthNumberField.setPromptText("irrelevant felt.");
            carBrandField.setPromptText("irrelevant felt.");
            adressField.setPromptText("irrelevant felt.");
        }
    }

    public void setEditableCustomerFields(){
        if (birthNumberField.getText().trim().isEmpty() &&
                carBrandField.getText().trim().isEmpty() &&
                adressField.getText().trim().isEmpty()){
            employeeNumberField.setEditable(true);
            employeeNumberField.setPromptText("Ansattnummer");
        }else {
            employeeNumberField.setEditable(false);
            employeeNumberField.setPromptText("Irrelevant felt");
        }
    }

    public Iterator getTextFieldsIterator(){
        return textFieldsIterator;
    }

    public void resetIterator(){
        textFieldsIterator = textFieldList.iterator();
    }
}
