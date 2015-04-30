package baminsurances.gui.window.scene;

import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.window.OperationWindow;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Created by Adrian on 30/04/2015.
 */
public class SearchScene extends PersonSearchScene{

    private HBox header, footer;
    private TextArea printArea;

    public SearchScene(HBox header, HBox footer, GuiEventHandler handler){
        super(header, footer, handler);
        this.header = header;
        this.footer = footer;

        printArea = new TextArea();
        printArea.setPrefWidth(OperationWindow.STAGE_WIDTH * 2/4);
        printArea.setEditable(false);

        borderPane = new BorderPane(itemContainer, header, personList, footer, null);
        scene = new Scene(borderPane);
    }


























}

