package unibz.pp201920.b2.datamanipulation.transformation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Activity}.
 *
 * @author Samuel Dalvai
 */
@DisplayName("Test class for Activity objects")
class ActivityTest {

	private static final Activity expected = new Activity("01CFEFF8DA586E548327E539276C42F3",
			"01 Cross Country Stadio Track Dobbiaco/Toblach",
			"Demanding round-trip tours starting at the cross-country stadium in Dobbiaco",
			Arrays.asList("loipen", "klassisch und skating"), true,
			"Dolomites Region Three Peaks", "D2633A1FC24E11D18F1B006097B8970B");

	/**
	 * Test method for
	 * {@link unibz.pp201920.b2.datamanipulation.transformation.Activity#equals(java.lang.Object)}.
	 */
	@Test
	@DisplayName("Test that two Activity objects are equal")
	void testEqualsObject() {
		Activity actual = new Activity("01CFEFF8DA586E548327E539276C42F3",
				"01 Cross Country Stadio Track Dobbiaco/Toblach",
				"Demanding round-trip tours starting at the cross-country stadium in Dobbiaco",
				Arrays.asList("loipen", "klassisch und skating"), true,
				"Dolomites Region Three Peaks", "D2633A1FC24E11D18F1B006097B8970B");

		assertEquals(expected, actual, " the two Activity Objects should be equal");
	}

	/**
	 * Test method for
	 * {@link unibz.pp201920.b2.datamanipulation.transformation.Activity#equals(java.lang.Object)}.
	 */
	@Test
	@DisplayName("Test that two Activity objects are not equal just because they have a different ID")
	void testNotEqualsObject() {
		Activity actual = new Activity("B66FEFF8DA586E548327E539276C4A78",
				"01 Cross Country Stadio Track Dobbiaco/Toblach",
				"Demanding round-trip tours starting at the cross-country stadium in Dobbiaco",
				Arrays.asList("loipen", "klassisch und skating"), true,
				"Dolomites Region Three Peaks", "D2633A1FC24E11D18F1B006097B8970B");

		assertNotEquals(expected, actual, " the two Activity Objects should not be equal");
	}

}
