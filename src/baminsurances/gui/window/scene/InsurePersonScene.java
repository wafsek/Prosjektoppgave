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

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Adrian on 15/04/2015.
 */
public class InsurePersonScene extends PersonSearchScene{

    private final int COMBOBOX_WIDTH = 175;

    private Button register;
    private TextArea printArea;
    private ScrollPane scrollPane;
    private ObservableList<String> kidsArray, relationshipArray, incomeArray, deptArray;
    private ComboBox kids, relationship, income, dept;

    /**
     * creates a new Scene based on the values given.
     *
     * @param header
     * @param footer
     * @param handler
     */
    public InsurePersonScene(HBox header, HBox footer, GuiEventHandler handler)
    {
        super(header, footer, handler);
    }

    /**
     * recreated the initial Scene, adds -FX components and returns it.
     * @return the Scene created.
     */
    public Scene requestApproved(){
        incomeArray = FXCollections.observableArrayList("150.000 eller minde.", "150.000 - 250.000",
                "250.000 - 500.000", "500.000 - 1.500.000", "1.500.000 eller mer");
        deptArray = FXCollections.observableArrayList("150.000 eller minde.", "150.000 - 250.000",
                "250.000 - 500.000", "500.000 - 1.500.000", "1.500.000 eller mer");
        kidsArray = FXCollections.observableArrayList("0", "1", "2", "3", "4", "5 eller fler");
        relationshipArray = FXCollections.observableArrayList("Har samboer", "Har ikke samboer");

        kids = new ComboBox(kidsArray);
        kids.setPromptText("Barn");
        kids.setPrefWidth(COMBOBOX_WIDTH);
        relationship = new ComboBox(relationshipArray);
        relationship.setPromptText("Forholdsstatus");
        relationship.setPrefWidth(COMBOBOX_WIDTH);
        income = new ComboBox(incomeArray);
        income.setPromptText("Inntekt");
        income.setPrefWidth(COMBOBOX_WIDTH);
        dept = new ComboBox(deptArray);
        dept.setPromptText("Gjeld");
        dept.setPrefWidth(COMBOBOX_WIDTH);

        register = new Button("Registrer forsikring");
        register.setOnAction(handler);

        printArea = new TextArea();
        printArea.setEditable(false);
        scrollPane = new ScrollPane(printArea);
        scrollPane.setPrefWidth(600);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-border-color: gray;");

        itemContainer.getChildren().removeAll(firstNameLabel, lastNameLabel, birthNoLabel,
                adressLabel, zipCodeLabel, firstName, lastName, birthNo, adress, zipCode,
                requestRegistration);
        itemContainer.addColumn(0, income, dept, kids, relationship, register);
        borderPane = new BorderPane(itemContainer, header, scrollPane, footer, null);
        return new Scene(borderPane);
    }

    public void displayPossibleCustomers(){

    }
}

