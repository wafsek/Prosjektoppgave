package baminsurances.gui.window.scene;

import baminsurances.data.Customer;
import baminsurances.data.Person;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.OperationWindow;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Adrian Melsom
 */
public abstract class PersonSearchScene extends GeneralScene{

    protected TextField firstNameField, lastNameField, adressField,
        zipCodeField, birthNumberField;
    protected Label firstNameLabel, lastNameLabel, adressLabel, zipCodeLabel,
        birthNoLabel, discribtionLabel;
    protected TableView<Customer> customerTable;
    protected TableColumn birthNoColumn, nameColumn, adressColumn, tlfNoColumn;

    protected ArrayList<TextField> textFieldArrayList;
    protected Iterator textFieldsIterator;

    protected Button choosePersonButton;

    protected GridPane itemContainer;

    public PersonSearchScene(GuiEventHandler guiEventHandler,
            KeyPressHandler keyPressHandler, String displayName) {
        super(guiEventHandler, keyPressHandler, displayName);

        customerTable = new TableView();
        birthNoColumn = new TableColumn("Fødselsnummer");
        birthNoColumn.setCellValueFactory(
                new PropertyValueFactory<Person, String>("birthNo"));
        nameColumn = new TableColumn("Navn");
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));
        adressColumn = new TableColumn("Adresse");
        adressColumn.setCellValueFactory(
                new PropertyValueFactory<Person, String>("streetAddress"));
        tlfNoColumn = new TableColumn("Telefonnummer");
        tlfNoColumn.setCellValueFactory(
                new PropertyValueFactory<Person, String>("telephoneNo"));
        customerTable.getColumns().addAll(
                birthNoColumn, nameColumn, adressColumn, tlfNoColumn);
        customerTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);
        customerTable.setEditable(false);
        customerTable.setStyle("-fx-border-color: gray;");
        customerTable.setPrefWidth(OperationWindow.STAGE_WIDTH * 1 / 2);

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
        discribtionLabel = new Label("Bruk feltene nedenfor til å søke.");
        discribtionLabel.setStyle("-fx-font: 15px Times");

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

        choosePersonButton = new Button("Velg Person");
        choosePersonButton.setDisable(true);
        customerTable.setOnMouseClicked(e -> {
                    choosePersonButton.setDisable(false);
            choosePersonButton.setOnAction(guiEventHandler);
                });

        itemContainer = new GridPane();
        itemContainer.addColumn(0, discribtionLabel, firstNameLabel, lastNameLabel, birthNoLabel,
                adressLabel, zipCodeLabel);
        itemContainer.addColumn(1, new Label(""), firstNameField, lastNameField, birthNumberField,
                adressField, zipCodeField, choosePersonButton);
        itemContainer.setAlignment(Pos.CENTER);
        itemContainer.setVgap(30);
        itemContainer.setHgap(20);
        itemContainer.setStyle("-fx-border-color: gray;");
    }

    public void setChoosePersonClickable() {
        if (birthNumberField.getText().trim().isEmpty() &&
                adressField.getText().trim().isEmpty() &&
                firstNameField.getText().trim().isEmpty() &&
                lastNameField.getText().trim().isEmpty() &&
                zipCodeField.getText().trim().isEmpty()){
            choosePersonButton.setDisable(true);
        }
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
    public Button getChoosePersonButton() {
        return choosePersonButton;
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
        customerTable.setItems(observableList);
    }

    public Customer getCustomer() {
        return customerTable.getSelectionModel().getSelectedItem();
    }
}
