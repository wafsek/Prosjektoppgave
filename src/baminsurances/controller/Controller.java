package baminsurances.controller;

import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.window.LoginWindow;
import baminsurances.gui.window.OperationWindow;
import baminsurances.gui.window.MessageDialog;
import javafx.scene.control.Control;


/**
 * Created by baljit on 30.04.2015.
 * @author baljit 
 */
public class Controller {

    private LoginWindow loginWindow;
    private OperationWindow operationWindow;
    
    public Controller(){
        
    }
    /*
    /**
     * This method registers a Customer and adds a new CustomerInsurance Object
     * @param birthNo
     * @param firstName
     * @param lastName
     * @param telephoneNo
     * @param zipCode
     * @param streetAddress
     * @param billingZipCode
     * @param billingStreetAddress
     */
    
    public void start(){
        loginWindow = LoginWindow.getLoginWindow();
        operationWindow = OperationWindow.getOperationWindow();
        new GuiEventHandler(loginWindow, operationWindow, this);    
    }
    
    private void login(){
        
        loginWindow.close();
        operationWindow.displayWelcomeScene();
    }
    
    public void handle(Control control){
        if(control == loginWindow.getLoginButton()){
            this.login();
        }else if (control == operationWindow.getAdd()) {
            operationWindow.displayAddScene();
        } else if (control == operationWindow.getPerson()){
            operationWindow.displayInsurePersonScene(1);
        }else if (control == operationWindow.getInsurePersonScene().getRequestRegistration()){
            operationWindow.displayInsurePersonScene(2);
        } else if (control == operationWindow.getLogOut()){
            if (new MessageDialog().showMessageDialog("Logg ut", "Er du sikke" +
                    "r på at du vil logge ut?", MessageDialog.QUESTION_ICON,
                    MessageDialog.YES__NO_OPTION)==MessageDialog.YES_OPTION){
                operationWindow.close();
                loginWindow.show();
            }
        }else if (control == operationWindow.getCar()){
            operationWindow.displayInsureCarScene(1);
        }else if (control == operationWindow.getInsureCarScene().getRequestRegistration()) {
            operationWindow.displayInsureCarScene(2);
        }else if (control == operationWindow.getStats()){
            operationWindow.displayStatsScene();
        }else if(control == operationWindow.getStatisticsScene().getBackToRegistration()){
            operationWindow.displayWelcomeScene();
        }else if (control == operationWindow.getHouse()){
            operationWindow.displayInsureHouseScene(1);
        }else if (control == operationWindow.getInsureHouseScene().getRequestRegistration()){
            operationWindow.displayInsureHouseScene(2);
        }else if (control == operationWindow.getBoat()){
            operationWindow.displayInsureBoatScene(1);
        }else if (control == operationWindow.getInsureBoatScene().getRequestRegistration()){
            operationWindow.displayInsureBoatScene(2);
        }
    }
}
