package unibz.pp201920.b2.exceptions;

import java.io.IOException;

/**
 * Signals that a json file could not be loaded, because of incorrect syntax.
 *
 * @author Gioele De Vitti, Samuel Dalvai
 */
public class InvalidJsonException extends IOException {

    public InvalidJsonException(String message) {
        super(message);
    }

    public InvalidJsonException(Throwable cause) {
        super(cause);
    }

}
