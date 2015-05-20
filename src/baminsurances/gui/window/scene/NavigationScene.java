package baminsurances.gui.window.scene;

import baminsurances.gui.button.IconButton;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.GuiConfig;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.security.Key;

/**
 * @author Adrian Melsom
 */
public class NavigationScene extends GeneralScene{

    private Scene scene;
    private Button statisticsButton, customerInteractionButton, searchButton,
            settingsButton;
    private GuiEventHandler guiEventHandler;
    private BorderPane borderPane;
    private VBox container;
    private static final double WIDTH = GuiConfig.PRIMARY_WIDTH*1/2, HEIGHT = GuiConfig.PRIMARY_HEIGHT*1/10;

    public NavigationScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler) {
        super(guiEventHandler, keyPressHandler);
        this.guiEventHandler = guiEventHandler;
        statisticsButton = new IconButton().IconButtonWithText(
                HEIGHT, HEIGHT, IconButton.STATISTIC_BUTTON,
                "Statistikk");
        statisticsButton.setPrefWidth(WIDTH * 1 / 2);
        statisticsButton.setOnAction(guiEventHandler);
        customerInteractionButton = new IconButton().IconButtonWithText(
                HEIGHT, HEIGHT, IconButton.CUSTOMERS_BUTTON,
                "Kundebehandling");
        customerInteractionButton.setPrefWidth(WIDTH * 1 / 2);
        customerInteractionButton.setPrefHeight(
                HEIGHT);
        customerInteractionButton.setOnAction(guiEventHandler);
        searchButton = new IconButton().IconButtonWithText(
                HEIGHT,
                HEIGHT, IconButton.SEARCH_BUTTON,
                "Søk");
        searchButton.setPrefWidth(WIDTH * 1 / 2);
        searchButton.setPrefHeight(HEIGHT);
        searchButton.setOnAction(guiEventHandler);

        settingsButton = new IconButton().IconButtonWithText(
                HEIGHT, HEIGHT, IconButton.SEARCH_BUTTON, "Innstillinger");
        settingsButton.setPrefWidth(WIDTH * 1 / 2);
        settingsButton.setPrefHeight(HEIGHT);
        settingsButton.setOnAction(guiEventHandler);
        container = new VBox(10, statisticsButton, customerInteractionButton ,searchButton, settingsButton);
        container.setAlignment(Pos.CENTER);
        container.setPrefWidth(WIDTH);
        container.setStyle("-fx-padding: 10");

        footerLeftSide.setPrefWidth(WIDTH * 1 / 2);
        footerRightSide.setPrefWidth(WIDTH * 1 / 2);
        footerRightSide.getChildren().remove(backButton);
        footer = new HBox(0, footerLeftSide, footerRightSide);
        footer.setStyle("-fx-border-color: gray;");
        borderPane = new BorderPane(container, null, null, footer, null);
        borderPane.backgroundProperty().setValue(
                new Background(new BackgroundFill(Color.web("D7EBE6"),
                        CornerRadii.EMPTY, Insets.EMPTY)));

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

    public Button getSettingsButton() {
        return settingsButton;
    }
}
