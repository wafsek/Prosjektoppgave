package baminsurances.gui.window;

import baminsurances.gui.button.IconButton;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.window.scene.AddScene;
import baminsurances.gui.window.scene.WelcomeScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Adrian PC on 10/04/2015.
 */
public class RegistrationWindow{

    private Stage stage;
    private Scene scene;
    private GridPane buttonPane;

    BorderPane borderPane;

    private Button search, add, stats, house, car, person, boat;

    private static RegistrationWindow registrationWindow = new RegistrationWindow();

    private WelcomeScene welcomeScene;
    private AddScene addScene;

    GuiEventHandler handler;

    private RegistrationWindow() {

        stage = new Stage();
        search = new IconButton().iconButton(100, 100, IconButton.SEARCH_BUTTON);
        add = new IconButton().iconButton(100, 100, IconButton.ADD_BUTTON);
        stats = new IconButton().iconButton(100, 100, IconButton.STATISTIC_BUTTON);
        house = new IconButton().iconButton(100, 100, IconButton.INSURE_HOUSE_BUTTON);
        car = new IconButton().iconButton(100, 100, IconButton.INSURE_CAR_BUTTON);
        person = new IconButton().iconButton(100, 100, IconButton.INSURE_PERSON_BUTTON);
        boat = new IconButton().iconButton(100, 100, IconButton.INSURE_BOAT_BUTTON);



        buttonPane = new GridPane();
        buttonPane.add(search, 0, 0);
        buttonPane.add(add, 1, 0);
        buttonPane.add(stats, 2, 0);
        buttonPane.add(house, 3, 0);
        buttonPane.add(car, 4, 0);
        buttonPane.add(person, 5, 0);
        buttonPane.add(boat, 6, 0);
        buttonPane.setHgap(10);
        buttonPane.setAlignment(Pos.CENTER);


        stage.setMinHeight(600);
        stage.setMinWidth(900);

    }

    public Button getSearch() {
        return search;
    }

    public Button getAdd() {
        return add;
    }

    public Button getStats() {
        return stats;
    }

    public Button getHouse() {
        return house;
    }

    public Button getCar() {
        return car;
    }

    public Button getPerson() {
        return person;
    }

    public Button getBoat() {
        return boat;
    }

    public static RegistrationWindow getRegistrationWindow() {
        return registrationWindow;
    }

    public void setGuiEventHandler(GuiEventHandler geh){
        handler = geh;
        search.setOnAction(handler);
        add.setOnAction(handler);
        stats.setOnAction(handler);
        house.setOnAction(handler);
        car.setOnAction(handler);
        person.setOnAction(handler);
        boat.setOnAction(handler);
    }

    public void clearScene(){
        addScene = null;
        welcomeScene = null;
    }

    public void displayWelcomeScene(){
        clearScene();
        welcomeScene = new WelcomeScene(buttonPane);
        stage.setScene(welcomeScene.getScene());
        stage.show();
    }

    public void displayAddScene(){
        clearScene();
        addScene = new AddScene(buttonPane);
        stage.setScene(addScene.getScene());
    }
}