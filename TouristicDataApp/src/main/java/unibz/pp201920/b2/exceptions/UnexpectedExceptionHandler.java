package unibz.pp201920.b2.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import unibz.pp201920.b2.main.App;

/**
 * Exception handler of uncaught exceptions. Allows the application to shut down gracefully.
 * To take effect, this class must be set as the default UncaughtExceptionHandler of the main thread of the application.
 *
 * @see Thread#setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler)
 */
public class UnexpectedExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static final Logger logger = LogManager.getLogger();

    /**
     * This method is called when an uncaught exception was thrown.
     * It is the last chance for the application to report the occured error.
     *
     * @param t The thread that aborted because of an uncaught exception.
     * @param e The uncaught exception that occured.
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        logger.fatal("Thread '" + t.getName() + "' threw an uncaught exception.", e);
        App.shutdown(2);
    }

}
