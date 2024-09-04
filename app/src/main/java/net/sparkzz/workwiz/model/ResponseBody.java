package net.sparkzz.workwiz.model;

import java.time.LocalDateTime;

/**
 * Response body for http request responses.
 *
 * @author Brendon Butler
 * @since 0.0.1-SNAPSHOT
 */
public class ResponseBody {

    private final int status;
    private final String message;
    private final LocalDateTime timestamp;

    /**
     * Constructor for creating a new response body for http request responses.
     *
     * @param status  the status code of the response
     * @param message the message of the response
     */
    public ResponseBody(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Get the status code of the response.
     *
     * @return the status code of the response
     */
    public int getStatus() {
        return status;
    }

    /**
     * Get the message of the response.
     *
     * @return the message of the response
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get the timestamp of the response.
     *
     * @return the timestamp of the response
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
