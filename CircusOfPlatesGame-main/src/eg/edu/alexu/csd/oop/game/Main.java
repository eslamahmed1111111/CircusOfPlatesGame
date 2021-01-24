package eg.edu.alexu.csd.oop.game;
import java.io.*;
import java.util.logging.*;
public class Main {
    public Level level;
    public static Logger logger;
    public static void main(String[] args){
        logger = Logger.getLogger("MyLog");
        FileHandler fh;

        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler("logger.txt");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.setUseParentHandlers(false);
            // the following statement is used to log any messages
            //logger.info("My first log");

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        View view=View.singleton();
        view.beginning();


    }
}
