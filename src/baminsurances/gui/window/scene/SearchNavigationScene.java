package baminsurances.gui.window.scene;

import baminsurances.gui.Gui;
import baminsurances.gui.button.IconButton;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.GuiConfig;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Created by Adrian on 18/05/2015.
 */
public class SearchNavigationScene extends GeneralScene {

    private Label informationLabel;
    private Button customerSearchButton, insuranceSearchButton, claimAdviceButton;
    private static final double WIDTH = GuiConfig.PRIMARY_WIDTH*1/2, HEIGHT = GuiConfig.PRIMARY_HEIGHT*1/10;;
    private VBox container;

    /**
     * Creates the components which are used in this class.
     *
     * @param guiEventHandler
     * @param keyPressHandler
     */
    public SearchNavigationScene(GuiEventHandler guiEventHandler, KeyPressHandler keyPressHandler) {
        super(guiEventHandler, keyPressHandler);
        informationLabel = new Label("Dette vinduet er under utvikling.\nIngen funksjoner er knyttet enda.");
        customerSearchButton = new IconButton().IconButtonWithText(HEIGHT,
                HEIGHT, IconButton.CUSTOMERS_BUTTON, "Kundesøk");
        customerSearchButton.setPrefWidth(WIDTH * 2 / 3);
        insuranceSearchButton = new IconButton().IconButtonWithText(HEIGHT,
                HEIGHT, IconButton.SEARCH_BUTTON, "Forsikringssøk");
        insuranceSearchButton.setPrefWidth(WIDTH * 2/3);
        claimAdviceButton = new IconButton().IconButtonWithText(HEIGHT,
                HEIGHT, IconButton.CUSTOMERS_BUTTON, "Skademeldingssøk");
        claimAdviceButton.setPrefWidth(WIDTH * 2/3);
        container = new VBox(10, informationLabel, customerSearchButton, insuranceSearchButton, claimAdviceButton);
        container.setAlignment(Pos.CENTER);
        container.setStyle("-fx-padding: 10");

        footerRightSide.setPrefWidth(WIDTH * 1 / 2);
        footerLeftSide.setPrefWidth(WIDTH * 1 / 2);
        footer = new HBox(0, footerLeftSide, footerRightSide);
        footer.setStyle("-fx-border-color: gray;");

        borderPane = new BorderPane(container, null, null, footer, null);
        borderPane.setPrefWidth(WIDTH);
        borderPane.setPrefHeight(HEIGHT);
        borderPane.backgroundProperty().setValue(
                new Background(new BackgroundFill(Color.web("D7EBE6"),
                        CornerRadii.EMPTY, Insets.EMPTY)));

        scene = new Scene(borderPane);
    }
} // End of File
