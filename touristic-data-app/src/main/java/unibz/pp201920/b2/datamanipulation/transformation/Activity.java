package unibz.pp201920.b2.datamanipulation.transformation;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class that represents an activity object.
 * 
 * All the fields are obtained by the deserialization of an Item JsonNode object
 * through the use of an ActivityDeserializer Class.
 * 
 * The field "regionId" is initialized from the deserialization process, but is
 * ignored during the serialization, it is only used in the data analysis
 * process.
 *
 * @author Gioele De Vitti, Samuel Dalvai
 */
@JsonIgnoreProperties(value = "regionId")
public class Activity implements JsonSerializable {

	// main fields of the activity object
	private String id;
	private String name;
	private String description;
	private List<String> types;
	private boolean hasGPSTrack;
	private String region;
	private String regionId;

	// Json filename for the serialization
	private String jsonFilename;

	/**
	 * @param id          the Id of the activity
	 * @param name        the name of the activity
	 * @param description the description of the activity
	 * @param types       array representing the categories of the activity
	 * @param hasGPSTrack defines if the activity has Gps tracking or not
	 * @param region      the name of the region
	 * @param regionId    the Id of the region
	 */
	public Activity(String id, String name, String description, List<String> types, boolean hasGPSTrack, String region,
			String regionId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.types = types;
		this.hasGPSTrack = hasGPSTrack;
		this.region = region;
		this.regionId = regionId;
	}

    public Activity() { }

	/**
	 * Returns the file name used for writing a serialized object.
	 *
	 * @return The file name used for writing a serialized object.
	 */
	@Override
	public String getJsonFilename() {
		return (jsonFilename == null ? "Activity_" + id + ".json" : jsonFilename);
	}

	/**
	 * Sets the json file name used for serialization.
	 *
	 * @param filename The name of the file used for serialization.
	 */
	public void setJsonFilename(String filename) {
		jsonFilename = filename;
	}

	/**
	 * @return The id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id The id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return The description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description The description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return The types
	 */
	public List<String> getTypes() {
		return types;
	}

	/**
	 * @param types The types to set
	 */
	public void setTypes(List<String> types) {
		this.types = types;
	}

	/**
	 * @return The hasGPSTrack
	 */
	public boolean getHasGPSTrack() {
		return hasGPSTrack;
	}

	/**
	 * @param hasGPSTrack The hasGPSTrack to set
	 */
	public void setHasGPSTrack(boolean hasGPSTrack) {
		this.hasGPSTrack = hasGPSTrack;
	}

	/**
	 * @return The region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region The region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return The regionId
	 */
	public String getRegionId() {
		return regionId;
	}

	/**
	 * @param regionId The regionId to set
	 */
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	@Override
	public String toString() {
		return "Activity \n[id = " + id + ", \nname = " + name + ", \ndescription = " + description + ", \ntypes = "
				+ types + ", \nhasGPSTrack = " + hasGPSTrack + ", \nregion = " + region + ", \nregionId = " + regionId
				+ ", \njsonFilename = " + jsonFilename + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Activity activity = (Activity) o;
		return Objects.equals(id, activity.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
