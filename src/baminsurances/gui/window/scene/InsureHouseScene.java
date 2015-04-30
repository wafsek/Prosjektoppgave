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

import javax.print.DocFlavor;

/**
 * Created by Adrian on 15/04/2015.
 */
public class InsureHouseScene extends PersonSearchScene{

    private final int PREFERRED_TEXTFIELD_COMBOBOX_WIDTH = 175;

    private TextArea printArea;
    private ScrollPane scrollPane;
    private TextField houseZipCode, houseAdress;
    private ObservableList<String> yearOfConstruction, area;
    private ComboBox yearOfConstructionBox, areaBox;
    private HBox footer, header;

    private Button register;

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
        borderPane = new BorderPane(itemContainer, header, personList, footer, null);
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
        yearOfConstruction = FXCollections.observableArrayList("1900 eller tidligere",
                "1901 - 1920", "1921 - 1950", "1951 - 1970", "1971 - 1990",
                "1991 - nå");
        area = FXCollections.observableArrayList("50 eller mindre",
                "51 - 75", "76 - 125", "126 - 175", "176 - 250", "251 eller mer");
        yearOfConstructionBox = new ComboBox(yearOfConstruction);
        yearOfConstructionBox.setPromptText("Konstruksjonsår");
        yearOfConstructionBox.setPrefWidth(PREFERRED_TEXTFIELD_COMBOBOX_WIDTH);
        areaBox = new ComboBox(area);
        areaBox.setPromptText("Areal (m^2)");
        areaBox.setPrefWidth(PREFERRED_TEXTFIELD_COMBOBOX_WIDTH);

        houseZipCode = new TextField();
        houseZipCode.setPrefWidth(PREFERRED_TEXTFIELD_COMBOBOX_WIDTH);
        houseAdress = new TextField();

        register = new Button("Registrer forsikring");
        register.setOnAction(handler);

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
        itemContainer.addColumn(0, zipCodeLabel, adressLabel);
        itemContainer.addColumn(1, zipCode, adress, yearOfConstructionBox, areaBox, register);
        itemContainer.setHgap(20);
        borderPane = new BorderPane(itemContainer, header, scrollPane, footer, null);
        return new Scene(borderPane);
    }

    public Button getRequestRegistration(){
        return requestRegistration;
    }
}