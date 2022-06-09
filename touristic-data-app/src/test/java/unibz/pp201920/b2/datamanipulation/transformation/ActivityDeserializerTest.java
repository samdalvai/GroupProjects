package unibz.pp201920.b2.datamanipulation.transformation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import unibz.pp201920.b2.constants.JsonNodeExample;
import unibz.pp201920.b2.exceptions.InvalidJsonException;

/**
 * Test class for {@link ActivityDeserializer}.
 *
 * @author Samuel Dalvai
 */
@DisplayName("Test class for ActivityDeserializer class")
class ActivityDeserializerTest {

    private static final Activity expected1 = new Activity("01CFEFF8DA586E548327E539276C42F3",
            "01 Cross Country Stadio Track Dobbiaco/Toblach",
            "Demanding round-trip tours starting at the cross-country stadium in Dobbiaco",
            new ArrayList<>(Arrays.asList("loipen", "klassisch und skating")),
            true,
            "Dolomites Region Three Peaks", "D2633A1FC24E11D18F1B006097B8970B");

    private static final Activity expected2 = new Activity("B66FA66DA650717E0964A4E30A716DAE",
            "02 Cross Country Stadio Dobbiaco/Toblach FISI - Stephanie",
            "Demanding round-trip tours starting at the cross-country stadium in Dobbiaco",
            new ArrayList<>(Arrays.asList("loipen", "klassisch und skating")),
            true,
            "Three Peaks Dolomites", "522822A751CA11D18F1400A02427D15E");

    /**
     * Test method for {@link ActivityDeserializer#deserialize()}.
     */
    @Test
    @DisplayName("Test if deserialization of multitple Activity.json objects result in the same Set of Activity " +
            "objects")
    void testDeserializeList() {
        ActivityDeserializer deserializer = new ActivityDeserializer(JsonNodeExample.ROOT.getNode());
        Set<Activity> actual = new HashSet<>();
        Set<Activity> expected = new HashSet<>();

        expected.add(expected1);
        expected.add(expected2);
        try {
            actual = deserializer.deserialize();
        } catch (InvalidJsonException e) {
            e.printStackTrace();
        }

        assertEquals(expected, actual, "The two Sets of Activity objects should be equal");
    }


    /**
     * Test method for {@link ActivityDeserializer#deserialize()}
     */
    @Test
    @DisplayName("Test if deserialization of an activity json object with no Gps tags"
            + " results in an Activity object with hasGPSTrack == false")
    void testhasGPSTrack() {
        ActivityDeserializer deserializer = new ActivityDeserializer(JsonNodeExample.ROOT_NO_GPS.getNode());
        ArrayList<Activity> actualList = new ArrayList<>();

        try {
            actualList = new ArrayList<>(deserializer.deserialize());
        } catch (InvalidJsonException e) {
            e.printStackTrace();
        }

        assertFalse(actualList.get(0).getHasGPSTrack(), "The Activity object should have hasGPSTrack equal " +
                "to false");
    }

    @Test
    @DisplayName("Test if deserialization throws InvalidJsonException if the parenthesis of the root json node are " +
            "malformed")
    void testMalformedRootParenthesis() {
        ActivityDeserializer deserializer = new ActivityDeserializer(
                JsonNodeExample.ROOT_MALFORMED_PARENTHESIS.getNode());

        assertThrows(InvalidJsonException.class, deserializer::deserialize,
                "Should throw a InvalidJsonException");
    }

}
