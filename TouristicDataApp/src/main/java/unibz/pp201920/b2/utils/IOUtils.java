package unibz.pp201920.b2.utils;

import unibz.pp201920.b2.exceptions.InvalidPathException;

import java.io.File;

/**
 * Non-instantiable class containing IO utilities.
 *
 * @author Gioele De Vitti
 */
public final class IOUtils {

    private IOUtils() {
    }

    /**
     * Creates the given directory and all its parent directories.
     *
     * @param dirPath The path of the directory.
     * @throws InvalidPathException If the directory could not be created because of missing permissions.
     */
    public static void createDirectory(File dirPath) throws InvalidPathException {
        boolean success;

        if (dirPath.exists())
            return;

        try {
            success = dirPath.mkdirs();
        } catch (SecurityException e) {
            throw new InvalidPathException(dirPath.getPath(), e);
        }

        if (!success)
            throw new InvalidPathException(dirPath.getPath());
    }

}
