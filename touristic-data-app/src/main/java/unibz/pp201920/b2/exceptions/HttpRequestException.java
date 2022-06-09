package unibz.pp201920.b2.exceptions;

import java.io.IOException;

/**
 * Signals that http request failed due to a connectivity problem, timeout or cancellation.
 *
 * @author Gioele De Vitti
 */
public class HttpRequestException extends IOException {

    public HttpRequestException(Throwable cause) {
        super(cause);
    }

}
