package unibz.pp201920.b2.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import unibz.pp201920.b2.exceptions.UnexpectedExceptionHandler;
import unibz.pp201920.b2.tasks.TouristicActivitiesTask;

/**
 * Main class of the application.
 * Is responsible for booting and shutting down the application.
 *
 * @author Gioele De Vitti
 */
public final class App {

    private static final Logger logger = LogManager.getLogger();

    /**
     * The entry point of the application.
     *
     * @param args The arguments passed when the application has been started.
     */
    public static void main(String[] args) {
        boot();

        TouristicActivitiesTask task = new TouristicActivitiesTask();
        task.execute();

        shutdown(0);
    }

    /**
     * Sets important parameters of the application.
     */
    private static void boot() {
        logger.info("Booting");
        Thread.setDefaultUncaughtExceptionHandler(new UnexpectedExceptionHandler());
    }

    /**
     * Shuts down the application with the given exit status code.
     *
     * @param status The status code of the application.
     *               A nonzero status code indicates abnormal termination.
     */
    public static void shutdown(int status) {
        logger.info("Shutting down");
        logger.info("----------------------------");
        System.exit(status);
    }

}
