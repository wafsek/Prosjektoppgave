package baminsurances.gui.eventhandler;

import baminsurances.controller.Controller;
import baminsurances.logging.CustomLogger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Control;

import java.util.logging.Level;

/**
 * Created by Adrian on 14/04/2015.
 */
public class GuiEventHandler implements EventHandler<ActionEvent> {
    private CustomLogger logger = CustomLogger.getInstance();
    private Controller controller;
    public GuiEventHandler(Controller controller) {
        this.controller = controller;
        setGuiEventHandler();
    }

    public void setGuiEventHandler()
    {
        
    }


    @Override
    public void handle(ActionEvent e) {
        logger.log("Gui event", Level.FINER);
        controller.handleControl((Control) (e.getSource()));
    }
}
