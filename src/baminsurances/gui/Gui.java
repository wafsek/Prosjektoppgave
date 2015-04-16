package baminsurances.gui;
import baminsurances.api.Config;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.window.MessageDialog;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * Created by Adrian PC on 10/04/2015.
 */
public class Gui extends Application {

    public static void main(String[] args){
        Config.setProperties();
        System.out.println("Welkommen til " +Config.getApplicationName());
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        new GuiEventHandler();
    }
}
