package unibz.pp201920.b2.constants;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * {@code ResourceFile} enumerates resource files, verifying their existence and providing instances
 * of {@link InputStreamReader} to access them.
 * ResourceFile files are files inside the 'resources' directory, which is the root directory of a packaged JAR file.
 *
 * @author Gioele De Vitti
 */
public enum ResourceFile {
    USER_CONFIG("/input.txt"),
    JSONSCHEMA_ACTIVITY("/JsonSchemaActivity.json"),
    JSONSCHEMA_ANALYSIS("/JsonSchemaAnalysis.json");

    private final String filepath;

    /**
     * Creates a new instance of {@link ResourceFile}.
     *
     * @param filepath The relative file path to the resource file.
     *                 This path must begin with a / and its root directory is the 'resources' directory.
     */
    ResourceFile(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Returns the path of this resource file.
     *
     * @return The path of this resource file.
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * Opens this resource file with a {@link InputStreamReader}.
     *
     * @return The resource as a new {@link InputStreamReader}.
     */
    public InputStreamReader open() {
        InputStream stream = getClass().getResourceAsStream(filepath);
        return new InputStreamReader(stream);
    }

}
