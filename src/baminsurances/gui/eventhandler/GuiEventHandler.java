package baminsurances.gui.eventhandler;

import baminsurances.gui.window.LoginWindow;
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
        setGuiEventHandler();
    }

    public void setGuiEventHandler()
    {
        loginWindow.setGuiEventHandler(this);
    }


    @Override
    public void handle(ActionEvent e) {
        if(e.getSource() == loginWindow.getLoginButton()){
            loginWindow.close();
            registrationWindow = RegistrationWindow.getRegistrationWindow();
        }
    }
}
