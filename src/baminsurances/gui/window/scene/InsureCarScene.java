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
public class InsureCarScene extends PersonSearchScene {
    private ObservableList<String> yearOfProductionList, carBrandList,
        carTypeList, bonusList;
    private ComboBox yearOfProductionBox, carBrandBox, carTypeBox, bonusBox;
    private TextArea printArea;
    private Button registerCarScene;
    private HBox footer, header;
    private ScrollPane scrollPane;

    /**
     * Creates a new Scene based on the given values.
     * 
     * @param handler
     */
    public InsureCarScene(HBox header, HBox footer, GuiEventHandler handler,
            KeyPressHandler keyPressHandler, String displayName) {
        super(handler, keyPressHandler, displayName);
        this.header = header;
        this.footer = footer;
        borderPane = new BorderPane(itemContainer, header, personTable, footer, null);
        scene = new Scene(borderPane);
    }

    /**
     * Recreates and adds -FX components to the initial Scene.
     *
     * @return the recreated Scene.
     */
    public Scene requestApproved() {
        yearOfProductionList = FXCollections.observableArrayList(
                "1960 eller tidligere", "1961 - 1970", "1971 - 1980",
                "1981 - 1990", "1991 - 2000", "2001 - 2010", "2011 - 2015");
        carBrandList = FXCollections.observableArrayList("Alfa Romeo",
                "Aston Martin", "Audi", "Austin", "Bentley", "BMW", "Buddy",
                "Buick", "Cadillac", "Chevrolet", "Chrysler", "Citroen",
                "Dacia", "Daewoo", "Daihatsu", "Datsun", "DeLorean", "Dodge",
                "Ferrari", "Fiat", "Fisker", "Ford", "GMC", "Honda", "Hummer",
                "Hyundai", "Infiniti", "Isuzu", "Iveco", "Jaguar", "Jeep",
                "Jensen", "Kewet", "Kia", "Lada", "Lamborghini", "Lancia",
                "Land Rover", "Lexus", "Lincoln", "Lotus", "Maserati", "Mazda",
                "McLaren", "Mercedes-Benz", "Mercury", "MG", "mia electric",
                "MINI", "Mitsubishi", "Morgan", "Morris", "Nissan", "Oldsmobile",
                "Opel", "Peugeot", "Plymouth", "Pontiac", "Porsche", "Renault",
                "Reva", "Rolls Royce", "Rover", "Saab", "Seat", "Skoda", "Smart",
                "Ssangyong", "Subaru", "Suzuki", "Tazzari", "Tesla", "Think",
                "Toyota", "Triumph", "Volkswagen", "Volvo", "Andre");
        carTypeList = FXCollections.observableArrayList("Kombinertbil",
                "Lett lastebil", "Minibuss", "Personbil", "Varebil");
        bonusList = FXCollections.observableArrayList("75%", "70%", "60%",
                "50%", "40%", "30%", "20%", "f�rste bil", "10%", "0%",
                "-10%", "-20%", "-30%", "-40%", "-50%");

        yearOfProductionBox = new ComboBox(yearOfProductionList);
        yearOfProductionBox.setPromptText("Velg produksjons�r");
        yearOfProductionBox.setPrefWidth(OperationWindow.STAGE_WIDTH * 1 / 5);
        carBrandBox = new ComboBox(carBrandList);
        carBrandBox.setPromptText("Velg bilmerke");
        carBrandBox.setPrefWidth(OperationWindow.STAGE_WIDTH * 1 / 5);
        carTypeBox = new ComboBox(carTypeList);
        carTypeBox.setPromptText("Velg forsikringsgrunnlag");
        carTypeBox.setPrefWidth(OperationWindow.STAGE_WIDTH * 1 / 5);
        bonusBox = new ComboBox(bonusList);
        bonusBox.setPromptText("Velg bonus");
        bonusBox.setPrefWidth(OperationWindow.STAGE_WIDTH * 1 / 5);
        printArea = new TextArea();
        printArea.setEditable(false);

        registerCarScene = new Button("Registrer");
        registerCarScene.setOnAction(guiEventHandler);

        scrollPane = new ScrollPane(printArea);
        scrollPane.setPrefWidth(OperationWindow.STAGE_WIDTH * 3/5);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        itemContainer.getChildren().removeAll(firstNameLabel, lastNameLabel,
                birthNoLabel, adressLabel, zipCodeLabel, firstNameField,
                lastNameField, birthNumberField, adressField, zipCodeField,
                requestRegistration);
        itemContainer.addColumn(0, yearOfProductionBox, carBrandBox, carTypeBox,
                bonusBox, registerCarScene);
        borderPane = new BorderPane(itemContainer, header, scrollPane, footer,
                null);
        return new Scene(borderPane);
    }
}
