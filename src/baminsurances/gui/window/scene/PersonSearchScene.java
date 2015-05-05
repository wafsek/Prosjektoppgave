package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.window.OperationWindow;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Adrian on 23/04/2015.
 */
public abstract class PersonSearchScene {

    protected Scene scene;

    protected TextField firstNameField, lastNameField, adressField, zipCodeField, birthNumberField;
    protected Label firstNameLabel, lastNameLabel, adressLabel, zipCodeLabel,
            birthNoLabel;
    protected TableView personTable;
    protected TableColumn birthNoColumn, nameColumn, adressColumn, tlfNoColumn;

    protected ArrayList<TextField> textFieldArray;
    protected Iterator textFieldsIterator;

    protected Button requestRegistration;

    protected GridPane itemContainer;
    protected BorderPane borderPane;
    protected GuiEventHandler handler;

    public PersonSearchScene(GuiEventHandler handler){
        this.handler = handler;

        personTable = new TableView();
        birthNoColumn = new TableColumn("Fødselsnummer");
        nameColumn = new TableColumn("Navn");
        adressColumn = new TableColumn("Adresse");
        tlfNoColumn = new TableColumn("Telefonnummer");
        personTable.getColumns().addAll(birthNoColumn, nameColumn, adressColumn, tlfNoColumn);
        personTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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

        textFieldArray = new ArrayList<TextField>();
        textFieldArray.add(birthNumberField);
        textFieldArray.add(firstNameField);
        textFieldArray.add(lastNameField);
        textFieldArray.add(adressField);
        textFieldArray.add(zipCodeField);
        textFieldsIterator = textFieldArray.iterator();

        requestRegistration = new Button("Finn person");
        requestRegistration.setOnAction(handler);

        itemContainer = new GridPane();
        itemContainer.addColumn(0, firstNameLabel, lastNameLabel, birthNoLabel, adressLabel, zipCodeLabel);
        itemContainer.addColumn(1, firstNameField, lastNameField, birthNumberField, adressField, zipCodeField, requestRegistration);
        itemContainer.setAlignment(Pos.CENTER);
        itemContainer.setVgap(30);
        itemContainer.setHgap(20);
        itemContainer.setStyle("-fx-border-color: gray;");
    }

    /**
     * returns the Scene created in this class.
     *
     * @return the Scene created in this class.
     */
    public Scene getScene(){
        return scene;
    }

    /**
     * returns the Button that requests the change between the two
     * stages.
     *
     * @return the button that requests the Scene change.
     */
    public Button getRequestRegistration(){
        return requestRegistration;
    }

    public Iterator getTextFieldsIterator(){
        return textFieldsIterator;
    }

    public void resetIterator(){
        textFieldsIterator = textFieldArray.iterator();
    }

    public void displayPossiblePeople(){
        /*ObservableList = getAllPossibleCustomers()*/
    }
}
