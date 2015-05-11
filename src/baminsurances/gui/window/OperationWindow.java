package baminsurances.gui.window;

import baminsurances.api.Config;
import baminsurances.gui.button.IconButton;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    private Button searchButton, claimAdviceButton, addButton, statisticsButton, houseButton, carButton,
            personButton, boatButton, logOutButton, backButton;
    private Label displaNameLabel;

    private static OperationWindow operationWindow = new OperationWindow();

    private GuiEventHandler handler;
    private KeyPressHandler keyHandler;

    private OperationWindow() {
        stage = new Stage();
        //searchButton = new IconButton().iconButton(STAGE_HEIGHT*1/7, STAGE_HEIGHT*1/7, IconButton.SEARCH_BUTTON);
        claimAdviceButton = new IconButton().iconButton(
                STAGE_HEIGHT*1/7, STAGE_HEIGHT*1/7, IconButton.ADD_BUTTON);
        statisticsButton = new IconButton().iconButton(
                STAGE_HEIGHT*1/7,STAGE_HEIGHT*1/7, IconButton.STATISTIC_BUTTON);
        addButton = new IconButton().iconButton(
                STAGE_HEIGHT*1/7, STAGE_HEIGHT*1/7, IconButton.ADD_BUTTON);
        houseButton = new IconButton().iconButton(
                STAGE_HEIGHT*1/7, STAGE_HEIGHT*1/7, IconButton.INSURE_HOUSE_BUTTON);
        carButton = new IconButton().iconButton(
                STAGE_HEIGHT*1/7, STAGE_HEIGHT*1/7, IconButton.INSURE_CAR_BUTTON);
        personButton = new IconButton().iconButton(
                STAGE_HEIGHT*1/7, STAGE_HEIGHT*1/7, IconButton.INSURE_PERSON_BUTTON);
        boatButton = new IconButton().iconButton(
                STAGE_HEIGHT*1/7, STAGE_HEIGHT*1/7, IconButton.INSURE_BOAT_BUTTON);
        backButton = new IconButton().iconButton(
                STAGE_HEIGHT*1/7, STAGE_HEIGHT*1/7, IconButton.STATISTIC_BUTTON);

        logOutButton = new Button("Logg ut");

        header = new HBox(50, addButton, personButton, houseButton, carButton,
                boatButton, claimAdviceButton, backButton);
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-border-color: gray;" +
                "-fx-padding: 5;");

        stage.setWidth(STAGE_WIDTH);
        stage.setHeight(STAGE_HEIGHT);
        stage.setResizable(false);
        stage.setTitle(Config.getApplicationName());
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream(
                "../img/temp_logo.png")));
    }

    public Button getSearchSceneButton() {
        return searchButton;
    }

    public Button getAddSceneButton() {
        return addButton;
    }

    public Button getStatsSceneButton() {
        return statisticsButton;
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

    public Button getClaimAdviceButton(){
        return claimAdviceButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getLogOutButton() {
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

    public void setGuiEventHandler(GuiEventHandler geh) {
        handler = geh;
        //searchButton.setOnAction(guiEventHandler);
        claimAdviceButton.setOnAction(handler);
        addButton.setOnAction(handler);
        statisticsButton.setOnAction(handler);
        houseButton.setOnAction(handler);
        carButton.setOnAction(handler);
        personButton.setOnAction(handler);
        boatButton.setOnAction(handler);
        logOutButton.setOnAction(handler);
        backButton.setOnAction(handler);
    }

    public void createFooter(String displayName) {
        displaNameLabel = new Label(displayName);
        footer = new HBox(STAGE_WIDTH*5/6, displaNameLabel, logOutButton);
        footer.setAlignment(Pos.BASELINE_RIGHT);
        footer.setStyle("-fx-border-color: gray;");
    }
    
    public void setKeyHandler(KeyPressHandler keyHandler) {
        this.keyHandler = keyHandler;
    }

    public void initialize(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }

    public void displayScene(Scene scene) {
        stage.setScene(scene);
    }

    public void close() {
        stage.close();
    }
}