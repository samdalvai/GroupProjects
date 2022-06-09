package unibz.pp201920.b2.exceptions;

import java.io.IOException;

/**
 * Signals that a specific line of a text file could not be interpreted, because of invalid format.
 *
 * @author Gioele De Vitti
 */
public class InvalidLineException extends IOException {

    private final int lineNumber;

    public InvalidLineException(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public InvalidLineException(int lineNumber, Throwable cause) {
        this.lineNumber = lineNumber;
    }

    @Override
    public String getMessage() {
        return "Couldn't read file at line " + lineNumber + ".";
    }
}
