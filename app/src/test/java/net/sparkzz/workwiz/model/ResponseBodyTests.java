package net.sparkzz.workwiz.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ResponseBodyTests {

    @Test
    public void test_ResponseBody() {
        int status = 200;
        String message = "OK";

        ResponseBody responseBody = new ResponseBody(status, message);

        assertEquals(status, responseBody.getStatus());
        assertEquals(message, responseBody.getMessage());
        assertNotNull(responseBody.getTimestamp());
    }
}