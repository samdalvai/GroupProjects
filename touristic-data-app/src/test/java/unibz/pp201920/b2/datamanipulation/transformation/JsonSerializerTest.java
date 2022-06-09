package unibz.pp201920.b2.datamanipulation.transformation;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import unibz.pp201920.b2.constants.JsonNodeExample;
import unibz.pp201920.b2.exceptions.InvalidPathException;

/**
 * Test class for {@link JsonSerializer}.
 *
 * @author Samuel Dalvai
 */
class JsonSerializerTest {

    private static final Set<JsonSerializable> set = new HashSet<>();

    // anonymous object for testing purposes
    private static final JsonSerializable simpleTestObject = new JsonSerializable() {

        @JsonProperty
        private final String testName = "test name";

        @Override
        public String getJsonFilename() {
            return "testfile.json";
        }

    };

    @BeforeAll
    static void beforeAll() {
        for (int i = 0; i < 5; i++)
            set.add(simpleTestObject);
    }

    /**
     * Test method for {@link JsonSerializer#serialize(JsonSerializable, String, boolean)}.
     */
    @Test
    @DisplayName("Test serialization of a single object")
    void testSerializeSingleObject() throws IOException {
        JsonSerializer serializer = new JsonSerializer(JsonNodeExample.SCHEMA_SIMPLE.getNode());

        assertDoesNotThrow(() -> serializer.serialize(simpleTestObject, "temp/", false),
                "Should not throw an Exception");

        // delete files after completion of the test
        FileUtils.deleteDirectory(new File("temp/"));
    }

    /**
     * Test method for {@link JsonSerializer#serialize(Set, String, boolean)}.
     */
    @Test
    @DisplayName("Test serialization of a list of objects")
    void testSerializeObjectList() throws IOException {
        JsonSerializer serializer = new JsonSerializer(JsonNodeExample.SCHEMA_SIMPLE.getNode());

        assertDoesNotThrow(() -> serializer.serialize(set, "temp/", false),
                "Should not throw an Exception");

        // delete files after completion of the test
        FileUtils.deleteDirectory(new File("temp/"));
    }

    @Test
    @DisplayName("Test initalization of JsonSerializer with wrong Json Schema path")
    void testWrongJsonSchemaPath() {
        assertThrows(InvalidPathException.class,
                () -> new JsonSerializer("src/test/resources/thisPathIsWrong.json"));
    }

}
