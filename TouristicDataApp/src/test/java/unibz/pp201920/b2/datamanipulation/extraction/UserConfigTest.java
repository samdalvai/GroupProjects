package unibz.pp201920.b2.datamanipulation.extraction;

import org.junit.jupiter.api.Test;
import unibz.pp201920.b2.exceptions.InvalidPathException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link UserConfig}.
 * It tests basic functions without performing any IO operations.
 *
 * @author Gioele De Vitti
 */
public class UserConfigTest {

    @Test
    void testEquality() {
        UserConfig config1 = new UserConfig();
        UserConfig config2 = new UserConfig();

        assertEquals(config1, config2, "Two new UserConfig instances should be equal.");
    }

    @Test
    void testInvalidPath() {
        UserConfig config = new UserConfig();

        assertThrows(InvalidPathException.class, () -> config.loadFrom("invalid/filepath"));
    }

}
