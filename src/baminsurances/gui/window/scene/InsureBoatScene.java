package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.GuiEventHandler;
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
public class InsureBoatScene {

    private Scene scene;

    private TextField birthNo;
    private TextArea printArea;
    private ScrollPane scrollPane;
    private ObservableList<String> boatType, yearOfConstruction, lengthInFeet, harbor;
    private ComboBox yearOfConstructionBox, boatTypeBox, lengthInFeetBox, harborBox;

    private Button requestRegistration, register;

    private GridPane itemContainer;
    private BorderPane borderPane;

    private GuiEventHandler handler;

    private HBox rowBox, footer;

    public InsureBoatScene(HBox rowBox, HBox footer, GuiEventHandler handler) {
        this.rowBox = rowBox;
        this.footer = footer;
        this.handler = handler;
        birthNo = new TextField();
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
        boatTypeBox = new ComboBox(boatType);
        boatTypeBox.setPromptText("Båttype");
        lengthInFeetBox = new ComboBox(lengthInFeet);
        lengthInFeetBox.setPromptText("Lengde (fot)");
        harborBox = new ComboBox(harbor);
        harborBox.setPromptText("Havntype");

        requestRegistration = new Button("Finn person");
        requestRegistration.setOnAction(handler);
        register = new Button("Registrer forsikring");
        //register.setOnAction(e -> System.out.println(kids.getSelectionModel().getSelectedItem().toString()))
        //kids.getValue()
        //Code to get the selected value in a ComboBox
        birthNo = new TextField();
        birthNo.setPromptText("Skriv inn personnummer");
        printArea = new TextArea();
        printArea.setEditable(false);
        scrollPane = new ScrollPane(printArea);
        scrollPane.setPrefWidth(600);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        itemContainer = new GridPane();
        itemContainer.addColumn(0, birthNo, requestRegistration);
        itemContainer.setAlignment(Pos.CENTER);
        itemContainer.setVgap(30);
        borderPane = new BorderPane(itemContainer, rowBox, null, footer, null);

        scene = new Scene(borderPane);
    }

    public Scene getScene(){
        return scene;
    }

    public Scene requestApproved(){
        itemContainer.getChildren().removeAll(birthNo, requestRegistration);
        itemContainer.addColumn(0, boatTypeBox, yearOfConstructionBox, lengthInFeetBox, harborBox, register);
        borderPane = new BorderPane(itemContainer, rowBox, scrollPane, footer, null);
        return new Scene(borderPane);
    }

    public Button getRequestRegistration(){
        return requestRegistration;
    }
}
