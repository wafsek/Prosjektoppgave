package baminsurances.gui.window;

import baminsurances.api.Config;
import baminsurances.gui.button.IconButton;
import baminsurances.gui.eventhandler.Controller;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.scene.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Adrian PC on 10/04/2015.
 */
public class OperationWindow {

    private Stage stage;
    private HBox header, footer;

    private int width;
    private int height;

    private Button search, add, stats, house, car, person, boat, logOut;

    private static OperationWindow operationWindow = new OperationWindow();

    private GuiEventHandler handler;
    private KeyPressHandler keyHandler;
    private Controller controller;

    private WelcomeScene welcomeScene;
    private AddScene addScene;
    private InsurePersonScene insurePersonScene;
    private InsureCarScene insureCarScene;
    private StatisticsScene statisticsScene;
    private InsureHouseScene insureHouseScene;
    private InsureBoatScene insureBoatScene;

    private OperationWindow() {
        height = 600;
        width = (height*16)/9;

        keyHandler = new KeyPressHandler(this);
        controller = new Controller(this);

        stage = new Stage();
        search = new IconButton().iconButton(100, 100, IconButton.SEARCH_BUTTON);
        add = new IconButton().iconButton(100, 100, IconButton.ADD_BUTTON);
        stats = new IconButton().iconButton(100, 100, IconButton.STATISTIC_BUTTON);
        house = new IconButton().iconButton(100, 100, IconButton.INSURE_HOUSE_BUTTON);
        car = new IconButton().iconButton(100, 100, IconButton.INSURE_CAR_BUTTON);
        person = new IconButton().iconButton(100, 100, IconButton.INSURE_PERSON_BUTTON);
        boat = new IconButton().iconButton(100, 100, IconButton.INSURE_BOAT_BUTTON);
        logOut = new Button("Logg ut");

        header = new HBox(30, search, add, person, house, car, boat, stats);
        header.setAlignment(Pos.CENTER);
        footer = new HBox(40, logOut);
        footer.setAlignment(Pos.BASELINE_RIGHT);
        footer.setStyle("-fx-border-color: gray;");

        header.setStyle("-fx-border-color: gray;" +
                "-fx-padding: 5;");

        stage.setWidth(width);
        stage.setHeight(height);
        stage.setResizable(false);
        stage.setTitle(Config.getApplicationName());
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("../img/temp_logo.png")));

        welcomeScene = new WelcomeScene(header, footer, handler);
        addScene = new AddScene(header, footer, handler, controller);
        insurePersonScene = new InsurePersonScene(header, footer, handler);
        insureHouseScene = new InsureHouseScene(header, footer, handler);
        insureCarScene = new InsureCarScene(header, footer, handler);
        insureBoatScene = new InsureBoatScene(header, footer, handler);
        statisticsScene = new StatisticsScene(footer, keyHandler, controller, handler);
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

    public InsureCarScene getInsureCarScene(){
        return insureCarScene;
    }

    public StatisticsScene getStatisticsScene(){
        return statisticsScene;
    }

    public InsureHouseScene getInsureHouseScene(){
        return insureHouseScene;
    }

    public InsureBoatScene getInsureBoatScene(){
        return insureBoatScene;
    }

    public static OperationWindow getOperationWindow() {
        return operationWindow;
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
        welcomeScene = new WelcomeScene(header, footer, handler);
        stage.setScene(welcomeScene.getScene());
        stage.show();
    }

    public void displayAddScene(){
        addScene = new AddScene(header, footer, handler, controller);
        stage.setScene(addScene.getScene());
    }

    public void displayInsurePersonScene(int sceneNumber) {
        if(sceneNumber == 1) {
            insurePersonScene = new InsurePersonScene(header, footer, handler);
            stage.setScene(insurePersonScene.getScene());
        } else{
            stage.setScene(insurePersonScene.requestApproved());
        }

    }

    public void displayInsureCarScene(int sceneNumber){
        if (sceneNumber == 1) {
            insureCarScene = new InsureCarScene(header, footer, handler);
            stage.setScene(insureCarScene.getScene());
        }else{
            stage.setScene(insureCarScene.requestApproved());
        }
    }

    public void displayStatsScene(){
        statisticsScene = new StatisticsScene(footer, keyHandler, controller, handler);
        stage.setScene(statisticsScene.getScene());
        keyHandler.setStatisticsScene(statisticsScene, statisticsScene.getTextFields());

    }

    public void displayInsureHouseScene(int sceneNumber){
        if(sceneNumber == 1){
            insureHouseScene = new InsureHouseScene(header, footer, handler);
            stage.setScene(insureHouseScene.getScene());
        }else{
            stage.setScene(insureHouseScene.requestApproved());
        }
    }

    public void displayInsureBoatScene(int sceneNumber){
        if(sceneNumber == 1){
            insureBoatScene = new InsureBoatScene(header, footer, handler);
            stage.setScene(insureBoatScene.getScene());
        }else{
            stage.setScene(insureBoatScene.requestApproved());
        }
    }

    public void close(){
        stage.close();
    }
}