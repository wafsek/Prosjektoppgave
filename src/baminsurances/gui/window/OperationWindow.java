package baminsurances.gui.window;

import baminsurances.api.Config;
import baminsurances.gui.button.IconButton;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.scene.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Adrian PC on 10/04/2015.
 */
public class OperationWindow {

    public static final int STAGE_HEIGHT = 700, STAGE_WIDTH = (STAGE_HEIGHT*16)/9;
    private Stage stage;
    private HBox header, footer;

    private Button searchButton, addButton, statsButton, houseButton, carButton, personButton, boatButton, logOutButton;

    private static OperationWindow operationWindow = new OperationWindow();

    private GuiEventHandler handler;
    private KeyPressHandler keyHandler;

    private OperationWindow() {

        

        stage = new Stage();
        searchButton = new IconButton().iconButton(100, 100, IconButton.SEARCH_BUTTON);
        addButton = new IconButton().iconButton(100, 100, IconButton.ADD_BUTTON);
        statsButton = new IconButton().iconButton(100, 100, IconButton.STATISTIC_BUTTON);
        houseButton = new IconButton().iconButton(100, 100, IconButton.INSURE_HOUSE_BUTTON);
        carButton = new IconButton().iconButton(100, 100, IconButton.INSURE_CAR_BUTTON);
        personButton = new IconButton().iconButton(100, 100, IconButton.INSURE_PERSON_BUTTON);
        boatButton = new IconButton().iconButton(100, 100, IconButton.INSURE_BOAT_BUTTON);
        logOutButton = new Button("Logg ut");

        header = new HBox(30, searchButton, addButton, personButton, houseButton, carButton, boatButton, statsButton);
        header.setAlignment(Pos.CENTER);
        footer = new HBox(40, logOutButton);
        footer.setAlignment(Pos.BASELINE_RIGHT);
        footer.setStyle("-fx-border-color: gray;");

        header.setStyle("-fx-border-color: gray;" +
                "-fx-padding: 5;");

        stage.setWidth(STAGE_WIDTH);
        stage.setHeight(STAGE_HEIGHT);
        stage.setResizable(false);
        stage.setTitle(Config.getApplicationName());
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("../img/temp_logo.png")));
    }

    public Button getSearchSceneButton() {
        return searchButton;
    }

    public Button getAddSceneButton() {
        return addButton;
    }

    public Button getStatsSceneButton() {
        return statsButton;
    }

    public Button getHouseSceneButton() {
        return houseButton;
    }

    public Button getCarSceneButton() {
        return carButton;
    }

    public Button getPersonSceneButton() {
        return personButton;
    }

    public Button getBoatSceneButton() {
        return boatButton;
    }

    public Button getLogOutButton(){
        return logOutButton;
    }

    public static OperationWindow getOperationWindow() {
        return operationWindow;
    }

    public HBox getHeader() {
        return header;
    }

    public HBox getFooter() {
        return footer;
    }

    public void setGuiEventHandler(GuiEventHandler geh){
        handler = geh;
        searchButton.setOnAction(handler);
        addButton.setOnAction(handler);
        statsButton.setOnAction(handler);
        houseButton.setOnAction(handler);
        carButton.setOnAction(handler);
        personButton.setOnAction(handler);
        boatButton.setOnAction(handler);
        logOutButton.setOnAction(handler);
    }
    
    public void setKeyHandler(KeyPressHandler keyHandler){
        this.keyHandler = keyHandler;
    }

    public void initialize(Scene scene){
        stage.setScene(scene);
        stage.show();
    }

    public void displayScene(Scene scene){
        stage.setScene(scene);
    }

    public void close(){
        stage.close();
    }
}