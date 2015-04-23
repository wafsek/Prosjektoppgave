package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.GuiEventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Adrian on 23/04/2015.
 */
public abstract class PersonSearchScene {

    protected Scene scene;

    protected TextField firstName, lastName, adress, zipCode, birthNo;
    protected Label firstNameLabel, lastNameLabel, adressLabel, zipCodeLabel,
            birthNoLabel;
    protected TableView personList;
    protected TableColumn birthNoColumn, nameColumn, adressColumn, tlfNoColumn;

    protected ArrayList<TextField> textFields;
    protected Iterator textFieldsIterator;

    protected Button requestRegistration;

    protected GridPane itemContainer;
    protected BorderPane borderPane;
    protected GuiEventHandler handler;

    protected HBox header, footer;

    public PersonSearchScene(HBox header, HBox footer, GuiEventHandler handler){
        this.header = header;
        this.footer = footer;
        this.handler = handler;

        personList = new TableView();
        birthNoColumn = new TableColumn("Fødselsnummer");
        nameColumn = new TableColumn("Navn");
        adressColumn = new TableColumn("Adresse");
        tlfNoColumn = new TableColumn("Telefonnummer");
        personList.getColumns().addAll(birthNoColumn, nameColumn, adressColumn, tlfNoColumn);
        personList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        personList.setEditable(false);
        personList.setStyle("-fx-border-color: gray;");
        personList.setPrefWidth(500);

        birthNo = new TextField();
        birthNoLabel = new Label("Fødselsnummer:");
        firstName = new TextField();
        firstNameLabel = new Label("Fornavn:");
        lastName = new TextField();
        lastNameLabel = new Label("Etternavn:");
        adress = new TextField();
        adressLabel = new Label("Adresse:");
        zipCode = new TextField();
        zipCodeLabel = new Label("Postnummer:");

        textFields = new ArrayList<TextField>();
        textFields.add(birthNo);
        textFields.add(firstName);
        textFields.add(lastName);
        textFields.add(adress);
        textFields.add(zipCode);
        textFieldsIterator = textFields.iterator();

        requestRegistration = new Button("Finn person");
        requestRegistration.setOnAction(handler);

        itemContainer = new GridPane();
        itemContainer.addColumn(0, firstNameLabel, lastNameLabel, birthNoLabel, adressLabel, zipCodeLabel);
        itemContainer.addColumn(1, firstName, lastName, birthNo, adress, zipCode, requestRegistration);
        itemContainer.setAlignment(Pos.CENTER);
        itemContainer.setVgap(30);
        itemContainer.setHgap(20);
        itemContainer.setStyle("-fx-border-color: gray;");
        borderPane = new BorderPane(itemContainer, header, personList, footer, null);

        scene = new Scene(borderPane);
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
        textFieldsIterator = textFields.iterator();
    }
}
