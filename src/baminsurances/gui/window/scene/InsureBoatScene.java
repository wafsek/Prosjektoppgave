package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.OperationWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.security.Key;

/**
 * @author Adrian Melsom
 */
public class InsureBoatScene extends PersonSearchScene {

    private TextArea printArea;
    private ScrollPane scrollPane;
    private ObservableList<String> boatTypeList, yearOfConstructionList,
        lengthInFeetList, harborTypeList;
    private ComboBox yearOfConstructionBox, boatTypeBox, lengthInFeetBox,
        harborTypeBox;
    private HBox footer, header;

    private Button registerBoatButton;

    /**
     * Creates a new Scene based on the values.
     *
     * @param header
     * @param footer
     * @param handler
     */
    public InsureBoatScene(HBox header, HBox footer, GuiEventHandler handler,
            KeyPressHandler keyPressHandler) {
        super(handler, keyPressHandler);
        this.footer = footer;
        this.header = header;
        borderPane = new BorderPane(itemContainer, header, personTable, footer, null);
        scene = new Scene(borderPane);
    }

    /**
     * Returns the initial scene made by this class.
     *
     * @return the initial scene made by this class
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Recreates and adds -FX components to the initial Scene and returns it.
     *
     * @return the recreated Scene.
     */
    public Scene requestApproved() {
        yearOfConstructionList = FXCollections.observableArrayList(
                "1900 eller tidligere", "1901 - 1920", "1921 - 1950",
                "1951 - 1970", "1971 - 1990", "1991 - nå");
        lengthInFeetList = FXCollections.observableArrayList("10 eller minde",
                "11 - 20", "21 - 50", "51 eller større");
        boatTypeList = FXCollections.observableArrayList("Cabincruiser",
                "Daycruiser", "Gummibåt/Jolle", "RIB", "Seilbåt/Motorseiler",
                "Skjærgårdsjeep/Landstedsbåt", "Speedbåt", "Trebåt/Snekke",
                "Vannscooter", "Yacht", "Yrkesbåt/Sjark/Skøyte", "Andre");
        harborTypeList = FXCollections.observableArrayList("Åpen havn",
                "Lukket havn");
        yearOfConstructionBox = new ComboBox(yearOfConstructionList);
        yearOfConstructionBox.setPromptText("Konstruksjonsår");
        yearOfConstructionBox.setPrefWidth(OperationWindow.STAGE_WIDTH * 1/6);
        boatTypeBox = new ComboBox(boatTypeList);
        boatTypeBox.setPromptText("Båttype");
        boatTypeBox.setPrefWidth(OperationWindow.STAGE_WIDTH * 1/6);
        lengthInFeetBox = new ComboBox(lengthInFeetList);
        lengthInFeetBox.setPromptText("Lengde (fot)");
        lengthInFeetBox.setPrefWidth(OperationWindow.STAGE_WIDTH * 1/6);
        harborTypeBox = new ComboBox(harborTypeList);
        harborTypeBox.setPromptText("Havntype");
        harborTypeBox.setPrefWidth(OperationWindow.STAGE_WIDTH * 1 / 6);

        registerBoatButton = new Button("Registrer forsikring");

        printArea = new TextArea();
        printArea.setEditable(false);
        scrollPane = new ScrollPane(printArea);
        scrollPane.setPrefWidth(OperationWindow.STAGE_WIDTH * 3/5);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-border-color: gray;");

        itemContainer.getChildren().removeAll(firstNameLabel, lastNameLabel,
                birthNoLabel, adressLabel, zipCodeLabel, firstNameField,
                lastNameField, birthNumberField, adressField, zipCodeField,
                requestRegistration);
        itemContainer.addColumn(0, boatTypeBox, yearOfConstructionBox,
                lengthInFeetBox, harborTypeBox, registerBoatButton);
        borderPane = new BorderPane(itemContainer, header, scrollPane, footer,
                null);
        return new Scene(borderPane);
    }
}
