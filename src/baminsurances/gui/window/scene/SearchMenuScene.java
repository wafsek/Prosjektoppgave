package baminsurances.gui.window.scene;

import baminsurances.gui.Gui;
import baminsurances.gui.eventhandler.GuiEventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Created by Adrian on 11/05/2015.
 */
public class SearchMenuScene {
    private Scene scene;
    private Button customerSearchButton, insuranceSearchButton,
            claimAdviceSearchButton, backButton;
    private Label informationLabel;

    public SearchMenuScene(HBox footer, GuiEventHandler guiEventHandler){
        customerSearchButton = new Button("Kundesøk");
        insuranceSearchButton = new Button("Forsikringssøk");
        claimAdviceSearchButton = new Button();
    }
}
