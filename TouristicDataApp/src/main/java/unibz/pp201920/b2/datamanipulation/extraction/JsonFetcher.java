package unibz.pp201920.b2.datamanipulation.extraction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import kotlin.text.Charsets;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import unibz.pp201920.b2.exceptions.HttpRequestException;

import java.io.IOException;

/**
 * Class {@code JsonFetcher} is used to make http requests to a specified url.
 * The url is given to the contructor and cannot be changed afterwards.
 * It offer methods to directly retrieve json responses as a {@code JsonNode}.
 *
 * @author Gioele De Vitti
 */
public class JsonFetcher {

    private static final Logger logger = LogManager.getLogger();

    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final HttpUrl url;

    /**
     * Creates a new {@code JsonFetcher} for the given url.
     * The created instance will be used to make requests to this url only.
     *
     * @param url The url to which http requests will be made.
     */
    public JsonFetcher(HttpUrl url) {
        this.url = url;
    }

    /**
     * Makes a http request to this web API and returns the root {@code JsonNode} of the response.
     *
     * @return The root {@code JsonNode} of the response.
     * @throws HttpRequestException           If the http request failed and no json string could be received.
     * @throws JsonProcessingException If the received json string is of invalid syntax.
     */
    public JsonNode fetch() throws HttpRequestException, JsonProcessingException {
        String json = requestJson();
        return mapper.readTree(json);
    }

    /**
     * Makes a http request to this web API and returns the json string of the response.
     *
     * @return The json string of the response.
     * @throws HttpRequestException If the http request failed and no json string could be received.
     */
    private String requestJson() throws HttpRequestException {
        Call call = httpClient.newCall(new Request.Builder().url(url).build());
        String json = null;

        logger.debug("Invoking request");
        try (ResponseBody body = call.execute().body()) {
            if (body != null) {
                logger.debug("Receiving bytes and decoding them as string");
                // This is resource intensive!
                json = body.source().readString(Charsets.UTF_8);
            }
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }

        return json;
    }

}
