package unibz.pp201920.b2.datamanipulation.extraction;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unibz.pp201920.b2.constants.JsonNodeExample;
import unibz.pp201920.b2.exceptions.HttpRequestException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link JsonFetcher}.
 * It uses okhttp's mock web server to test http requests locally.
 *
 * @see <a href="https://github.com/square/okhttp/blob/master/mockwebserver/README.md">The description on github</a>
 * for further details.
 *
 * @author Gioele De Vitti
 */
public class JsonFetcherTest {

    private static final String requestPath = "/some/endpoint";

    private MockWebServer server;
    private HttpUrl url;

    @BeforeEach
    void newServer() throws IOException {
        server = new MockWebServer();
        server.start();
        // Ask the server for its url
        url = server.url(requestPath);
    }

    @AfterEach
    void stopServer() throws IOException {
        server.shutdown();
    }

    @Test
    void testNormal() throws InterruptedException, HttpRequestException, JsonProcessingException {
        server.enqueue(new MockResponse().setBody(JsonNodeExample.GENERAL.getRaw()));

        // Make http request
        JsonFetcher client = new JsonFetcher(url);
        JsonNode root = client.fetch();

        // Verify on server-side if a http request was actually made
        RecordedRequest request = server.takeRequest();
        assertEquals(requestPath, request.getPath(), "Server should have received the request.");

        // Verify response
        assertEquals(root, JsonNodeExample.GENERAL.getNode(), "Response should be equal to original.");
    }

    @Test
    void testMalformedCutoff() throws InterruptedException {
        server.enqueue(new MockResponse().setBody(JsonNodeExample.GENERAL_MALFORMED_CUTOFF.getRaw()));

        // Make http request
        JsonFetcher client = new JsonFetcher(url);
        assertThrows(JsonParseException.class, client::fetch);

        // Verify on server-side if a http request was actually made
        RecordedRequest request = server.takeRequest();
        assertEquals(requestPath, request.getPath(), "Server should have received the request.");
    }

}
