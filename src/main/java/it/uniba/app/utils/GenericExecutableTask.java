package it.uniba.app.utils;

/**
 * {@literal <<noECB>>}
 * A class that implements this interface can
 * execute a task that returns something you choose.
 * It indicates that there's only one task and
 * nothing else.
 *
 * @param <T> the type you want to return
 *            from the task.
 */

public interface GenericExecutableTask<T> {
    /**
     * Executes a command on the object.
     *
     * @return something as result.
     */
    T execute();
}
