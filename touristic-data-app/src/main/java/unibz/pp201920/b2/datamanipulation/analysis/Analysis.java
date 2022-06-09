package unibz.pp201920.b2.datamanipulation.analysis;

import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;

import unibz.pp201920.b2.datamanipulation.transformation.JsonSerializable;

/**
 * Class containing the results of an Analysis.
 *
 * The Analysis itself is typically computed by {@link ActivitiesAnalyzer}.
 *
 * @author Gioele De Vitti, Filippo Cenacchi, Samuel Dalvai
 */
public class Analysis implements JsonSerializable {

    private final TreeMap<String, Long> activitiesTypes;
    private final TreeSet<String> trackedActivityIds;
    private final RegionsActivitiesRecord regionsWithMostActivities;
    private final RegionsActivitiesRecord regionsWithLeastActivities;

    // Json filename for the serialization
    private String jsonFilename = "analysis.json";

    /**
     * @param activitiesTypes            The activity types with their number of occurrence.
     * @param trackedActivityIds         The activity IDs of the GPS tracked activities.
     * @param regionsWithMostActivities  The region ID of the region/regions with the most activities.
     * @param regionsWithLeastActivities The region ID of the region/regions with the least activities.
     */
    public Analysis(TreeMap<String, Long> activitiesTypes, TreeSet<String> trackedActivityIds,
                    RegionsActivitiesRecord regionsWithMostActivities,
                    RegionsActivitiesRecord regionsWithLeastActivities) {
        this.activitiesTypes = activitiesTypes;
        this.trackedActivityIds = trackedActivityIds;
        this.regionsWithMostActivities = regionsWithMostActivities;
        this.regionsWithLeastActivities = regionsWithLeastActivities;
    }

    /**
     * @return the activitiesTypes
     */
    public TreeMap<String, Long> getActivitiesTypes() {
        return activitiesTypes;
    }

    /**
     * @return the trackedActivityIds
     */
    public TreeSet<String> getTrackedActivityIds() {
        return trackedActivityIds;
    }
    
    /**
     * @return the regionsWithMostActivities
     */
    public RegionsActivitiesRecord getRegionsWithMostActivities() {
        return regionsWithMostActivities;
    }

    /**
     * @return the regionsWithLeastActivities
     */
    public RegionsActivitiesRecord getRegionsWithLeastActivities() {
        return regionsWithLeastActivities;
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
     * Returns the file name used for writing a serialized object.
     *
     * @return The file name used for writing a serialized object.
     */
    @Override
    public String getJsonFilename() {
        return jsonFilename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Analysis analysis = (Analysis) o;
        return activitiesTypes.equals(analysis.activitiesTypes) &&
                trackedActivityIds.equals(analysis.trackedActivityIds) &&
                regionsWithMostActivities.equals(analysis.regionsWithMostActivities) &&
                regionsWithLeastActivities.equals(analysis.regionsWithLeastActivities) &&
                jsonFilename.equals(analysis.jsonFilename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activitiesTypes, trackedActivityIds, regionsWithMostActivities,
                regionsWithLeastActivities, jsonFilename);
    }

}
