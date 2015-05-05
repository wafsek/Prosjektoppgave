package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.window.OperationWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Created by Adrian on 15/04/2015.
 */
public class InsureHouseScene extends PersonSearchScene{

    private TextArea printArea;
    private ScrollPane scrollPane;
    private TextField houseZipCodeField, houseAdressField;
    private ObservableList<String> yearOfConstructionList, areaList;
    private ComboBox yearOfConstructionBox, areaBox;
    private HBox footer, header;
    private Button registerHouseButton;

    /**
     * creates a new Scene based on the given values.
     *
     * @param header
     * @param footer
     * @param handler
     */
    public InsureHouseScene(HBox header, HBox footer, GuiEventHandler handler) {
        super(handler);
        this.header = header;
        this.footer = footer;
        borderPane = new BorderPane(itemContainer, header, personTable, footer, null);
        scene = new Scene(borderPane);
    }

    /**
     * return the Scene created by this class
     *
     * @return the Scene created by this class
     */
    public Scene getScene(){
        return scene;
    }

    /**
     * recreates the Scene and adds -FX components and returns it.
     *
     * @return the recreated initial Scene.
     */
    public Scene requestApproved(){
        yearOfConstructionList = FXCollections.observableArrayList("1900 eller tidligere",
                "1901 - 1920", "1921 - 1950", "1951 - 1970", "1971 - 1990",
                "1991 - nå");
        areaList = FXCollections.observableArrayList("50 eller mindre",
                "51 - 75", "76 - 125", "126 - 175", "176 - 250", "251 eller mer");
        yearOfConstructionBox = new ComboBox(yearOfConstructionList);
        yearOfConstructionBox.setPromptText("Konstruksjonsår");
        yearOfConstructionBox.setPrefWidth(OperationWindow.STAGE_WIDTH * 1/5);
        areaBox = new ComboBox(areaList);
        areaBox.setPromptText("Areal (m^2)");
        areaBox.setPrefWidth(OperationWindow.STAGE_WIDTH * 1/5);

        houseZipCodeField = new TextField();
        houseZipCodeField.setPrefWidth(OperationWindow.STAGE_WIDTH * 1/5);
        houseAdressField = new TextField();

        registerHouseButton = new Button("Registrer forsikring");
        registerHouseButton.setOnAction(handler);

        printArea = new TextArea();
        printArea.setEditable(false);
        scrollPane = new ScrollPane(printArea);
        scrollPane.setPrefWidth(OperationWindow.STAGE_WIDTH * 3/5);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-border-color: gray;");

        itemContainer.getChildren().removeAll(firstNameLabel, lastNameLabel, birthNoLabel,
                adressLabel, zipCodeLabel, firstNameField, lastNameField, birthNumberField, adressField, zipCodeField,
                requestRegistration);
        itemContainer.addColumn(0, zipCodeLabel, adressLabel);
        itemContainer.addColumn(1, zipCodeField, houseAdressField, yearOfConstructionBox, areaBox, registerHouseButton);
        itemContainer.setHgap(20);
        borderPane = new BorderPane(itemContainer, header, scrollPane, footer, null);
        return new Scene(borderPane);
    }

    public Button getRegisterHouseButton(){
        return registerHouseButton;
    }
}