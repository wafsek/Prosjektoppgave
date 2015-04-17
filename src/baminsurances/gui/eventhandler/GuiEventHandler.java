package baminsurances.gui.eventhandler;

import baminsurances.gui.window.LoginWindow;
import baminsurances.gui.window.MessageDialog;
import baminsurances.gui.window.RegistrationWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by Adrian on 14/04/2015.
 */
public class GuiEventHandler implements EventHandler<ActionEvent> {

    private LoginWindow loginWindow;
    private RegistrationWindow registrationWindow;

    public GuiEventHandler() {
        loginWindow = LoginWindow.getLoginWindow();
        registrationWindow = RegistrationWindow.getRegistrationWindow();
        setGuiEventHandler();
    }

    public void setGuiEventHandler()
    {
        loginWindow.setGuiEventHandler(this);
        registrationWindow.setGuiEventHandler(this);
    }


    @Override
    public void handle(ActionEvent e) {
        if(e.getSource() == loginWindow.getLoginButton()){
            loginWindow.close();
            registrationWindow.displayWelcomeScene();
        }else if (e.getSource() == registrationWindow.getAdd()){
            registrationWindow.displayAddScene();
        }else if (e.getSource() == registrationWindow.getPerson()){
            registrationWindow.displayInsurePersonScene(1);
        }else if (registrationWindow.getInsurePersonScene() != null &&
                e.getSource() == registrationWindow.getInsurePersonScene().getRequestRegistration()){
            registrationWindow.displayInsurePersonScene(2);
        }else if (e.getSource() == registrationWindow.getCar()){
            registrationWindow.displayInsureCarScene();
        }else if (e.getSource() == registrationWindow.getLogOut()){
            if (new MessageDialog().showMessageDialog("Logg ut", "Er du sikke" +
                    "r på at du vil logge ut?", MessageDialog.QUESTION_ICON,
                    MessageDialog.YES__NO_OPTION)==MessageDialog.YES_OPTION){
                registrationWindow.close();
                loginWindow.show();
            }
        }
    }
}
