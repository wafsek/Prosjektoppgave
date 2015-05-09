package baminsurances.gui.window.scene;

import baminsurances.data.Person;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.OperationWindow;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Adrian Melsom
 */
public abstract class PersonSearchScene {

    protected Scene scene;

    protected TextField firstNameField, lastNameField, adressField,
        zipCodeField, birthNumberField;
    protected Label firstNameLabel, lastNameLabel, adressLabel, zipCodeLabel,
        birthNoLabel;
    protected TableView personTable;
    protected TableColumn birthNoColumn, nameColumn, adressColumn, tlfNoColumn;

    protected ArrayList<TextField> textFieldArrayList;
    protected Iterator textFieldsIterator;

    protected Button requestRegistration;

    protected GridPane itemContainer;
    protected BorderPane borderPane;
    protected GuiEventHandler handler;
    protected KeyPressHandler keyPressHandler;

    public PersonSearchScene(GuiEventHandler handler,
            KeyPressHandler keyPressHandler) {
        this.handler = handler;
        this.keyPressHandler = keyPressHandler;

        personTable = new TableView();
        birthNoColumn = new TableColumn("Fødselsnummer");
        birthNoColumn.setCellValueFactory(
                new PropertyValueFactory<Person, String>("birthNo"));
        nameColumn = new TableColumn("Navn");
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));
        adressColumn = new TableColumn("Adresse");
        adressColumn.setCellValueFactory(
                new PropertyValueFactory<Person, String>("streetAdress"));
        tlfNoColumn = new TableColumn("Telefonnummer");
        tlfNoColumn.setCellValueFactory(
                new PropertyValueFactory<Person, String>("telephoneNo"));
        personTable.getColumns().addAll(
                birthNoColumn, nameColumn, adressColumn, tlfNoColumn);
        personTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);
        personTable.setEditable(false);
        personTable.setStyle("-fx-border-color: gray;");
        personTable.setPrefWidth(OperationWindow.STAGE_WIDTH * 2 / 4);

        birthNumberField = new TextField();
        birthNoLabel = new Label("Fødselsnummer:");
        firstNameField = new TextField();
        firstNameLabel = new Label("Fornavn:");
        lastNameField = new TextField();
        lastNameLabel = new Label("Etternavn:");
        adressField = new TextField();
        adressLabel = new Label("Adresse:");
        zipCodeField = new TextField();
        zipCodeLabel = new Label("Postnummer:");

        textFieldArrayList = new ArrayList<TextField>();
        textFieldArrayList.add(birthNumberField);
        textFieldArrayList.add(firstNameField);
        textFieldArrayList.add(lastNameField);
        textFieldArrayList.add(adressField);
        textFieldArrayList.add(zipCodeField);
        textFieldsIterator = textFieldArrayList.iterator();

        firstNameField.setOnKeyReleased(keyPressHandler);
        lastNameField.setOnKeyReleased(keyPressHandler);
        birthNumberField.setOnKeyReleased(keyPressHandler);
        adressField.setOnKeyReleased(keyPressHandler);
        zipCodeField.setOnKeyReleased(keyPressHandler);

        textFieldArrayList = new ArrayList<TextField>();
        textFieldArrayList.add(firstNameField);
        textFieldArrayList.add(lastNameField);
        textFieldArrayList.add(birthNumberField);
        textFieldArrayList.add(adressField);
        textFieldArrayList.add(zipCodeField);

        requestRegistration = new Button("Velg Person");
        requestRegistration.setOnAction(handler);

        itemContainer = new GridPane();
        itemContainer.addColumn(0, firstNameLabel, lastNameLabel, birthNoLabel,
                adressLabel, zipCodeLabel);
        itemContainer.addColumn(1, firstNameField, lastNameField,
                birthNumberField, adressField, zipCodeField,
                requestRegistration);
        itemContainer.setAlignment(Pos.CENTER);
        itemContainer.setVgap(30);
        itemContainer.setHgap(20);
        itemContainer.setStyle("-fx-border-color: gray;");
    }

    /**
     * Returns the Scene created in this class.
     *
     * @return the Scene created in this class.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Returns the Button that requests the change between the two
     * stages.
     *
     * @return the button that requests the Scene change.
     */
    public Button getRequestRegistration() {
        return requestRegistration;
    }

    public String getFirstName() {
        return firstNameField.getText();
    }

    public String getLastName() {
        return lastNameField.getText();
    }

    public String getAdress() {
        return adressField.getText();
    }

    public String getZipCode() {
        return zipCodeField.getText();
    }

    public String getBirthNumber() {
        return birthNumberField.getText();
    }

    public ArrayList getTextFieldArrayList() {
        return textFieldArrayList;
    }

    public Iterator getTextFieldIterator() {
        return textFieldsIterator;
    }

    public void resetIterator() {
        textFieldsIterator = textFieldArrayList.iterator();
    }

    public void setTableData(ObservableList observableList) {
        personTable.setItems(null);
        personTable.setItems(observableList);
    }
}
