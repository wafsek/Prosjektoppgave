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
            if (statisticsScene != null && e.getSource() == statisticsScene.getTextFieldsIterator().next()) {
                statisticsScene.setEditableEmployeeFields();
                statisticsScene.setEditableCustomerFields();
                txtFieldsIterator = statisticsScene.getTextFieldList().iterator();
                resetIterators();
                return;
            }else if(travelInsuranceScene != null && e.getSource() == travelInsuranceScene.getTextFieldIterator().next()){
                System.out.println("jeg funker");
                travelInsuranceScene.setTableData(controller.findPeople());
                resetIterators();
                txtFieldsIterator = travelInsuranceScene.getTextFieldArrayList().iterator();
                return;
            }
        }
    }

    public void setStatisticsScene(StatisticsScene statisticsScene/*, ArrayList<TextField> textfields*/){
        //this.resetScenes();
        this.statisticsScene = statisticsScene;
        txtFieldsIterator = statisticsScene.getTextFieldList().iterator();
    }

    public void setTravelInsuranceScene(TravelInsuranceScene travelInsuranceScene){
        //this.resetScenes();
        this.travelInsuranceScene = travelInsuranceScene;
        txtFieldsIterator = travelInsuranceScene.getTextFieldArrayList().iterator();
    }

    public void setInsureHouseScene(InsureHouseScene insureHouseScene, ArrayList<TextField> textFields){
        //this.resetScenes();
        this.insureHouseScene = insureHouseScene;
        txtFieldsIterator = textFields.iterator();
    }

    public void setInsureBoatScene(InsureBoatScene insureBoatScene, ArrayList<TextField> textFields){
        //this.resetScenes();
        this.insureBoatScene = insureBoatScene;
        txtFieldsIterator = textFields.iterator();
    }

    public void setInsureCarScene(InsureCarScene insureCarScene, ArrayList<TextField> textFields){
        //this.resetScenes();
        this.insureCarScene = insureCarScene;
        txtFieldsIterator = textFields.iterator();
    }

    public void setSearchScene(SearchScene searchScene){
        //this.resetScenes();
        this.searchScene = searchScene;
        txtFieldsIterator = searchScene.getTextFieldArrayList().iterator();
    }

    /*private void resetScenes(){
        statisticsScene = null;
        travelInsuranceScene = null;
        insureHouseScene = null;
        insureBoatScene = null;
        insureCarScene = null;
        addScene = null;
        searchScene = null;
    }*/

    private void resetIterators(){
        if(statisticsScene != null) {
            statisticsScene.resetIterator();
        }if(travelInsuranceScene != null){
            travelInsuranceScene.resetIterator();
        }if(insureHouseScene != null){
            insureHouseScene.resetIterator();
        }if(insureBoatScene != null){
            insureBoatScene.resetIterator();
        }if(insureCarScene != null){
            insureCarScene.resetIterator();
        }if(addScene != null){
            addScene.resetIterator();
        }if(searchScene != null) {
            searchScene.resetIterator();
        }
    }
}
