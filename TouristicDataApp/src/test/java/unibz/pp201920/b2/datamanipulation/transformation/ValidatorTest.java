package unibz.pp201920.b2.datamanipulation.transformation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import unibz.pp201920.b2.constants.JsonNodeExample;

/**
 * Test class for {@link Validator}.
 *
 * @author Samuel Dalvai
 */
@DisplayName("Test class for Validator class")
class ValidatorTest {

	/**
	 * Test method for
	 * {@link Validator#isJsonValid(com.fasterxml.jackson.databind.JsonNode)}.
	 */
	@Test
	@DisplayName("Test if JsonSchema validation works")
	void testIsJsonValid() {
		Validator validator = new Validator(JsonNodeExample.SCHEMA.getNode());
		JsonNode testNode = JsonNodeExample.ACTIVITY_VALID.getNode();

		assertTrue(validator.isJsonValid(testNode), "Should return true");
	}

	@Test
	@DisplayName("Test if JsonSchema validation spots wrong property")
	void testIsNotJsonValid() {
		Validator validator = new Validator(JsonNodeExample.SCHEMA.getNode());
		JsonNode testNode = JsonNodeExample.ACTIVITY_NONVALID.getNode();

		assertFalse(validator.isJsonValid(testNode), "Should return false");
	}

	@Test
	@DisplayName("Test validation with wrong Json Schema")
	void testWrongJsonSchema() {
		Validator validator = new Validator(JsonNodeExample.SCHEMA_WRONG.getNode());
		JsonNode testNode = JsonNodeExample.ACTIVITY_VALID.getNode();

		assertFalse(validator.isJsonValid(testNode), "Should return false");
	}

}
