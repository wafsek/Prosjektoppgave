package baminsurances.gui;
import baminsurances.api.Commandline;
import baminsurances.controller.Controller;
import baminsurances.data.generation.DataBankGenerator;
import baminsurances.logging.CustomLogger;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.logging.Level;

/**
 * @author Baljit Sarai
 * @author Adrian Melsom
 */
public class Gui extends Application {
    private static CustomLogger logger = CustomLogger.getInstance(); 
    
    public static void main(String[] args){
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        addHook();
        //Config.setProperties();
        logger.log("Starting the Program", Level.INFO);
        Commandline commandline = new Commandline();
        commandline.start();
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
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                logger.log("Program Shutdown", Level.INFO);
            }
        });
    }
}
