package baminsurances.gui.window;

import baminsurances.api.Config;
import baminsurances.gui.button.IconButton;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.window.scene.AddScene;
import baminsurances.gui.window.scene.InsureCar;
import baminsurances.gui.window.scene.InsurePersonScene;
import baminsurances.gui.window.scene.WelcomeScene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Adrian PC on 10/04/2015.
 */
public class RegistrationWindow{

    private Stage stage;
    private HBox buttonPane, footer;

    private int width;
    private int height;

    private Button search, add, stats, house, car, person, boat, logOut;

    private static RegistrationWindow registrationWindow = new RegistrationWindow();

    private GuiEventHandler handler;

    private WelcomeScene welcomeScene;
    private AddScene addScene;
    private InsurePersonScene insurePersonScene;
    private InsureCar insureCarScene;


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
        logOut = new Button("Logg ut");

        buttonPane = new HBox(30, search, add, person, house, car, boat, stats);
        buttonPane.setAlignment(Pos.CENTER);
        footer = new HBox(40, logOut);
        footer.setAlignment(Pos.BASELINE_RIGHT);

        stage.setWidth(width);
        stage.setHeight(height);
        stage.setResizable(false);
        stage.setTitle(Config.getApplicationName());
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("../img/temp_logo.png")));

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

    public Button getLogOut(){
        return logOut;
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
        logOut.setOnAction(handler);
    }

    public void displayWelcomeScene(){
        clearScene();
        welcomeScene = new WelcomeScene(buttonPane, footer, handler);
        stage.setScene(welcomeScene.getScene());
        stage.show();
    }

    public void displayAddScene(){
        clearScene();
        addScene = new AddScene(buttonPane, footer, handler);
        stage.setScene(addScene.getScene());
    }

    public void displayInsurePersonScene(int sceneNumber) {
        if(sceneNumber == 1) {
            clearScene();
            insurePersonScene = new InsurePersonScene(buttonPane, footer, handler);
            stage.setScene(insurePersonScene.getScene());
        } else{
          stage.setScene(insurePersonScene.requestAccepted());
        }

    }

    public void displayInsureCarScene(){
        clearScene();
        insureCarScene = new InsureCar(buttonPane, footer, handler);
        stage.setScene(insureCarScene.getScene());
    }

    public void close(){
        stage.close();
    }
}