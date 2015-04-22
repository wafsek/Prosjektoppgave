package baminsurances.gui;
import baminsurances.api.Config;
import baminsurances.api.CustomLogger;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.window.MessageDialog;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.lang.model.element.Name;
import javax.swing.*;
import java.util.logging.Level;

/**
 * Created by Adrian PC on 10/04/2015.
 * @author baljit 
 * @author Adrian
 */
public class Gui extends Application {
    private static CustomLogger logger; 
    public static void main(String[] args){
        Config.setProperties();
        CustomLogger.setUp();
        System.out.println("Welkommen til " + Config.getApplicationName());
        addHook();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        logger.log("Staring the Program", Level.INFO);
        new GuiEventHandler();
    }
    
    
    /**
     * Adds a shutdown hook to the program that lets you run any code 
     * that is in the run() method before the program shuts down.
     * Should have as little code as possible.
     * That is why it contains just one logging line.
     */
    public static void addHook(){
        logger = new CustomLogger(Gui.class.getName());
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Gui.logger.log("Program Shutdown", Level.INFO);
            }
        });
    }
}
