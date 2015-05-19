package baminsurances.gui.eventhandler;

import baminsurances.controller.Controller;
import baminsurances.logging.CustomLogger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Control;

import java.util.logging.Level;

/**
 * Created by Adrian on 14/04/2015.
 * @author Adrian Melsom
 */

public class GuiEventHandler implements EventHandler<ActionEvent> {
    private CustomLogger logger = CustomLogger.getInstance();
    private Controller controller;

    /**
     * Keeps control of which class is allowed to control GUI
     *
     * @param controller
     */
    public GuiEventHandler(Controller controller) {
        this.controller = controller;
    }

    /**
     * Listens to any Action event that happens in GUI, which is
     * set to be controlled by this class.
     *
     * @param e
     */
    @Override
    public void handle(ActionEvent e) {
        logger.log("Gui event", Level.FINER);
        controller.handleControl((Control) (e.getSource()));
    }
}
