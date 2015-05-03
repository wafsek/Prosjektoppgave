package baminsurances.gui.eventhandler;

import baminsurances.controller.Controller;
import baminsurances.logging.CustomLogger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Control;

/**
 * Created by Adrian on 14/04/2015.
 */
public class GuiEventHandler implements EventHandler<ActionEvent> {
    private CustomLogger logger = new CustomLogger(GuiEventHandler.class.getName());
    private Controller controller;
    public GuiEventHandler(Controller controller) {
        this.controller = controller;
        setGuiEventHandler();
    }

    public void setGuiEventHandler()
    {
        
    }


    @Override
    public void handle(ActionEvent e) {
        System.out.println(e.getEventType().getSuperType().getSuperType());
        controller.handleControl((Control) (e.getSource()));
        
        /*if(e.getSource() == loginWindow.getLoginButton()){
            logger.log("Logged in", Level.SEVERE);
            loginWindow.close();
            operationWindow.displayWelcomeScene();
        }else if (e.getSource() == operationWindow.getAddSceneButton()){
            operationWindow.displayAddScene();
        }else if (e.getSource() == operationWindow.getPersonSceneButton()){
            operationWindow.displayInsurePersonScene(1);
        }else if (e.getSource() == operationWindow.getInsurePersonScene().getRequestRegistration()){
            operationWindow.displayInsurePersonScene(2);
        }else if (e.getSource() == operationWindow.getLogOutSceneButton()){
            if (new MessageDialog().showMessageDialog("Logg ut", "Er du sikke" +
                    "r på at du vil logge ut?", MessageDialog.QUESTION_ICON,
                    MessageDialog.YES__NO_OPTION)==MessageDialog.YES_OPTION){
                operationWindow.close();
                loginWindow.show();
            }
        }else if (e.getSource() == operationWindow.getCarSceneButton()){
            operationWindow.displayInsureCarScene(1);
        }else if (e.getSource() == operationWindow.getInsureCarScene().getRequestRegistration()) {
            operationWindow.displayInsureCarScene(2);
        }else if (e.getSource() == operationWindow.getStatsSceneButton()){
            operationWindow.displayStatsScene();
        }else if(e.getSource() == operationWindow.getStatisticsScene().getBackToRegistration()){
            operationWindow.displayWelcomeScene();
        }else if (e.getSource() == operationWindow.getHouseSceneButton()){
            operationWindow.displayInsureHouseScene(1);
        }else if (e.getSource() == operationWindow.getInsureHouseScene().getRequestRegistration()){
            operationWindow.displayInsureHouseScene(2);
        }else if (e.getSource() == operationWindow.getBoatSceneButton()){
            operationWindow.displayInsureBoatScene(1);
        }else if (e.getSource() == operationWindow.getInsureBoatScene().getRequestRegistration()){
            operationWindow.displayInsureBoatScene(2);
        }else if (e.getSource() == operationWindow.getAddScene().getRegister()) {
            operationWindow.getAddScene().requestRegistration();
        }else if (e.getSource() == operationWindow.getSearchSceneButton()){
            operationWindow.displaySearchScene();
        }*/
    }
}
