package baminsurances.gui.eventhandler;

import baminsurances.controller.Controller;
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
    private TravelInsuranceScene travelInsuranceScene;
    private InsureHouseScene insureHouseScene;
    private InsureBoatScene insureBoatScene;
    private InsureCarScene insureCarScene;
    private AddScene addScene;
    private SearchScene searchScene;
    private Iterator txtFieldsIterator;
    private Controller controller;

    public KeyPressHandler(OperationWindow operationWindow,Controller controller){
        this.operationWindow = operationWindow;
        this.controller = controller;
    }

    @Override
    public void handle(KeyEvent e) {
        while (txtFieldsIterator.hasNext()){
            if (e.getSource() == statisticsScene.getTextFieldsIterator()) {
                statisticsScene.setEditableEmployeeFields();
                statisticsScene.setEditableCustomerFields();
                statisticsScene.resetIterator();
                return;
            }/*else if(e.getSource() == searchScene.getTextFieldArrayList().iterator().next()){
                System.out.println("jeg funker");
                return;
            }*/
        }
    }

    public void setStatisticsScene(StatisticsScene statisticsScene/*, ArrayList<TextField> textfields*/){
        this.statisticsScene = statisticsScene;
        txtFieldsIterator = statisticsScene.getTextFieldList().iterator();
    }

    public void setInsurePersonScene(TravelInsuranceScene travelInsuranceScene, ArrayList<TextField> textFields){
        this.travelInsuranceScene = travelInsuranceScene;
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

    public void setSearchScene(SearchScene searchScene){
        this.searchScene = searchScene;
        txtFieldsIterator = searchScene.getTextFieldArrayList().iterator();
    }
}
