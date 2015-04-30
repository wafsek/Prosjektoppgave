package baminsurances.gui.eventhandler;

import baminsurances.logging.CustomLogger;
import baminsurances.gui.window.LoginWindow;
import baminsurances.gui.window.MessageDialog;
import baminsurances.gui.window.OperationWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Control;
import java.util.logging.Level;

/**
 * Created by Adrian on 14/04/2015.
 */
public class GuiEventHandler implements EventHandler<ActionEvent> {
    private CustomLogger logger = new CustomLogger(GuiEventHandler.class.getName());
    private LoginWindow loginWindow;
    private OperationWindow operationWindow;
    private baminsurances.controller.Controller controller;
    public GuiEventHandler(LoginWindow loginWindow,OperationWindow operationWindow,baminsurances.controller.Controller controller) {
        this.loginWindow = loginWindow;
        this.operationWindow = operationWindow;
        this.controller = controller;
        setGuiEventHandler();
    }

    public void setGuiEventHandler()
    {
        loginWindow.setGuiEventHandler(this);
        operationWindow.setGuiEventHandler(this);
    }


    @Override
    public void handle(ActionEvent e) {
            controller.handle((Control) e.getSource());
        
        
        /*if(e.getSource() == loginWindow.getLoginButton()){
            controller.login();
        }else if (e.getSource() == operationWindow.getAdd()){
            operationWindow.displayAddScene();
        }else if (e.getSource() == operationWindow.getPerson()){
            operationWindow.displayInsurePersonScene(1);
        }else if (e.getSource() == operationWindow.getInsurePersonScene().getRequestRegistration()){
            operationWindow.displayInsurePersonScene(2);
        }else if (e.getSource() == operationWindow.getLogOut()){
            if (new MessageDialog().showMessageDialog("Logg ut", "Er du sikke" +
                    "r på at du vil logge ut?", MessageDialog.QUESTION_ICON,
                    MessageDialog.YES__NO_OPTION)==MessageDialog.YES_OPTION){
                operationWindow.close();
                loginWindow.show();
            }
        }else if (e.getSource() == operationWindow.getCar()){
            operationWindow.displayInsureCarScene(1);
        }else if (e.getSource() == operationWindow.getInsureCarScene().getRequestRegistration()) {
            operationWindow.displayInsureCarScene(2);
        }else if (e.getSource() == operationWindow.getStats()){
            operationWindow.displayStatsScene();
        }else if(e.getSource() == operationWindow.getStatisticsScene().getBackToRegistration()){
            operationWindow.displayWelcomeScene();
        }else if (e.getSource() == operationWindow.getHouse()){
            operationWindow.displayInsureHouseScene(1);
        }else if (e.getSource() == operationWindow.getInsureHouseScene().getRequestRegistration()){
            operationWindow.displayInsureHouseScene(2);
        }else if (e.getSource() == operationWindow.getBoat()){
            operationWindow.displayInsureBoatScene(1);
        }else if (e.getSource() == operationWindow.getInsureBoatScene().getRequestRegistration()){
            operationWindow.displayInsureBoatScene(2);
        }*/
    }
}
