package baminsurances.logging;

import baminsurances.api.Config;

import java.io.IOException;
import java.util.logging.*;


/**
 * Created by baljit on 22.04.2015.
 * @author baljit sarai
 */
public class CustomLogger extends Thread{
    private Logger logger = null;
    private static FileHandler txtFileHandler;
    private static ConsoleHandler consoleHandler;
    
    
    String className;
    
    public static void setUp() {
    try{
       
        
        CustomFormatter customFormatter = new CustomFormatter();
        txtFileHandler = new FileHandler("log.txt",true);
        txtFileHandler.setFormatter(customFormatter);
        consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new ConsoleFormatter());
        }
    catch (IOException ioe){
        System.out.print("Something went wrong opening the log files");
        }
    }
    
    
    public CustomLogger(String className) {
            
            this.className = className;
            this.logger = Logger.getLogger(this.className);
            this.logToConsole(false);
            this.setLoggerLevel();
            this.logger.addHandler(CustomLogger.txtFileHandler);
            logger.setUseParentHandlers(false);
            this.logger.addHandler(consoleHandler);
    }
    
   
    private void customLog(String msg,Level level) {
        this.logger.logp(level, this.className, Thread.currentThread().getStackTrace()[3].getMethodName(), msg);
    }
    
    
    public void log(String msg, Level level) {
        customLog(msg, level);
    }
    
    
    public void logToConsole(Boolean b) {
        logger.setUseParentHandlers(b);
    }
    
    private void setLoggerLevel(){
        this.logger.setLevel(Level.INFO);
    }
}
