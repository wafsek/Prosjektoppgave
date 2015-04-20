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
public class InsureHouseScene {

    private Scene scene;

    private TextField birthNo;
    private TextArea printArea;
    private ScrollPane scrollPane;
    private TextField zipCode, adress;
    private ObservableList<String> yearOfConstruction, area, horsePower;
    private ComboBox yearOfConstructionBox, areaBox;

    private Button requestRegistration, register;

    private GridPane itemContainer;
    private BorderPane borderPane;

    private GuiEventHandler handler;

    private HBox rowBox, footer;

    public InsureHouseScene(HBox rowBox, HBox footer, GuiEventHandler handler) {
        this.rowBox = rowBox;
        this.footer = footer;
        this.handler = handler;
        birthNo = new TextField();
        yearOfConstruction = FXCollections.observableArrayList("1900 eller tidligere",
                "1901 - 1920", "1921 - 1950", "1951 - 1970", "1971 - 1990",
                "1991 - nå");
        area = FXCollections.observableArrayList("50 eller mindre",
                "51 - 75", "76 - 125", "126 - 175", "176 - 250", "251 eller mer");
        yearOfConstructionBox = new ComboBox(yearOfConstruction);
        yearOfConstructionBox.setPromptText("Konstruksjonsår");
        yearOfConstructionBox.setPrefWidth(200);
        areaBox = new ComboBox(area);
        areaBox.setPromptText("Areal (m^2)");
        areaBox.setPrefWidth(200);

        zipCode = new TextField();
        zipCode.setPrefWidth(200);
        zipCode.setPromptText("Postnummer");
        adress = new TextField();
        adress.setPromptText("Adresse");

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
        itemContainer.addColumn(0, zipCode, adress, yearOfConstructionBox, areaBox, register);
        borderPane = new BorderPane(itemContainer, rowBox, scrollPane, footer, null);
        return new Scene(borderPane);
    }

    public Button getRequestRegistration(){
        return requestRegistration;
    }
}