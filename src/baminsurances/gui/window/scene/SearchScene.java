package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.OperationWindow;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Adrian Melsom
 */
public class SearchScene extends PersonSearchScene {

    private TextArea printArea;
    private VBox rightSideContainer;
    private HBox footer, header;

    public SearchScene(HBox header, HBox footer, GuiEventHandler handler,
            KeyPressHandler keyPressHandler, String displayName){
        super(handler, keyPressHandler, displayName);
        this.header = header;
        this.footer = footer;

        printArea = new TextArea();
        printArea.setPrefHeight(OperationWindow.STAGE_HEIGHT * 2 / 3);
        printArea.setEditable(false);
        rightSideContainer = new VBox(0, customerTable, printArea);

        itemContainer = new GridPane();
        itemContainer.addColumn(0, firstNameLabel, lastNameLabel, birthNoLabel,
                adressLabel, zipCodeLabel);
        itemContainer.addColumn(1, firstNameField, lastNameField,
                birthNumberField, adressField, zipCodeField);
        itemContainer.setAlignment(Pos.CENTER);
        itemContainer.setVgap(30);
        itemContainer.setHgap(20);
        itemContainer.setStyle("-fx-border-color: gray;");

        borderPane = new BorderPane(itemContainer, header, rightSideContainer,
                footer, null);
        scene = new Scene(borderPane);

        keyPressHandler.setSearchScene(this);
    }
}
