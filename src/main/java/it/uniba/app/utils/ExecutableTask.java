package it.uniba.app.utils;
/**
 * {@literal <<noECB>>}
 * A class that implements this interface can
 * execute a task without returning and passing anything.
 * It indicates that there's only one task and
 * nothing else.
 */
public interface ExecutableTask {

    /** Executes a command on the object. */
    void execute();
}
