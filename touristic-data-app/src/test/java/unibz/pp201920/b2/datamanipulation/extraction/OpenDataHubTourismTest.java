package unibz.pp201920.b2.datamanipulation.extraction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link OpenDataHubTourism}.
 * It tests basic functions without performing any http request.
 *
 * @author Gioele De Vitti
 */
public class OpenDataHubTourismTest {

    @Test
    void testFactoryValid() {
        assertDoesNotThrow(() -> OpenDataHubTourism.create(OpenDataHubTourism.Endpoint.ACTIVITY, 5),
                "New OpenDataHubTourism instance with valid parameters shouldn't throw exceptions.");
    }

    @Test
    void testFactoryInvalid() {
        assertDoesNotThrow(() -> OpenDataHubTourism.create(null, -100),
                "New OpenDataHubTourism instance with invalid parameters shouldn't throw exceptions.");
    }

    @Test
    void testFactoryInvalidEndpoint() {
        assertDoesNotThrow(() -> OpenDataHubTourism.create(null, 5),
                "New OpenDataHubTourism instance with invalid endpoint shouldn't throw exceptions.");
    }

    @Test
    void testFactoryInvalidNumber() {
        assertDoesNotThrow(() -> OpenDataHubTourism.create(OpenDataHubTourism.Endpoint.ACTIVITY, -100),
                "New OpenDataHubTourism instance with invalid number of objects to retrieve shouldn't throw " +
                        "exceptions.");
    }

}
