package baminsurances.controller;

import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.LoginWindow;
import baminsurances.gui.window.OperationWindow;
import baminsurances.gui.window.MessageDialog;
import baminsurances.logging.CustomLogger;
import javafx.scene.control.Control;

import java.util.logging.Level;


/**
 * Created by baljit on 30.04.2015.
 * @author baljit
 * 
 */
public class Controller {
    
    private GuiEventHandler guiEventHandler;
    private KeyPressHandler keyPressHandler;
    private LoginWindow loginWindow;
    private OperationWindow operationWindow;
    private CustomLogger logger = new CustomLogger(GuiEventHandler.class.getName());
    
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
        loginWindow.show();
        operationWindow = OperationWindow.getOperationWindow();
        guiEventHandler = new GuiEventHandler(this);
        keyPressHandler = new KeyPressHandler(operationWindow,this);
        loginWindow.setGuiEventHandler(guiEventHandler);
        operationWindow.setGuiEventHandler(guiEventHandler);
        operationWindow.setKeyHandler(keyPressHandler);
    }
    
    private void login(){
        loginWindow.close();
        operationWindow.displayWelcomeScene();
        logger.log("Logged in", Level.INFO);
    }
    
    /**
     * This method takes a Control object
     * @param control
     */
    public void handle(Control control){
        if(control == loginWindow.getLoginButton()){
            this.login();
        }else if (control == operationWindow.getAddSceneButton()) {
            operationWindow.displayAddScene();
        } else if (control == operationWindow.getPersonSceneButton()){
            operationWindow.displayInsurePersonScene(1);
        }else if (control == operationWindow.getInsurePersonScene().getRequestRegistration()){
            operationWindow.displayInsurePersonScene(2);
        } else if (control == operationWindow.getLogOutButton()){
            if (new MessageDialog().showMessageDialog("Logg ut", "Er du sikke" +
                    "r på at du vil logge ut?", MessageDialog.QUESTION_ICON,
                    MessageDialog.YES__NO_OPTION)==MessageDialog.YES_OPTION){
                operationWindow.close();
                loginWindow.show();
            }
        }else if (control == operationWindow.getCarSceneButton()){
            operationWindow.displayInsureCarScene(1);
        }else if (control == operationWindow.getInsureCarScene().getRequestRegistration()) {
            operationWindow.displayInsureCarScene(2);
        }else if (control == operationWindow.getStatsSceneButton()){
            operationWindow.displayStatsScene();
        }else if(control == operationWindow.getStatisticsScene().getBackToRegistration()){
            operationWindow.displayWelcomeScene();
        }else if (control == operationWindow.getHouseSceneButton()){
            operationWindow.displayInsureHouseScene(1);
        }else if (control == operationWindow.getInsureHouseScene().getRequestRegistration()){
            operationWindow.displayInsureHouseScene(2);
        }else if (control == operationWindow.getBoatSceneButton()){
            operationWindow.displayInsureBoatScene(1);
        }else if (control == operationWindow.getInsureBoatScene().getRequestRegistration()){
            operationWindow.displayInsureBoatScene(2);
        }else if (control == operationWindow.getAddScene().getRegister()) {
            operationWindow.getAddScene().requestRegistration();
        }else if (control == operationWindow.getSearchSceneButton()){
            operationWindow.displaySearchScene();
        }
    }
}
