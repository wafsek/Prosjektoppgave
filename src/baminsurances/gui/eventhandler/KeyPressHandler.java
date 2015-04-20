package baminsurances.gui.eventhandler;

import baminsurances.gui.window.OperationWindow;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * Created by Adrian PC on 19/04/2015.
 */
public class KeyPressHandler implements EventHandler<KeyEvent> {

    private OperationWindow operationWindow;
    private TextField[] textFields;

    public KeyPressHandler(OperationWindow operationWindow){
        this.operationWindow = operationWindow;
    }

    @Override
    public void handle(KeyEvent e) {
        if(operationWindow.getStatisticsScene() != null){
            textFields = operationWindow.getStatisticsScene().getTextFields();
            for(int i = 0; i < textFields.length; i++){
                if(e.getSource() == textFields[i]){

                }
            }
        }
    }
}
