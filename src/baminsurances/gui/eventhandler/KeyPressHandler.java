package baminsurances.gui.eventhandler;

import baminsurances.gui.window.OperationWindow;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

/**
 * Created by Adrian PC on 19/04/2015.
 */
public class KeyPressHandler implements EventHandler<KeyEvent> {

    private OperationWindow operationWindow;
    private ArrayList<TextField> textFields;

    public KeyPressHandler(OperationWindow operationWindow){
        this.operationWindow = operationWindow;
    }

    @Override
    public void handle(KeyEvent e) {

    }
}
