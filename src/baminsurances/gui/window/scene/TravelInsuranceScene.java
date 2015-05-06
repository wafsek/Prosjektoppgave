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

/**
 * Created by Adrian on 15/04/2015.
 */
public class TravelInsuranceScene extends PersonSearchScene{

    private final int COMBOBOX_WIDTH = 175;

    private Button registerPersonInsurance;
    private TextArea printArea;
    private ScrollPane scrollPane;
    private ObservableList<String> kidsList, relationshipList, incomeList, deptList;
    private ComboBox kidsBox, relationshipBox, incomeBox, deptBox;
    private HBox header, footer;

    /**
     * creates a new Scene based on the values given.
     *
     * @param header
     * @param footer
     * @param handler
     */
    public TravelInsuranceScene(HBox header, HBox footer, GuiEventHandler handler, KeyPressHandler keyPressHandler) {
        super(handler, keyPressHandler);
        this.header = header;
        this.footer = footer;
        borderPane = new BorderPane(itemContainer, header, personTable, footer, null);
        scene = new Scene(borderPane);
    }

    /**
     * recreated the initial Scene, adds -FX components and returns it.
     * @return the Scene created.
     */
    public Scene requestApproved(){
        incomeList = FXCollections.observableArrayList("150.000 eller minde.", "150.000 - 250.000",
                "250.000 - 500.000", "500.000 - 1.500.000", "1.500.000 eller mer");
        deptList = FXCollections.observableArrayList("150.000 eller minde.", "150.000 - 250.000",
                "250.000 - 500.000", "500.000 - 1.500.000", "1.500.000 eller mer");
        kidsList = FXCollections.observableArrayList("0", "1", "2", "3", "4", "5 eller fler");
        relationshipList = FXCollections.observableArrayList("Har samboer", "Har ikke samboer");

        kidsBox = new ComboBox(kidsList);
        kidsBox.setPromptText("Barn");
        kidsBox.setPrefWidth(COMBOBOX_WIDTH);
        relationshipBox = new ComboBox(relationshipList);
        relationshipBox.setPromptText("Forholdsstatus");
        relationshipBox.setPrefWidth(COMBOBOX_WIDTH);
        incomeBox = new ComboBox(incomeList);
        incomeBox.setPromptText("Inntekt");
        incomeBox.setPrefWidth(COMBOBOX_WIDTH);
        deptBox = new ComboBox(deptList);
        deptBox.setPromptText("Gjeld");
        deptBox.setPrefWidth(COMBOBOX_WIDTH);

        registerPersonInsurance = new Button("Registrer forsikring");
        registerPersonInsurance.setOnAction(handler);

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
        itemContainer.addColumn(0, incomeBox, deptBox, kidsBox, relationshipBox, registerPersonInsurance);
        borderPane = new BorderPane(itemContainer, header, scrollPane, footer, null);
        return new Scene(borderPane);
    }

    public void displayPossibleCustomers(){

    }
}

