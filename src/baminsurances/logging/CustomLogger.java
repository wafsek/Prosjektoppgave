package baminsurances.logging;

import baminsurances.api.Config;

import java.io.IOException;
import java.util.logging.*;


/**
 * A custom logger made for this software.
 * 
 * @author Baljit Sarai
 */
public class CustomLogger extends Thread {
    private Logger logger = null;
    private FileHandler txtFileHandler;
    private ConsoleHandler consoleHandler;
    String className = CustomLogger.class.getName();
    
    private static CustomLogger customLogger;
    
    private CustomLogger() {
        try {
            CustomFormatter customFormatter = new CustomFormatter();
            txtFileHandler = new FileHandler("log.txt",true);
            txtFileHandler.setFormatter(customFormatter);
            consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new ConsoleFormatter());
        } catch (IOException ioe) {
            System.out.print("Something went wrong opening the log files");
        }
            this.logger = Logger.getLogger(this.className);
            this.setConsoleOutput();
        this.setLoggerLevel(Level.FINEST);
        this.logger.addHandler(txtFileHandler);
    }
    
    public static CustomLogger getInstance() {
        if(customLogger==null) {
            customLogger= new CustomLogger();
        }
        return customLogger;
    }
    
    private void customLog(String msg, Level level) {
        this.logger.logp(level,
                Thread.currentThread().getStackTrace()[3].getClassName(),
                Thread.currentThread().getStackTrace()[3].getMethodName(), msg);
    }
    
    public void log(String msg, Level level) {
        customLog(msg, level);
    }
    
    public void setConsoleOutput() {
        if (Config.getConsoleOutputOption().toLowerCase().equals("trimmed")) {
            this.logger.addHandler(consoleHandler);
            logger.setUseParentHandlers(false);
        } else if (Config.getConsoleOutputOption().toLowerCase().equals("detail")) {
            logger.setUseParentHandlers(true);
        } else if (Config.getConsoleOutputOption().toLowerCase().equals("none")) {
            logger.removeHandler(consoleHandler);
            logger.setUseParentHandlers(false);
        }
    }
    
    private void setLoggerLevel(Level loggerLevel) {
        this.logger.setLevel(Level.FINEST);
    }
}
