package unibz.pp201920.b2.datamanipulation.transformation;

import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import com.networknt.schema.SpecVersion.VersionFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import unibz.pp201920.b2.utils.ParsingUtils;

/**
 * Class used to test the validity of a {@link JsonNode} in compliance with
 * a {@code JsonSchema}.
 *
 * @author Samuel Dalvai, Gioele De Vitti
 */
public class Validator {

    private static final Logger logger = LogManager.getLogger();

    private final JsonNode jsonSchema;
    private String errorMessages;

    /**
     * Creates a new instance of {@link Validator} with the given json schema.
     *
     * @param jsonSchema The {@link JsonNode} representing the {@link JsonSchema}.
     */
    public Validator(JsonNode jsonSchema) {
        this.jsonSchema = jsonSchema;
    }

    /**
     * Tests if a {@link JsonNode} is in compliance with a {@link JsonSchema}.
     * If the validation check reports some errors, it also prints the errors
     * with the properties that do not correspond to the desired type.
     *
     * @param jsonTest The {@link JsonNode} to be tested
     * @return True iff {@code jsonTest} is in compliance with the json schema.
     */
    public boolean isJsonValid(JsonNode jsonTest) {
        // Compile the schema
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(VersionFlag.V7);
        JsonSchema schema = factory.getSchema(jsonSchema);

        // Validates the tested Node against the schema, and add ValidationMessage
        // objects to the Set "errors", if any
        // set of ValidationMessage objects used to store error messages for validation
        logger.trace("Validating json against json schema");
        Set<ValidationMessage> errors = schema.validate(jsonTest);

        // If at least one error occurred during the validation
        if (errors.size() > 0) {
            errorMessages = ParsingUtils.setToReadable(errors);
            return false;
        }
        return true;
    }

    /**
     * Get error messages generated during the last call of {@link #isJsonValid(JsonNode)}.
     * 
     * @return the error messages as a String
     */
    public String getErrorMessages() {
        return errorMessages;
    }

}
