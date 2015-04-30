package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.window.OperationWindow;
import javafx.collections.FXCollections;
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
public class InsureBoatScene extends PersonSearchScene{

    private TextArea printArea;
    private ScrollPane scrollPane;
    private ObservableList<String> boatType, yearOfConstruction, lengthInFeet, harbor;
    private ComboBox yearOfConstructionBox, boatTypeBox, lengthInFeetBox, harborBox;
    private HBox footer, header;

    private Button register;

    /**
     * creates a new Scene based on the values.
     *
     * @param header
     * @param footer
     * @param handler
     */
    public InsureBoatScene(HBox header, HBox footer, GuiEventHandler handler) {
        super(handler);
        this.footer = footer;
        this.header = header;
        borderPane = new BorderPane(itemContainer, header, personList, footer, null);
        scene = new Scene(borderPane);
    }

    /**
     * returns the initial scene made by this class.
     *
     * @return the initial scene made by this class
     */
    public Scene getScene(){
        return scene;
    }

    /**
     * recreates and adds -FX components to the initial Scene and returns it.
     *
     * @return the recreated Scene.
     */
    public Scene requestApproved(){
        yearOfConstruction = FXCollections.observableArrayList("1900 eller tidligere",
                "1901 - 1920", "1921 - 1950", "1951 - 1970", "1971 - 1990",
                "1991 - nå");
        lengthInFeet = FXCollections.observableArrayList("10 eller minde", "11 - 20",
                "21 - 50", "51 eller større");
        boatType = FXCollections.observableArrayList("Cabincruiser", "Daycruiser",
                "Gummibåt/Jolle", "RIB", "Seilbåt/Motorseiler",
                "Skjærgårdsjeep/Landstedsbåt", "Speedbåt", "Trebåt/Snekke",
                "Vannscooter", "Yacht", "Yrkesbåt/Sjark/Skøyte", "Andre");
        harbor = FXCollections.observableArrayList("Åpen havn", "Lukket havn");
        yearOfConstructionBox = new ComboBox(yearOfConstruction);
        yearOfConstructionBox.setPromptText("Konstruksjonsår");
        yearOfConstructionBox.setPrefWidth(OperationWindow.STAGE_WIDTH * 1/6);
        boatTypeBox = new ComboBox(boatType);
        boatTypeBox.setPromptText("Båttype");
        boatTypeBox.setPrefWidth(OperationWindow.STAGE_WIDTH * 1/6);
        lengthInFeetBox = new ComboBox(lengthInFeet);
        lengthInFeetBox.setPromptText("Lengde (fot)");
        lengthInFeetBox.setPrefWidth(OperationWindow.STAGE_WIDTH * 1/6);
        harborBox = new ComboBox(harbor);
        harborBox.setPromptText("Havntype");
        harborBox.setPrefWidth(OperationWindow.STAGE_WIDTH * 1/6);

        register = new Button("Registrer forsikring");

        printArea = new TextArea();
        printArea.setEditable(false);
        scrollPane = new ScrollPane(printArea);
        scrollPane.setPrefWidth(OperationWindow.STAGE_WIDTH * 3/5);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-border-color: gray;");

        itemContainer.getChildren().removeAll(firstNameLabel, lastNameLabel, birthNoLabel,
                adressLabel, zipCodeLabel, firstName, lastName, birthNo, adress, zipCode,
                requestRegistration);
        itemContainer.addColumn(0, boatTypeBox, yearOfConstructionBox, lengthInFeetBox, harborBox, register);
        borderPane = new BorderPane(itemContainer, header, scrollPane, footer, null);
        return new Scene(borderPane);
    }
}
