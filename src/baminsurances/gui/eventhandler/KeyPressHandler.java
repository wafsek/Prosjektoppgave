package baminsurances.gui.eventhandler;

import baminsurances.gui.window.OperationWindow;
import baminsurances.gui.window.scene.StatisticsScene;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Adrian PC on 19/04/2015.
 */
public class KeyPressHandler implements EventHandler<KeyEvent> {

    private OperationWindow operationWindow;
    private ArrayList<TextField> textFields;
    private StatisticsScene statisticsScene;
    private Iterator txtFieldsIterator;

    public KeyPressHandler(OperationWindow operationWindow){
        this.operationWindow = operationWindow;
    }

    @Override
    public void handle(KeyEvent e) {
        while(statisticsScene.getTextFieldsIterator().hasNext()){
            if(e.getSource() == statisticsScene.getTextFieldsIterator().next()){
                statisticsScene.setEditableEmployeeFields();
                statisticsScene.setEditableCustomerFields();
                statisticsScene.resetIterator();
                break;
            }
        }
    }

    public void setStatisticsScene(StatisticsScene statisticsScene, ArrayList<TextField> textfields){
        this.statisticsScene = statisticsScene;
        this.textFields = textfields;
        txtFieldsIterator = textfields.iterator();
    }
}
