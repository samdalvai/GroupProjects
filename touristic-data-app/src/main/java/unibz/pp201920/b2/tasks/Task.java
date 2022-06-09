package unibz.pp201920.b2.tasks;

/**
 * Interface that can be implemented by objects that act as facade classes.
 *
 * Implementations should not provide additional methods, except getters to retrieve execution results.
 *
 * @author Gioele De Vitti
 */
public interface Task {

    /**
     * Gives control to this facade class.
     */
    void execute();

}
