package unibz.pp201920.b2.datamanipulation.transformation;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classes that implement {@code JsonSerializable} are compatible with
 * the serialization process of unibz.pp201920.b2.datamanipulation.datatransformation.JsonSerializer.
 *
 * @author Gioele De Vitti
 */
public interface JsonSerializable {

    /**
     * Returns the file name used for writing a serialized object.
     *
     * @return The file name used for writing a serialized object.
     */
    @JsonIgnore
    String getJsonFilename();

}
