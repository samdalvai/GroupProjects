package unibz.pp201920.b2.datamanipulation.transformation;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import unibz.pp201920.b2.constants.ResourceFile;
import unibz.pp201920.b2.exceptions.InvalidJsonException;
import unibz.pp201920.b2.exceptions.InvalidPathException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import unibz.pp201920.b2.utils.IOUtils;

/**
 * Class used for serializing objects that implement {@link JsonSerializable} into {@code .json} files.
 * Serializes an object or a List of objects into one or more json nodes, validates
 * the resulting {@link JsonNode} in compliance with a given json schema node and produces
 * one or more json files.
 * <p>
 * Methods of this class use generics in order to be compatible for every type of object
 * that implements {@link JsonSerializable}.
 *
 * @author Gioele De Vitti, Samuel Dalvai
 */
public class JsonSerializer {

    private static final Logger logger = LogManager.getLogger();

    private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
    private final Validator validator;

    /**
     * Creates a new instance of {@link JsonSerializer}, loading the json schema.
     *
     * @param jsonSchemaPath The path to the json file containing the schema for validation.
     * @throws InvalidPathException If the path to the json schema or to the output file is not valid.
     * @throws InvalidJsonException If the json schema does not conform to json syntax.
     */
    public JsonSerializer(String jsonSchemaPath) throws InvalidPathException, InvalidJsonException {
        JsonNode jsonSchema = loadJsonSchema(jsonSchemaPath);
        validator = new Validator(jsonSchema);
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
    }

    /**
     * Creates a new instance of {@link JsonSerializer}, loading the json schema.
     *
     * @param jsonSchemaRes {@link ResourceFile} of the resource file to be accessed.
     * @throws InvalidPathException If the path to the json schema or to the output file is not valid.
     * @throws InvalidJsonException If the json schema does not conform to json syntax.
     */
    public JsonSerializer(ResourceFile jsonSchemaRes) throws InvalidPathException, InvalidJsonException {
        JsonNode jsonSchema = loadJsonSchema(jsonSchemaRes);
        validator = new Validator(jsonSchema);
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
    }

    /**
     * Creates a new instance of {@code JsonSerializer}.
     *
     * @param jsonSchema The json schema as {@code JsonNode}.
     */
    public JsonSerializer(JsonNode jsonSchema) {
        validator = new Validator(jsonSchema);
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
    }

    /**
     * Loads the json schema file of this instance as {@link JsonNode}.
     *
     * @param filepath The file path to the json schema.
     * @return The json schema of this instance as {@link JsonNode}.
     * @throws InvalidPathException If the json schema file could not be loaded as {@link JsonNode}.
     * @throws InvalidJsonException If the json schema does not conform to json syntax.
     */
    private JsonNode loadJsonSchema(String filepath) throws InvalidPathException, InvalidJsonException {
        try (Reader reader = new FileReader(new File(filepath))) {
            return readJson(reader);
        } catch (InvalidJsonException e) {
            throw e;
        } catch (IOException e) {
            throw new InvalidPathException("Json Schema '" + filepath + "'", e);
        }
    }

    /**
     * Loads the json schema file of this instance as {@link JsonNode}.
     *
     * @param resourceFile The resource file represented as {@link ResourceFile}.
     * @return The json schema of this instance as {@link JsonNode}.
     * @throws InvalidPathException If the json schema file could not be loaded as {@link JsonNode}.
     * @throws InvalidJsonException If the json schema does not conform to json syntax.
     */
    private JsonNode loadJsonSchema(ResourceFile resourceFile) throws InvalidPathException, InvalidJsonException {
        try (Reader reader = resourceFile.open()) {
            return readJson(reader);
        } catch (InvalidJsonException e) {
            throw e;
        } catch (IOException e) {
            throw new InvalidPathException("Json Schema '" + resourceFile.getFilepath() + "'", e);
        }
    }

    /**
     * Reads a json object from the given reader.
     *
     * @param reader The {@link Reader} used to read the json file.
     * @return The read json object as {@link JsonNode}.
     * @throws InvalidJsonException If the json schema does not conform to json syntax.
     */
    private JsonNode readJson(Reader reader) throws InvalidJsonException {
        try (BufferedReader buffReader = new BufferedReader(reader)) {
            return mapper.readTree(buffReader);
        } catch (IOException e) {
            throw new InvalidJsonException(e);
        }
    }

    /**
     * Serializes a {@link JsonSerializable} object and writes it to a json file. Before creation, every json file
     * is tested in compliance with the json schema, if one or more fields are not
     * valid, the output json file is not created.
     *
     * @param obj         The JsonSerializable object
     * @param directory   The directory path where the json file is created
     * @param arrayIndent The flag for the indentation of the arrays in the json files
     * @throws InvalidPathException If the path to the json schema or to the output directory is not valid.
     * @throws InvalidJsonException If the created {@link JsonNode} does not conform to the json schema.
     */
    public void serialize(JsonSerializable obj, String directory, boolean arrayIndent)
            throws InvalidPathException, InvalidJsonException {
        logger.debug("Serializing {} to {}", () -> obj.getClass().getSimpleName(), obj::getJsonFilename);

        File parentDir = new File(directory);
        String jsonFilepath = Paths.get(directory, obj.getJsonFilename()).toString();
        JsonNode jsonToSerialize;

        logger.trace("Creating target directory");
        IOUtils.createDirectory(parentDir);

        // Convert JsonSerializable to JsonNode
        logger.trace("Converting {} to JsonNode", () -> obj.getClass().getSimpleName());
        jsonToSerialize = mapper.convertValue(obj, JsonNode.class);

        // Write json file if the json object is valid according to the json schema
        if (validator.isJsonValid(jsonToSerialize))
            writeJsonFile(jsonToSerialize, jsonFilepath, arrayIndent);
        else
            throw new InvalidJsonException(validator.getErrorMessages());
    }

    /**
     * Same as {@link #serialize(JsonSerializable, String, boolean)}, but for a set of {@link JsonSerializable} objects.
     * If the serialization fails for a single object, the operation is interrupted and {@link InvalidPathException}
     * is thrown.
     *
     * @param <T>	The generic object extending {@link JsonSerializable}
     * @param objList     The set containing {@link JsonSerializable} objects.
     * @param directory   The directory path where the json file is created
     * @param arrayIndent The flag for the indentation of the arrays in the json files
     * @throws InvalidPathException If the json files could not be written.
     */
    public <T extends JsonSerializable> void serialize(Set<T> objList, String directory, boolean arrayIndent)
            throws InvalidPathException {
        int serialized = 0;

        // Serialize each and continue if some cannot be serialized
        for (JsonSerializable obj : objList) {
            try {
                serialize(obj, directory, arrayIndent);
                serialized++;
            } catch (InvalidJsonException e) {
                logger.warn("Could not serialize, because json does not conform to schema: " + e.getMessage());
            }
        }

        logger.debug("Number of serialized activities: {}", serialized);
    }

    /**
     * Writes a {@link JsonNode} to a file.
     *
     * @param jsonNode     The {@link JsonNode} to write.
     * @param jsonFilepath The file path used for writing the {@link JsonNode}.
     * @param arrayIndent  The flag for the indentation of the arrays in the json files
     * @throws InvalidPathException If the json file could not be written to the file.
     */
    private void writeJsonFile(JsonNode jsonNode, String jsonFilepath, boolean arrayIndent)
            throws InvalidPathException {
        // Create json file
        logger.trace("Opening json file");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(jsonFilepath)))) {
            logger.trace("Writing json file");
            if (arrayIndent)
                mapper.writer(prettyPrinter).writeValue(writer, jsonNode);
            else
                mapper.writeValue(writer, jsonNode);
        } catch (JsonGenerationException | JsonMappingException e) {
            logger.error("Could not create json file: " + jsonFilepath, e);
        } catch (IOException e) {
            throw new InvalidPathException(jsonFilepath, e);
        }
        logger.trace("Json file closed");
    }

}
