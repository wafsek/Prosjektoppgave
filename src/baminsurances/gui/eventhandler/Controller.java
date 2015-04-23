package baminsurances.gui.eventhandler;

import baminsurances.gui.window.OperationWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 * Created by Adrian PC on 19/04/2015.
 */
public class Controller implements EventHandler<ActionEvent> {

    OperationWindow operationWindow;

    public Controller(OperationWindow operationWindow){
        this.operationWindow = operationWindow;
    }

    @Override
    public void handle(ActionEvent e) {
        if(e.getSource() == operationWindow.getAddScene().getRegister()){
            operationWindow.getAddScene().requestRegistration();
        }
    }
}
