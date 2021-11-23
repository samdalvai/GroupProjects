package unibz.pp201920.b2.datamanipulation.analysis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unibz.pp201920.b2.datamanipulation.transformation.Activity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link ActivitiesAnalyzer}.
 * Creates some example activities for the analyzer and tests then the resulting analysis.
 * Each test verifies a different property of the Analysis.
 *
 * @author Gioele De Vitti
 */
class ActivitiesAnalyzerTest {

    Set<Activity> activities = new HashSet<>();

    @BeforeEach
    void setUp() {
        activities.add(new Activity("01CFEFF8DA586E548327E539276C42F1", "some name","some description",
                Arrays.asList("type1", "type2"), true, "Dolomites Region Three Peaks",
                "22CFEFF8DA586E5483273539276C4274"));
        activities.add(new Activity("GG0FEFF8DA586E548327E539276C4233", "some name","some description",
                Arrays.asList("type1", "type3"), false, "Cool Region",
                "190FEFF8DA586E5483273539276C4772"));
        activities.add(new Activity("FJ0FEFF8DA586E548327E539276C4200", "some name","some description",
                Arrays.asList("type1", "type3"), false, "Cool Region",
                "190FEFF8DA586E5483273539276C4772"));
    }

    @Test
    void testAnalyzeEmpty() {
        ActivitiesAnalyzer analyzer = new ActivitiesAnalyzer(new HashSet<>());
        Analysis analysis = analyzer.analyze();

        assertNotNull(analysis);
    }

    @Test
    void testAnalyzeActivityTypes() {
        ActivitiesAnalyzer analyzer = new ActivitiesAnalyzer(activities);
        Analysis analysis = analyzer.analyze();

        TreeMap<String, Long> activitiesTypes = new TreeMap<>();
        activitiesTypes.put("type1", (long) 3);
        activitiesTypes.put("type2", (long) 1);
        activitiesTypes.put("type3", (long) 2);

        assertEquals(activitiesTypes, analysis.getActivitiesTypes());
    }

    @Test
    void testAnalyzeTrackedActivityIds() {
        ActivitiesAnalyzer analyzer = new ActivitiesAnalyzer(activities);
        Analysis analysis = analyzer.analyze();

        TreeSet<String> trackedActivityIds = new TreeSet<>();
        trackedActivityIds.add("01CFEFF8DA586E548327E539276C42F1");

        assertEquals(trackedActivityIds, analysis.getTrackedActivityIds());
    }

    @Test
    void testAnalyzeRegionsWithLeastActivities() {
        ActivitiesAnalyzer analyzer = new ActivitiesAnalyzer(activities);
        Analysis analysis = analyzer.analyze();

        TreeSet<String> regions = new TreeSet<>();
        regions.add("22CFEFF8DA586E5483273539276C4274");
        RegionsActivitiesRecord regionsWithLeastActivities = new RegionsActivitiesRecord(1, regions);

        assertEquals(regionsWithLeastActivities, analysis.getRegionsWithLeastActivities());
    }

    @Test
    void testAnalyzeRegionsWithMostActivities() {
        ActivitiesAnalyzer analyzer = new ActivitiesAnalyzer(activities);
        Analysis analysis = analyzer.analyze();

        TreeSet<String> regions = new TreeSet<>();
        regions.add("190FEFF8DA586E5483273539276C4772");
        RegionsActivitiesRecord regionsWithMostActivities = new RegionsActivitiesRecord(2, regions);

        assertEquals(regionsWithMostActivities, analysis.getRegionsWithMostActivities());
    }

}