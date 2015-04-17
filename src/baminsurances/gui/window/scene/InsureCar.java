package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.GuiEventHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by Adrian on 15/04/2015.
 */
public class InsureCar {

    private Scene scene;

    private ObservableList<String> yearOfProductionList, carBrandList,
    carTypeList, bonusList;
    private ComboBox yearOfProduction, carBrand, carType, bonus;
    private TextArea printArea;

    private Button register;

    private BorderPane borderPane;
    private VBox vBox;
    private ScrollPane scrollPane;
    private GridPane itemContainer;

    private GuiEventHandler handler;

    public InsureCar(HBox rowBox, HBox footer, GuiEventHandler handler){
        this.handler = handler;

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
        carBrand = new ComboBox(carBrandList);
        carBrand.setPromptText("Velg bilmerke");
        carType = new ComboBox(carTypeList);
        carType.setPromptText("Velg forsikringsgrunnlag");
        bonus = new ComboBox(bonusList);
        bonus.setPromptText("Velg bonus");
        printArea = new TextArea();
        printArea.setEditable(false);

        register = new Button("Registrer");

        itemContainer = new GridPane();
        itemContainer.addColumn(0, yearOfProduction, carBrand, carType, bonus, register);
        itemContainer.setAlignment(Pos.CENTER);
        itemContainer.setVgap(30);
        scrollPane = new ScrollPane(printArea);
        scrollPane.setPrefWidth(600);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        borderPane = new BorderPane(itemContainer, rowBox, scrollPane, footer, null);

        scene = new Scene(borderPane);
    }

    public Scene getScene(){
        return scene;
    }
}
