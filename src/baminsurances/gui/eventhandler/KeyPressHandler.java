package baminsurances.gui.eventhandler;

import baminsurances.gui.window.OperationWindow;
import baminsurances.gui.window.scene.*;
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
    private StatisticsScene statisticsScene;
    private InsurePersonScene insurePersonScene;
    private InsureHouseScene insureHouseScene;
    private InsureBoatScene insureBoatScene;
    private InsureCarScene insureCarScene;
    private Iterator txtFieldsIterator;

    public KeyPressHandler(OperationWindow operationWindow){
        this.operationWindow = operationWindow;
    }

    @Override
    public void handle(KeyEvent e) {
        while(txtFieldsIterator.hasNext()){
            if(e.getSource() == statisticsScene.getTextFieldsIterator().next()){
                statisticsScene.setEditableEmployeeFields();
                statisticsScene.setEditableCustomerFields();
                statisticsScene.resetIterator();
                txtFieldsIterator = statisticsScene.getTextFieldsIterator();
                break;
            }
        }
    }

    public void setStatisticsScene(StatisticsScene statisticsScene, ArrayList<TextField> textfields){
        this.statisticsScene = statisticsScene;
        txtFieldsIterator = textfields.iterator();
    }

    public void setInsurePersonScene(InsurePersonScene insurePersonScene, ArrayList<TextField> textFields){
        this.insurePersonScene = insurePersonScene;
        txtFieldsIterator = textFields.iterator();
    }

    public void setInsureHouseScene(InsureHouseScene insureHouseScene, ArrayList<TextField> textFields){
        this.insureHouseScene = insureHouseScene;
        txtFieldsIterator = textFields.iterator();
    }

    public void setInsureBoatScene(InsureBoatScene insureBoatScene, ArrayList<TextField> textFields){
        this.insureBoatScene = insureBoatScene;
        txtFieldsIterator = textFields.iterator();
    }

    public void setInsureCarScene(InsureCarScene insureCarScene, ArrayList<TextField> textFields){
        this.insureCarScene = insureCarScene;
        txtFieldsIterator = textFields.iterator();
    }
}
