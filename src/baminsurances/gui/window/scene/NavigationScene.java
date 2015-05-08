package baminsurances.gui.window.scene;

import baminsurances.gui.Gui;
import baminsurances.gui.button.IconButton;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.window.NavigationWindow;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Created by Adrian PC on 08/05/2015.
 */
public class NavigationScene {

    private Scene scene;
    private Button statisticsButton, customerInteractionButton, searchButton;
    private GuiEventHandler guiEventHandler;
    private BorderPane borderPane;
    private VBox container;

    public NavigationScene(GuiEventHandler guiEventHandler){
        this.guiEventHandler = guiEventHandler;
        statisticsButton = new IconButton().IconButtonWithText(NavigationWindow.STAGE_WIDTH*1/5,
                NavigationWindow.STAGE_WIDTH*1/5, IconButton.ADD_BUTTON, "Statistikk");
        statisticsButton.setPrefWidth(NavigationWindow.STAGE_WIDTH);
        statisticsButton.setPrefHeight(NavigationWindow.STAGE_HEIGHT * 1 / 4);
        statisticsButton.setOnAction(guiEventHandler);
        customerInteractionButton = new IconButton().IconButtonWithText(NavigationWindow.STAGE_WIDTH*1/5,
                NavigationWindow.STAGE_WIDTH*1/5, IconButton.ADD_BUTTON, "Kundebehandling");
        customerInteractionButton.setPrefWidth(NavigationWindow.STAGE_WIDTH);
        customerInteractionButton.setPrefHeight(NavigationWindow.STAGE_HEIGHT * 1 / 4);
        customerInteractionButton.setOnAction(guiEventHandler);
        searchButton = new IconButton().IconButtonWithText(NavigationWindow.STAGE_WIDTH*1/5,
                NavigationWindow.STAGE_WIDTH*1/5, IconButton.ADD_BUTTON, "Statistikk");
        searchButton.setPrefWidth(NavigationWindow.STAGE_WIDTH);
        searchButton.setPrefHeight(NavigationWindow.STAGE_HEIGHT * 1 / 4);
        searchButton.setOnAction(guiEventHandler);
        container = new VBox(24, statisticsButton, customerInteractionButton, searchButton);
        scene = new Scene(container);
    }

    public Scene getScene(){
        return scene;
    }

    public Button getStatisticsButton() {
        return statisticsButton;
    }

    public Button getCustomerInteractionButton() {
        return customerInteractionButton;
    }

    public Button getSearchButton() {
        return searchButton;
    }
}
