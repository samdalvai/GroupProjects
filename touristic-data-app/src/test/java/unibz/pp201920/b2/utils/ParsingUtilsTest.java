package unibz.pp201920.b2.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link ParsingUtils}.
 * Tests the function of its various utility methods.
 *
 * @author Gioele De Vitti
 */
public class ParsingUtilsTest {

    @Test
    void testNoElements() {
        String input = "This should not be changed";
        String output = ParsingUtils.htmlToReadable(input);

        assertEquals(input, output, "String with no html elements should not be changed.");
    }

    @Test
    void testMalformedHtml() {
        String input = "<html itemscope=\"\" itemtype=\"http://schema.org/QAPage\" " +
                "class=\"html__responsive\"><head>\n\n" +
                "        <title>Nice text with strange ' symbols ;.%$~` because that's nice</title>\n" +
                "        <link rel=\"shortcut icon\" href=\"https://cdn.sstatic.net/Sites/stackoverflow/Img/favicon" +
                ".ico?v=ec617d715196\">\n" +
                "        <link rel=\"apple-touch-icon\" href=\"https://cdn.sstatic" +
                ".net/Sites/stackoverflow/Img/apple-touch-icon.png?v=c78bd457575a\">\n" +
                "        <link rel=\"image_src\" href=\"https://cdn.sstatic" +
                ".net/Sites/stackoverflow/Img/apple-touch-icon.png?v=c78bd457575a\"> \n";
        String output = ParsingUtils.htmlToReadable(input);

        assertEquals("Nice text with strange ' symbols ;.%$~` because that's nice", output,
                "Malformed html elements should be removed.");
    }

    @Test
    void testPerfectHtml() {
        String input = "<html><title>Nice text with strange ' symbols ;.%$~` because that's nice</title>" +
                "<p>The WARN sign means <strong>something bad happ' happen " +
                "next</strong> today.</p></html>";
        String output = ParsingUtils.htmlToReadable(input);

        assertEquals("Nice text with strange ' symbols ;.%$~` because that's nice " +
                        "The WARN sign means something bad happ' happen next today.", output,
                "Html elements should be removed.");
    }

}
