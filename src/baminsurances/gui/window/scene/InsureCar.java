package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.GuiEventHandler;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Adrian on 15/04/2015.
 */
public class InsureCar {

    private Scene scene;

    private ObservableList<String> yearOfProductionList, carBrandList,
    carTypeList, bonusList;
    private ComboBox yearOfProduction, carBrand, carType, bonus;
    private TextArea printArea;

    GuiEventHandler handler;

    public InsureCar(HBox rowBox, GuiEventHandler handler){
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
        carBrand = new ComboBox(carBrandList);
        carType = new ComboBox(carTypeList);
        bonus = new ComboBox(bonusList);

    }
}
