package unibz.pp201920.b2.datamanipulation.analysis;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import unibz.pp201920.b2.datamanipulation.transformation.Activity;

/**
 * Class that performs analysis of data on a {@link Set} of {@link Activity} objects.
 *
 * Extracts the data required from the {@link Set} of {@link Activity} objects and
 * does some basic calculations in order to obtain the required {@link Analysis}
 * object.
 *
 * @author Gioele De Vitti, Filippo Cenacchi, Samuel Dalvai
 */
public class ActivitiesAnalyzer {

    private static final Logger logger = LogManager.getLogger();

    private final Set<Activity> activities;

    /**
     * Creates a new instance of {@link ActivitiesAnalyzer}.
     *
     * @param activities The activities to be analyzed.
     */
    public ActivitiesAnalyzer(Set<Activity> activities) {
        this.activities = activities;
    }

    /**
     * Constructs an {@link Analysis} object by passing his parameters based on
     * the values extracted and analyzed from the {@link Set} of {@link Activity} objects.
     *
     * @return New instance of {@link Analysis} containing the results.
     */
    public Analysis analyze() {
        TreeMap<String, Long> regionsActivities = countRegionsActivities();

        return new Analysis(
                countActivitiesTypes(),
                searchTrackedActivities(),
                getRegionActivitiesMax(regionsActivities),
                getRegionActivitiesMin(regionsActivities));
    }

    /**
     * Counts how often a certain activity type occurs, in whatever region.
     *
     * @return A {@link TreeMap} mapping activity types to their number of occurrence.
     */
    private TreeMap<String, Long> countActivitiesTypes() {
        logger.debug("Counting activitiy types");
        return activities.stream()
                /*
                 Because each activity is of various types, we are dealing with a list of activities and each
                 activity has a own list of types. Flatmap transforms this to a single list containing all types of
                 all activities.
                 */
                .flatMap(a -> a.getTypes().stream())
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(activityType -> activityType, TreeMap::new, Collectors.counting()));
    }

    /**
     * Extracts the ID of each activity that has GPS tracking available.
     *
     * @return A {@link TreeSet} containing IDs of GPS tracked activities.
     */
    private TreeSet<String> searchTrackedActivities() {
        logger.debug("Searching for GPS tracked activities");
        return activities.stream()
                .filter(Activity::getHasGPSTrack)
                .map(Activity::getId)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Extracts the region ID which has the highest number of activities,
     * there might be more than one region ID which can correspond to
     * this value.
     *
     * @return The regions with the most number of activities as {@link RegionsActivitiesRecord}.
     */
    private RegionsActivitiesRecord getRegionActivitiesMax(TreeMap<String, Long> regionsActivities) {
        logger.debug("Computing the highest number of activities");
        long maxValue = regionsActivities.values().stream()
                .max(Long::compareTo)
                .orElse((long) -1);

        logger.debug("Searching regions with the highest number of activities");
        TreeSet<String> maxRegions = regionsActivities.entrySet().stream()
                .filter(entry -> entry.getValue() == maxValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(TreeSet::new));

        return new RegionsActivitiesRecord(maxValue, maxRegions);
    }

    /**
     * Extracts the region ID which has the lowest number of activities,
     * there might be more than one region ID which can correspond to
     * this value.
     *
     * @return The regions with the least number of activities as {@link RegionsActivitiesRecord}.
     */
    private RegionsActivitiesRecord getRegionActivitiesMin(TreeMap<String, Long> regionsActivities) {
        logger.debug("Computing the lowest number of activities");
        long minValue = regionsActivities.values().stream()
                .min(Long::compareTo)
                .orElse((long) -1);

        logger.debug("Searching regions with the lowest number of activities");
        TreeSet<String> minRegions = regionsActivities.entrySet().stream()
                .filter(entry -> entry.getValue() == minValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(TreeSet::new));

        return new RegionsActivitiesRecord(minValue, minRegions);
    }

    /**
     * Counts how many activities each region has.
     *
     * @return A {@link TreeMap} mapping region IDs to the number of activities the region has.
     */
    private TreeMap<String, Long> countRegionsActivities() {
        logger.debug("Counting the number of activities of each region");
        return activities.stream()
                .map(Activity::getRegionId)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(id -> id, TreeMap::new, Collectors.counting()));
    }

}