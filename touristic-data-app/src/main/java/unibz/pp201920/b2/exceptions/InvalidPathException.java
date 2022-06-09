package unibz.pp201920.b2.exceptions;

import java.io.IOException;

/**
 * Signals that a file could not be accessed.
 *
 * @author Samuel Dalvai, Gioele De Vitti
 */
public class InvalidPathException extends IOException {

    public InvalidPathException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPathException(String message) {
        super(message);
    }

    public InvalidPathException(Throwable cause) {
        super(cause);
    }

}
