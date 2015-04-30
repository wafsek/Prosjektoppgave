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
public class InsureCarScene extends PersonSearchScene{

    private final int PREFERRED_TEXTFIELD_COMBOBOX_WIDTH = 175;
    private ObservableList<String> yearOfProductionList, carBrandList,
    carTypeList, bonusList;
    private ComboBox yearOfProduction, carBrand, carType, bonus;
    private TextArea printArea;
    private Button register;
    private HBox footer, header;


    private ScrollPane scrollPane;

    /**
     * creates a new Scene based on the given values.
     * @param handler
     */
    public InsureCarScene(HBox header, HBox footer, GuiEventHandler handler){
        super(handler);
        this.header = header;
        this.footer = footer;
        borderPane = new BorderPane(itemContainer, header, personList, footer, null);
        scene = new Scene(borderPane);

    }

    /**
     * recreates and adds -FX components to the initial Scene.
     *
     * @return the recreated Scene.
     */
    public Scene requestApproved(){

        yearOfProductionList = FXCollections.observableArrayList("1960 eller ti" +
                        "dligere", "1961 - 1970", "1971 - 1980", "1981 - 1990", "1991 - 2000",
                "2001 - 2010", "2011 - 2015");
        carBrandList = FXCollections.observableArrayList("Alfa Romeo", "Aston Martin",
                "Audi", "Austin", "Bentley", "BMW", "Buddy", "Buick", "Cadillac",
                "Chevrolet", "Chrysler", "Citroen", "Dacia", "Daewoo", "Daihatsu",
                "Datsun", "DeLorean", "Dodge", "Ferrari", "Fiat", "Fisker", "Ford",
                "GMC", "Honda", "Hummer", "Hyundai", "Infiniti", "Isuzu", "Iveco",
                "Jaguar", "Jeep", "Jensen", "Kewet", "Kia", "Lada", "Lamborghini",
                "Lancia", "Land Rover", "Lexus", "Lincoln", "Lotus", "Maserati",
                "Mazda", "McLaren", "Mercedes-Benz", "Mercury", "MG", "mia electric",
                "MINI", "Mitsubishi", "Morgan", "Morris", "Nissan", "Oldsmobile",
                "Opel", "Peugeot", "Plymouth", "Pontiac", "Porsche", "Renault",
                "Reva", "Rolls Royce", "Rover", "Saab", "Seat", "Skoda", "Smart",
                "Ssangyong", "Subaru", "Suzuki", "Tazzari", "Tesla", "Think",
                "Toyota", "Triumph", "Volkswagen", "Volvo", "Andre");
        carTypeList = FXCollections.observableArrayList("Kombinertbil",
                "Lett lastebil", "Minibuss", "Personbil", "Varebil");
        bonusList = FXCollections.observableArrayList("75%", "70%", "60%",
                "50%", "40%", "30%", "20%", "første bil", "10%", "0%",
                "-10%", "-20%", "-30%", "-40%", "-50%");

        yearOfProduction = new ComboBox(yearOfProductionList);
        yearOfProduction.setPromptText("Velg produksjonsår");
        yearOfProduction.setPrefWidth(PREFERRED_TEXTFIELD_COMBOBOX_WIDTH);
        carBrand = new ComboBox(carBrandList);
        carBrand.setPromptText("Velg bilmerke");
        carBrand.setPrefWidth(PREFERRED_TEXTFIELD_COMBOBOX_WIDTH);
        carType = new ComboBox(carTypeList);
        carType.setPromptText("Velg forsikringsgrunnlag");
        carType.setPrefWidth(PREFERRED_TEXTFIELD_COMBOBOX_WIDTH);
        bonus = new ComboBox(bonusList);
        bonus.setPromptText("Velg bonus");
        bonus.setPrefWidth(PREFERRED_TEXTFIELD_COMBOBOX_WIDTH);
        printArea = new TextArea();
        printArea.setEditable(false);

        register = new Button("Registrer");
        register.setOnAction(handler);

        scrollPane = new ScrollPane(printArea);
        scrollPane.setPrefWidth(OperationWindow.STAGE_WIDTH * 3/5);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        itemContainer.getChildren().removeAll(firstNameLabel, lastNameLabel, birthNoLabel,
                adressLabel, zipCodeLabel, firstName, lastName, birthNo, adress, zipCode,
                requestRegistration);
        itemContainer.addColumn(0, yearOfProduction, carBrand, carType, bonus, register);
        borderPane = new BorderPane(itemContainer, header, scrollPane, footer, null);
        return new Scene(borderPane);
    }
}
