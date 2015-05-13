package baminsurances.gui.window.scene;

import baminsurances.gui.button.IconButton;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.GuiConfig;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.security.Key;

/**
 * @author Adrian Melsom
 */
public class NavigationScene extends GeneralScene{

    private Scene scene;
    private Button statisticsButton, customerInteractionButton, searchButton;
    private GuiEventHandler guiEventHandler;
    private BorderPane borderPane;
    private VBox container;
    private static final double WIDTH = GuiConfig.PRIMARY_WIDTH*1/4, HEIGHT = GuiConfig.PRIMARY_HEIGHT*1/10;

    public NavigationScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler, String displayName) {
        super(guiEventHandler, keyPressHandler, displayName);
        this.guiEventHandler = guiEventHandler;
        statisticsButton = new IconButton().IconButtonWithText(
                HEIGHT, HEIGHT, IconButton.STATISTIC_BUTTON,
                "Statistikk");
        statisticsButton.setPrefWidth(WIDTH);
        statisticsButton.setPrefHeight(HEIGHT);
        statisticsButton.setOnAction(guiEventHandler);
        customerInteractionButton = new IconButton().IconButtonWithText(
                HEIGHT, HEIGHT, IconButton.CUSTOMERS_BUTTON,
                "Kundebehandling");
        customerInteractionButton.setPrefWidth(WIDTH);
        customerInteractionButton.setPrefHeight(
                HEIGHT);
        customerInteractionButton.setOnAction(guiEventHandler);
        searchButton = new IconButton().IconButtonWithText(
                HEIGHT,
                HEIGHT, IconButton.SEARCH_BUTTON,
                "Søk");
        searchButton.setPrefWidth(WIDTH);
        searchButton.setPrefHeight(HEIGHT);
        searchButton.setOnAction(guiEventHandler);
        container = new VBox(10, statisticsButton, customerInteractionButton,
                searchButton);
        footerLeftSide.setPrefWidth(WIDTH * 1/2);
        footerRightSide.setPrefWidth(WIDTH * 1/2);
        footer = new HBox(0, footerLeftSide, footerRightSide);
        footer.setStyle("-fx-border-color: gray;");
        borderPane = new BorderPane(container, null, null, footer, null);
        scene = new Scene(borderPane);
    }

    public Scene getScene() {
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
