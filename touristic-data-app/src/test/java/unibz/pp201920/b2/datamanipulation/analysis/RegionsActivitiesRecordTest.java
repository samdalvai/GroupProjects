package unibz.pp201920.b2.datamanipulation.analysis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link RegionsActivitiesRecord}.
 * It tests the contruction of new instances and the comparison of equal and not equal instances.
 *
 * @author Gioele De Vitti
 */
class RegionsActivitiesRecordTest {

    TreeSet<String> regions = new TreeSet<>(Arrays.asList("D2633A1FC24E11D18F1B006097B8970B",
            "A2633A1FC24E11D18F1B006097B89702",
            "C4633A1FC24E11D18F1B006097B89701",
            "E5633A1FC24E11D18F1B006097B8970C",
            "HH633A1FC24E11D18F1B006097B8970G"));

    @Test
    @DisplayName("New instance with valid parameters.")
    void create() {
        assertDoesNotThrow(() -> new RegionsActivitiesRecord(10, regions));
    }

    @Test
    @DisplayName("New instance with negative number of activities.")
    void createNegative() {
        assertDoesNotThrow(() -> new RegionsActivitiesRecord(-3, regions));
    }

    @Test
    void testEqualsTrue() {
        RegionsActivitiesRecord record1 = new RegionsActivitiesRecord(7, regions);
        RegionsActivitiesRecord record2 = new RegionsActivitiesRecord(7, regions);

        assertEquals(record1, record2);
    }

    @Test
    void testEqualsDiffRegions() {
        RegionsActivitiesRecord record1 = new RegionsActivitiesRecord(7, regions);
        RegionsActivitiesRecord record2 = new RegionsActivitiesRecord(7, null);

        assertNotEquals(record1, record2);
    }

    @Test
    void testEqualsDiffNumberOfActivities() {
        RegionsActivitiesRecord record1 = new RegionsActivitiesRecord(7, regions);
        RegionsActivitiesRecord record2 = new RegionsActivitiesRecord(3, regions);

        assertNotEquals(record1, record2);
    }

}