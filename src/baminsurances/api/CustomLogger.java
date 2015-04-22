package baminsurances.api;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * Created by baljit on 22.04.2015.
 * @author baljit sarai
 */
public class CustomLogger{
    private Logger logger = null;
    private static FileHandler logFileHandler ;
    private static FileHandler txtFileHandler;
    
    
    String className; 
    
    public static void setUp(){
        
    try{
        logFileHandler = new FileHandler("log.log",true);
        txtFileHandler = new FileHandler("log.txt",true);
        txtFileHandler.setFormatter(new SimpleFormatter());
        }
    catch (IOException ioe){
        System.out.print("Something went wrong opening the log files");
        }
    }
    
    
    public CustomLogger(String className){
            this.className = className;
            this.logger = Logger.getLogger(this.className);
            this.logger.addHandler(CustomLogger.logFileHandler);
            this.logger.addHandler(txtFileHandler);
    }
    
   
    private void customLog(String msg, Level level){
        //logger.setUseParentHandlers(false);
        this.logger.logp(level, this.className,Thread.currentThread().getStackTrace()[3].getMethodName(),msg);
    }
    
    public void log(String msg, Level level){
        customLog(msg,level);
    }
}
