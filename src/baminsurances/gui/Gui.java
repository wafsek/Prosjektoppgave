package baminsurances.gui;
import baminsurances.api.Config;
import baminsurances.controller.Controller;
import baminsurances.logging.CustomLogger;
import baminsurances.gui.eventhandler.GuiEventHandler;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.logging.Level;

/**
 * Created by Adrian PC on 10/04/2015.
 * @author baljit sarai
 * @author Adrian
 */
public class Gui extends Application {
    private static CustomLogger logger; 
    public static void main(String[] args){
        //Config.setProperties();
        CustomLogger.setUp();
        System.out.println("Welkommen til " + Config.getApplicationName());
        addHook();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        logger.log("Starting the Program", Level.INFO);
        Controller controller = new Controller();
        controller.start();
    }

    /**
     * Adds a shutdown hook to the program that lets you run any code 
     * that is in the run() method before the program shuts down.
     * Should have as little code as possible.
     * That is why it contains just one logging line.
     * After a lot of testing, i have observed that the result is 
     * a bit unpredictable.
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
