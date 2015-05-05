package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.window.OperationWindow;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by Adrian on 30/04/2015.
 */
public class SearchScene extends PersonSearchScene{

    private TextArea printArea;
    private VBox rightSideContainer;
    private HBox footer, header;

    public SearchScene(HBox header, HBox footer, GuiEventHandler handler){
        super(handler);
        this.header = header;
        this.footer = footer;

        printArea = new TextArea();
        printArea.setPrefHeight(OperationWindow.STAGE_HEIGHT * 2/3);
        printArea.setEditable(false);

        rightSideContainer = new VBox(0, personTable, printArea);

        borderPane = new BorderPane(itemContainer, header, rightSideContainer, footer, null);
        scene = new Scene(borderPane);
    }


























}

