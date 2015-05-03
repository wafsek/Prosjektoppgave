package baminsurances.controller;

import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.LoginWindow;
import baminsurances.gui.window.OperationWindow;
import baminsurances.gui.window.MessageDialog;
import baminsurances.gui.window.scene.*;
import baminsurances.logging.CustomLogger;
import baminsurances.security.Authenticator;
import javafx.scene.control.Control;

import java.util.logging.Level;


/**
 * Created by baljit on 30.04.2015.
 * @author baljit
 * 
 */
public class Controller {


    /**
     * The fields
     * 
     */
    private Authenticator authenticator;
    
    
    /**
     * The Gui type fields
     */
    private WelcomeScene welcomeScene;
    private AddScene addScene;
    private InsurePersonScene insurePersonScene;
    private InsureCarScene insureCarScene;
    private StatisticsScene statisticsScene;
    private InsureHouseScene insureHouseScene;
    private InsureBoatScene insureBoatScene;
    private SearchScene searchScene;
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
        welcomeScene = new WelcomeScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler);
        addScene = new AddScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler);
        insurePersonScene = new InsurePersonScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler);
        insureCarScene = new InsureCarScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler);
        statisticsScene = new StatisticsScene(operationWindow.getFooter(), keyPressHandler, guiEventHandler);
        insureHouseScene = new InsureHouseScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler);
        insureBoatScene = new InsureBoatScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler);
        searchScene = new SearchScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler);
    }
    
    private void login(){
        
        loginWindow.close();
        welcomeScene = new WelcomeScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler);
        operationWindow.initialize(welcomeScene.getScene());
        logger.log("Logged in", Level.INFO);
    }
    
    public String getDisplayName(){
        return "Brukernavn: "+authenticator.getDisplayName();
    }
    
    /**
     * This method takes a Control object
     * @param control
     */
    public void handleControl(Control control){
        if(control == loginWindow.getLoginButton()){
                this.login();
        }else if (control == operationWindow.getAddSceneButton()) {
            addScene = new AddScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler);
        operationWindow.displayScene(addScene.getScene());
    } else if (control == operationWindow.getPersonSceneButton()){
            insurePersonScene = new InsurePersonScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler);
            operationWindow.displayScene(insurePersonScene.getScene());
            }else if (control == insurePersonScene.getRequestRegistration()) {
                operationWindow.displayScene(insurePersonScene.requestApproved());
        } else if (control == operationWindow.getLogOutButton()){
            if (new MessageDialog().showMessageDialog("Logg ut", "Er du sikke" +
                    "r p� at du vil logge ut?", MessageDialog.QUESTION_ICON,
                    MessageDialog.YES__NO_OPTION)==MessageDialog.YES_OPTION){
                operationWindow.close();
                loginWindow.show();
            }
        }else if (control == operationWindow.getCarSceneButton()){
            insureCarScene = new InsureCarScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler);
            operationWindow.displayScene(insureCarScene.getScene());
        }else if (control == insureCarScene.getRequestRegistration()) {
                operationWindow.displayScene(insureCarScene.requestApproved());
        }else if (control == operationWindow.getStatsSceneButton()){
            statisticsScene = new StatisticsScene(operationWindow.getFooter(), keyPressHandler, guiEventHandler);
            operationWindow.displayScene(statisticsScene.getScene());
        }else if(control == statisticsScene.getBackToRegistration()){
            welcomeScene = new WelcomeScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler);
            operationWindow.displayScene(welcomeScene.getScene());
        }else if (control == operationWindow.getHouseSceneButton()){
            insureHouseScene = new InsureHouseScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler);
            operationWindow.displayScene(insureHouseScene.getScene());
        }else if (control == insureHouseScene.getRequestRegistration()) {
                operationWindow.displayScene(insureHouseScene.requestApproved());
        }else if (control == operationWindow.getBoatSceneButton()){
            insureBoatScene = new InsureBoatScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler);
            operationWindow.displayScene(insureBoatScene.getScene());
        }else if (control == insureBoatScene.getRequestRegistration()) {
                operationWindow.displayScene(insureBoatScene.requestApproved());
        }else if (control == addScene.getRegister()) {
            addScene.requestRegistration();
        }else if (control == operationWindow.getSearchSceneButton()){
            searchScene = new SearchScene(operationWindow.getHeader(), operationWindow.getFooter(), guiEventHandler);
            operationWindow.displayScene(searchScene.getScene());
        }
        
    }


    /**
     * This method takes a Control object
     * @param 
     */
    public void handleKey(Control control) {
    
    }
    
}
