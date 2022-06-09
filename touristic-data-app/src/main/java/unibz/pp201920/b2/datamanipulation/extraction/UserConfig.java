package unibz.pp201920.b2.datamanipulation.extraction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import unibz.pp201920.b2.constants.ResourceFile;
import unibz.pp201920.b2.exceptions.InvalidLineException;
import unibz.pp201920.b2.exceptions.InvalidPathException;

import java.io.*;
import java.util.Objects;

/**
 * Class {@link UserConfig} opens a config file and reads its values, which are separated by new line separators.
 * {@link LineNumberReader} is used to read the file, because it is very convenient
 * to know the line number when working with user files. If the file does not match the expected format, the line
 * number is included in the error message.
 *
 * @author Gioele De Vitti
 */
public final class UserConfig {

    private static final Logger logger = LogManager.getLogger();

    /**
     * Number of objects to retrieve from the web API.
     */
    private int numberObjects = 0;

    /**
     * Loads the config file from the given file.
     *
     * @param filepath The path to the config file.
     * @throws InvalidLineException If a specific line of the config file could not be interpreted correctly.
     * @throws InvalidPathException If the config file could not be found.
     */
    public void loadFrom(String filepath) throws InvalidLineException, InvalidPathException {
        try {
            Reader reader = new FileReader(new File(filepath));
            loadConfig(reader);
        } catch (FileNotFoundException e) {
            throw new InvalidPathException("Config file not found", e);
        }
    }

    /**
     * Loads the config file from the given resource file.
     *
     * @param resourceFile The {@code ResourceFile} representing the config file.
     * @throws InvalidLineException If a specific line of the config file could not be interpreted correctly.
     */
    public void loadFrom(ResourceFile resourceFile) throws InvalidLineException {
        Reader reader = resourceFile.open();
        loadConfig(reader);
    }

    /**
     * Reads the config file using the given reader and closes the reader once done.
     *
     * @param reader The {@code Reader} used to read the config file, which is closed after completion.
     * @throws InvalidLineException If a specific line could not be interpreted correctly.
     */
    private void loadConfig(Reader reader) throws InvalidLineException {
        int lineNumber = -1;

        logger.trace("Opening user config");
        try (LineNumberReader lineReader = new LineNumberReader(reader)) {
            logger.trace("Reading user config");
            lineNumber = lineReader.getLineNumber();
            numberObjects = Integer.parseInt(lineReader.readLine());
        } catch (NumberFormatException | IOException e) {
            throw new InvalidLineException(lineNumber, e);
        }
        logger.trace("User config closed");
    }

    /**
     * Get the number of objects to retrieve from the web API.
     * 
     * @return the number of objects to retrieve
     */
    public int getNumberObjects() {
        return numberObjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserConfig that = (UserConfig) o;
        return numberObjects == that.numberObjects;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberObjects);
    }

}
