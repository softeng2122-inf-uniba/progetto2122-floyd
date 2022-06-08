package it.uniba.app.utils;

public interface GenericExecutableTask<T> {
    /**
     * Executes a command on the object.
     *
     * @return something as result.
     */
    T execute();
}
