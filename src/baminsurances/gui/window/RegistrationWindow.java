package baminsurances.gui.window;

import baminsurances.gui.button.IconButton;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.window.scene.AddScene;
import baminsurances.gui.window.scene.InsurePersonScene;
import baminsurances.gui.window.scene.WelcomeScene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Adrian PC on 10/04/2015.
 */
public class RegistrationWindow{

    private Stage stage;
    private HBox buttonPane;

    private int width;
    private int height;

    private Button search, add, stats, house, car, person, boat;

    private static RegistrationWindow registrationWindow = new RegistrationWindow();

    GuiEventHandler handler;

    WelcomeScene welcomeScene;
    AddScene addScene;
    InsurePersonScene insurePersonScene;

    private RegistrationWindow() {

        height = 600;
        width = (height*16)/9;

        stage = new Stage();
        search = new IconButton().iconButton(100, 100, IconButton.SEARCH_BUTTON);
        add = new IconButton().iconButton(100, 100, IconButton.ADD_BUTTON);
        stats = new IconButton().iconButton(100, 100, IconButton.STATISTIC_BUTTON);
        house = new IconButton().iconButton(100, 100, IconButton.INSURE_HOUSE_BUTTON);
        car = new IconButton().iconButton(100, 100, IconButton.INSURE_CAR_BUTTON);
        person = new IconButton().iconButton(100, 100, IconButton.INSURE_PERSON_BUTTON);
        boat = new IconButton().iconButton(100, 100, IconButton.INSURE_BOAT_BUTTON);

        buttonPane = new HBox(30, search, add, person, house, car, boat, stats);
        buttonPane.setAlignment(Pos.CENTER);

        stage.setWidth(width);
        stage.setHeight(height);
        stage.setResizable(false);

    }

    public void clearScene()
    {
        welcomeScene = null;
        addScene = null;
        insurePersonScene = null;
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

    public WelcomeScene getWelcomeScene() {
        return welcomeScene;
    }

    public AddScene getAddScene() {
        return addScene;
    }

    public InsurePersonScene getInsurePersonScene() {
        return insurePersonScene;
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

    public void displayWelcomeScene(){
        clearScene();
        welcomeScene = new WelcomeScene(buttonPane, handler);
        stage.setScene(welcomeScene.getScene());
        stage.show();
    }

    public void displayAddScene(){
        clearScene();
        addScene = new AddScene(buttonPane, handler);
        stage.setScene(addScene.getScene());
    }

    public void displayInsurePersonScene(int sceneNumber) {
        if(sceneNumber == 1) {
            clearScene();
            insurePersonScene = new InsurePersonScene(buttonPane, handler);
            stage.setScene(insurePersonScene.getScene());
        } else{
          stage.setScene(insurePersonScene.requestAccepted());
        }

    }
}