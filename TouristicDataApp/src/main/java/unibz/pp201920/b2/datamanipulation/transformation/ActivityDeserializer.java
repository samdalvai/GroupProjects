package unibz.pp201920.b2.datamanipulation.transformation;

import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import unibz.pp201920.b2.exceptions.InvalidJsonException;
import unibz.pp201920.b2.utils.ParsingUtils;

/**
 * Class used for deserializing a {@link JsonNode} into one or multiple Activity objects.
 * 
 * Extracts the required properties from a {@link JsonNode} representing a list of activities
 * and creates a {@link Set} of {@link Activity} objects.
 *
 * @author Samuel Dalvai
 */
public class ActivityDeserializer {

    private static final Logger logger = LogManager.getLogger();

    private final JsonNode rootNode;

    /**
     * Creates a new instance of {@link ActivityDeserializer}.
     *
     * @param rootNode The JsonNode to be deserialized. {@link JsonNode} objects listed under the field
     *                 {@code Items} must conform to the {@link Activity} json schema.
     */
    public ActivityDeserializer(JsonNode rootNode) {
        this.rootNode = rootNode;
    }

    /**
     * Deserializes instances of {@link Activity} from the root json node. For each node in the node {@code Items}, it
     * extracts the data and creates an {@link Activity} object.
     *
     * @return The deserialized activities as {@link Set}.
     * @throws InvalidJsonException If the root json node does not contain the field {@code Items}.
     */
    public Set<Activity> deserialize() throws InvalidJsonException {
        Set<Activity> activities = new HashSet<>();
        JsonNode itemsNode = rootNode.findValue("Items");

        if (itemsNode == null)
            throw new InvalidJsonException("Malformed root json node. No activities found");

        for (int i = 0; i < itemsNode.size(); i++) {
            logger.debug("Deserializing item #" + i);
            JsonNode activityNode = itemsNode.get(i);
            Activity activity = deserializeActivity(activityNode);
            if (!activities.add(activity))
                logger.warn("Found activities with the same ID '" + activity.getId() + "'. The ID of each activity " +
                        "should be unique! Rejecting the most recent one");
        }

        return activities;
    }

    /**
     * Extracts the required properties from a {@link JsonNode} representing one
     * activity and deserializes it into an {@link Activity} object.
     *
     * @param activity A {@link JsonNode} representing a single activity to be deserialized.
     * @return A new instance of {@link Activity} with the required fields set.
     */
    private Activity deserializeActivity(JsonNode activity) {
        JsonNode detail = getLanguageNode(activity, "Detail");
        return new Activity(
                getId(activity),
                getName(detail),
                getDescription(detail),
                getTypes(activity),
                hasGPSTrack(activity),
                getRegionName(activity),
                getRegionId(activity));
    }

    private JsonNode getLanguageNode(JsonNode node, String field) {
        logger.trace("Extracting " + field);
        JsonNode fieldNode = node.path(field);

        JsonNode lang = fieldNode.get("en");
        if (lang == null) {
            logger.trace(field + " not found in English, switching to Italian");
            lang = fieldNode.get("it");
        }
        if (lang == null) {
            logger.trace(field + " not found in Italian, switching to German");
            lang = fieldNode.path("de");
        }

        return lang;
    }

    /**
     * Extracts the Id of the activity.
     *
     * @param node A {@link JsonNode} representing a single activity.
     * @return The Id of the activity.
     */
    private String getId(JsonNode node) {
        logger.trace("Extracting ID");
        return node.path("Id").textValue();
    }

    /**
     * Extracts the name of the activity, following the preferred language order:
     * English, Italian, German.
     * The field is found following the path: {@code Detail.**.BaseText},
     * where {@code **} stands for any possible language (en, it, de).
     *
     * @param detailNode The node containing details of the activity.
     * @return The name of the activity.
     */
    private String getName(JsonNode detailNode) {
        logger.trace("Extracting name");
        return detailNode.path("Title").textValue();
    }

    /**
     * Extracts the description of the activity, following the preferred language order:
     * English, Italian, German.
     *
     * @param detailNode The node containing details of the activity.
     * @return The description of the activity.
     */
    private String getDescription(JsonNode detailNode) {
        logger.trace("Extracting description");
        String desc = detailNode.path("BaseText").textValue();
        return ParsingUtils.htmlToReadable(desc);
    }

    /**
     * Extracts types of the activity, which are found under {@code ODHTags}.
     *
     * @param node A {@link JsonNode} representing a single activity.
     * @return The list of activity types.
     */
    private ArrayList<String> getTypes(JsonNode node) {
        logger.trace("Extracting activity types");
        ArrayList<String> types = new ArrayList<>();
        JsonNode odhTags = node.path("ODHTags");

        for (JsonNode n : odhTags)
            types.add(n.path("Id").textValue());

        return types;
    }

    /**
     * Verifies the presence of any sort of GPS tracking.
     *
     * @param node A {@link JsonNode} representing a single activity.
     * @return True if the activity has any sort of GPS data, false otherwise.
     */
    private boolean hasGPSTrack(JsonNode node) {
        logger.trace("Searching GPS information");

        if (node.path("GpsInfo").size() > 0 || node.path("GpsPoints").size() > 0 || node.path("GpsTrack").size() > 0)
            return true;

        return false;
    }

    /**
     * Extracts the name of the region of the activity, following the preferred language order
     * English, Italian, German.
     *
     * @param node A {@link JsonNode} representing a single activity.
     * @return The Id of the activity region.
     */
    private String getRegionName(JsonNode node) {
        logger.trace("Extracting region name");

        JsonNode regionInfo = node.path("LocationInfo").path("RegionInfo");
        JsonNode name = getLanguageNode(regionInfo, "Name");

        return name.textValue();
    }

    /**
     * Extracts the regionId of the activity.
     *
     * @param node A {@link JsonNode} representing a single activity.
     * @return The regionId of the activity.
     */
    private String getRegionId(JsonNode node) {
        logger.trace("Extracting region ID");
        return node.path("LocationInfo").path("RegionInfo").path("Id").textValue();
    }

}
