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
public class InsurePersonScene {

    private Scene scene;

    private TextField birthNo;
    private TextArea printArea;
    private ScrollPane scrollPane;
    private ObservableList<String> kidsArray, relationshipArray, incomeArray, deptArray;
    private ComboBox kids, relationship, income, dept;

    private Button requestRegistration, register;

    private GridPane itemContainer;
    private BorderPane borderPane;

    private GuiEventHandler handler;

    private HBox rowBox, footer;

    public InsurePersonScene(HBox rowBox, HBox footer, GuiEventHandler handler)
    {
        this.rowBox = rowBox;
        this.footer = footer;
        this.handler = handler;
        birthNo = new TextField();
        incomeArray = FXCollections.observableArrayList("150.000 eller minde.", "150.000 - 250.000",
                "250.000 - 500.000", "500.000 - 1.500.000", "1.500.000 eller mer");
        deptArray = FXCollections.observableArrayList("150.000 eller minde.", "150.000 - 250.000",
                "250.000 - 500.000", "500.000 - 1.500.000", "1.500.000 eller mer");
        kidsArray = FXCollections.observableArrayList("0", "1", "2", "3", "4", "5 eller fler");
        relationshipArray = FXCollections.observableArrayList("Singel", "I et forhold", "Gift");
        requestRegistration = new Button("Finn person");
        kids = new ComboBox(kidsArray);
        kids.setPromptText("Barn");
        relationship = new ComboBox(relationshipArray);
        relationship.setPromptText("Forholdsstatus");
        income = new ComboBox(incomeArray);
        income.setPromptText("Inntekt");
        dept = new ComboBox(deptArray);
        dept.setPromptText("Gjeld");

        requestRegistration.setOnAction(handler);
        register = new Button("Registrer forsikring");
        //register.setOnAction(e -> System.out.println(kids.getSelectionModel().getSelectedItem().toString()));
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

    public Scene requestAccepted(){
        itemContainer.getChildren().removeAll(birthNo, requestRegistration);
        itemContainer.addColumn(0, income, dept, kids, relationship, register);
        borderPane = new BorderPane(itemContainer, rowBox, scrollPane, footer, null);
        return new Scene(borderPane);

    }

    public Button getRequestRegistration(){
        return requestRegistration;
    }
}

