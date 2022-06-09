package unibz.pp201920.b2.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Set;

/**
 * Non-instantiable class containing parsing utilities.
 *
 * @author Samuel Dalvai, Gioele De Vitti
 */
public final class ParsingUtils {

    private ParsingUtils() {
    }

    /**
     * Cleans a string from its html elements and returns its title and its body, separated by a space character.
     * <p>{@code title + " " + body}
     *
     * @param html A String (can contain html elements or not).
     * @return The formatted String with no html elements, null if the input is null.
     */
    public static String htmlToReadable(String html) {
        if (html == null)
            return null;

        StringBuilder readable = new StringBuilder();
        /*
         * Strip string from element "&nbsp" (Non Breakable Space),
         * otherwise the parse method will print white spaces
         * for every element found
         */
        String cleanHtml = html.replace("&nbsp;", "");

        // Strip html elements from htmlString using the Jsoup API
        Document parsedString = Jsoup.parse(cleanHtml);
        String title = parsedString.title();
        String body = parsedString.body().text();
        readable.append(title);
        if(!title.isEmpty() && !body.isEmpty())
            readable.append(" ");
        readable.append(body);

        return readable.toString();
    }

    /**
     * Transforms the given set to a human-readable string.
     * Each element is separated with a new line.
     *
     * @param set The {@code Set} to be transformed.
     * @param <T> The {@code Set}'s element type
     * @return The transformed {@code String}.
     */
    public static <T> String setToReadable(Set<T> set) {
        StringBuilder readable = new StringBuilder();
        int i = 0;

        for (T element : set) {
            readable.append(element.toString());
            // Do not append a new line to the last message
            if (i < set.size() - 1)
                readable.append(System.lineSeparator());
            i++;
        }

        return readable.toString();
    }

}
