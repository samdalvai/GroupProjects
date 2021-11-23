package unibz.pp201920.b2.datamanipulation.analysis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Analysis}.
 * It tests the contruction of new instances and the comparison of equal and not equal instances.
 *
 * @author Gioele De Vitti
 */
class AnalysisTest {

    TreeSet<String> regions = new TreeSet<>(Arrays.asList("D2633A1FC24E11D18F1B006097B8970B",
            "A2633A1FC24E11D18F1B006097B89702",
            "C4633A1FC24E11D18F1B006097B89701",
            "E5633A1FC24E11D18F1B006097B8970C",
            "HH633A1FC24E11D18F1B006097B8970G"));
    TreeMap<String, Long> activitiesTypes = new TreeMap<>();
    TreeSet<String> trackedActivityIds = new TreeSet<>();
    RegionsActivitiesRecord regionsWithMostActivities = new RegionsActivitiesRecord(8, regions);
    RegionsActivitiesRecord regionsWithLeastActivities = new RegionsActivitiesRecord(9, regions);

    Analysis analysis1 = new Analysis(activitiesTypes, trackedActivityIds, regionsWithMostActivities,
            regionsWithLeastActivities);

    @Test
    @DisplayName("Json file name is not null.")
    void testFilename() {
        assertNotNull(analysis1.getJsonFilename());
    }

    @Test
    void testEqualsTrue() {
        Analysis analysis2 = new Analysis(activitiesTypes, trackedActivityIds, regionsWithMostActivities,
                regionsWithLeastActivities);

        assertEquals(analysis1, analysis2);
    }

    @Test
    void testNotNull() {
        assertNotNull(analysis1);
    }

    @Test
    @DisplayName("Comparing two instances with different activity types.")
    void testEqualsFalse() {
        TreeMap<String, Long> activitiesTypes2 = new TreeMap<>();
        activitiesTypes2.put("SomeKey", (long) 20);

        Analysis analysis2 = new Analysis(activitiesTypes2, trackedActivityIds, regionsWithMostActivities,
                regionsWithLeastActivities);

        assertNotEquals(analysis1, analysis2);
    }

}